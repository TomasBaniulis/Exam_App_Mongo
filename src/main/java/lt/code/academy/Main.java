package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        Faker faker = new Faker();
        MongoDBService dBService = new MongoDBService();
        StudentsAndTeachersService studentsAndTeachersService = new StudentsAndTeachersService(faker, dBService);

//        studentsAndTeachersService.generateStudents(10);
//        studentsAndTeachersService.generateTeachers(10);

        List<Student> students = dBService.getStudentList();
        List<Teacher> teachers = dBService.getTeacherList();

//        students.forEach(System.out::println);
//        teachers.forEach(System.out::println);

        LoggingMenu menu = new LoggingMenu(students, teachers);

        menu.mainMenu(students, teachers);

    }

}
