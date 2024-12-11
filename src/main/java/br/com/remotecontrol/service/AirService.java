package br.com.remotecontrol.service;

import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.model.Mode;

public interface AirService extends DeviceService<Air>{
    void adjustTemperature(Air air, int temperature);
    void changeMode(Air air, Mode mode);
}
