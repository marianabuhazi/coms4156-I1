# BUG ANALYSIS

## Course
**Improper initialization**: (line 34) the enrolledStudentCount was originally set to 500, when the Javadoc says that it must be initially at 0. I discovered this myself and fixed.

**Missing conditional check**: (line 43) the enrollStudent() method only incremented the enrolledStudentCount by one but did not first check if the course was full. I found this bug myself and fixed by using the isCourseFull() method before increasing the count.

**Missing conditional check**: (line 57) the dropStudent() mehtod decremented the student count without first checking if there were any students enrolled in the class. I used the getEnrolledStudentCount() method to check that the enrollment count is greater than 0 before attempting to decrease this count.

**Improper return**: (line 71) the getCourseLocation() method returned the instructorName instead of courseLocation. I have manually found and fixed this by returning the proper private variable.

**Improper return**: (line 80) the getInstructorName() method returned the courseLocation instead of instructorName. I have manually found and fixed this by returning the proper private variable.
 
**Functionality**: (line 143) modified the isCourseFull() method to "enrolledStudentCount >= enrollmentCapacity" because it attempts to check whether the current enrollment is over the capacity not the other way around.

## Department

**Functionality**: (line 36) removed the - sign from in front of the "this.numberOfMajors" as it would return a negative value. The number of majors should be a non-negative number. I found this manually and fixed it.

**Functionality**: (line 46) removed the quotation marks from the this.departmentChair. While the method should return a string, it should be the actual value name of the chair. I found this manually and fixed.

**Functionality**: (line 113) removed the quotation marks from the result.toString(). While the method should return a string, it should be the actual method. --> return result.toString(); I found this manually and fixed.

## MyFileDatabase

**ConstructorCallsOverridableMethod**: (line 26) the static analyzer detected that this constructor should not be making calls to overridable methods. What I did to fix it was I made the deSerializeObjectFromFile method static and I passed in the filepath string.

**Return a valid object**: (line 54) the static analyzer proposed that instead of returning null which could cause an exception here, an empty object be returned instead. I returned a new HashMap<String, Department>().

## RouteController

**Multiple instances ofthe same string**: (line 18) the static analyzer detected that I was using the "Department Not Found" and "Course Not Found" and "Attribute was updated successfully." too many times. Instead, I decided to make final String variables for each of these and reused them throughout the code. However, the bug analyzer also proposed that I was using "deptCode" and "courseCode" too much. It was not easy to fix this because this is used for the route itself. Therefore, if we run PMD, this error will still remain. I hope the TAs can exclude it, because it's definitely a design choice and I don't think it causes real bugs.

**Locale**: (line 52) the bug analyzer proposed that when using .ToUpperCase() and .ToLowerCase(), I include the Locale.US specification. I have fixed this.

Additionally, there were various instances throughout this page where the incorrect HTTP status was sent. For example, at times we expected OK status, but got FORBIDDEN, or NOT_FOUND. I have updated every instance of this. 

Furthermore, I have removed all the strings surrounding the reponses coming from the database. This is a design choice, but I believe each response should just include the answer (e.g., location, department chair name, etc) rather than including so much information around it.