import br.com.remotecontrol.controller.HomeController;
import br.com.remotecontrol.model.Home;
import br.com.remotecontrol.service.HomeService;
import br.com.remotecontrol.service.impl.HomeServiceImpl;
import br.com.remotecontrol.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        Home home = new Home();
        HomeService homeService = new HomeServiceImpl(home);
        ConsoleView view = new ConsoleView();
        HomeController controller = new HomeController(homeService, view);
        controller.start();
    }

}