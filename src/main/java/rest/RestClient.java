package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Yurii Pavliuk
 */

public class RestClient {

    private static final String CONTENT_TYPE_JSON = "application/json";

    public static Response get(RequestSpecification requestSpecification, String url) {
        return requestSpecification
                .when()
                .log().all()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static RequestSpecification createRequestSpecification() {
        return RestAssured.with().header("content-type", CONTENT_TYPE_JSON);

    }
}
