package Models;

import Helpers.BaseClass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseClass {
    public static ArrayList<Product> ProductCollection;
    boolean isSamsung;
    boolean isCasio;
    boolean isGarmin;
    String name;
    String description;
    String wholePrice;
    String decimalPricePart;
    WebElement productWebElement;
    WebElement samsungSelection;
    WebElement casioSelection;
    WebElement productImageLink;
    WebElement garminSelection;

    public Product(String productName, String productWholePricePart, String productDecimalPricePart, boolean isSamsung, WebElement product, WebElement samsungSelection, boolean isCasio, WebElement casioSelection, WebElement productImageLink, boolean isGarmin, WebElement garminSelection) {
        this.name = productName;
        this.wholePrice = productWholePricePart;
        this.decimalPricePart = productDecimalPricePart;
        this.isSamsung = isSamsung;
        this.productWebElement = product;
        this.samsungSelection = samsungSelection;
        this.casioSelection = casioSelection;
        this.isCasio = isCasio;
        this.productImageLink = productImageLink;
        this.garminSelection = garminSelection;
        this.isGarmin = isGarmin;
        //add the image selector here somewhere as a webelement
    }

//    public void addToCart(){
//        waitForElementToAppearAndDisappear(PWA_LOADER,5,5);
//        jsScrollElementInCenter(submitButton);
//        clickUsingJS(submitButton);
//    }

//    public void updateProductQty(String qty){
//        productQty.sendKeys(qty);
//    }

//    public void selectSubscription(){
//        waitForElementToAppearAndDisappear(PWA_LOADER,5,5);
//        if (isSubscription()==true){
//            LOG.info("Selecting subscription");
//            subscriptionSelection.click();
//        }
//    }

    public void display(){
        System.out.println("****************************");
        System.out.println("PRODUCT NAME : " + getName());
        System.out.println("PRODUCT PRICE : " + getWholePrice() + "." + getDecimalPricePart());
        System.out.println("PRODUCT SAMSUNG : " + isSamsung);
    }

}

