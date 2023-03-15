import java.util.ArrayList;

public class CourseList {
    
    private ArrayList <Course> courses;
    private static CourseList courseList;

    private CourseList() {}

    public static CourseList getInstance() {
		if (courseList == null) {
			courseList = new CourseList();
		}
		return courseList;
	}

    public void addCourse(Course course){

    }
    public void getAll(courses){

    }
    public void deleteCourse(Course course){

    }
    public void save(){

    }
    public void editCourse(Course course){

    }
}
