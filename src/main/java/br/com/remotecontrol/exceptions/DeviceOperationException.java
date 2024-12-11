package br.com.remotecontrol.exceptions;

public class DeviceOperationException extends RuntimeException{
    public DeviceOperationException(String message, Throwable cause){
        super(message, cause);
    }
}
