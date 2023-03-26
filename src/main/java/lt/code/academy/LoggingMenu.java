package lt.code.academy;

import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoggingMenu {

    Scanner scanner = new Scanner(System.in);
    MongoDBService dbService = new MongoDBService();

    void mainMenu(List<Student> students, List <Teacher> teachers){
        String action;
        do {
            System.out.println("""
                [1] -> Teacher login
                [2] -> Student login
                [0] -> Exit
                """);
            action = scanner.nextLine();
            mainMenuAction(action,students, teachers);
        }while (!action.equals("0"));
    }

    void mainMenuAction(String action, List<Student> students, List <Teacher> teachers){
        switch (action){
            case "1" -> System.out.println("teacher login");
            case "2" -> studentLogin(students, action );
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }

    void studentLogin (List<Student> students, String action){
        System.out.println("Enter your user name");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        Student student = dbService.getStudent(username, password);
        studentMenu(student);
    }

    void studentMenu( Student student){
        String action;
        do {
            System.out.println("""
                [1] -> Take Exam
                [2] -> Get your results
                [0] -> Exit
                """);
            action = scanner.nextLine();
            studentMenuAction(action, student);
        }while (!action.equals("0"));
    }

    void studentMenuAction(String action, Student student){
        switch (action){
            case "1"-> System.out.println("take exam");
            case "2"-> System.out.println("get grade");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }




}
