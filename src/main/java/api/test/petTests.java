package api.test;

import api.endpoints.User.Routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.endpoints.store.routesPets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class petTests {

    private int petID;

    @Test(priority = 1)
    public void createPet() {



        RestAssured.baseURI = routesPets.base_url;

        String requestBody = """
                {
                    "id": 12345,
                    "name": "Celina",
                    "category": { "id": 1, "name": "Dog" },
                    "status": "available"
                }
                """;


        // POST-Anfrage zum Erstellen eines PETs
        Response response = given().contentType("application/json").body(requestBody)
                .when()
                .post(routesPets.post_url)
                .then()
                .statusCode(200)
                .extract().response();

        // Antwort-Details ausgeben (optional, für Debugging)
        response.then().log().all();
        int petId = response.jsonPath().getInt("id");


        // Gebe die erhaltene ID aus (zur Überprüfung)
        System.out.println("Erhaltene ID: " + petId);

        // Überprüfe, ob der Status korrekt gesetzt wurde
        given().pathParam("petId", petId)
                .when().get(routesPets.get_url)
                .then()
                .statusCode(200)
                .body("name", equalTo("Celina"));
    }

    @Test(priority = 2)
    public void testUpdateStatus() {



        String updatBody = """
                {
                    "id": 12345,
                    "name": "Celina",
                    "status": "sold"
                }
                """;
        given().contentType(ContentType.JSON)
                .body(updatBody)
                .when()
                    .put(routesPets.put_url)
                .then()
                .statusCode(200)
                .body("status", equalTo("sold"));


        given().pathParam("status", petId)
                .when()
                .get(routesPets.get_url_status)
                .then().statusCode(200)
                .body("status", equalTo("sold"));


    }
}
