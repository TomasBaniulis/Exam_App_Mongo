package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

public class StudentsAndTeachersService {

    Faker faker;
    MongoDBService dBService;

    public StudentsAndTeachersService(Faker faker, MongoDBService dBService) {
        this.faker = faker;
        this.dBService = dBService;
    }

    void generateStudents (int numberOfStudents){
        int counter = 1;
        for (int i = 0; i<numberOfStudents; i++){
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String username = String.valueOf(counter);
            String password = "alio";
            counter ++;
            dBService.insertStudents(new Student(name, surname, username,password ));
        }
    }

    void generateTeachers (int numberOfTeachers){
        int counter = 1;
        for (int i = 0; i<numberOfTeachers; i++){
            String userName = String.valueOf(counter);
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String password = "alio";
            counter++;
            dBService.insertTeachers(new Teacher(userName, name, surname, password));
        }
    }



}
