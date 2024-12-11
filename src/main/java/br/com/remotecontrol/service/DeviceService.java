package br.com.remotecontrol.service;

public interface DeviceService<T> {

    default boolean turnOnOff(boolean online){
        return !online;
    }

    void turnOn(T t);
    void turnOff(T t);

    T renameBrand(T t,String brand);
    T renameModel(T t,String model);
}
