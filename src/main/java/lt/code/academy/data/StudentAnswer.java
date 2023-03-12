package lt.code.academy.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Map;

public class StudentAnswer {

    private String id;
    private String examId;
    private String StudentId;
    private Map <String, String> answers;

    public StudentAnswer() {
    }

    public StudentAnswer(String id, String examId, String studentId, Map<String, String> answers) {
        this.id = id;
        this.examId = examId;
        StudentId = studentId;
        this.answers = answers;
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
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "id='" + id + '\'' +
                ", examId='" + examId + '\'' +
                ", StudentId='" + StudentId + '\'' +
                ", answers=" + answers +
                '}';
    }
}
