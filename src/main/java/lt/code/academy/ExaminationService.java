package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.javafaker.Faker;
import com.mongodb.client.ClientSession;
import lt.code.academy.data.*;

import java.io.File;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExaminationService {

    Scanner scanner;
    Faker faker;
    MongoDBService dbService;
    Random random;

    StatisticsService statisticsService;

    public ExaminationService(Scanner scanner, Faker faker, MongoDBService dbService, Random random, StatisticsService statisticsService) {
        this.scanner = scanner;
        this.faker = faker;
        this.dbService = dbService;
        this.random = random;
        this.statisticsService = statisticsService;
    }

    void generateExam (Teacher teacher){
        String examName = faker.programmingLanguage().name();
        LocalDate examDate = getDate();
        System.out.println("Set number of exam questions");
        int qNumber = Integer.parseInt(scanner.nextLine());
        Map<String, String> questions = generateQuestions(qNumber);
        Map<String, String> testAnswers = generateTestAnswers(qNumber);
        Map<String, String> rightAnswers = generateRightAnswers(qNumber);
        dbService.writeExam(new Exam(teacher.getId(), teacher.getTeacherName(), teacher.getTeacherSurname(), examName, examDate, questions, testAnswers, rightAnswers));
    }

    private Map<String, String> generateQuestions (int numberOfQuestions){
        Map <String, String> questions = new HashMap<>();
        int counter = 1;
        for ( int i = 0; i <numberOfQuestions; i++){
            String question = faker.chuckNorris().fact();
            questions.put(String.valueOf(counter), question);
            counter++;
        }
        return questions;
    }

    private Map<String, String> generateTestAnswers (int numberOfQuestions){
        Map <String, String> testAnswers = new HashMap<>();
        int counter = 1;
        for ( int i = 0; i <numberOfQuestions; i++){
            String answers = " [1] -> funny;  [2] -> not funny; [3] -> this fact is not about Chuck Noris;";
            testAnswers.put(String.valueOf(counter), answers);
            counter++;
        }
        return testAnswers;
    }

    private Map<String, String> generateRightAnswers (int numberOfQuestions){
        Map <String, String> rightAnswers = new HashMap<>();
        int counter = 1;
        for ( int i = 0; i <numberOfQuestions; i++){
            String answer = String.valueOf(random.nextInt(1,4));
            rightAnswers.put(String.valueOf(counter), answer);
            counter++;
        }
        return rightAnswers;
    }

    LocalDate getDate (){
        while (true){
            try{
                System.out.println("Set exam date yyyy.MM.dd :");
                String date = scanner.nextLine();
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                return localDate;
            }catch (DateTimeException e) {
                System.out.println("Wrong date format. Try again!");
            }
        }
    }

    void update(Teacher teacher) {
        System.out.println("Enter exam name");
        String examName = scanner.nextLine();
        LocalDate date = getDate();
        Exam exam = dbService.getExam(examName, date);
        if (exam == null) {
            System.out.printf("no such exam on: %s %n", date.toString());
            return;
        }
        if (!exam.getTeacherSurname().equals(teacher.getTeacherSurname())) {
            System.out.printf("You have no authorisation to make any changes!  %s %s has created this exam", exam.getTeacherName(), exam.getTeacherSurname());
            return;
        }
        Map<String, String> updatedQuestions = updateQuestions(exam);
        dbService.updateExamQuestions(exam, updatedQuestions);
    }

    private Map<String, String> updateQuestions(Exam exam){
        Map<String, String> questions = exam.getQuestions();
        System.out.println("Enter question number you want to update");
        String questionNumber = scanner.nextLine();
        System.out.printf("Old question: %s %n Please type new Question", questions.get(questionNumber));
        String newQuestion = scanner.nextLine();
        questions.put(questionNumber, newQuestion);
        return questions;
    }

    void takeExam (Student student){
        System.out.println("Enter exam name");
        String examName = scanner.nextLine();
        LocalDate date = getDate();
        Exam exam = dbService.getExam(examName, date);
        if (exam == null){
            System.out.println("exam does not exist");
            return;
        }
        if (! LocalDate.now().equals(exam.getExamDate())) {
            System.out.println("you can't take exam today");
           return;
        }

        Boolean secondAttempt = checkForSecondAttempt(student, exam);

        if (secondAttempt = true) {
            System.out.println("It's your second attempt to take exam");
            return;
        }

        Map<String, String > studentAnswers = generateStudentAnswers(exam);
        Map<String, Map<String, String>> allAnswers = new HashMap<>();
        Map<String, String> grades = new HashMap<>();
        allAnswers.put(student.getId().toString(), studentAnswers);
        String grade = evaluateStudent(exam, studentAnswers);
        grades.put(student.getId().toString(), grade);
        dbService.updateStudentAnswers(exam, allAnswers, grades);
        List<String> studentGrades = student.getGrades();
        if (studentGrades == null){
            studentGrades = new ArrayList<>();
        }
        StringBuilder builder = new StringBuilder();
        String studentGrade  = builder.append(examName).append("->").append(grade).toString();
        studentGrades.add(studentGrade);
        dbService.updateStudentGrades(student, studentGrades);

        Statistic statistic = statisticsService.generateStatistic(exam);
        dbService.updateExamStatistics(exam, statistic);

    }

    Map <String, String> generateStudentAnswers(Exam exam){
        Map<String, String> answers = new HashMap<>();
        Map<String, String> questions = exam.getQuestions();
        Map<String, String> testAnswers = exam.getAnswersToChoose();
        for(Map.Entry<String, String> question : questions.entrySet()){
            System.out.println(question.getValue());
            System.out.println(testAnswers.get(question.getKey()));
            String answer = String.valueOf(random.nextInt(1,4));
            System.out.printf("MY ANSWER : %s %n", answer);
            answers.put(question.getKey(), answer);
        }
        return answers;
    }

    private String evaluateStudent( Exam exam, Map <String, String> studentAnswers){
        Map <String, String> rightAnswers = exam.getRightAnswers();
        int counter = 0;
        int totalQuestions = rightAnswers.size();
        for (Map.Entry<String, String> answer : rightAnswers.entrySet()){
            if(answer.getValue().equals(studentAnswers.get(answer.getKey()))){
                counter++;
            }
        }
        int grade = counter * 10 / totalQuestions;
        return String.valueOf(grade);
    }

    boolean checkForSecondAttempt (Student student, Exam exam ){
        Map <String, String> grades = exam.getGrades();
        boolean isSecondAttempt = false;
        for (Map.Entry<String, String> grade : grades.entrySet()){
            if (grade.getKey().equals(student.getId())){
                isSecondAttempt = true;
            }
        }
        return isSecondAttempt;

    }





    void showStudentGrades (Student student){
        try{
            student.getGrades().forEach(System.out::println);
        }catch (NullPointerException e){
            System.out.println("No grades in the system");
        }
    }


    void  showAllStudentGrades (){
        System.out.println("enter exam id");
        String examId = scanner.nextLine();

        try {
            Exam exam = dbService.getExamById(examId);
            Map<String, String> studentGrades = exam.getGrades();
            for (Map.Entry<String, String> grade : studentGrades.entrySet()){
                System.out.printf("%s grade: %s %n", grade.getKey(), grade.getValue());
            }
        } catch (NullPointerException e){
            System.out.println("No such exam or no student grades exist");
        }
    }

   void printExamStatistics (Exam exam){
        Statistic stat = exam.getExamStatistic();
        List<QuestionStatistic> questionStat = stat.getQuestionStatistics();
       System.out.printf( "Exam %s statistics: %n", exam.getExamName() );
       System.out.println("Grade average: " + stat.getGradeAverage());
       System.out.println("Highest grade: " + stat.getHighestGrade());
       System.out.println("Lowest grade: " + stat.getLowestGrade());
       System.out.println("Number of students who took exam: " + stat.getExamId());
       System.out.println(----------------------------------------------------------------------------);
       System.out.println("Exam question statistic:");
       questionStat.forEach(System.out::println);

   }

   void showExamStatistic (){
       System.out.println("Enter exam id");
       String examId = scanner.nextLine();
       Exam exam = dbService.getExamById(examId);
       printExamStatistics(exam);
   }


}
