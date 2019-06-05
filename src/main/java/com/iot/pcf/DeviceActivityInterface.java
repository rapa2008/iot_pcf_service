package com.iot.pcf;

import com.iot.pcf.model.DeviceActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceActivityInterface extends JpaRepository<DeviceActivity, Integer> {
    //@Query("SELECT * FROM DEVICEACTIVITY WHERE DEVICEID=(:deviceId)")
    //DeviceActivity findByDeviceId(@Param("deviceId") String deviceId);
    List<DeviceActivity> findBydeviceID(String id);
}
