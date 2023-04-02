package lt.code.academy;

import com.mongodb.client.*;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Statistic;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
           return student = studentCollection.find(and(eq("studentUserName", userName), eq("studentPassword", password))).first();
    }

    Teacher getTeacher (String userName, String password){
        return teacher = teacherCollection.find(and(eq("teacherUserName", userName), eq("teacherPassword", password))).first();
    }

    Exam getExam (String examName, LocalDate date){
        return  exam = examCollection.find(and(eq("examName", examName), eq("examDate", date))).first();
    }

    void updateExamQuestions (Exam exam, Map<String, String> questions){
        examCollection.updateOne(eq("_id", exam.getId()), Updates.set("examQuestions", questions));
    }

    void updateStudentAnswers (Exam exam, Map <String, Map <String, String>> studentAnswers, Map <String, String> grades) {
        examCollection.updateOne(eq("_id", exam.getId()), Updates.set("studentAnswers", studentAnswers));
        examCollection.updateOne( eq("_id", exam.getId()), Updates.set ("grades", grades));
    }

    void updateStudentGrades (Student student, List<String> grades){
        studentCollection.updateOne(eq("_id", student.getId()), Updates.set("grades", grades));
    }


    void updateExamStatistics (Exam exam, Statistic statistic){
        examCollection.updateOne(eq("_id", exam.getId()), Updates.set("statistic", statistic));
    }
}
