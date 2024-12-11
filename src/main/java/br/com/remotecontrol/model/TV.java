package br.com.remotecontrol.model;

public final class TV extends Device{
    private TVState state;

    private record TVState(int channel, int volume){
        public TVState{
            if(channel < 1) throw new IllegalArgumentException("Channel must be positive");
            if(volume < 0 || volume > 100) throw new IllegalArgumentException("Volume must be between 0 and 100");
        }

        public TVState changeChannel(int channel){
            return new TVState(channel, volume());
        }

        public TVState changeVolume(int volume){
            return new TVState(channel(), volume);
        }
    }

    public TV(String brand, String model){
        super(brand, model);
        this.state = new TVState(1,0);
    }

    public int getChannel() {return state.channel();}
    public int getVolume() {return state.volume();}
    public void setChannel(int channel) {state = state.changeChannel(channel);}
    public void setVolume(int volume) {state = state.changeVolume(volume);}
}
