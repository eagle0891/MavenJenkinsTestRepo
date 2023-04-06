package GraphQLRestAssured.GQLCalls;

import Helpers.ReadFrom;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GQLCalls {

    private static final String SWAPI_BASE_URI = ReadFrom.propertiesFile("api", "swapiBaseUri");
    private static final String SWAPI_QUERY = ReadFrom.propertiesFile("api", "swapiQuery");
    public void firstStarWarsTestCall(String contentType, String query, String uri, Integer statusCode, String responseBody){
        //https://swapi-graphql.netlify.app/.netlify/functions/index //retrieved via Network > index request > Request URL

        RestAssured.baseURI = SWAPI_BASE_URI; //main part of the URI
//        String query = SWAPI_QUERY; //retrieved from Network > index request > request payload > view source
//        String uri = "/.netlify/functions/index";

        given().log().all()
                .contentType(contentType)
                .body(query)
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(statusCode)
                .body(responseBody, equalTo("A New Hope"));
    }
}
