import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    
    private ArrayList <Course> courses;
    private static CourseList courseList;

    public CourseList() {
        courses = DataLoader.loadCourses();
    }

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
        for(int i = 0; i <courses.size(); i++) {
            if(courses.get(i).uuid.equals(course.uuid)) {
                courses.remove(i);
            }
        }
    }
    public void save(){
        DataWriter.saveCourse(DataWriter.COURSES_FILE_NAME);
    }
}
