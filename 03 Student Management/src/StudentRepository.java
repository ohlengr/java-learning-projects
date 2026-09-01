import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public void save(List<Student> students){
        File file = new File("students.csv");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                String line = student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.getMarks();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("Students saved successfully!");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> load(){
        List<Student> student1 = new ArrayList<>();
        Path path = Paths.get("students.csv");
        if(Files.exists(path) && Files.isRegularFile(path)){
            try (BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(path))) {
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    int id = Integer.parseInt(line.split(",")[0]);
                    String name = line.split(",")[1];
                    int age = Integer.parseInt(line.split(",")[2]);
                    double marks = Double.parseDouble(line.split(",")[3]);
                    student1.add(new Student(id, name, age, marks));
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("File not exist!");
        }
        return student1;
    }
}
