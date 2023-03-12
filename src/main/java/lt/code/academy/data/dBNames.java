package lt.code.academy.data;

public enum dBNames {

    DB_NAME ("exam_app"),
    TEACHERS_COLLECTION ("teachers"),
    STUDENTS_COLLECTION ("students"),
    EXAM_COLLECTION ("exams");

    private final String string;

    dBNames(String string) {this.string = string;}
}
