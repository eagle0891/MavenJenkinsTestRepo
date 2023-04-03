package Helpers;

import Models.Product;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Helpers.ActionTypesEnum.click;
import static org.junit.Assert.assertTrue;

public class BaseClass extends WaitHelper {
    public static void main(String args[]) {
    }

//    public BaseClass (WaitHelper waitHelper){
//        this.waitHelper = waitHelper;
//    }
protected WebDriver driver;
    public static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Getter
    protected WebDriverWait wait;
//    @Getter
//    protected WebDriver webDriver;
    public Product product;

//    private WaitHelper waitHelper;
    public void openBrowser(String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                DesiredCapabilities cp = new DesiredCapabilities();
                cp.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(cp);
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
//                FirefoxOptions options = new FirefoxOptions();
//                options.setBinary(new FirefoxBinary(new File("C:\\Users\\uagwo\\AppData\\Local\\Mozilla Firefox\\firefox.exe")));
//                WebDriver driver = new FirefoxDriver(options);
            }
            case "internet explorer" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "remote" -> {
                //set up remote test envs
                DesiredCapabilities caps = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-web-security");
                options.addArguments("--test-type");
                options.addArguments("allow-running-insecure-content");
                caps.setCapability(ChromeOptions.CAPABILITY, options);

                caps.setCapability("browser", "chrome");
                caps.setCapability("browser_version", "100");
                caps.setCapability("os", "windows");
                caps.setCapability("os_version", "11");
                caps.setCapability("seleniumVersion", "4.2.2");
                caps.setCapability("project", "BAT");
                driver = new RemoteWebDriver(new URL("https://batbsauto_IG7R8rDiiDf:vNhrigLoLtzizHEKhTti@hub-cloud.browserstack.com/wd/hub"), caps);
                driver.manage().window().maximize();
            }
        }
    }

    public void navigateToSite (String string){
        driver.navigate().to(string);
    }

    private static final By AMAZON_ACCEPT_TERMS_AND_CONDITIONS = By.cssSelector(ReadFrom.propertiesFile("css","acceptTermsAndConditions"));

    public void amazonAcceptCookies() {
        driver.findElement(AMAZON_ACCEPT_TERMS_AND_CONDITIONS).click();
    }

    private static final By AMAZON_SEARCH_FIELD = By.cssSelector(ReadFrom.propertiesFile("css", "amazonSearchField"));

    public void enterSearchTerm(String string){
        driver.findElement(AMAZON_SEARCH_FIELD).sendKeys(string, Keys.RETURN);
    }

    public void clickEnter(String selector){
        driver.findElement(By.cssSelector(selector)).click();
    }

    private static final By ARGOS_PLP_PRODUCT_TITLE = By.cssSelector(ReadFrom.propertiesFile("css", "argosPlpProductTitle"));
    private static final By ARGOS_SEARCH_RESULT_LIST = By.cssSelector(ReadFrom.propertiesFile("css", "argosSearchResultList"));
    private static final By ARGOS_PLP_PRODUCT_TILE = By.cssSelector(ReadFrom.propertiesFile("css", "argosPlpProductTile"));
    private static final By ARGOS_ADD_TO_CART_BUTTON = By.cssSelector(ReadFrom.propertiesFile("css", "argosAddToCartButton"));

    public void collectProducts(){
        isElementDisplayed(ARGOS_PLP_PRODUCT_TITLE);
        String css = driver.findElement(ARGOS_SEARCH_RESULT_LIST).getAttribute("class");
        System.out.println("CSS is: " + css);
        List<WebElement> products = driver.findElements(ARGOS_PLP_PRODUCT_TILE);
        System.out.println("Number of products is: " + products.size());
        for (WebElement product : products) {
            WebElement link = product.findElement(ARGOS_PLP_PRODUCT_TITLE);
            WebElement button = product.findElement(ARGOS_ADD_TO_CART_BUTTON);

            String linkTextUrl = link.getAttribute("href");
            String buttonText = button.getText();

            System.out.println(linkTextUrl);
            System.out.println(buttonText);
        }
    }

    private static final By SEARCH_RESULTS_LIST = By.cssSelector(ReadFrom.propertiesFile("css", "searchResultsList"));
    private static final By SEARCH_RESULT_ITEM = By.cssSelector(ReadFrom.propertiesFile("css", "searchResultItem"));
    private static final By PLP_PRODUCT_NAME = By.cssSelector(ReadFrom.propertiesFile("css", "plpProductName"));
    private static final By PLP_PRODUCT_WHOLE_PRICE = By.cssSelector(ReadFrom.propertiesFile("css", "plpProductWholePrice"));
    private static final By PLP_PRODUCT_DECIMAL_PRICE = By.cssSelector(ReadFrom.propertiesFile("css", "plpProductDecimalPrice"));
    private static final By PLP_PRODUCT_IMAGE_LINK = By.cssSelector(ReadFrom.propertiesFile("css", "plpProductImageLink"));
    private static final By PLP_PRODUCT_BRAND_NAME = By.cssSelector(ReadFrom.propertiesFile("css", "plpProductBrandName"));

    public ArrayList<Product> getProducts() throws InterruptedException {
        Product.ProductCollection = new ArrayList<>();
        String css = driver.findElement(SEARCH_RESULTS_LIST).getAttribute("class");
        System.out.println("Search result CSS is: " + css);
        waitForElementToDisplay(driver.findElement(SEARCH_RESULTS_LIST), 10);
        WebElement searchResultsList = driver.findElement(SEARCH_RESULTS_LIST);
        List<WebElement> products = searchResultsList.findElements(SEARCH_RESULT_ITEM);
        System.out.println("Number of products is: " + products.size());
        for (WebElement product : products) {
            try {
                String productName = product.findElement(PLP_PRODUCT_NAME).getText();
                String productWholePricePart = product.findElement(PLP_PRODUCT_WHOLE_PRICE).getText();
                String productDecimalPricePart = product.findElement(PLP_PRODUCT_DECIMAL_PRICE).getText();
                WebElement productImageLink = product.findElement(PLP_PRODUCT_IMAGE_LINK);
                String productBrandName = product.findElement(PLP_PRODUCT_BRAND_NAME).getText();
                System.out.println("Brand is: " + productBrandName);
                System.out.println("Product title is: " + productName);
                System.out.println("Product Price is: £" + productWholePricePart + "." + productDecimalPricePart);
                boolean isSamsung = productName.contains("Samsung");
                WebElement samsungSelection = null;
                if (isSamsung) {
                    samsungSelection = product.findElement(PLP_PRODUCT_NAME);
                }
                boolean isCasio = productName.contains("Casio");
                WebElement casioSelection = null;
                if (isCasio) {
                    casioSelection = product.findElement(PLP_PRODUCT_NAME);
                }
                Product.ProductCollection.add(new Product(productName, productWholePricePart, productDecimalPricePart, isSamsung, product, samsungSelection, isCasio, casioSelection, productImageLink));
            } catch (NoSuchElementException e) {
                LOG.info("**** Element does not contain a product ****");
            }
        }
        return Product.ProductCollection;
    }

    public void findProductType(String productType) throws Exception {
        //clearProductCollectionIfPopulated();
        outer: for (Product product : Product.ProductCollection) {
            switch(productType){
                case "samsung":
                    if (product.isSamsung()){
                        System.out.println("*** Samsung switch statement being executed ***");
                        LOG.info("** SAMSUNG PRODUCT FOUND **");
                        product.display();
                        waitForElementToBeClickable(driver, product.getProductImageLink(), Duration.ofSeconds(10));
//                        customerAction(click, product.getProductImageLink());
                        product.getProductImageLink().click();
                        LOG.info("** SAMSUNG PDP SHOULD BE DISPLAYED **");
                        break outer;
                    }
                    break;
                case "casio":
                    if (product.isCasio()){
                        System.out.println("*** Casio switch statement being executed ***");
                        LOG.info("** CASIO PRODUCT FOUND **");
                        product.display();
                        waitForElementToBeClickable(driver, product.getProductImageLink(), Duration.ofSeconds(10));
//                        customerAction(click, product.getProductImageLink());
                        product.getProductImageLink().click();
                        LOG.info("** CASIO PDP SHOULD BE DISPLAYED **");
                        break outer;
                    }
                    break;
                default:
                    LOG.info("ERROR : Product type not recognised, please select a valid product type.");
            }
        }
    }

    private static final By BREADCRUMB_BACK_LINK = By.cssSelector(ReadFrom.propertiesFile("css", "breadcrumbBackLink"));

    public void confirmPdpPageIsDisplayed(){
        assertTrue(driver.findElement(BREADCRUMB_BACK_LINK).isDisplayed());
    }

//    @After
     //TO BE IMPLEMENTED TOGETHER
    public void customerAction(ActionTypesEnum action, WebElement element, String attributeType, String... requiredText ) throws Exception {
        switch (action) {
            case click -> {
                element.click(); //customerAction(click, product.getProductImageLink()); doesn't like this
            }
            case enterText-> {
                element.sendKeys(requiredText);
            }
            case SelectFromDropDown -> {
                //Select select = new Select(find(by));
                //select.selectByVisibleText(requiredText[0]);
            }
            case getText -> {
                element.getText();
            }
            case getValue -> {
                element.getAttribute(attributeType); //i.e. "class","id", etc
            }
        }
    }

    //@After
    public void quit (){
        driver.quit();
    }
}