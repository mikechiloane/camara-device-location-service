package com.faboda.services.location.dto;


import com.faboda.services.location.models.Device;
import com.faboda.services.location.models.fields.DeviceIpv4Addr;
import com.faboda.services.location.models.fields.Ipv6Address;
import com.faboda.services.location.models.fields.Location;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DeviceDto {

    private String id;
    private String userId;
    private String phoneNumber;
    private String networkAccessIdentifier;
    private DeviceIpv4Addr ipv4Address;
    private Ipv6Address ipv6Address;
    private Location location;

    public Device toDevice() {

        return Device.builder()
                .phoneNumber(this.phoneNumber)
                .lastLocationTime(location.getLastLocationTime())
                .ipv4Address(this.ipv4Address)
                .ipv6Address(this.ipv6Address)
                .location(this.location)
                .networkAccessIdentifier(this.networkAccessIdentifier)
                .build();
    }
}
