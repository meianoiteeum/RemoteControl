package br.com.remotecontrol.command.impl.home;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.service.HomeService;
import br.com.remotecontrol.view.ConsoleView;

public class ListAllDeviceCommand implements MenuCommand {
    private final HomeService service;
    private final ConsoleView view;

    public ListAllDeviceCommand(HomeService service, ConsoleView view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void execute() {
        service.getAllDevices().forEach(view::displayDeviceStatus);
    }

    @Override
    public String getDescription() {
        return "List of All Devices";
    }
}
