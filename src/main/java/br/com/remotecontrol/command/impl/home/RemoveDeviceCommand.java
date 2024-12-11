package br.com.remotecontrol.command.impl.home;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.service.HomeService;

public class RemoveDeviceCommand implements MenuCommand {
    private final HomeService service;
    private final Device device;

    public RemoveDeviceCommand(HomeService service, Device device) {
        this.service = service;
        this.device = device;
    }

    @Override
    public void execute() {
        service.removeDevice(device);
    }

    @Override
    public String getDescription() {
        return "Remove Device";
    }
}
