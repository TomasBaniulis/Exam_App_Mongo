package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Student;

import java.util.List;

public class Main {



    public static void main(String[] args) {

        Faker faker = new Faker();
        MongoDBService dBService = new MongoDBService();
        StudentsAndTeachersService studentsAndTeachersService = new StudentsAndTeachersService(faker, dBService);

        studentsAndTeachersService.generateStudents(10);
        studentsAndTeachersService.generateTeachers(10);

        List<Student> students = dBService.getStudentList();

        students.forEach(System.out::println);

    }

}
