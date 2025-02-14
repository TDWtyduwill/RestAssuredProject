package api.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.store.storeEndpoints;

import java.util.logging.Logger;

public class storeTests {


    @Test
    public void createOrderForStoreAnsVerify(){


        // Creating order
        Response response = storeEndpoints.createOrder();

        response.then().log().all();

        int statusCode = response.getStatusCode();
        System.out.println("Statuscode: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //Verify status

    }


}
