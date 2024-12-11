package br.com.remotecontrol.model;

import java.util.Arrays;

public enum Mode {
    AUTO, COOL, DRY, HEAT;

    public static Mode findCode(String type) {
        return Arrays.stream(values())
                .filter(mode -> mode.name().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado: " + type));
    }
}
