package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

public class dBService {
    
    private final MongoCollection<Student> studentCollection;
    private final MongoCollection<Teacher> teacherCollection;
    private  final MongoCollection<Exam> examCollection;
    
    public dBService(){
        MongoClient client = MongoClientProvider.getClient();
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
    void writeExam(Exam exam){
        examCollection.insertOne(exam);
    }




}
