package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.equalTo;

public class GitHubProjectTest {

    RequestSpecification requestSpec;

    int id;

    @BeforeClass
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
               // .setAuth(oauth2("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"))
                .addHeader("Authorization","token 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")
                .setBaseUri("https://api.github.com/user/keys").build();
    }

    @Test(priority = 1)
    public void addSSHKey(){
        Map<String,Object> reqBody = new HashMap<>();
        reqBody.put("title","TestAPIKey");
        reqBody.put("key","ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCPiSexuTmpYuPfOplPGBJo9B9QwZu6uVU9YQWrl/DE7z6PM1bg0qkDnXsFgwKfPozncX3imQeuC946xr2HF5KqPoBnMNS/PbyyVVXq13PMC3zepngvFw/pTh0Zk92pD0n6CCyEC7+e/jggVxmj87ThIWegoQ6E+hBIBOmYV35iFbCknOZiO5nebn2C3aZ1cG7OLGovl//BG7XT8oF5KqOlYFnpUan8iYltXCxc6hx6g0CwETzKZo8kjhUicYoBT7JK96SyFYY2w7yIh16zeKtFoWP4Vu/QFbEYwrmw+DH4pQ65XldE7Gx4e2damzKQz1zrRgEHhbbHa8rtgufvLDUX");
        Response response= given().log().all().spec(requestSpec).body(reqBody).when().post();
        id = response.then().extract().path("id");
        response.then().log().all().statusCode(201);

    }

    @Test(priority =2)
    public void getSSHKey(){
        Response response = given().log().all().spec(requestSpec).pathParam("keyId",id).when().get("/{keyId}");
        response.then().log().all().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteSSHKey(){
        Response response = given().log().all().spec(requestSpec).pathParam("keyId",id).when().delete("/{keyId}");
        response.then().log().all().statusCode(204);

    }


}
