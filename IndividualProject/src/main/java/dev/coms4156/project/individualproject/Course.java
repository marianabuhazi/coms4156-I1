package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;

/** 
 * Represents a course within a department at an educational institution.
 * This class stores information about the course, including the instructor's name,
 * the location of the course, the time slot, and enrollment capacity.
 */
public class Course implements Serializable {

  @Serial
  private static final long serialVersionUID = 123456L;
  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String courseTimeSlot;
  private String instructorName;

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * @param instructorName     The name of the instructor teaching the course.
   * @param courseLocation     The location where the course is held.
   * @param timeSlot           The time slot of the course.
   * @param capacity           The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    this.courseLocation = courseLocation;
    this.instructorName = instructorName;
    this.courseTimeSlot = timeSlot;
    this.enrollmentCapacity = capacity;
    this.enrolledStudentCount = 0;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public boolean enrollStudent() {
    if (!isCourseFull()) {
      this.enrolledStudentCount++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public boolean dropStudent() {
    if (getEnrolledStudentCount() > 0) {
      this.enrolledStudentCount--;
      return true;
    } else {
      return false;
    } 
  }

  /**
   * Gets the location for the course.
   *
   * @return The course location.
   */
  public String getCourseLocation() {
    return this.courseLocation;
  }

  /**
   * Gets the instructor's name for the course.
   *
   * @return The instructor name.
   */
  public String getInstructorName() {
    return this.instructorName;
  }

  /**
   * Gets the time slot for the course.
   *
   * @return The time slot.
   */
  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }

  /**
   * Gets the enrolled student count for the course.
   *
   * @return The enrolled student count.
   */
  public int getEnrolledStudentCount() {
    return this.enrolledStudentCount;
  }

  /**
   * Sets the instructor name to a new instructor.
   *
   * @param newInstructorName   The new instructor name for the course
   */
  public void setInstructor(String newInstructorName) {
    this.instructorName = newInstructorName;
  }

  /**
   * Sets the course location to a new location.
   *
   * @param newLocation   The new location for the course
   */
  public void setLocation(String newLocation) {
    this.courseLocation = newLocation;
  }

  /**
   * Sets the course time slot to a new time slot.
   *
   * @param newTime   The new time slot for the course
   */
  public void setTime(String newTime) {
    this.courseTimeSlot = newTime;
  }

  /**
   * Sets the enrolled student count to the value provided.
   *
   * @param count   The enrolled student count.
   */
  public void setEnrolledStudentCount(int count) {
    this.enrolledStudentCount = count;
  }

  /**
   * Returns true if the course is full.
   *
   * @return true if the enrollment capacity is exceeded, false otherwise.
   */
  public boolean isCourseFull() {
    return enrolledStudentCount >= enrollmentCapacity;
  }

  /**
   * Returns a string representation of the course, including the instructor's name, 
   * course location, and time slot.
   *
   * @return A string representing the course.
   */
  public String toString() {
    return "\nInstructor: " + instructorName 
        +  "; Location: "  + courseLocation
        +  "; Time: " + courseTimeSlot;
  }
}
