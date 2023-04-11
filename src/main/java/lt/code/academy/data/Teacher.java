package lt.code.academy.data;

import org.bson.types.ObjectId;

public class Teacher {

    private ObjectId id;
    private String teacherUserName;
    private String teacherName;
    private String teacherSurname;
    private String teacherPassword;

    public Teacher() {
    }

    public Teacher(ObjectId id, String teacherName, String teacherSurname) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
    }

    public Teacher(String teacherUserName, String teacherName, String teacherSurname, String teacherPassword) {
        this.teacherUserName = teacherUserName;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherPassword = teacherPassword;
    }

    public Teacher(ObjectId id, String teacherUserName, String teacherName, String teacherSurname, String teacherPassword) {
        this.id = id;
        this.teacherUserName = teacherUserName;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherPassword = teacherPassword;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTeacherUserName() {
        return teacherUserName;
    }

    public void setTeacherUserName(String teacherUserName) {
        this.teacherUserName = teacherUserName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", teacherUserName='" + teacherUserName + '\'' + ", teacherName='" + teacherName + '\'' + ", teacherSurname='" + teacherSurname + '\'' + ", teacherPassword='" + teacherPassword + '\'' + '}';
    }
}
