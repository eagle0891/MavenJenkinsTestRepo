package GraphQLRestAssured.POJO;

import GraphQLRestAssured.Modules.ConsoleLogger;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Data;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

@Data
public class Customer extends ModuleConfigQueries {

    String firstname;
    String lastname;
    String email = randomEmail();
    String setEmail;
    //String password = randomPassword();
    String date_of_birth = randomDOB();
    String is_subscribed = "False";
    String createCustomerV2Query;
    String createCustomerCreationQuery;
    String createCustomerTokenQuery;
    String createCustomerSelfDeletionRequest;
    String updateCustomerMutation;
    String bearerToken;
    String cartId;
    String cartFullDetailsId;
    ConsoleLogger consoleLogger = new ConsoleLogger();
    String emailExtension = "@mailinator.com";
    String customerConfirmationKey;
    String customerConfirmationId;
    String customerConfirmationQuery;
    String meQuery;
    String returnGuestSubscribeNewsLetterMarketing;
    String orderNumber;
    String paRec;
    String yotiToken;
    String backInStockNotification;

    public Customer() {
        // 1. Customer Constructor called
        // 2. Random Email is generated (Faker:Random)
        // 3. Fist and Last Name separated
        // 4. Random DOB is generated
        // 5. Generate Random Password (Faker:Password)  :: This isn't conforming to the expected format - needs investigation
        // 6. Log the details for verification
        //buildRandomlyNamedCustomer();
    }

    public String setBearerToken(String bearerToken){
        this.bearerToken = bearerToken;
        return bearerToken;
    }

    public void buildRandomlyNamedCustomer(){
        //randomEmail();
        generateFakeUserNames();
        randomDOB();
        //randomPassword();
        getFirstandLastNameFromEmail();
        showCustomerDetails();
    }

    public String buildNewsLetterSubscriptionQuery(String email){
        return returnGuestSubscribeNewsLetterMarketing = "{\"query\":\"mutation {\\n  " +
                "guestSubscribeNewsletterMarketing (\\n    " +
                "email: \\\""+email+"\\\"\\n    " +
                "firstname: \\\"Angus\\\"\\n    " +
                "lastname: \\\"Macflakpac\\\"\\n    " +
                "phone: \\\"07768900435\\\"\\n    " +
                "date_of_birth: \\\"14/08/1972\\\"\\n    " +
                "marketing_by_email: true\\n    " +
                "marketing_by_sms: false\\n  )\\n  " +
                "{\\n    status\\n  }\\n}\"}";
    }

    public String buildQueryStringMe(){
        return meQuery = "{\"query\":\"query {\\n" +
                "  me {\\n    " +
                "is_logged_in\\n    " +
                "active_cart_id\\n  " +
                "}\\n}\"}";
    }

    public String buildQueryStringCustomerEmailConfirmation(){
        customerConfirmationQuery = "{\"query\":\"mutation {\\n  " +
                "confirmCustomer(\\n    " +
                "customer_id: "+ Integer.parseInt(getCustomerConfirmationId()) +",\\n    " +
                "key: \\\""+getCustomerConfirmationKey()+"\\\"\\n  ) " +
                "{\\n    auth_token\\n  }\\n}\"}";
        return customerConfirmationQuery;
    }

    public String buildQueryStringCustomerTokenRequest(){
        createCustomerTokenQuery = "{\"query\":\"mutation {\\n  " +
                "generateCustomerToken(\\n    " +
                "email: \\\""+getEmail().trim()+"\\\",\\n    " +
                "password: \\\"Pa55w@rd12345\\\") " +
                "{\\n    token\\n  }\\n}\"}";
        return createCustomerTokenQuery;
    }

    public String buildQueryStringExistingCustomerTokenRequest(String email){
        createCustomerTokenQuery = "{\"query\":\"mutation {\\n  " +
                "generateCustomerToken(\\n    " +
                "email: \\\""+email+"\\\",\\n    " +
                "password: \\\"Pa55w@rd12345\\\") " +
                "{\\n    token\\n  }\\n}\"}";
        return createCustomerTokenQuery;
    }

