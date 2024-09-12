# Citations
For this assignment I used the official [Java documentation](https://docs.oracle.com/en/java/),  the [PMD documentation](https://pmd.github.io/pmd/index.html), the [Junit documentation](https://junit.org/junit5/docs/5.0.2/api/overview-summary.html) and the [MockMVC documents](https://docs.spring.io/spring-framework/reference/overview.html).

I did not reference any online threads nor any artificial intelligence.

## Running static bug analyzer
In the IndividualProject directory run: `mvn pmd:check` after running the setup command.

NOTE: This will return 2 violations! Read below in the RouteController section, as to why I didn't fix them and don't think they should count as violations.

## Running unit tests
In the IndividualProject directory run: `mvn clean test` after running the setup command.

## Checking the code coverage with JaCoCo
In the IndividualProject directory run: `mvn jacoco:report` after running the setup command and running unit tests. **The branch coverage is 73%.**