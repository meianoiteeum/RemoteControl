package br.com.remotecontrol.exceptions;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException(String message){
        super(message);
    }
}
