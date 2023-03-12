package lt.code.academy.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Statistic {
    private ObjectId id;
    private String examId;
    private Double gradeAverage;
    private Double highestGrade;
    private Double lowestGrade;
    private int numberOfStudents;
    private List<QuestionStatistic> questionStatistics;





}
