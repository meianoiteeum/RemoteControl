package br.com.remotecontrol.command.impl.tv;

import br.com.remotecontrol.command.MenuCommand;
import br.com.remotecontrol.model.TV;
import br.com.remotecontrol.service.TVService;
import br.com.remotecontrol.view.ConsoleView;

public class ChangeChannelCommand implements MenuCommand {
    private final TV tv;
    private final TVService tvService;
    private final ConsoleView view;

    public ChangeChannelCommand(TV tv, TVService tvService, ConsoleView view) {
        this.tv = tv;
        this.tvService = tvService;
        this.view = view;
    }

    @Override
    public void execute() {
        int channel = view.readIntInput(0,100, "Enter channel number (0-100): ");
        tvService.changeChannel(tv,channel);
    }

    @Override
    public String getDescription() {
        return "Change TV Channel";
    }
}
