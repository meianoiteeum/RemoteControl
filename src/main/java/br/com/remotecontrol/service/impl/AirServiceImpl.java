package br.com.remotecontrol.service.impl;

import br.com.remotecontrol.exceptions.DeviceOperationException;
import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.model.Mode;
import br.com.remotecontrol.service.AirService;
import br.com.remotecontrol.util.Util;

import java.util.Objects;

public class AirServiceImpl implements AirService {
    @Override
    public void turnOn(Air air) {
        validateDevide(air);
        Util.validateTurnOn(air.isPowered());
        air.setPowered(turnOnOff(air.isPowered()));
    }

    @Override
    public void turnOff(Air air) {
        validateDevide(air);
        Util.validateTurnOff(air.isPowered());
        air.setPowered(turnOnOff(air.isPowered()));
    }

    @Override
    public Air renameBrand(Air air, String brand) {
        validateDevide(air);
        air.renameBrand(brand);
        return air;
    }

    @Override
    public Air renameModel(Air air, String model) {
        validateDevide(air);
        air.renameBrand(model);
        return air;
    }

    @Override
    public void adjustTemperature(Air air, int temperature) {
        validateDevide(air);
        try{
            if(!Util.validNumber(17,30, temperature))
                throw new IllegalArgumentException("Please, enter a number between 17 and 30");

            air.setTemperature(temperature);
        }catch (IllegalStateException ise){
            throw new DeviceOperationException("Failed to adjust temperature", ise);
        }
    }

    @Override
    public void changeMode(Air air, Mode mode) {
        validateDevide(air);
        if(Objects.isNull(mode))
            throw new IllegalArgumentException("Mode cannot be null");

        try{
            air.setMode(mode);
        }catch (IllegalStateException ise){
            throw new DeviceOperationException("Failed to change mode", ise);
        }
    }

    private void validateDevide(Air air) {
        if(Objects.isNull(air)){
            throw new IllegalArgumentException("TV cannot be null");
        }
    }
}