    public String buildQueryStringCustomerSelfDeletionRequest(String email){
        createCustomerSelfDeletionRequest= "{\"query\":\"mutation {\\n  " +
                "deleteCustomer (\\n    " +
                "email: \\\""+email+"\\\",\\n)" +
                "{\\n    result\\n  }\\n}\"}";
        return createCustomerSelfDeletionRequest;
    }

    public String buildQueryStringCustomerSelfDeletionRequest(){
        createCustomerSelfDeletionRequest= "{\"query\":\"mutation {\\n  " +
                "deleteCustomer (\\n    " +
                "email: \\\""+getEmail().trim()+"\\\",\\n)" +
                "{\\n    result\\n  }\\n}\"}";
        return createCustomerSelfDeletionRequest;
    }

    public String buildQueryStringCustomCustomerAttribtuesCreation(String nodeInjection, String valueInjection){
        createCustomerCreationQuery =       "{\"query\":\"mutation {\\n  " +
                "createCustomerV2(\\n    " +
                "input: {\\n      " +
                "firstname: \\\""+getFirstname().trim()+"\\\"\\n      " +
                "lastname: \\\""+getLastname().trim()+"\\\"\\n      " +
                "email: \\\""+getEmail().trim()+"\\\"\\n      " +
                "password: \\\"Pa55w@rd12345\\\"\\n      " +
                "date_of_birth: \\\""+getDate_of_birth().trim()+"\\\"\\n      " +
                //NodeInjection is for the customer Attribute testing
                nodeInjection+"\\\""+valueInjection+"\\\"\\n"+
                "is_subscribed: false\\n    }\\n  ) {\\n    " +
                "customer {\\n      " +
                "firstname\\n      " +
                "lastname\\n      " +
                "email\\n      " +
                "is_subscribed\\n      " +
                "created_at\\n    " +
                nodeInjection.replace(":","")+"\\n    " +
                "}\\n    yoti_token\\n  }\\n}\"}";
        return createCustomerCreationQuery;
    }

    public String buildQueryStringCustomerCreation(){
        return createCustomerCreationQuery =       "{\"query\":\"mutation {\\n  " +
                "createCustomer(\\n    " +
                "input: {\\n      " +
                "firstname: \\\""+getFirstname().trim()+"\\\"\\n      " +
                "lastname: \\\""+getLastname().trim()+"\\\"\\n      " +
                "email: \\\""+getEmail().trim()+"\\\"\\n      " +
                "password: \\\"Pa55w@rd12345\\\"\\n      " +
                "dob: \\\"19-07-2003\\\"\\n" +
                "is_subscribed: false\\n    }\\n  ) {\\n    " +
                "customer {\\n      " +
                "firstname\\n      " +
                "lastname\\n      " +
                "email\\n      " +
                "is_subscribed\\n      " +
                "created_at\\n    " +
                "}\\n    yoti_token\\n  }\\n}\"}";
    }

    public String buildQueryStringCustomerCreation(String bankID){
        //System.out.println("TRYING TO CREATE CUSTOMER WITH NEW BANK ID ");
        return createCustomerCreationQuery =       "{\"query\":\"mutation {\\n  " +
                "createCustomerV2(\\n    " +
                "input: {\\n      " +
                "firstname: \\\""+getFirstname().trim()+"\\\"\\n      " +
                "lastname: \\\""+getLastname().trim()+"\\\"\\n      " +
                "email: \\\""+getEmail().trim()+"\\\"\\n      " +
                "password: \\\"Pa55w@rd12345\\\"\\n      " +
                "gender: 1\\n      " +
                "dob: \\\"19-07-2003\\\"\\n" +
                "bankid_order_ref: \\\""+bankID+"\\\"\\n" +
                //"date_of_birth: \\\""+getDate_of_birth().trim()+"\\\"\\n      " +
                //"is_subscribed: false\\n    }\\n  ) " +
                "is_subscribed: false\\n  "+
                "address: {\\nfirstname: \\\"Angus\\\"\\nlastname: \\\"Macflakpac\\\"\\nstreet: [\\\"Lantmannagatan 25\\\", \\\"Ruda\\\"]\\ntelephone: \\\"07768 901398\\\"\\n        postcode: \\\"610 24\\\"\\n        region: {\\nregion: \\\"Kalmar\\\"\\n\\t\\t\\t\\t}\\n\\t\\t\\t\\tcity: \\\"Kalmar\\\"\\n\\t\\t\\t\\tcountry_code: SE\\n        default_shipping: true\\n        default_billing: true\\n\\t\\t\\t}\\n    }\\n)  " +
                "" +
                "{\\n    " +
                "customer {\\n      " +
                "firstname\\n      " +
                "lastname\\n      " +
                "email\\n      " +
                "gender\\n      " +
                "is_approved\\n      " +
                "is_subscribed\\n      " +
                "created_at\\n    " +
                "addresses {\\n\\t\\t\\t\\tstreet\\n\\t\\t\\t\\tcity\\n\\t\\t\\t\\tpostcode\\n\\t\\t\\t\\tcountry_code\\n\\t\\t\\t\\n  } " +
                "}\\n    yoti_token\\n  }\\n}\"}";
    }

