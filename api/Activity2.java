package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {

    final static String BASE_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority=1)
    public void addNewUserFromJSONFile() throws IOException {

        File file = new File("src/test/java/activities/userinfo.json");
        FileInputStream inputJSON = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputJSON.read(bytes);
        String reqBody = new String(bytes);

        Response response = given().contentType(ContentType.JSON).body(reqBody).
                when().post(BASE_URI);

        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("6983"));
    }

    @Test(priority=2)
    public void getUserDetails() throws IOException {

        File outputJSON = new File("src/test/java/activities/userGETResponse.json");
        Response response = given().contentType(ContentType.JSON)
                .when().pathParam("username","VidhyaJJ").get(BASE_URI+"/{username}");

        String resBody = response.getBody().asPrettyString();

        outputJSON.createNewFile();
        FileWriter writer = new FileWriter(outputJSON.getPath());
        writer.write(resBody);
        writer.close();

        response.then().body("id", equalTo(6983));
        response.then().body("username", equalTo("VidhyaJJ"));
        response.then().body("firstName", equalTo("Vidhya"));
        response.then().body("lastName", equalTo("Nair"));
        response.then().body("email", equalTo("vn@gmail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9812763452"));
    }


    @Test(priority=3)
    public void deletePetDetails(){
        Response response = given().contentType(ContentType.JSON)
                .when().pathParam("username","VidhyaJJ").delete(BASE_URI+"/{username}");
        response.then().body("code",equalTo(200));
        response.then().body("message",equalTo("VidhyaJJ"));
    }

}
