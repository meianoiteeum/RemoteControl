package br.com.remotecontrol.model;

import br.com.remotecontrol.util.Util;

import java.util.UUID;

public sealed abstract class Device permits TV, Air{
    private final UUID id;
    private DeviceInfo info;
    private boolean powered;

    protected record DeviceInfo(String brand, String model){
        public DeviceInfo{
            if(Util.isStringEmpty(brand))
                throw new IllegalArgumentException("Brand cannot be null or empty");
            if(Util.isStringEmpty(model))
                throw new IllegalArgumentException("Model cannot be null or empty");
        }

        public DeviceInfo renameBrand(String newBrand){
            return new DeviceInfo(newBrand, model());
        }
        public DeviceInfo renameModel(String newModel){
            return new DeviceInfo(brand(), newModel);
        }
    }

    protected Device(String brand, String model){
        this.id = UUID.randomUUID();
        this.info = new DeviceInfo(brand, model);
        this.powered = false;
    }

    public UUID getId() {return id;}
    public boolean isPowered() {return powered;}
    public String getBrand() {return info.brand();}
    public String getModel() {return info.model();}
    public void renameBrand(String newBrand){info = info.renameBrand(newBrand);}
    public void renameModel(String newModel){info = info.renameModel(newModel);}
    public void setPowered(boolean powered) {this.powered = powered;}
}
