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

    public Statistic() {}

    public Statistic(String id, String examId, Double gradeAverage, Double highestGrade, Double lowestGrade, int numberOfStudents, List<QuestionStatistic> questionStatistics) {
        this.id = id;
        this.examId = examId;
        this.gradeAverage = gradeAverage;
        this.highestGrade = highestGrade;
        this.lowestGrade = lowestGrade;
        this.numberOfStudents = numberOfStudents;
        this.questionStatistics = questionStatistics;
    }
}
