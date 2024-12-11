package br.com.remotecontrol.util;

import br.com.remotecontrol.menu.MenuManager;

public class Util {

    public static boolean isStringEmpty(String type){
        return type == null || type.isEmpty();
    }

    public static void validateTurnOn(boolean powered){
        if(powered)
            throw new IllegalStateException("Device is already ON");
    }

    public static void validateTurnOff(boolean powered){
        verifyTurnOff(powered,"Device is already OFF");
    }

    public static void verifyTurnOff(boolean powered, String message) {
        if(!powered)
            throw new IllegalStateException(message);
    }

    public static boolean executeCommand(int choice, MenuManager manager){
        if(choice == 0)
            return false;
        else{
            manager.executeCommand(choice);
            return true;
        }
    }

    public static boolean validNumber(int min, int max, int value){
        return value >= min && value <= max;
    }

}
