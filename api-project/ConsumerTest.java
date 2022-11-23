package liveProject;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {
    Map<String,String> headers = new HashMap<>();
    String resourcesPath = "/api/users";
    //Create the contract
    @Pact(consumer = "UserConsumer",provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        headers.put("Content-Type","application/json");
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id",987)
                .stringType("firstName","vidhya")
                .stringType("lastName","nair")
                .stringType("email","vidhya1@gg.com");

        //Record interaction to pact
        return builder.given("A request to create a user")
                .uponReceiving("A request to create a user")
                .method("POST")
                .headers(headers)
                .path(resourcesPath)
                .body(requestResponseBody)
                .willRespondWith()
                .status(201)
                .body(requestResponseBody)
                .toPact();
    }

    //Test with mock Provider
    @Test
    @PactTestFor(providerName = "UserProvider",port = "8282")
    public void consumerTest(){
        String baseURI = "http://localhost:8282/api/users";
        Map<String, Object>  reqBody = new HashMap<>();
        reqBody.put("id",987);
        reqBody.put("firstName","vidhya");
        reqBody.put("lastName","nair");
        reqBody.put("email","vidhya1@gg.com");

        given().headers(headers).body(reqBody).log().all()
                .when().post(baseURI)
                        .then().statusCode(201).log().all();


    }


}

