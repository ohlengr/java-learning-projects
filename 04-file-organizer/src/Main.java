import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FileOrganizer fileOrganizer = new FileOrganizer();
        FileConsole fileConsole = new FileConsole(scanner, fileOrganizer);

        boolean running = true;

        while (running) {
            fileConsole.menu();
            int option = fileConsole.readOption();
            if(option==5){
                running = false;
            }
            fileConsole.performAction(option);
        }
    }
}
