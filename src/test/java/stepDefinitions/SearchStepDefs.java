package stepDefinitions;

import Helpers.BaseClass;
import Helpers.ReadFrom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SearchStepDefs extends BaseClass {
    private static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Given("^the user is on the website '(.*)' using '(.*)'$")
    public void navigateToWebsite(String websiteURL, String browser) throws MalformedURLException {
        System.out.println("**** Navigating to site " + websiteURL);
        openBrowser(browser);
        navigateToSite(websiteURL);
        amazonAcceptCookies();
    }

    @When("^the user searches for a product '(.*)'$")
    public void searchForProduct(String product) {
        System.out.println("**** Searching for product");
        enterSearchTerm(product);
    }

    @Then("^the search results should be displayed$")
    public void searchResults() throws InterruptedException {
        System.out.println("**** Search results are displayed");
        getProducts();
    }

    @Test
    public void simplePropertiesFileTest(){
        assertEquals("cssFromPropertiesFile",ReadFrom.propertiesFile("css","test"));
    }

    @When("^I click on the product '(.*)'$")
    public void selectTheProduct(String productType) throws Exception {
        System.out.println("Product type provided by the test is: " + productType);
        findProductType(productType);
    }

    @Then("^the PDP should be displayed$")
    public void pdpShouldBeDisplayed() {
        confirmPdpPageIsDisplayed();
    }

//    @Given("^request contains contentType and requestBody, and request is sent to uri, and response returns status statusCode and body responseBody$")
//    public void firstStarWarsTest() {
//        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI
//        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}"; //retrieved from Network > index request > request payload > view source
//        String uri = "/.netlify/functions/index";
//
//        given().log().all()
//                .contentType("application/json")
//                .body(query)
//                .when().log().all()
//                .post(uri)
//                .then().log().all()
//                .assertThat()
//                .statusCode(200)
//                .body("data.allFilms.films[0].title", equalTo("A New Hope"));
//    }
}