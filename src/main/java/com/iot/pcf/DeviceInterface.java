package com.iot.pcf;

import com.iot.pcf.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInterface extends JpaRepository<Device, String> {
    //@Query(value = "SELECT * FROM DEVICE WHERE ID=(:id)")
    //Device findByDeviceId(@Param("id") String id);
    Device findByIdContaining(String id);
}
