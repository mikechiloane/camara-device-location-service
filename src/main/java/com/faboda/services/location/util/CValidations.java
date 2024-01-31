package com.faboda.services.location.util;

import com.faboda.services.location.exceptions.InvalidArgumentException;
import com.faboda.services.location.models.Device;

import java.util.Objects;

public class CValidations {

    public static void validateDevice(Device device1, Device device2) {
        if (device1 == null || device2 == null) {
           throw new InvalidArgumentException();
        }

        if (!Objects.equals(device1.getPhoneNumber(), device2.getPhoneNumber())) {
            throw new InvalidArgumentException();
        }

        if (!Objects.equals(device1.getNetworkAccessIdentifier(), device2.getNetworkAccessIdentifier())) {
            throw new InvalidArgumentException();
        }

    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InvalidArgumentException();
        }
    }

    public static void phoneNumbersAreEqual(String phoneNumber1, String phoneNumber2) {
        if (!Objects.equals(phoneNumber1, phoneNumber2)) {
            throw new InvalidArgumentException();
        }
    }

}



