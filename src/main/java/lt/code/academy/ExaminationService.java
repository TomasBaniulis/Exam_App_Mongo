package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.javafaker.Faker;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExaminationService {

    Scanner scanner;
    Faker faker;
    MongoDBService dbService;
    Random random;

    public ExaminationService(Scanner scanner, Faker faker, MongoDBService dbService, Random random) {
        this.scanner = scanner;
        this.faker = faker;
        this.dbService = dbService;
        this.random = random;
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
            String answer = String.valueOf(random.nextInt(0,3));
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
        Map<String, String > studentAnswers = generateStudentAnswers(exam);
        Map<String, Map<String, String>> allAnswers = new HashMap<>();
        Map<String, String> grades = new HashMap<>();
        allAnswers.put(student.getId().toString(), studentAnswers);
        String grade = evaluateStudent(exam, studentAnswers);
        grades.put(student.getId().toString(), grade);
        dbService.updateStudentAnswers(exam, allAnswers, grades);
    }

    Map <String, String> generateStudentAnswers(Exam exam){
        Map<String, String> answers = new HashMap<>();
        Map<String, String> questions = exam.getQuestions();
        Map<String, String> testAnswers = exam.getAnswersToChoose();
        for(Map.Entry<String, String> question : questions.entrySet()){
            System.out.println(question.getValue());
            System.out.println(testAnswers.get(question.getKey()));
            String answer = String.valueOf(random.nextInt(1,3));
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



}
