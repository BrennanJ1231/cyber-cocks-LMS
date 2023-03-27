import java.util.ArrayList;
import java.util.UUID;

public class CourseList {

/*
 * instance var
 */
private ArrayList<Course> courses;
private static CourseList courseList;

public CourseList(){
    courses = DataLoader.loadCourses();
}

public static CourseList getInstance(){
    if(courseList == null){
        courseList = new CourseList();
    }
    return courseList;
}

/*
 * Adds a course 
 * @parm course of type Course
 */
public void addCourse(Course course){
    if(courses == null) 
            return;
        courses.add(course);
}

/*
 * gets all the courses
 */
public ArrayList<Course> getAll(){
    return courses;
}
/*
 * deletes a course
 * @parm course of type Course
 */
public void deleteCourse(Course course){
    for(int i = 0; i <courses.size(); i++) {
        if(courses.get(i).uuid.equals(course.uuid)) {
            courses.remove(i);
        }
    }
}

/*
 * Saves a course
 */
public void save(){
    DataWriter.saveCourse(DataWriter.COURSES_FILE_NAME);
}

/*
 * find a course 
 * @returns a Course
 * @parm a String name
 */
public Course findCourse(String name) {
    for (int i=0; i<courses.size();i++) {
        if (courses.get(i).name.equalsIgnoreCase(name)) {
            return courses.get(i);
        }
    }
    return null;

}
}

