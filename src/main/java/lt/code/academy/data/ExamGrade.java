package lt.code.academy.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class ExamGrade {

    private String id;
    private String examId;
    private String studentId;
    private double grade;

    public ExamGrade() {
    }

    public ExamGrade(String id, String examId, String studentId, double grade) {
        this.id = id;
        this.examId = examId;
        this.studentId = studentId;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ExamGrade{" +
                "id='" + id + '\'' +
                ", examId='" + examId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", grade=" + grade +
                '}';
    }
}
