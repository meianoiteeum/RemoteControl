package br.com.remotecontrol.command.impl.home;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.service.DeviceService;

public class TurnOnOffCommand<T extends Device> implements MenuCommand {

    private final T device;
    private final DeviceService<T> service;

    public TurnOnOffCommand(T device, DeviceService<T> service) {
        this.device = device;
        this.service = service;
    }

    @Override
    public void execute() {
        if(!device.isPowered())
            service.turnOn(device);
        else
            service.turnOff(device);
    }

    @Override
    public String getDescription() {
        return "TV Power";
    }
}
