package data;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class User {

    private Boolean active;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String userId;

    public User(String id) {

        this.active = true;
        this.firstName = "Auto";
        this.lastName = "APITest"+id;
        this.email = "aapitest"+id+"@test.com";
        this.username = "apitest"+id;

    }

    public Boolean getActive() {
        return this.active;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ResponseSpecification getUserSpec(){
        ResponseSpecBuilder builder = new ResponseSpecBuilder ();
        builder.expectContentType(ContentType.JSON);
        builder.expectStatusCode(200);
        builder.expectBody("data.active", equalTo(this.getActive()));
        builder.expectBody("data.firstName", equalTo(this.getFirstName()));
        builder.expectBody("data.lastName", equalTo(this.getLastName()));
        builder.expectBody("data.email", equalTo(this.getEmail()));
        builder.expectBody("data.username", equalTo(this.getUsername()));
        return builder.build ();
    }

}
