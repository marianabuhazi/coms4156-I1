package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains all the unit tests for the Course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  /** The test course instance used for testing. */
  public static Course coms1004;

  @BeforeEach
  public void setupCourseForTesting() {
    coms1004 = new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400);
    coms1004.setEnrolledStudentCount(249);
  }

  @Test
  public void getToString() {
    assertEquals("\nInstructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55", 
        coms1004.toString());
  }

  @Test
  public void enrollStudentSuceeds() {
    assertEquals(true, coms1004.enrollStudent());
    assertEquals(250, coms1004.getEnrolledStudentCount());
  }

  @Test
  public void enrollStudentFails() {
    coms1004.setEnrolledStudentCount(400);
    assertEquals(false, coms1004.enrollStudent());
  }

  @Test
  public void dropStudentSuceeds() {
    assertEquals(true, coms1004.dropStudent());
    assertEquals(248, coms1004.getEnrolledStudentCount());
  }

  @Test
  public void dropStudentFails() {
    coms1004.setEnrolledStudentCount(0);
    assertEquals(false, coms1004.dropStudent());
  }

  @Test
  public void getCourseLocation() {
    assertEquals("417 IAB", coms1004.getCourseLocation());
  }

  @Test
  public void getInstructorName() {
    assertEquals("Adam Cannon", coms1004.getInstructorName());
  }

  @Test
  public void getCourseTimeSlot() {
    assertEquals("11:40-12:55", coms1004.getCourseTimeSlot());
  }

  @Test
  public void setInstructor() {
    coms1004.setInstructor("Brian Borowski");
    assertEquals("Brian Borowski", coms1004.getInstructorName());
  }

  @Test
  public void setLocation() {
    coms1004.setLocation("301 URIS");
    assertEquals("301 URIS", coms1004.getCourseLocation());
  }

  @Test
  public void setTime() {
    coms1004.setTime("4:10-5:25");
    assertEquals("4:10-5:25", coms1004.getCourseTimeSlot());
  }
}