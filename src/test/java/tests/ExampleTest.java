package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import models.CareerRequestDetails;
import models.CareerResponseDetails;
import models.DBDetails;
import models.UserResponseDetails;
import org.testng.annotations.Test;
import util.A;
import util.ResponseUtil;
import util.RestAssuredWrapper;
import util.TestBase;

import java.util.HashMap;
import java.util.Map;

import static common.URI.*;
import static data.AssertionErrorMessages.*;
import static data.RequestComponents.*;


public class ExampleTest extends TestBase {

    @Test(description = "TC2", alwaysRun = true, priority = 1)
    public void testUserDetailsAreReturned() {
        RestAssured.baseURI = USER_DETAILS;
        RestAssured.basePath = "/todos/1";
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON);

        Response response = request.get();
        UserResponseDetails userResponseDetails = ResponseUtil.mapResponseToModel(response, UserResponseDetails.class);
        softAssert.assertEquals(userResponseDetails.getUserId(), ID, INVALID_ID);
        softAssert.assertEquals(userResponseDetails.getTitle(), TITLE, INVALID_TITLE);
        softAssert.assertEquals(response.statusCode(), 200, VALID_STATUS_CODE_NOT_FOUND);
        softAssert.assertAll();
    }

    @Test(description = "TC3", alwaysRun = true, priority = 2)
    public void testUserCareerDetailsAreReturned() {

        CareerRequestDetails careerRequestDetails = new CareerRequestDetails();
        careerRequestDetails.setName(USER_NAME);
        careerRequestDetails.setJob(USER_JOB);

        RestAssuredWrapper wrapper = new RestAssuredWrapper();
        wrapper.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        wrapper.setBody(careerRequestDetails);

        Response response = wrapper.post("/use");
        CareerResponseDetails careerResponseDetails = ResponseUtil.mapResponseToModel(response, CareerResponseDetails.class);
        softAssert.assertEquals(careerResponseDetails.getName(), USER_NAME, VALID_USER_NAME_NOT_FOUND);
        softAssert.assertEquals(careerResponseDetails.getJob(), USER_JOB, VALID_JOB_NOT_FOUND);
        softAssert.assertEquals(response.statusCode(), 201, VALID_STATUS_CODE_NOT_FOUND);
        softAssert.assertAll();
    }

    @Test(description = "TC3", alwaysRun = true, priority = 2)
    public void testUser() {
        Map<String, String> headers = new HashMap<>();
        headers.put(CONTENT_TYPE, APPLICATION_JSON);

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("", "");

        Response response = A.get(USER_DETAILS, "/todos/1", queryParameters, headers, "");
        ResponseBody body = response.getBody();
        UserResponseDetails responseBody = body.as(UserResponseDetails.class);
        softAssert.assertEquals(responseBody.getUserId(), ID, INVALID_ID);
        softAssert.assertEquals(responseBody.getTitle(), TITLE, INVALID_TITLE);
        softAssert.assertEquals(response.statusCode(), 200, VALID_STATUS_CODE_NOT_FOUND);
        softAssert.assertAll();
    }

    @Test(description = "TC3", alwaysRun = true, priority = 2)
    public void testa() {
        Map<String, String> headers = new HashMap<>();
        headers.put(CONTENT_TYPE, APPLICATION_JSON);

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("", "");

        CareerRequestDetails careerRequestDetails = new CareerRequestDetails();
        careerRequestDetails.setName(USER_NAME);
        careerRequestDetails.setJob(USER_JOB);

        Response response = A.post(REQRES_BASE_URI, "/users", queryParameters, headers, careerRequestDetails);
        ResponseBody body = response.getBody();
        CareerResponseDetails responseBody = body.as(CareerResponseDetails.class);
        softAssert.assertEquals(responseBody.getName(), USER_NAME, VALID_USER_NAME_NOT_FOUND);
        softAssert.assertEquals(responseBody.getJob(), USER_JOB, VALID_JOB_NOT_FOUND);
        softAssert.assertEquals(response.statusCode(), 201, VALID_STATUS_CODE_NOT_FOUND);
        softAssert.assertAll();
    }

    @Test
    public void testDbConnection() {
        DBDetails details = dbHelper.getDetails(1);
        System.out.println(details);
    }

}