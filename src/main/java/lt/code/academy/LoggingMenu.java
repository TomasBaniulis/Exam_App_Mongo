package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LoggingMenu {

    List<Student> students;
    List<Teacher> teachers;

    public LoggingMenu(List<Student> students, List<Teacher> teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    Faker faker = new Faker();

    MongoDBService dbService = new MongoDBService();

    Scanner scanner = new Scanner(System.in);

    Random random = new Random();

    StatisticsService statisticsService = new StatisticsService();

    ExaminationService examinationService = new ExaminationService(scanner, faker, random, statisticsService);

    void mainMenu(List<Student> students, List<Teacher> teachers) {
        String action;
        do {
            System.out.println("""
                    [1] -> Teacher login
                    [2] -> Student login
                    [0] -> Exit
                    """);
            action = scanner.nextLine();
            mainMenuAction(action, students, teachers);
        } while (!action.equals("0"));
    }

    void mainMenuAction(String action, List<Student> students, List<Teacher> teachers) {
        switch (action) {
            case "1" -> teacherLogin();
            case "2" -> studentLogin();
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }

    void studentLogin() {
        System.out.println("Enter your user name");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        Student student = dbService.getStudent(username, password);
        if (student == null) {
            System.out.println("No such user name or wrong password");
            return;
        }
        System.out.printf("Welcome %s %s %n", student.getStudentName(), student.getStudentSurname());
        studentMenu(student);
    }

    void studentMenu(Student student) {
        String action;
        do {
            System.out.println("""
                    [1] -> Take Exam
                    [2] -> Get your results
                    [0] -> Exit
                    """);
            action = scanner.nextLine();
            studentMenuAction(action, student);
        } while (!action.equals("0"));
    }

    void studentMenuAction(String action, Student student) {
        switch (action) {
            case "1" -> examinationService.takeExam(student);
            case "2" -> examinationService.showStudentGrades(student);
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }

    void teacherLogin() {
        System.out.println("Enter your user name");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        Teacher teacher = dbService.getTeacher(username, password);
        if (teacher == null) {
            System.out.println("No such user name or wrong password");
            return;
        }
        System.out.printf("Welcome %s %s %n", teacher.getTeacherName(), teacher.getTeacherSurname());

        teacherMenu(teacher);
    }

    void teacherMenu(Teacher teacher) {
        String action;
        do {
            System.out.println("""
                    [1] -> Create exam
                    [2] -> Update exam question
                    [3] -> Get student result
                    [4] -> Print exam list
                    [5] -> Exam statistics
                    [0] -> Exit
                    """);
            action = scanner.nextLine();
            teacherMenuAction(action, teacher);
        } while (!action.equals("0"));
    }

    void teacherMenuAction(String action, Teacher teacher) {
        switch (action) {
            case "1" -> examinationService.generateExam(teacher);
            case "2" -> examinationService.update(teacher);
            case "3" -> examinationService.showAllStudentGrades(students);
            case "4" -> dbService.showAllExams();
            case "5" -> examinationService.showExamStatistic();
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
}
