package lt.code.academy.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Statistic {
    private String id;
    private String examId;
    private Double gradeAverage;
    private Double highestGrade;
    private Double lowestGrade;
    private int numberOfStudents;
    private List<QuestionStatistic> questionStatistics;

    public Statistic() {
    }

    public Statistic(String id, String examId, Double gradeAverage, Double highestGrade, Double lowestGrade, int numberOfStudents, List<QuestionStatistic> questionStatistics) {
        this.id = id;
        this.examId = examId;
        this.gradeAverage = gradeAverage;
        this.highestGrade = highestGrade;
        this.lowestGrade = lowestGrade;
        this.numberOfStudents = numberOfStudents;
        this.questionStatistics = questionStatistics;
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

    public Double getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(Double gradeAverage) {
        this.gradeAverage = gradeAverage;
    }

    public Double getHighestGrade() {
        return highestGrade;
    }

    public void setHighestGrade(Double highestGrade) {
        this.highestGrade = highestGrade;
    }

    public Double getLowestGrade() {
        return lowestGrade;
    }

    public void setLowestGrade(Double lowestGrade) {
        this.lowestGrade = lowestGrade;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public List<QuestionStatistic> getQuestionStatistics() {
        return questionStatistics;
    }

    public void setQuestionStatistics(List<QuestionStatistic> questionStatistics) {
        this.questionStatistics = questionStatistics;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id='" + id + '\'' +
                ", examId='" + examId + '\'' +
                ", gradeAverage=" + gradeAverage +
                ", highestGrade=" + highestGrade +
                ", lowestGrade=" + lowestGrade +
                ", numberOfStudents=" + numberOfStudents +
                ", questionStatistics=" + questionStatistics +
                '}';
    }
}
