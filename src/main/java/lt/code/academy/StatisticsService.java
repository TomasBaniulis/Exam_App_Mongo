package lt.code.academy;

import lt.code.academy.data.Exam;
import lt.code.academy.data.QuestionStatistic;

import java.util.Map;

public class StatisticsService {

    private MongoDBService dbService;
    private Exam exam;

    public StatisticsService(MongoDBService dbService, Exam exam) {
        this.dbService = dbService;
        this.exam = exam;
    }

    double getExamGradeAverage (){
        Map <String, String> grades = exam.getGrades();
        int counter = 0;
        for (Map.Entry<String, String> grade : grades.entrySet()){
            counter += Integer.parseInt(grade.getValue());
        }
       return counter/grades.size();
    }

    QuestionStatistic getQuestionStatistics (){
        Map <String, String> rightAnswers = exam.getRightAnswers();
        Map<String, Map<String, String>> studentAnswers = exam.getStudentAnswers();
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        for(Map.Entry<String, Map <String, String>> answer : studentAnswers.entrySet()){
            Map <String, String> oneStudentAnswers= answer.getValue();
            for(Map.Entry<String, String> asw : oneStudentAnswers.entrySet()){
                if (asw.getValue().equals("1")){
                    counterA++;
                } else if (asw.getValue().equals("2")) {
                    counterB++;
                } else {
                    counterC++;
                }
            }

            double FirstAnswerPercent = counterA /



        }

    }










}
