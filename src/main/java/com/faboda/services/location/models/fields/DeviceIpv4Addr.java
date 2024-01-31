package com.faboda.services.location.models.fields;

import lombok.Data;

@Data
public class DeviceIpv4Addr {
    private SingleIpv4Addr publicAddress;
    private SingleIpv4Addr privateAddress;
    private int publicPort;
}
