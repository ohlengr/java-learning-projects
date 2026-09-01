import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentConsole {

    StudentService studentService;
    Scanner scanner;

    public StudentConsole(StudentService studentService, Scanner scanner){
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public void showMenu(){
        System.out.println("Student's Management System");
        System.out.println("============================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search by ID");
        System.out.println("4. Search by Name");
        System.out.println("5. Update Student");
        System.out.println("6. Delete Student");
        System.out.println("7. Sort Students");
        System.out.println("8. Save");
        System.out.println("9. Exit");
    }

    public int readMenu(){
        System.out.print("Enter option: ");
        return scanner.nextInt();
    }

    public void performAction(int option){
        switch (option) {
            case 1:
                handleAddStudent();
                break;
            case 2:
                handleLoadStudents();
                break;
            case 3:
                handleSearchById();
                break;
            case 4:
                handleSearchByName();
                break;
            case 5:
                handleUpdateById();
                break;
            case 6:
                handleDeleteById();
                break;
            case 7:
                handleStudentSort();
                break;
            case 8:
                handleStudentSave();
                break;
            case 9:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Option is invalid");
        }
    }

    public void handleAddStudent(){
        System.out.println("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Student Age: ");
        int age = scanner.nextInt();
        System.out.println("Enter Student Marks: ");
        double marks = scanner.nextDouble();
        Student student = new Student(id, name, age, marks);
        boolean status = studentService.addStudent(student);
        if(!status){
            System.out.println("Student Already Exist with ID");
        }else {
            System.out.println("Student Add Successfully!");
        }
    }

    public void handleLoadStudents(){
        System.out.println(studentService.getAllStudents());
    }

    public void handleSearchById(){
        System.out.println("Enter Student ID to search: ");
        int id = scanner.nextInt();
        try {
            Student student = studentService.getStudentById(id);
            System.out.println(student);
        }catch (StudentNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void handleSearchByName(){
        System.out.println("Enter Student Name to search: ");
        String name = scanner.nextLine();
        try {
            List<Student> students = studentService.getStudentByName(name);
            System.out.println(students);
        }catch (StudentNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void handleUpdateById(){
        System.out.println("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Student Age: ");
        int age = scanner.nextInt();
        System.out.println("Enter Student Marks: ");
        double marks = scanner.nextDouble();

        Student student = new Student(id, name, age, marks);
        try {
            studentService.updateStudentById(student);
            System.out.println("Student updated!");
        }catch (StudentNotFoundException e){
            System.out.println("Student not found!");
        }
    }

    public void handleDeleteById(){
        System.out.println("Enter Student ID to Delete: ");
        int id = scanner.nextInt();

        try {
            studentService.deleteStudentById(id);
            System.out.println("Student deleted!");
        }catch (StudentNotFoundException e) {
            System.out.println("Student not found!");
        }
    }

    public void handleStudentSort(){
        System.out.println("Choose option: ");
        System.out.println("===============");
        System.out.println("1. By Name");
        System.out.println("2. By Marks Ascending");
        System.out.println("3. By Marks Descending");
        System.out.println("4. Multi Level Sort");
        int sortOption = scanner.nextInt();

        switch (sortOption){
            case 1:
                System.out.println(studentService.sortByName());
                break;
            case 2:
                System.out.println(studentService.sortByMarksAss());
                break;
            case 3:
                System.out.println(studentService.sortByMarksDesc());
                break;
            case 4:
                System.out.println(studentService.multiLevelSorting());
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public void handleStudentSave(){
        studentService.saveStudents();
    }
}
