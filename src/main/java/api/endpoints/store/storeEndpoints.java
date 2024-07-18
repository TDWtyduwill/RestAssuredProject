package api.endpoints.store;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class storeEndpoints {


    public static Response createOrder() {


        Response response = given()
                .contentType("application/json")
                .body("{ \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2024-07-16T15:53:08.426Z\", \"status\": \"shipped\", \"complete\": true }")
                .when()
                .post(RoutesPetStore.post_url);

        return response;

    }

    public static  Response getOrderInformation(int orderId){

        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .get("/store/order/{orderId}");

        return response;



    }
}
