import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentService(studentRepository);
        StudentConsole studentConsole = new StudentConsole(studentService, scanner);

        boolean running = true;

        while(running) {
            studentConsole.showMenu();
            int option = studentConsole.readMenu();
            if(option == 9){
                running = false;
            }
            studentConsole.performAction(option);
        }
    }
}