    public String buildQueryStringUpdateCustomer(){
        return updateCustomerMutation = "{\"query\":\"mutation {\\n  " +
                "updateCustomer (\\n    " +
                "input: {\\n      " +
                "firstname: \\\"Marcus\\\"\\n      " +
                "lastname: \\\"Bobby\\\"\\n      " +
                "is_subscribed: false\\n\\t\\t\\t" +
                "dob: \\\"12-08-1964\\\"\\n\\t\\t\\t" +
                //"email: \\\"marcus.sparky84@gmail.com\\\"\\n    }\\n  ) " +
                "email: \\\""+getEmail().trim()+"\\\"\\n      }\\n  ) " +
                "{\\n    " +
                "customer {\\n      " +
                "firstname\\n      " +
                "lastname\\n      " +
                "email\\n      " +
                "is_subscribed\\n      " +
                "created_at\\n      " +
                "consent_third_parties\\n    " +
                "}\\n  }\\n}\"}";
    }

    public String backInStockNotificationMutationQuery(){
        return backInStockNotification = "{\"query\":\"mutation{productAlertCustomerNotifyInStock(input:{productId:5723}){alert_stock_id customer_id product_id add_date\\tsend_count send_date status\\tstore_id\\twebsite_id}}\"}";
    }

    public void showCustomerDetails(){
        consoleLogger.returnCustomerDetails(getFirstname(), getLastname(), getEmail(), getDate_of_birth());
    }

    @Test
    public void whenBothifyCalled_checkPatternMatches() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        Matcher emailMatcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);

        assertTrue(emailMatcher.find());
    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String randomPassword(){
        Faker faker = new Faker();
        return faker.internet().password();
    }

    public void getFirstandLastNameFromEmail(){
        if (email != null){
            firstname = email.substring(0,email.indexOf("."));
            lastname = email.substring(email.indexOf(".")+1,email.indexOf("@"));
        } else {
            consoleLogger.printMessageToConsole("**** ERROR ****\n **** EMAIL VARIABLE HAS NOT BEEN SET ****");
        }
    }

    @Test
    public void randomSEPostCode() {
        Faker fakerSE = new Faker(new Locale("sv-SE"));
        System.out.println(String.format("Swedish postcode: %s", fakerSE.address().zipCode()));
    }

    @Test
    public void randomBankID() {
        Faker fakerSE = new Faker(new Locale("sv-SE"));
        String validBankId = fakerSE.idNumber().validSvSeSsn();
        System.out.println(validBankId);
        //return validBankId;
    }

    @Test
    public String randomDOB() {
        Faker faker = new Faker();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(faker.date().birthday());
        return date;
    }

    @Test
    public String generateFakeUserNames(){
        Faker faker = new Faker();
        firstname = faker.name().firstName().toLowerCase();
        lastname = faker.name().lastName().toLowerCase();
        if (lastname.contains("'")){
            System.out.println("Non-Standard format found - fixing");
            lastname = lastname.replace("'","");
        }
        email = firstname +"."+lastname+emailExtension;
        return email;

    }

    public void createSpecificCustomer(String email) {
        setEmail(email);
        System.out.println("Email overridden and set to : " + email);
        randomDOB();
        //randomPassword();
        firstname = email.substring(0,email.indexOf("@"));
        lastname = "Tester";
        showCustomerDetails();
    }

}