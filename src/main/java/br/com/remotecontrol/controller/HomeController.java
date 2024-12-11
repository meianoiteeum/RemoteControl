package br.com.remotecontrol.controller;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.command.impl.home.AddDeviceCommand;
import br.com.remotecontrol.command.impl.home.ListAllDeviceCommand;
import br.com.remotecontrol.menu.MenuManager;
import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.service.HomeService;
import br.com.remotecontrol.service.impl.AirServiceImpl;
import br.com.remotecontrol.service.impl.TVServiceImpl;
import br.com.remotecontrol.util.Util;
import br.com.remotecontrol.view.ConsoleView;

import java.util.UUID;

public class HomeController {
    private final HomeService homeService;
    private final ConsoleView view;
    private final MenuManager mainMenu;

    public HomeController(HomeService homeService, ConsoleView view) {
        this.homeService = homeService;
        this.view = view;

        this.mainMenu = createMainMenu();
    }

    private MenuManager createMainMenu() {
        MenuManager menu = new MenuManager(view);

        menu.addCommand(1, new AddDeviceCommand<>(homeService, view, Air.class));
        menu.addCommand(2, new AddDeviceCommand<>(homeService, view, TV.class));
        menu.addCommand(3, new ListAllDeviceCommand(homeService, view));
        menu.addCommand(4, new MenuCommand() {
            @Override
            public void execute() {
                var tvService = new TVServiceImpl();
                var airService = new AirServiceImpl();
                var deviceController = new DeviceController(tvService, airService, view);
                view.displayMessage("Insert ID from Device");
                String id = view.readStringInput();

                homeService.findAllDevices(UUID.fromString(id), null)
                        .ifPresent(deviceController::start);
            }

            @Override
            public String getDescription() {
                return "Operators";
            }
        });
        menu.addCommand(5, new MenuCommand() {
            @Override
            public void execute() {
                homeService.turnOnAllDevices();
                view.displaySuccess("Turn On Successfully");
            }

            @Override
            public String getDescription() {
                return "Turn On All Devices";
            }
        });
        menu.addCommand(6, new MenuCommand() {
            @Override
            public void execute() {
                homeService.turnOffAllDevices();
                view.displaySuccess("Turn Off Successfully");
            }

            @Override
            public String getDescription() {
                return "Turn Off All Devices";
            }
        });

        return menu;
    }

    public void start(){
        boolean run = true;
        while (run){
            try{
                mainMenu.displayMenu("Control Home");
                int choice = view.readIntInput();
                run = Util.executeCommand(choice, mainMenu);
            }catch (Exception ex){
                view.displayError(ex.getMessage());
            }
        }
    }
}
