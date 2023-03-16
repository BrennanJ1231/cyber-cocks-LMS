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
        if(courses == null) 
            return;
        courses.add(course);
    }
    public ArrayList<Course> getAll(){
        return courses;
    }
    public void deleteCourse(Course course){
        
    }
    public void save(){

    }
    public void editCourse(Course course){

    }
}
