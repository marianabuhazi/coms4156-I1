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

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentSuceedsTest() {
    boolean expectedResult = true;
    int expectedEnrolledStudentCount = 1;
    assertEquals(expectedResult, testCourse.enrollStudent());
    assertEquals(expectedEnrolledStudentCount, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void enrollStudentFailsTest() {
    boolean expectedResult = false;
    testCourse.setEnrolledStudentCount(250);
    assertEquals(expectedResult, testCourse.enrollStudent());
  }

  @Test
  public void dropStudentSuceedsTest() {
      boolean expectedResult = true;
      int expectedEnrolledStudentCount = 0;
      testCourse.setEnrolledStudentCount(1);
      assertEquals(expectedResult, testCourse.dropStudent());
      assertEquals(expectedEnrolledStudentCount, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void dropStudentFailsTest() {
      boolean expectedResult = false;
      testCourse.setEnrolledStudentCount(0);
      assertEquals(expectedResult, testCourse.dropStudent());
  }

  @Test
  public void getCourseLocationTest() {
      String expectedResult = "417 IAB";
      assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
      String expectedResult = "Griffin Newbold";
      assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
      String expectedResult = "11:40-12:55";
      assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void setInstructor() {
      String expectedResult = "Marian Abuhazi";
      testCourse.setInstructor("Marian Abuhazi");
      assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void setLocation() {
      String expectedResult = "Mudd 833";
      testCourse.setLocation("Mudd 833");
      assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void setTime() {
      String expectedResult = "10:10-11:25";
      testCourse.setTime("10:10-11:25");
      assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}