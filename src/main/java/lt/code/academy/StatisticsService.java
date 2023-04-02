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
        int counter = 0;
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
            int counterOne = 0;
            int counterTwo = 0;
            int counterThree = 0;
            for (Map.Entry<String, Map<String,String>> oneStudentAnswer : studentsAnswers.entrySet()){
                Map <String, String> studentAnswers = oneStudentAnswer.getValue();
                for (Map.Entry<String , String> answer: studentAnswers.entrySet()){
                    if (String.valueOf(questionNumber).equals(answer.getKey())){
                        switch (answer.getValue()){
                            case "1" -> counterOne++;
                            case "2" -> counterTwo++;
                            case "3" -> counterThree++;
                        }
                    }
                }

                int answerOnePercent = counterOne/studentsAnswers.size();
                int answerTwoPercent = counterTwo/studentsAnswers.size();
                int answerTheePercent = counterThree/studentsAnswers.size();

                Map <String, Integer> percentOfStudentClicksPerQuestionAnswer = new HashMap<>();
                percentOfStudentClicksPerQuestionAnswer.put("1", answerOnePercent);
                percentOfStudentClicksPerQuestionAnswer.put("2", answerTwoPercent);
                percentOfStudentClicksPerQuestionAnswer.put("3", answerTheePercent);

                String questionId = String.valueOf(questionNumber);

                int percentOfRightAnswer = 0;

                switch (rightAnswers.get(String.valueOf(questionNumber))){
                    case "1" -> percentOfRightAnswer = answerOnePercent;
                    case "2" -> percentOfRightAnswer = answerTwoPercent;
                    case "3" -> percentOfRightAnswer = answerTheePercent;
                }

                questionStatistics.add(new QuestionStatistic(questionId, percentOfRightAnswer, percentOfStudentClicksPerQuestionAnswer));

                 counterOne = 0;
                 counterTwo = 0;
                 counterThree = 0;

                 questionNumber ++;
                }
            }
            return questionStatistics;
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
