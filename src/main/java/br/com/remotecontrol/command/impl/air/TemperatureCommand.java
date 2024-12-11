package br.com.remotecontrol.command.impl.air;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.service.AirService;
import br.com.remotecontrol.view.ConsoleView;

public class TemperatureCommand implements MenuCommand {
    private final Air air;
    private final AirService service;
    private final ConsoleView view;

    public TemperatureCommand(Air air, AirService service, ConsoleView view) {
        this.view = view;
        this.service = service;
        this.air = air;
    }

    @Override
    public void execute() {
        int temp = view.readIntInput(17,30, "Enter temperature (17-30)");
        service.adjustTemperature(air, temp);
    }

    @Override
    public String getDescription() {
        return "Adjust air temperature";
    }
}
