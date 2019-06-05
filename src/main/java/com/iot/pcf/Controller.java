package com.iot.pcf;

import com.iot.pcf.model.Device;
import com.iot.pcf.model.DeviceActivity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
public class Controller {

    @Autowired
    private DeviceInterface deviceRepository;

    @Autowired
    DeviceActivityInterface deviceActivityRepository;

    @Value("${sleep_timer}")
    Integer sleepTimer;

    @GetMapping("/device")
    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    @PutMapping("/device")
    public Device addDevice(@RequestBody Device device){
        System.out.println("device " + device.toString());
        Date date = new Date();
        device.setCreatedDate(date);
        deviceRepository.save(device);
        return device;
    }

    @GetMapping("/device/activity")
    public List<DeviceActivity> getAllDeviceActivity(){
        return deviceActivityRepository.findAll();
    }

    @PutMapping("/device/activity")
    public DeviceActivity addDeviceActivity(@RequestBody DeviceActivity deviceActivity){
        Date date = new Date();
        deviceActivity.setCreatedDate(date);
        deviceActivityRepository.save(deviceActivity);
        try {
            sleep(sleepTimer);
        } catch (InterruptedException e){
            System.out.println("Sleep Interrupted: " + e.getMessage());
        }
        executeAction(deviceActivity);
        return deviceActivity;
    }

    public void executeAction(DeviceActivity deviceActivity){
        Device device = deviceRepository.findByIdContaining(deviceActivity.getDeviceID());

        String AUTH_KEY_FCM = "AAAAETxFTYY:APA91bFNyfWmfaOF3XxIq1xeQ1I6QhsZGPUM0aDneWHMaXdpPcVd7_mUxlkagqy00zJo9MC2fMHy8jgTJRkm0QNUkTljZbGjU_B8Q9OZK_ZPmnvk9IPNfsFuDxgpI4N9AlIhJt31Zop6";
        String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
        URL url = null;

        String result = "";
        try{
            url = new URL(API_URL_FCM);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception: " + e.getMessage());
        }

        HttpURLConnection conn = null;
        try{
            conn =   (HttpURLConnection) url.openConnection();
        } catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
        }

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        try{
            conn.setRequestMethod("POST");
        } catch (ProtocolException e){
            System.out.printf("Protocol Exception: " + e.getMessage());
        }

        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("to", device.getToken().trim());
        JSONObject info = new JSONObject();
        info.put("title", "CPU Overload"); // Notification title
        info.put("body", "Dear User, CPU utilization of your device exceeded 50%"); // Notification
        // body
        json.put("notification", info);
        try {
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            result = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            result = "FAILURE";
        }
        System.out.println("GCM Notification is sent successfully");

    }
}
