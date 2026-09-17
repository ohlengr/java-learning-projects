import console.UrlCheckerConsole;
import service.UrlCheckerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UrlCheckerService urlCheckerService = new UrlCheckerService();
        UrlCheckerConsole urlCheckerConsole = new UrlCheckerConsole(scanner, urlCheckerService);

        boolean running = true;
        while (running) {
            int option = urlCheckerConsole.getMenuOption();
            if (option == 4) {
                running = false;
            }else {
                urlCheckerConsole.performOptionAction(option);
            }
        }
    }
}