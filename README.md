# Employee DB 
    
## Introduction
You have been hired by a large staffing agency (Coders4Hire) to make modifications to their existing employee database application.  Right now the application holds some basic information on the employee, but it doesn't have any information on their work history. The current fields that it keeps track of are:
* name
* address
* city
* state
* zip
* phone number
* job title
* date hired

The application you'll be working on is the REST API that manages requests for information from the database.  There is a separate web applciation that calls this API, but there's another team working on that.

## The Ask...
Coders4Hire would like you to modify the API so that it can track work history for each employee.

The formal stories and acceptance criteria are shown below.  But the main thinking is that there will be a new work history entity that will have a one to many relationship with the employee entity that already exists.

![Employee-Job Relationship](images/Employee-WorkHistory.png)

### Story and Acceptance Criteria

```cucumber
As an API client 
I need to be able to perform CRUD operations on a Job table that has a one to many relationship with Employees 
so that I can track an employee's job history.

Given that an employee with EmpID==1 
When a post call is made to http://localhost:8080/job/add/
where the payload includes {jobID:2, EmpID:1, Customer:"Cust1", StartDate:"2018-10-25", EndDate:"2018-11-16"}
Then I should receive a 200 message and the new job should be serialized to the database.

Given that an employee with EmpID==1 and a job with JobID==2
When a get call is made to http://localhost:8080/job/get/2
Then I should receive a 200 message and a payload that includes {jobID:2, EmpID:1, Customer:"Cust1", StartDate:"2018-10-25", EndDate:"2018-11-16"}

Given that an employee with EmpID==1 and a job with JobID==1
When a put call is made to http://localhost:8080/job/put/2
where the payload includes {jobID:2, Customer:"Cust2"}
Then I should receive a 200 message and a payload that includes {jobID:2, EmpID:1, Customer:"Cust2", StartDate:"2018-10-25", EndDate:"2018-11-16"}

```

## How to submit your work
* Push to your cloned github repository.
* Make a pull request back to the original master.


## Useful APIs

   Application Health: [https://employeebackendservice.cfapps.io/actuator/health](https://employeebackendservice.cfapps.io/actuator/health)
    
   Application Swagger Doc: [https://employeebackendservice.cfapps.io/swagger-ui.html](https://employeebackendservice.cfapps.io/swagger-ui.html)
   
### Operations

   Get All Employees: [https://employeebackendservice.cfapps.io/employees/all](https://employeebackendservice.cfapps.io/employees/all)
      
    
# Swagger UI for REST APIs

   [Spring Boot RESTful API Documentation with Swagger 2](https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/)
    
    //Swagger Config
    compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.8.0'
    compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.8.0'
    

    //Create Config java file with below configuration
    
    package com.employeedb.Config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
    import springfox.documentation.builders.ApiInfoBuilder;
    import springfox.documentation.builders.RequestHandlerSelectors;
    import springfox.documentation.service.ApiInfo;
    import springfox.documentation.service.Contact;
    import springfox.documentation.spi.DocumentationType;
    import springfox.documentation.spring.web.plugins.Docket;
    import springfox.documentation.swagger2.annotations.EnableSwagger2;

    import static springfox.documentation.builders.PathSelectors.regex;

    //This class has more than one Bean.
    @Configuration
    //enables Swagger support in the class
    @EnableSwagger2
    public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * http://localhost:8081/swagger-ui.html#
     * <p>
     * The select() method called on the Docket bean instance returns an ApiSelectorBuilder,
     * which provides the apis() and paths() methods to filter the controllers and methods being documented using String predicates.
     * In the code, the RequestHandlerSelectors.basePackage predicate matches the com.galvanize.employeedb.Controllers base package to          filter the API.
     * The regex parameter passed to paths() acts as an additional filter to generate documentation only for the path starting with             /product.
     *
     * @return
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.galvanize.employeedb.Controllers"))
                .paths(regex("/employee.*"))
                .build()
                .apiInfo(metaData());

    }

    /**
     * metaData() method that returns and ApiInfo object initialized with information about our API.
     *
     * @return
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Employee records documentation")
                .description("\"Employee records REST APIs\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Swagger 2 documentation", "https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/", "john@springfrmework.guru"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
    
# Pivotal Cloud Foundry (Paas) - Deploy Service to the Cloud platform

## references

   [Deploying Spring Boot + MySQL Application to PCF](https://www.javainuse.com/pcf/pcf-sql)

   [Spring Boot Application + MySQL + PCF](https://dzone.com/articles/spring-boot-20-microservice-deployment-in-pivotal)
    
## Steps: 
 
 ### PCF
 
   1.  Check out the 1st commit of this Service.
   
   2.  Log IN to PCF.
   
   3.  Create Space.
   
   4.  Create User Service and Bind it that helps to create DB connection from PCF to local or whatever DB destination. If you can not do it from terminal then you can also do this step in PCF tool.
       - Select ClearDB MySql 
   
 ### Service (Implementation)
  
   1.  Build simple and small application with very few configuration. make sure DB is also connected.
   
   2.  Create manifest.yml file with very few details. This is require since menifest file gets treated as blueprint of the application deployment in production environment.
            
                    name: SpringBootMySqlPersonal
                    path: build/libs/employeedb-0.0.1-SNAPSHOT.jar
                    memory: 800M
                    services:
                            - mysqldb
                            
   3.   Have all DB credentials and rest of the properties in application.properties file
        
                        spring.datasource.url=jdbc:mysql://localhost/avengers_gmdb?serverTimezone=UTC
                        #spring.datasource.url=jdbc:mysql://localhost/bootdb?serverTimezone=UTC
                        spring.datasource.username=root
                        spring.datasource.password=
                        spring.datasource.initialize=false
                        spring.jpa.hibernate.ddl-auto=update
                        spring.jpa.generate-ddl=true
                        spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
                        spring.datasource.platform=mysql
                        spring.datasource.initialization-mode=always
                        
   4.    Make sure to run this command in Application terminal to bring jar file to run in production. Later this jar file will be use in manifest.yml/path as mentioned above. This will make PCF fetch the jar file of the application which will get executed in Production live:  
    
                gradlew clean build --refresh-dependencies
   
   5.   Make sure to Run the application locally healthy and all tests pass and then push it PCF.

   6.   In terminal in application do: cf login
   
   7.   cf push <application name>
                        
                        
  # Health Check  
  
  build.gradle
  
    //This will show us Health of our Application and DB connections
    compile("org.springframework.boot:spring-boot-starter-actuator")
  
   ## To show detailed Health check
  
    management.endpoint.health.show-details=always
    
 
