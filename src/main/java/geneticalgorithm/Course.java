package geneticalgorithm;

import com.ms.training.application.exception.BusinessException;

public class Course {


    //declaring variables

    private final int courseId;
    private final String courseCode;
    private final String course;
    private final int professorIds[];



    //initializing variables
    public Course(int courseId, String courseCode, String course, int professorIds[]) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.course = course;
        this.professorIds = professorIds;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return this.course;
    }

    public int getRandomProfessorId() {
        if (professorIds.length == 0) {
            return -1;
        }
        //randomizing
        int professorId = professorIds[(int) (professorIds.length * Math.random())];
        return professorId;
    }
    public int[] getProfessorIds() {
        return this.professorIds;
    }
}
