package lt.code.academy;

import lt.code.academy.data.Exam;
import lt.code.academy.data.QuestionStatistic;
import lt.code.academy.data.Statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {


    double getExamGradeAverage (Exam exam){
        Map <String, String> grades = exam.getGrades();
        if (grades == null){
            return 0;
        }
        double counter = 0;
        for (Map.Entry<String, String> grade : grades.entrySet()){
            counter += Integer.parseInt(grade.getValue());
        }
       return counter/grades.size();
    }

    double getHighestGrade (Exam exam){
        Map <String, String> grades = exam.getGrades();
        double highestGrade = 0;
        for (Map.Entry<String, String> grade : grades.entrySet()){
            double studentGrade = Double.parseDouble(grade.getValue());
            if ( studentGrade> highestGrade){
                highestGrade = studentGrade;
            }
        }
        return highestGrade;
    }

    double getLowesGrade (Exam exam) {
        Map<String, String> grades = exam.getGrades();
        double lowestGrade = 10;
        for (Map.Entry<String, String> grade : grades.entrySet()) {
            double studentGrade = Double.parseDouble(grade.getValue());
            if (studentGrade < lowestGrade) {
                lowestGrade = studentGrade;
            }
        }
        return lowestGrade;
    }

    List <QuestionStatistic> generateQuestionStatisticList (Exam exam){
        List <QuestionStatistic> questionStatistics= new ArrayList<>();
        Map<String, String > rightAnswers = exam.getRightAnswers();
        Map<String, Map<String,String>> studentsAnswers = exam.getStudentAnswers();
        int questionNumber = 1;
        for (int i = 0; i < rightAnswers.size(); i++){
            QuestionStatistic questionStatistic = questionStatisticsCalculation(rightAnswers, studentsAnswers,questionNumber);
            questionStatistics.add(questionStatistic);
            questionNumber++;
            }
            return questionStatistics;
        }


        QuestionStatistic questionStatisticsCalculation (Map<String , String> rightAnswers, Map<String, Map<String, String>> studentsAnswers, int questionNumber ) {
            QuestionStatistic questionStatistic = new QuestionStatistic();
            int counterOne = 0;
            int counterTwo = 0;
            int counterThree = 0;

            for (Map.Entry<String, Map<String, String>> oneStudentAnswer : studentsAnswers.entrySet()) {
                Map<String, String> studentAnswers = oneStudentAnswer.getValue();
                switch (studentAnswers.get(String.valueOf(questionNumber))) {
                    case "1" -> counterOne++;
                    case "2" -> counterTwo++;
                    case "3" -> counterThree++;
                }
            }
            double answerOnePercent = Math.round(counterOne / studentsAnswers.size()) * 100;
            int a = (int)answerOnePercent;
            double answerTwoPercent = Math.round(counterTwo / studentsAnswers.size()) * 100;
            int b = (int)answerTwoPercent;
            double answerTheePercent = Math.round(counterThree / studentsAnswers.size()) * 100;
            int c = (int)answerTheePercent;

            Map<String, Integer> percentOfStudentClicksPerQuestionAnswer = new HashMap<>();
            percentOfStudentClicksPerQuestionAnswer.put("1", a);
            percentOfStudentClicksPerQuestionAnswer.put("2", b);
            percentOfStudentClicksPerQuestionAnswer.put("3", c);


            String questionId = String.valueOf(questionNumber);

            int percentOfRightAnswer = 0;

            switch (rightAnswers.get(String.valueOf(questionNumber))) {
                case "1" -> percentOfRightAnswer = a;
                case "2" -> percentOfRightAnswer = b;
                case "3" -> percentOfRightAnswer = c;
            }

            questionStatistic = new QuestionStatistic(questionId, percentOfRightAnswer,percentOfStudentClicksPerQuestionAnswer);

            return questionStatistic;
        }



        Statistic generateStatistic (Exam exam){

        String examId = String.valueOf(exam.getId());

        String id = examId + "stat";

        double gradeAverage = getExamGradeAverage(exam);

        double highestGrade = getHighestGrade(exam);

        double lowestGrade = getLowesGrade(exam);

        int numberOfStudents = exam.getStudentAnswers().size();

        List<QuestionStatistic> questionStatistics= generateQuestionStatisticList(exam);


        return new Statistic(id, examId, gradeAverage, highestGrade, lowestGrade, numberOfStudents, questionStatistics );

        }

}
