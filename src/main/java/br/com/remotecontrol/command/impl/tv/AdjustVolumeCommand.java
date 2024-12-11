package br.com.remotecontrol.command.impl.tv;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.service.TVService;
import br.com.remotecontrol.view.ConsoleView;

public class AdjustVolumeCommand implements MenuCommand {

    private final TV tv;
    private final TVService tvService;
    private final ConsoleView view;

    public AdjustVolumeCommand(TV tv, TVService tvService, ConsoleView view) {
        this.tv = tv;
        this.tvService = tvService;
        this.view = view;
    }

    @Override
    public void execute() {
        int volume = view.readIntInput(0,100,"Enter volume (0-100)");
        tvService.adjustVolume(tv, volume);
    }

    @Override
    public String getDescription() {
        return "Adjust Volume";
    }
}
