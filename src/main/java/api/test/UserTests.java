package api.test;
import api.endpoints.User.UserEndPoints;
import api.paylouds.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;


public class UserTests {

    Faker faker = new Faker();
    User userPayload = new User();
    public Logger logger;
    ;

    @BeforeClass
    public void setupData() {

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Logs

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {

        logger.info("******Creating user **********");
        Response response = UserEndPoints.createUser(userPayload);

        response.then().log().all();

        int statusCode = response.getStatusCode();
        System.out.println("Statuscode: " + statusCode);
        Assert.assertEquals(statusCode, 200);


    }

    @Test(priority = 2)
    public void testGetUserByName() {

        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), (200));

    }

    @Test(priority = 3)
    public void testUpdateUser() {

        //update data using payload
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().all();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, (200));

        //Checking data after update
//        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
//        Assert.assertEquals(responseAfterUpdate.getStatusCode(), (200));

        // Response should be JSON with code, type, message
        String jsonResponse = response.getBody().asString();
        System.out.println("Response Body:");
        System.out.println(jsonResponse);

        // Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

        // Deserialize response to User object after update
        User userAfterUpdate = responseAfterUpdate.as(User.class);

        // Assert that the updated fields match the payload
        Assert.assertEquals(userAfterUpdate.getLastName(), userPayload.getLastName(),
                "Last name doesn't match after update");
        Assert.assertEquals(userAfterUpdate.getEmail(), userPayload.getEmail(),
                "Email doesn't match after update");


    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

    }


}
