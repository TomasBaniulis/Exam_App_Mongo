package lt.code.academy.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Map;

public class Student {

    private ObjectId id;
    private String studentName;
    private String studentSurname;
    private String studentUserName;
    private String studentPassword;
    private Map<String, ExamGrade> grades;

    public Student() {}

    public Student(ObjectId id, String studentName, String studentSurname, String studentUserName, String studentPassword, Map<String, ExamGrade> grades) {
        this.id = id;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentUserName = studentUserName;
        this.studentPassword = studentPassword;
        this.grades = grades;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public Map<String, ExamGrade> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, ExamGrade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", studentUserName='" + studentUserName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", grades=" + grades +
                '}';
    }
}
