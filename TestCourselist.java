import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Cody Hawkins
//{"comments":[{"comments":[],"author":"89253f65-4606-4f10-93a1-0a5bc9966247","datePosted":"11\/09\/2022","content":"fun"}],"name":"intro to python","rating":0.0,"description":"python introduction","language":"PYTHON","UUID":"d9057ce7-36c9-4080-94fc-77235f400c4a","modules":[{"material":[{"name":"for loops","content":"for loops"}],"assignment":[{"name":"for loops","questions":[{"name":"what is a for loop","choices":["a loop","not a loop"],"correctAnswer":"a loop"}]}],"description":"for and while loops","title":"loops"}]}]
public class TestCourselist {

    ArrayList<Course> courses =new ArrayList<>();
    CourseList CL = new CourseList(); 
    Course courseT;
    UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void setup() {
        courseT = new Course(null, null, null,uuid, null, null);
        CL.addCourse(courseT);
    }   
 
//if it is returning a cours list object rather than null.
@Test
public void testGetInstance(){
    CourseList clTest = CourseList.getInstance();
    assertNotNull(clTest);
}

//Sees if the course is adding to the list or not
@Test
public void addCourse(){
    assertEquals(1,CL.getAll().size());
}

//Testing to see if it returns courses instaed of null
@Test
public void testGetAll(){
assertNotNull(CL.getAll());
}

//testing to see if the course we add gets deleted 
@Test 
public void testDeletingCourse(){
CL.deleteCourse(courseT);
assertEquals(0, CL.getAll().size());
}

//This makses sure you can save and load from the JSON file
@Test
public void testSave(){
    DataWriter.saveCourse();
    ArrayList <Course> courseTest = DataLoader.loadCourses();
    assertNotNull(courseTest);
}

//Test if the exact data added tothe JSON fileis saved 
@Test
public void testSaveData(){
    int jFileSize = DataLoader.loadCourses().size();
    DataWriter.saveCourse();
    assertNotEquals(jFileSize, DataLoader.loadCourses().size());

}

//Test to see if you can find a course by a name
@Test
public void testFindCourse() {
assertNotNull(CL.findCourse("intro to python"));;
}




}
