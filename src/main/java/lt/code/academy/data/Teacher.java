package lt.code.academy.data;

import org.bson.types.ObjectId;

public class Teacher {

    private ObjectId id;
    private String TeacherUsername;
    private String TeacherName;
    private String TeacherSurname;
    private String TeacherPassword;

    public Teacher() {
    }

    public Teacher(ObjectId id, String teacherName, String teacherSurname) {
        this.id = id;
        TeacherName = teacherName;
        TeacherSurname = teacherSurname;
    }

    public Teacher(ObjectId id, String teacherUsername, String teacherName, String teacherSurname, String teacherPassword) {
        this.id = id;
        TeacherUsername = teacherUsername;
        TeacherName = teacherName;
        TeacherSurname = teacherSurname;
        TeacherPassword = teacherPassword;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTeacherUsername() {
        return TeacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        TeacherUsername = teacherUsername;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public String getTeacherSurname() {
        return TeacherSurname;
    }

    public String getTeacherPassword() {
        return TeacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        TeacherPassword = teacherPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", TeacherUsername='" + TeacherUsername + '\'' +
                ", TeacherName='" + TeacherName + '\'' +
                ", TeacherSurname='" + TeacherSurname + '\'' +
                ", TeacherPassword='" + TeacherPassword + '\'' +
                '}';
    }
}
