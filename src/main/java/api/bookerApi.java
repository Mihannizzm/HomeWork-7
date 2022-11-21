package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class bookerApi {
    private final static String baseUri = "https://restful-booker.herokuapp.com";

    public static Response auth(String body) {
        String path = "/auth";

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .log().body()
                .when()
                .post()
                .then()
                .log().body()
                .extract()
                .response();
        return response;
    }

    public static Response  bookingCreate (String body) {
        String path = "/booking";

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .log().body()
                .when()
                .post()
                .then()
                .log().body()
                .extract()
                .response();

        return response;

    }

    public static Response getBooking(Integer bookingId) {
        String path = "/booking/" + bookingId;

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().body()
                .extract()
                .response();

        return response;

    }

    public static Response bookingUpdate (String authToken,Integer bookingId, String body) {
        String path = "/booking/" + bookingId;

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .header("Cookie", "token=" + authToken)
                .body(body)
                .contentType(ContentType.JSON)
                .log().body()
                .when()
                .put()
                .then()
                .log().body()
                .extract()
                .response();

        return response;

    }

    public static Response  partialBookingUpdate (String authToken,Integer bookingId, String body) {
        String path = "/booking/" + bookingId;

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .header("Cookie", "token=" + authToken)
                .body(body)
                .contentType(ContentType.JSON)
                .log().body()
                .when()
                .patch()
                .then()
                .log().body()
                .extract()
                .response();

        return response;

    }

    public static Response  bookingDelete (String authToken,Integer bookingId) {
        String path = "/booking/" + bookingId;

        Response response = given()
                .baseUri(baseUri)
                .basePath(path)
                .log().uri()
                .header("Cookie", "token=" + authToken)
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .log().body()
                .extract()
                .response();

        return response;

    }
}

