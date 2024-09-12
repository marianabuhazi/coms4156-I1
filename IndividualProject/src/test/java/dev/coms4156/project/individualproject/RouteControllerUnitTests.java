package dev.coms4156.project.individualproject;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class contains all the unit tests for the RouteController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerUnitTests {

  @Autowired
  private MockMvc mockMvc;

  public static MyFileDatabase myFileDatabase;
  public static HashMap<String, Department> departmentMapping;

  @BeforeEach
  public void setupFileDatabaseForTesting() {
    myFileDatabase = new MyFileDatabase(0, "./data.txt");
    departmentMapping = myFileDatabase.getDepartmentMapping();
  }

  @Test
  void getWelcomeMessage() throws Exception {
    String message = 
        "Welcome, in order to make an API call direct your browser or Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";

    this.mockMvc.perform(get("/"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo(message)));
  }

  @Test
  void retrieveDepartmentCseeNotFound() throws Exception {
    this.mockMvc.perform(get("/retrieveDept").param("deptCode", "CSEE"))
      .andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void retrieveDepartmentComs() throws Exception {
    String expectedString = departmentMapping.get("COMS").toString();
    this.mockMvc.perform(get("/retrieveDept").param("deptCode", "COMS"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void retrieveCourseComs1004() throws Exception {
    HashMap<String, Course> coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    String expectedString = coursesMapping.get("1004").toString();
    this.mockMvc.perform(get("/retrieveCourse").param("deptCode", "COMS")
      .param("courseCode",  "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void retrieveCourseNotFound() throws Exception {
    this.mockMvc.perform(get("/retrieveCourse").param("deptCode", "COMS")
      .param("courseCode",  "6901")).andExpect(status().isNotFound())
      .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void retrieveCourseDepartmentNotFound() throws Exception {
    this.mockMvc.perform(get("/retrieveCourse").param("deptCode", "CSEE")
      .param("courseCode", "4823")).andExpect(status().isNotFound())
      .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void isCourseFullComs1004() throws Exception {
    HashMap<String, Course> coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    String expectedString = Boolean.toString(coursesMapping.get("1004").isCourseFull());
    this.mockMvc.perform(get("/isCourseFull").param("deptCode", "COMS")
      .param("courseCode", "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void isCourseFullDepartmentNotFound() throws Exception {
    this.mockMvc.perform(get("/isCourseFull").param("deptCode", "CSEE")
      .param("courseCode", "4823")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void isCourseFullCourseNotFound() throws Exception {
    this.mockMvc.perform(get("/isCourseFull").param("deptCode", "COMS")
      .param("courseCode", "6901")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void getMajorCtFromDeptComs() throws Exception {
    String expectedString = Integer.toString(departmentMapping.get("COMS").getNumberOfMajors());
    this.mockMvc.perform(get("/getMajorCountFromDept").param("deptCode", "COMS"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void getMajorCtFromDepartmentNotFound() throws Exception {
    this.mockMvc.perform(get("/getMajorCountFromDept").param("deptCode", "CSEE"))
      .andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void idDeptChairComs() throws Exception {
    String expectedString = departmentMapping.get("COMS").getDepartmentChair();
    this.mockMvc.perform(get("/idDeptChair").param("deptCode", "COMS"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void idDeptChairDepartmentNotFound() throws Exception {
    this.mockMvc.perform(get("/idDeptChair").param("deptCode", "CSEE"))
      .andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void findCourseLocationComs1004() throws Exception {
    HashMap<String, Course> coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    String expectedString = coursesMapping.get("1004").getCourseLocation();
    this.mockMvc.perform(get("/findCourseLocation").param("deptCode", "COMS")
      .param("courseCode",  "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void findCourseLocationCourseNotFound() throws Exception {
    this.mockMvc.perform(get("/findCourseLocation").param("deptCode", "COMS")
      .param("courseCode", "6901")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void findCourseInstructorComs1004() throws Exception {
    HashMap<String, Course> coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    String expectedString = coursesMapping.get("1004").getInstructorName();
    this.mockMvc.perform(get("/findCourseInstructor").param("deptCode", "COMS")
      .param("courseCode", "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void findCourseInstructorCourseNotFound() throws Exception {
    this.mockMvc.perform(get("/findCourseInstructor").param("deptCode", "COMS")
      .param("courseCode", "6901")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void findCourseTimeComs1004() throws Exception {
    HashMap<String, Course> coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    String expectedString = coursesMapping.get("1004").getCourseTimeSlot();
    this.mockMvc.perform(get("/findCourseTime").param("deptCode", "COMS")
      .param("courseCode", "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(expectedString)));
  }

  @Test
  void findCourseTimeCourseNotFound() throws Exception {
    this.mockMvc.perform(get("/findCourseTime").param("deptCode", "COMS")
      .param("courseCode", "6901")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void addMajorToDepartmentComs() throws Exception {
    this.mockMvc.perform(patch("/addMajorToDept").param("deptCode", "COMS"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo("Attribute was updated successfully.")));
  }

  @Test
  void addMajorToDepartmentNotFound() throws Exception {
    this.mockMvc.perform(patch("/addMajorToDept").param("deptCode", "CSEE"))
      .andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void removeMajorFromDepartmentComs() throws Exception {
    this.mockMvc.perform(patch("/removeMajorFromDept").param("deptCode", "COMS"))
      .andExpect(status().isOk())
        .andExpect(content().string(equalTo("Attribute was updated or is at minimum")));
  }

  @Test
  void removeMajorFromDepartmentNotFound() throws Exception {
    this.mockMvc.perform(patch("/removeMajorFromDept").param("deptCode", "CSEE"))
      .andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Department Not Found")));
  }

  @Test
  void dropStudentFromCourseComs() throws Exception {
    this.mockMvc.perform(patch("/dropStudentFromCourse").param("deptCode", "COMS")
      .param("courseCode", "1004")).andExpect(status().isOk())
        .andExpect(content().string(equalTo("Student has been dropped.")));
  }

  @Test
  void dropStudentFromCourseDepartmentNotFound() throws Exception {
    this.mockMvc.perform(patch("/dropStudentFromCourse").param("deptCode", "CSEE")
      .param("courseCode", "6901")).andExpect(status().isNotFound())
        .andExpect(content().string(equalTo("Course Not Found")));
  }

  @Test
  void setEnrollmentCountComs1004() throws Exception {
    this.mockMvc.perform(patch("/setEnrollmentCount").param("deptCode", "COMS")
      .param("courseCode", "1004").param("count", "100"))
        .andExpect(status().isOk())
          .andExpect(content().string(equalTo("Attribute was updated successfully.")));
  }

  @Test
  void setEnrollmentCountCourseNotFound() throws Exception {
    this.mockMvc.perform(patch("/setEnrollmentCount").param("deptCode", "CSEE")
      .param("courseCode", "6901").param("count", "100"))
        .andExpect(status().isNotFound())
          .andExpect(content().string(equalTo("Course Not Found")));
  }
}
