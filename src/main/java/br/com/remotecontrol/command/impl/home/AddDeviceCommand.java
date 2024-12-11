package br.com.remotecontrol.command.impl.home;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.service.HomeService;
import br.com.remotecontrol.view.ConsoleView;

import java.lang.reflect.Constructor;

public class AddDeviceCommand<T extends Device> implements MenuCommand {
    private final HomeService service;
    private final ConsoleView view;
    private final Class<T> classe;

    public AddDeviceCommand(HomeService service, ConsoleView view, Class<T> classe) {
        this.service = service;
        this.view = view;
        this.classe = classe;
    }

    @Override
    public void execute() {
        String header = String.format(" ----------------- Add %s -----------------", getNameClasse());
        view.displayMessage(header);

        view.displayMessage("Enter Brand Name: ");
        String brand = view.readStringInput();
        view.displayMessage("Enter Model: ");
        String model = view.readStringInput();

        try {
            Constructor<T> contructor = classe.getConstructor(String.class, String.class);
            var device = service.addDevice(contructor.newInstance(brand, model));

            view.displaySuccess("\nCreate Device Successfully \n\n");
            view.displayDeviceStatus(device.getDevices().getLast());
        }catch (Exception ex){
            throw new RuntimeException("Error ao criar dispositivo",ex);
        }
    }

    @Override
    public String getDescription() {
        return String.format("Add %s Device", getNameClasse());
    }

    private String getNameClasse(){
        return classe.getSimpleName();
    }

}
