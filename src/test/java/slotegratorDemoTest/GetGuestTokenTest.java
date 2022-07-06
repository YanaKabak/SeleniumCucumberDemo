package slotegratorDemoTest;

import api.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetGuestTokenTest {

    public static RequestSpecification requestSpec;
    public static String token;
    public static int playerID;
    public static String playerToken;

    @BeforeTest
    public void initSpec() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://test-api.d6.dev.devcaz.com")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    public String adminAuthorization(String authorization) {

        AuthorizationRequest requestAuthorisation =
                AuthorizationRequest.builder()
                        .grantType("client_credentials")
                        .scope("guest:default")
                        .build();

        token = given()
                .header("Authorization", authorization)
                .spec(requestSpec)
                .basePath("/v2/oauth2/token")
                .body(requestAuthorisation)
                .when()
                .post()
                .then()
                .statusCode(200).assertThat()
                .extract().as(AuthorizationResponse.class).getAccessToken();

        return token;
    }

    @Test
    public void player() {

        //authorisation as admin
        token = adminAuthorization("Basic front_2d6b0a8391742f5d789d7d915755e09e");

        //NewPlayer
        NewPlayerRequest requestPlayer =
                NewPlayerRequest.builder()
                        .username("janedoe")
                        .passwordChange("am9obmRvZTEyMw==")
                        .passwordRepeat("am9obmRvZTEyMw==")
                        .email("janedoe@example.com")
                        .name("Jane")
                        .surname("Doe")
                        .currencyCode("EUR")
                        .build();

        playerID = given()
                .spec(requestSpec)
                .header("Authorization", "Basic " + token)
                .basePath("/v2/players")
                .body(requestPlayer)
                .when()
                .post()
                .then()
                .statusCode(201)
                .assertThat()
                .extract().as(NewPlayerResponse.class).getId();


        //authorisation as player
        OwnerPasswordCredentialsGrantRequest request =
                OwnerPasswordCredentialsGrantRequest.builder()
                        .grant_type("password")
                        .username("janedoe")
                        .password("am9obmRvZTEyMw==")
                        .build();

        playerToken = given()
                .spec(requestSpec)
                .basePath("/v2/oauth/token")
                .body(request)
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .assertThat().extract().as(AuthorizationResponse.class).getAccessToken();


        //get data of  authorised player
        given()
                .spec(requestSpec)
                .header("Authorization", "Basic " + playerToken)
                .basePath("v2/players/" + playerID)
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .assertThat()
                .extract().as(NewPlayerResponse.class);


        //get data other player
        given()
                .spec(requestSpec)
                .header("Authorization", "Basic " + playerToken)
                .basePath("v2/players/" + "123")
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(404)
                .assertThat()
                .extract().as(NewPlayerResponse.class);
    }
}
