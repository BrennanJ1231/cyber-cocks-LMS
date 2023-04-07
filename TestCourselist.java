import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TestCourselist {
 
    //if it is returning a cours list object rather than null.
@Test
public void testGetInstance(){
    CourseList clTest = CourseList.getInstance();
    assertNotNull(clTest);
}

//Sees if the course is adding to the list or not
@Test
public void addCourse(){
    ArrayList<Course> courses = new ArrayList<>();
    CourseList CL = new CourseList(); 
    CL.addCourse(new Course(null, null, null, null, null, null));
    assertEquals(1,CL.getAll().size());
}



@Test
public void testDeleteCourse() {
    CourseList CL = new CourseList();
    Course course = new Course("Test Course", "123", null, null, null, null);
    CL.addCourse(course);
    CL.deleteCourse(course);
    ArrayList<Course> allCourses = CL.getAll();
    Assertions.assertTrue(allCourses.isEmpty());
}

@Test
public void testGetAll(){

}

@Test 
public void testDeletingCourse(){

}

@Test
public void testSave(){
    
}

@Test
public Course testFindCourse(String name) {

}




}
