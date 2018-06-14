package suites.users;

import data.User;

import io.restassured.http.*;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;

import common.TestBase;

public class Users extends TestBase {

    private User currentTestUser;

    @BeforeClass
    public void setUp() {
        RestAssured.basePath = "v1/customers/foundrytest/users";
        Reporter.log("Set Up", true);
        currentTestUser = new User(this.runId);
    }

    @Test
    public void createUser() {
        Reporter.log("Create User",true);
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        String userId =
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer "+this.token).
                body(currentTestUser).
                log().
                body().
        when().
                post().
        then().
                log().all().
                spec(userSpec).
        extract().
                path("data.userId").toString();
        Reporter.log(currentTestUser.getUsername());
        currentTestUser.setUserId(userId);
    }

    @Test
    public void readUser() {
        Reporter.log("Read User",true);
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        given().
                header("Authorization", "Bearer "+this.token).
        when().
                get("/"+currentTestUser.getUserId()).
        then().
                spec(userSpec);
    }

    @Test
    public void updateUser() {
        Reporter.log("Update User", true);
        currentTestUser.setFirstName("TestEdited");
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer "+this.token).
                body(currentTestUser).
        when().
                put("/"+currentTestUser.getUserId()).
        then().
                spec(userSpec);
    }

    @Test
    public void deleteUser() {
        Reporter.log("Delete User", true);
        currentTestUser.setActive(false);
        ResponseSpecification userSpec = currentTestUser.getUserSpec();

        given().
                header("Authorization", "Bearer "+this.token).
        when().
                delete("/"+currentTestUser.getUserId()).
        then().
                spec(userSpec);
    }

}
