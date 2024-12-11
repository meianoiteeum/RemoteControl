package br.com.remotecontrol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Home(List<Device> devices) {
    public Home{
        devices = new ArrayList<>(devices);
    }

    public Home(){
        this(new ArrayList<>());
    }

    public List<Device> getDevices(){return Collections.unmodifiableList(devices);}
}
