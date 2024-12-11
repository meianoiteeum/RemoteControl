package br.com.remotecontrol.view;

import br.com.remotecontrol.model.Air;
import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.util.Util;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner sc;

    public ConsoleView() {
        sc = new Scanner(System.in);
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayError(String error){
        System.out.println("ERROR: " + error);
    }

    public void displaySuccess(String message){
        System.out.println("SUCCESS: " + message);
    }

    public void displayDeviceStatus(Device device){
        String satus = """
        ------------------------------------ %s ----------------------------------
        ID: %s | Brand: %s | Model: %s |
        """.formatted(device.getClass().getSimpleName(), device.getId(), device.getBrand(), device.getModel());

        System.out.println(satus);

        if(device instanceof TV tv)
            displayTVStatus(tv);
        else if(device instanceof Air air)
            displayAir(air);
    }

    public int readIntInput(){
        try{
            String input = sc.nextLine().trim();
            return Integer.parseInt(input);
        }catch (NumberFormatException nfe){
            displayError("Please enter a valid number");
            return -1;
        }
    }

    public int readIntInput(int min, int max, String prompt){
        while(true){
            displayMessage(prompt);
            int value = readIntInput();
            if(Util.validNumber(min, max, value))
                return value;

            displayError("Please, enter a number between " + min + " and " + max);
        }
    }

    public String readStringInput(){
        return sc.nextLine().trim();
    }

    private void displayTVStatus(TV tv) {
        String settings = """
                Status: %s | Channel: %d | Volume: %d
                ___________________________________________________________________________
                """.formatted(tv.isPowered(), tv.getChannel(), tv.getVolume());
        System.out.println(settings);
    }

    private void displayAir(Air air) {
        String settings = """
                Status: %s | Temperature: %d | Mode: %s
                ___________________________________________________________________________
                """.formatted(air.isPowered(), air.getTemperature(), air.getMode());
        System.out.println(settings);
    }

}
