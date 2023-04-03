package stepDefinitions;

import GraphQLRestAssured.Modules.ConsoleLogger;
import GraphQLRestAssured.Modules.GraphQLDataFields;
import GraphQLRestAssured.Modules.GraphQLModule;
import GraphQLRestAssured.POJO.ModuleConfigQueries;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class APIStepsDefs extends ModuleConfigQueries{

//    GraphQLModule gqlModule = new GraphQLModule();
ConsoleLogger consoleLogger = new ConsoleLogger();
String jsonResponse;
JsonPath jsonPath;
public String loggedResponses;

    @Given("^request contains '(.*)' and '(.*)', and request is sent to '(.*)', and response returns status '(.*)' and body '(.*)' matches '(.*)'$")
    public void firstStarWarsTest(String contentType, String requestModuleToTest, String uri, Integer statusCode, String responseBody, String expectedResponse) {
        jsonResponse = GraphQLModule.returnAPIResponseForAssertion(contentType, requestModuleToTest, uri, statusCode, responseBody, expectedResponse).extract().jsonPath().prettify();
        jsonPath = new JsonPath(jsonResponse);

//        GsonConstructor(requestModuleToTest);

        for (String dataField : GraphQLDataFields.returnModuleArray(requestModuleToTest)) {
            assertTrue("ERROR : data Field not matched : " + dataField,jsonResponse.contains(dataField));
            consoleLogger.API_Response_Data_Logger(requestModuleToTest, jsonPath, dataField, this);
        }
        consoleLogger.printMessageToConsole(loggedResponses);
    }


    @Given("^request contains '(.*)', '(.*)', '(.*)', '(.*)', and request is sent to '(.*)', and response returns status '(.*)' and body '(.*)' matches '(.*)'$")
    public void firstHasuraTest(String contentType, String headerTitle, String headerValue, String requestModuleToTest, String uri, Integer statusCode, String responseBody, Integer expectedResponse) {
        jsonResponse = GraphQLModule.returnHasuraAPIResponseForAssertion(contentType, headerTitle, headerValue, requestModuleToTest, uri, statusCode, responseBody, expectedResponse).extract().jsonPath().prettify();
        jsonPath = new JsonPath(jsonResponse);

        for (String dataField : GraphQLDataFields.returnModuleArray(requestModuleToTest)) {
            assertTrue("ERROR : data Field not matched : " + dataField,jsonResponse.contains(dataField));
            consoleLogger.API_Response_Data_Logger(requestModuleToTest, jsonPath, dataField, this);
        }
        consoleLogger.printMessageToConsole(loggedResponses);
    }

    @Given("^Hasura employee request contains '(.*)', '(.*)', '(.*)', '(.*)', and request is sent to '(.*)', and response returns status '(.*)' and body '(.*)' matches '(.*)'$")
    public void hasuraEmployeeTest(String contentType, String headerTitle, String headerValue, String requestModuleToTest, String uri, Integer statusCode, String responseBody, String expectedResponse) {
        jsonResponse = GraphQLModule.returnHasuraEmployeeAPIResponseForAssertion(contentType, headerTitle, headerValue, requestModuleToTest, uri, statusCode, responseBody, expectedResponse).extract().jsonPath().prettify();
        jsonPath = new JsonPath(jsonResponse);

        for (String dataField : GraphQLDataFields.returnModuleArray(requestModuleToTest)) {
            assertTrue("ERROR : data Field not matched : " + dataField,jsonResponse.contains(dataField));
            consoleLogger.API_Response_Data_Logger(requestModuleToTest, jsonPath, dataField, this);
        }
        consoleLogger.printMessageToConsole(loggedResponses);
    }
}
