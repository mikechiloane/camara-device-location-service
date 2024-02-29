package com.faboda.services.location.aspects;

import com.faboda.services.location.dto.CDeviceLocationRequest;
import com.faboda.services.location.dto.CDeviceLocationVerificationRequest;
import com.faboda.services.location.exceptions.InvalidArgumentException;

import com.faboda.services.location.util.CValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LocationServiceAspects {


    @Pointcut("execution(* com.faboda.services.location.service.LocationServiceImpl.retrieveDeviceLocation(..)) " +
            "&& args(cDeviceLocationRequest)")
    public void retrieveDeviceLocationPointcut(CDeviceLocationRequest cDeviceLocationRequest) {
    }

    @Pointcut("execution(* com.faboda.services.location.service.LocationServiceImpl.verifyDeviceLocation(..)) " +
            "&& args(cDeviceLocationVerificationRequest)")
    public void verifyDeviceLocationPointcut(CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest) {
    }

    @Before("retrieveDeviceLocationPointcut(cDeviceLocationRequest)")
    public void beforeRetrieveDeviceLocation(JoinPoint joinPoint, CDeviceLocationRequest cDeviceLocationRequest) {
        if (cDeviceLocationRequest.getUserEmail() == null || cDeviceLocationRequest.getUserEmail().isEmpty())
            throw new InvalidArgumentException();
        if (cDeviceLocationRequest.getMaxAge() < 0 || cDeviceLocationRequest.getMaxAge() == 0)
            throw new InvalidArgumentException();

        CValidations.validatePhoneNumber(cDeviceLocationRequest.getDevice().getPhoneNumber());

        log.info("Before retrieveDeviceLocation method");
    }

    @Before(value = "verifyDeviceLocationPointcut(cDeviceLocationVerificationRequest)", argNames = "joinPoint,cDeviceLocationVerificationRequest")
    public void beforeVerifyDeviceLocation(JoinPoint joinPoint, CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest) {
        if (cDeviceLocationVerificationRequest.getUserEmail() == null || cDeviceLocationVerificationRequest.getUserEmail().isEmpty())
            throw new InvalidArgumentException();

        if (cDeviceLocationVerificationRequest.getMaxAge() < 60  || cDeviceLocationVerificationRequest.getMaxAge() >120 )
            throw new InvalidArgumentException("Max age should be between 60 and 120");

        if (cDeviceLocationVerificationRequest.getArea() == null)
            throw new InvalidArgumentException();

        if (cDeviceLocationVerificationRequest.getArea().getCenter() == null)
            throw new InvalidArgumentException();



        CValidations.validatePhoneNumber(cDeviceLocationVerificationRequest.getDevice().getPhoneNumber());

        log.info("Before verifyDeviceLocation method");
    }
}
