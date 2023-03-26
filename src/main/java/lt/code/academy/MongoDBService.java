package lt.code.academy;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.util.ArrayList;
import java.util.List;

public class MongoDBService {
    
    private final MongoCollection<Student> studentCollection;
    private final MongoCollection<Teacher> teacherCollection;
    private  final MongoCollection<Exam> examCollection;

    private Student student;
    private Teacher teacher;
    private Exam exam;
    
    public MongoDBService(){
        MongoClient client = MongoObjectClientProvider.getClient();
        MongoDatabase db = client.getDatabase("exam_app");

        
        studentCollection = db.getCollection("students", Student.class);
        teacherCollection = db.getCollection("teachers", Teacher.class);
        examCollection = db.getCollection("exams", Exam.class);
    }
    
    void insertStudents (Student student){
        studentCollection.insertOne(student);
    }
    void insertTeachers (Teacher teacher){
        teacherCollection.insertOne(teacher);
    }
    void writeExam (Exam exam){examCollection.insertOne(exam);}

    List<Student> getStudentList (){
        List <Student> students = new ArrayList<>();
        FindIterable< Student> studentsList = studentCollection.find();
        for (Student student : studentsList){
            students.add(student);
        }
        return students;
    }

    List<Teacher> getTeacherList (){
        List<Teacher> teachers = new ArrayList<>();
        FindIterable<Teacher> teacherList = teacherCollection.find();
        for (Teacher teacher : teacherList){
            teachers.add(teacher);
        }
        return teachers;
    }

    Student getStudent (String userName, String password){
        try {
            student = studentCollection.find(and(eq("studentUserName", userName), eq("studentPassword"))).first();
        } catch (NullPointerException e){
            System.out.println("No such user or wrong pasword" + e);
        }

        return student;
    }

}
