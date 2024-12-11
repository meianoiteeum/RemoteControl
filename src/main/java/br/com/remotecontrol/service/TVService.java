package br.com.remotecontrol.service;

import br.com.remotecontrol.model.TV;

public interface TVService extends DeviceService<TV>{
    void changeChannel(TV tv, int channel);
    void adjustVolume(TV tv, int volume);
}
