package com.faboda.services.location.models;

import com.faboda.services.location.models.fields.DeviceIpv4Addr;
import com.faboda.services.location.models.fields.Ipv6Address;
import com.faboda.services.location.models.fields.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Device {
    private String phoneNumber;
    private String networkAccessIdentifier;
    private String lastLocationTime;
    private DeviceIpv4Addr ipv4Address;
    private Ipv6Address ipv6Address;
    private Location location;
}
