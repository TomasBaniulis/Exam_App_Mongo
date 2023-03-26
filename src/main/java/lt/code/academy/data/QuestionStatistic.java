package lt.code.academy.data;

import java.util.Map;

public class QuestionStatistic {
    private String questionId;
    private double percentOfRightAnswers;
    private Map<String, Integer> percentOfStudentClicksPerAnswer;

    public QuestionStatistic() {}

    public QuestionStatistic(String questionId, double percentOfRightAnswers, Map<String, Integer> percentOfStudentClicksPerAnswer) {
        this.questionId = questionId;
        this.percentOfRightAnswers = percentOfRightAnswers;
        this.percentOfStudentClicksPerAnswer = percentOfStudentClicksPerAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public double getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public void setPercentOfRightAnswers(double percentOfRightAnswers) {
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    public Map<String, Integer> getPercentOfStudentClicksPerAnswer() {
        return percentOfStudentClicksPerAnswer;
    }

    public void setPercentOfStudentClicksPerAnswer(Map<String, Integer> percentOfStudentClicksPerAnswer) {
        this.percentOfStudentClicksPerAnswer = percentOfStudentClicksPerAnswer;
    }

    @Override
    public String toString() {
        return "QuestionStatistic{" +
                "questionId='" + questionId + '\'' +
                ", percentOfRightAnswers=" + percentOfRightAnswers +
                ", percentOfStudentClicksPerAnswer=" + percentOfStudentClicksPerAnswer +
                '}';
    }
}
