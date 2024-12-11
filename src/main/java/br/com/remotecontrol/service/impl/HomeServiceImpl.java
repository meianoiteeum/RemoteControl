package br.com.remotecontrol.service.impl;

import br.com.remotecontrol.model.Device;
import br.com.remotecontrol.model.Home;
import br.com.remotecontrol.service.HomeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class HomeServiceImpl implements HomeService {
    private final Home home;

    public HomeServiceImpl(Home home) {
        this.home = home;
    }

    @Override
    public Home addDevice(Device device) {
        validateDevide(device);

        home.devices().add(device);

        return home;
    }

    @Override
    public void removeDevice(Device device) {
        home.devices().remove(device);
    }

    @Override
    public List<Device> getAllDevices() {
        return home.getDevices();
    }

    @Override
    public void turnOnAllDevices() {
        home.devices().forEach(device -> device.setPowered(true));
    }

    @Override
    public void turnOffAllDevices() {
        home.devices().forEach(device -> device.setPowered(false));
    }

    @Override
    public Optional<? extends Device> findAllDevices(UUID id, Class<? extends Device> classe) {
        return home.getDevices().stream()
                .filter(device -> {
                    if(Objects.nonNull(id))
                        return device.getId().compareTo(id) == 0;
                    else
                        return classe.isInstance(device);
                })
                .findFirst();
    }

    private void validateDevide(Device device) {
        if(Objects.isNull(device))
            throw new IllegalArgumentException("Device cannot be null");
    }
}
