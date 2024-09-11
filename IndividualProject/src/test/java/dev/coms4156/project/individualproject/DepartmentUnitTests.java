package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains all the unit tests for the Department class.
 */
public class DepartmentUnitTests {

  /** The test department and courses instances used for testing. */
  public static Department compSci;
  public static HashMap<String, Course> courses;

  /** The setup data used for testing. */
  @BeforeEach
  public void setupDepartmentForTesting() {
    Course coms1004 = new Course("Adam Cannon", "11:40-12:55", "417 IAB", 400);
    coms1004.setEnrolledStudentCount(249);
    courses = new HashMap<>();
    courses.put("1004", coms1004);
    compSci = new Department("COMS", courses, "Luca Carloni", 2700);
  }
  
  @Test
  public void getNumberOfMajors() {
    assertEquals(2700, compSci.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChair() {
    assertEquals("Luca Carloni", compSci.getDepartmentChair());
  }

  @Test
  public void getCourseSelection() {
    assertEquals(courses, compSci.getCourseSelection());
  }

  @Test
  public void addPersonToMajor() {
    compSci.addPersonToMajor();
    assertEquals(2701, compSci.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajor() {
    compSci.dropPersonFromMajor();
    assertEquals(2699, compSci.getNumberOfMajors());
  }

  @Test
  public void addCourse() {
    Course coms3134 = new Course("Brian Borowski", 
        "301 URIS", "4:10-5:25", 250);
    coms3134.setEnrolledStudentCount(242);
    compSci.addCourse("3134", coms3134);
    assertEquals(true, courses.containsKey("3134"));
  }

  @Test
  public void createCourse() {
    compSci.createCourse("3157", "Jae Lee", "417 IAB", "4:10-5:25", 400);
    assertEquals(true, courses.containsKey("3157"));
  }

  @Test
  public void getToString() {
    assertEquals("COMS 1004: \nInstructor: Adam Cannon; Location: 11:40-12:55; Time: 417 IAB\n", 
        compSci.toString());
  }
}
