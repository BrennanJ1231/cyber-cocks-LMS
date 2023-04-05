import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


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


}
