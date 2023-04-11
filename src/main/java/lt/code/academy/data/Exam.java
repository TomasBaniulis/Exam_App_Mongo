package lt.code.academy.data;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Exam extends Teacher {

    private ObjectId id;
    private String examName;

    private LocalDate examDate;
    private Map<String, String> questions;
    private Map<String, String> answersToChoose;
    private Map<String, String> rightAnswers;

    private Map<String, Map<String, String>> studentAnswers;
    private Map<String, String> grades;
    private Statistic examStatistic;


    public Exam() {
    }

    public Exam(ObjectId id, String teacherName, String teacherSurname, String examName, LocalDate examDate, Map<String, String> questions, Map<String, String> answersToChoose, Map<String, String> rightAnswers) {
        super(id, teacherName, teacherSurname);
        this.examName = examName;
        this.examDate = examDate;
        this.questions = questions;
        this.answersToChoose = answersToChoose;
        this.rightAnswers = rightAnswers;
    }

    public Exam(ObjectId id, String teacherName, String teacherSurname, ObjectId id1, String examName, LocalDate examDate, Map<String, String> questions, Map<String, String> answersToChoose, Map<String, String> rightAnswers, Map<String, Map<String, String>> studentAnswers, Map<String, String> grades, Statistic examStatistic) {
        super(id, teacherName, teacherSurname);
        this.id = id1;
        this.examName = examName;
        this.examDate = examDate;
        this.questions = questions;
        this.answersToChoose = answersToChoose;
        this.rightAnswers = rightAnswers;
        this.studentAnswers = studentAnswers;
        this.grades = grades;
        this.examStatistic = examStatistic;
    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Map<String, String> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, String> questions) {
        this.questions = questions;
    }

    public Map<String, String> getAnswersToChoose() {
        return answersToChoose;
    }

    public void setAnswersToChoose(Map<String, String> answersToChoose) {
        this.answersToChoose = answersToChoose;
    }

    public Map<String, String> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Map<String, String> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public Map<String, Map<String, String>> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Map<String, Map<String, String>> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }

    public Statistic getExamStatistic() {
        return examStatistic;
    }

    public void setExamStatistic(Statistic examStatistic) {
        this.examStatistic = examStatistic;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", questions=" + questions +
                ", answersToChoose=" + answersToChoose +
                ", rightAnswers=" + rightAnswers +
                ", studentAnswers=" + studentAnswers +
                ", grades=" + grades +
                ", examStatistic=" + examStatistic +
                '}';
    }
}









