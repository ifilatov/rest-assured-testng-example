package common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.ITestContext;

public class TestBase {

    public String runId = String.valueOf(System.currentTimeMillis()).substring(5, 12);
    public String token;
    public String customProperty;

    @BeforeClass
    public void beforeClass(ITestContext context) {
        RestAssured.baseURI = context.getCurrentXmlTest().getParameter("BaseURI");
        customProperty = context.getCurrentXmlTest().getParameter("YourProperty");
        token = "123";//get token
    }

}
