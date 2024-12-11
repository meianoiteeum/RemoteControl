package br.com.remotecontrol.model;

public final class Air extends Device{
    private AirState state;

    private record AirState(int temperature, Mode mode){
        public AirState{
            if(temperature < 17 || temperature > 30)
                throw new IllegalArgumentException("Temperature must be between 17ºC and 30ºC");
        }

        public AirState changeTemperature(int temperature){
            return new AirState(temperature, mode());
        }

        public AirState changeMode(Mode mode){
            return new AirState(temperature(), mode);
        }
    }

    public Air(String brand, String model){
        super(brand, model);
        this.state = new AirState(22, Mode.AUTO);
    }

    public int getTemperature() {return state.temperature();}
    public Mode getMode() {return state.mode();}
    public void setTemperature(int temperature){state = state.changeTemperature(temperature);}
    public void setMode(Mode mode){state = state.changeMode(mode);}
}
