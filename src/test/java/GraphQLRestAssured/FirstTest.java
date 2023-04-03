package GraphQLRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class FirstTest {

    private final String limit;
    private final String albumName;

    public FirstTest(String limit, String albumName) {
        this.limit = limit;
        this.albumName = albumName;
    }

    @Test
    public void firstStarWarsTest(){
        //https://swapi-graphql.netlify.app/.netlify/functions/index //retrieved via Network > index request > Request URL

        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI
        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}"; //retrieved from Network > index request > request payload > view source
        String uri = "/.netlify/functions/index";

        given().log().all()
                .contentType("application/json")
                .body(query)
                    .when().log().all()
                        .post(uri)
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200)
                                    .body("data.allFilms.films[0].title", equalTo("A New Hope"));
    }

    @Test
    public void firstHasuraTest(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        String query = "{\"query\":\"query fetchAlbum {\\n  Album {\\n    AlbumId\\n    Title\\n    Tracks {\\n      TrackId\\n      Name\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
        String uri = "/v1/graphql";

        given().log().all()
                .contentType("application/json")
                .header("x-hasura-admin-secret","3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                    .when().log().all()
                        .post(uri)
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200)
                                    .body("data.Album.Tracks[0].TrackId[0]", equalTo(1))
                                    .body("data.Album.Tracks[0].Name[0]", equalTo("For Those About To Rock (We Salute You)"));

    }

    @Parameterized.Parameters
    public static Collection<Object[]> getQueryData(){
        return Arrays.asList(new Object[][]{
                {"3", "Restless and Wild"},
                {"2", "Restless and Wild"},
                {"4", "Restless and Wild"}});
    }

    @Test
    //Test is run using the Junit "parameterized" data approach
    //Set the class with annotation @RunWith(Parameterized.class)
    //Construct data parameters
    //Set up a static method that returns a list of parameterized data with the annotation @Parameterized.Parameters (in TestNG this is @DataProvider)
    public void hasuraTestWithLimitSet(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        String query = "{\"query\":\"query fetchAlbum {\\n  Album(limit: " + limit + ") {\\n    AlbumId\\n    Title\\n    Tracks {\\n      TrackId\\n      Name\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
        String uri = "/v1/graphql";

        given().log().all()
                .contentType("application/json")
                .header("x-hasura-admin-secret","3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                    .when().log().all()
                        .post(uri)
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200)
                                        .body("data.Album.Tracks[0].TrackId[0]", equalTo(1))
                                        .body("data.Album.Tracks[0].Name[0]", equalTo("For Those About To Rock (We Salute You)"));

    }

    @Test
    public void hasuraTestWithMultipleDataParams(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        String query = "{\"query\":\"query fetchAlbum {\\n  Album(limit: " + limit + ") {\\n    AlbumId\\n    Title\\n    Tracks(where: {Album: {Title: {_eq: \\\"" + albumName + "\\\"}}}, limit: 3) {\\n      TrackId\\n      Name\\n      Album {\\n        Title\\n      }\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
        String uri = "/v1/graphql";

        given().log().all()
                .contentType("application/json")
                .header("x-hasura-admin-secret","3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                    .when().log().all()
                        .post(uri)
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200);

    }

    @Test
    public void getAllAlbumsWithPojo(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        GraphQLPojoTestQuery query = new GraphQLPojoTestQuery();

        query.setQuery("query MyQuery($limit: Int!, $title: String!) {\n" +
                "  Album(limit: $limit, where: {Title: {_eq: $title}}) {\n" +
                "    Title\n" +
                "  }\n" +
                "}");

        QueryVariable variable = new QueryVariable();
        variable.setLimit(5);
        variable.setTitle("Balls to the Wall");

        query.setVariables(variable);

        given().log().all()
                .contentType(ContentType.JSON)
                .header("x-hasura-admin-secret", "3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                    .when()
                        .post("/v1/graphql")
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200)
                                    .and().body("data.Album[0].Title", equalTo("Balls to the Wall"));

    }
}
