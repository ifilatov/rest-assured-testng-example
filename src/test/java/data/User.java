package data;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class User {

    private String first_name;
    private String last_name;
    private String userId;

    public User(String fname, String lname) {
        this.first_name = fname;
        this.last_name = lname;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ResponseSpecification getUserSpec(){
        ResponseSpecBuilder builder = new ResponseSpecBuilder ();
        builder.expectContentType(ContentType.JSON);
        builder.expectBody("first_name", equalTo(this.getFirstName()));
        builder.expectBody("last_name", equalTo(this.getLastName()));
        return builder.build ();
    }

}
