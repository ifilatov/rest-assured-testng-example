package suites.users;

import data.User;

import io.restassured.http.*;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import common.TestBase;

public class CRUD extends TestBase {

    private User currentTestUser;

    @BeforeClass
    public void setUp() {
        RestAssured.basePath = "api/users";
        Reporter.log("Set Up", true);
        currentTestUser = new User("Janet","Weaver");
    }

    @Test
    public void createUser() {
        Reporter.log("Create User",true);
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        String userId =
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(currentTestUser).
//                log request body
//                log().body().
        when().
                post().
        then().
//                log full response (headers + body)
//                log().all().
                statusCode(201).
                spec(userSpec).
        extract().
                path("id").toString();
        currentTestUser.setUserId(userId);
        Reporter.log("user id = "+currentTestUser.getUserId(), true);
    }

    @Test
    public void readUser() {
        Reporter.log("Read User",true);

        given().
        when().
                get("/2").
        then().
                statusCode(200).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver"));

    }

    @Test
    public void updateUser() {
        Reporter.log("Update User", true);
        currentTestUser.setFirstName("TestEdited");
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(currentTestUser).
        when().
                put("/2").
        then().
                statusCode(200).
                spec(userSpec);
    }

    @Test
    public void deleteUser() {
        Reporter.log("Delete User", true);
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        given().
        when().
                delete("/"+currentTestUser.getUserId()).
        then().
                statusCode(204);
    }

}
