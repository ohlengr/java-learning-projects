import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentService {
    private final List<Student> students;
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        students = this.studentRepository.load();
    }

    public boolean addStudent(Student student) {
        try {
            getStudentById(student.getId());
            return false;
        }catch (StudentNotFoundException e) {
            students.add(student);
            return true;
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int student_id) {
        for(Student student : students){
            if(student.getId() == student_id){
                return student;
            }
        }
        throw new StudentNotFoundException("Student not found!");
    }

    public List<Student> getStudentByName(String name){
        List<Student> students1 = new ArrayList<>();
        for(Student student : students){
            if(student.getName().toLowerCase().contains(name.toLowerCase())){
                students1.add(student);
                return students1;
            }
        }
        throw new StudentNotFoundException("Student not found!");
    }

    public void updateStudentById(Student student){
        Student student1 = getStudentById(student.getId());
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMarks(student.getMarks());
    }

    public void deleteStudentById(int student_id) {
        Student student = getStudentById(student_id);
        students.remove(student);
    }

    public List<Student> sortByName(){
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }

    public List<Student> sortByMarksAss(){
        students.sort(Comparator.comparingDouble(Student::getMarks));
        return students;
    }

    public List<Student> sortByMarksDesc(){
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        return students;
    }

    public List<Student> multiLevelSorting(){
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed().thenComparing(Student::getName));
        return students;
    }

    public void saveStudents(){
        studentRepository.save(students);
    }
}
