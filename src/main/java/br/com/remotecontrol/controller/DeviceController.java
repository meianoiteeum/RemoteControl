package br.com.remotecontrol.controller;

import br.com.remotecontrol.command.impl.air.TemperatureCommand;
import br.com.remotecontrol.command.impl.air.ChangeModeCommand;
import br.com.remotecontrol.command.impl.home.TurnOnOffCommand;
import br.com.remotecontrol.command.impl.tv.AdjustVolumeCommand;
import br.com.remotecontrol.command.impl.tv.ChangeChannelCommand;
import br.com.remotecontrol.menu.MenuManager;
import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.service.AirService;
import br.com.remotecontrol.service.TVService;
import br.com.remotecontrol.util.Util;
import br.com.remotecontrol.view.ConsoleView;

public class DeviceController {
    private final TVService tvService;
    private final AirService airService;
    private final ConsoleView view;

    public DeviceController(TVService tvService, AirService airService, ConsoleView view) {
        this.tvService = tvService;
        this.airService = airService;
        this.view = view;
    }

    private MenuManager createTvMenu(TV tv){
        MenuManager menu = new MenuManager(view);
        menu.addCommand(1, new TurnOnOffCommand<>(tv, tvService));
        menu.addCommand(2, new ChangeChannelCommand(tv, tvService, view));
        menu.addCommand(3, new AdjustVolumeCommand(tv, tvService, view));

        return menu;
    }

    private MenuManager createAirMenu(Air air){
        MenuManager menu = new MenuManager(view);
        menu.addCommand(1, new TurnOnOffCommand<>(air, airService));
        menu.addCommand(2, new TemperatureCommand(air, airService, view));
        menu.addCommand(3, new ChangeModeCommand(air, airService, view));

        return menu;
    }

    public void start(Device device){
        var run = true;
        while (run){
            try {
                view.displayDeviceStatus(device);

                switch(device){
                    case Air air -> {
                        var airMenu = createAirMenu(air);
                        airMenu.displayMenu("Air Control");
                        int choice = view.readIntInput();
                        run = Util.executeCommand(choice, airMenu);
                    }
                    case TV tv -> {
                        var tvMenu = createTvMenu(tv);
                        tvMenu.displayMenu("TV Control");
                        int choice = view.readIntInput();
                        run = Util.executeCommand(choice, tvMenu);
                    }
                }
            }catch (Exception ex){
                view.displayError(ex.getMessage());
                run = false;
            }
        }
    }


}
