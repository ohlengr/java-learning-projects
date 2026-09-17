import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Todo> tasks = new ArrayList<>();

        int menuOptionId = 0, nextId = 1;

        while (true){
            menuOptionId = todoMenu(scanner);
            switch (menuOptionId) {
                case 1:
                    nextId = addTask(tasks, scanner, nextId);
                    break;

                case 2:
                    listTask(tasks);
                    break;

                case 3:
                    completeTask(tasks, scanner);
                    break;

                case 4:
                    deleteTask(tasks, scanner);
                    break;

                case 5:
                    System.out.println("Exit!");
                    return;

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }

    public static int todoMenu(Scanner scanner){
        System.out.println("\n");
        System.out.println("====== TODO APP ======");
        System.out.println("1. Add Task");
        System.out.println("2. List Task");
        System.out.println("3. Complete Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");

        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");

        return option;
    }

    public static int addTask(ArrayList<Todo> tasks, Scanner scanner, int id) {
        System.out.println("Enter task title: ");
        String title = scanner.nextLine();

        Todo task = new Todo(id, title);

        tasks.add(task);

        System.out.println("Task added successfully!");

        return (id+1);
    }

    public static void listTask(ArrayList<Todo> tasks) {
        if(tasks.isEmpty()){
            System.out.println("No task found!");
            return;
        }

        for(Todo task : tasks){
            System.out.println(
                    task.getId() + ". " +
                    task.getTitle() + " - " +
                    (task.isCompleted() ? "Completed" : "Pending")
            );
        }
    }

    public static void completeTask(ArrayList<Todo> tasks, Scanner scanner) {
        System.out.println("Enter task ID: ");
        int taskId = scanner.nextInt();

        for(Todo task : tasks) {
            if(task.getId() == taskId){
                task.setCompleted(true);
                System.out.println("Task Completed!");
                return;
            }
        }

        System.out.println("Task not found!");
    }

    public static void deleteTask(ArrayList<Todo> tasks, Scanner scanner) {
        System.out.println("Enter task ID: ");
        int taskId = scanner.nextInt();

        for(int i=0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == taskId){
                tasks.remove(i);
                System.out.println("Task deleted successfully!");
                return;
            }
        }

        System.out.println("Task not found!");
    }
}
