package com.iot.pcf;

import com.iot.pcf.model.Device;
import com.iot.pcf.model.DeviceActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DeviceInterface deviceRepository;

    @Autowired
    DeviceActivityInterface deviceActivityRepository;

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
        return deviceActivity;
    }
}
