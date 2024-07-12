package api.test;

import api.endpoints.UserEndPoints;
import api.paylouds.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String usermail, String pwd, String phone) {

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(usermail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

//        Response response = UserEndPoints.createUser(userPayload);
//        Assert.assertEquals(response.getStatusCode(), 200);

        // Ausgabe der Nutzlast in der Konsole
        System.out.println("Nutzlast der POST-Anfrage:");
        System.out.println("ID: " + userPayload.getId());
        System.out.println("Benutzername: " + userPayload.getUsername());
        System.out.println("Vorname: " + userPayload.getFirstname());
        System.out.println("Nachname: " + userPayload.getLastName());
        System.out.println("E-Mail: " + userPayload.getEmail());
        System.out.println("Passwort: " + userPayload.getPassword());
        System.out.println("Telefon: " + userPayload.getPhone());

        Response response = UserEndPoints.createUser(userPayload);

        // Ausgabe der Antwort in der Konsole
        System.out.println("Antwort der POST-Anfrage:");
        System.out.println("Statuscode: " + response.getStatusCode());
        System.out.println("Antwortkörper: " + response.getBody().asString());
        System.out.println("-------------------------------------------------------------");

        Assert.assertEquals(response.getStatusCode(), 200);


    }

    @Test(priority = 2, dataProvider = "UserNamesOnly", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
