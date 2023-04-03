//package Framework;
//
//import org.junit.After;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//
//public class ActionsHelper {
//
//    public void navigateToSite (String string){
//        driver.navigate().to(string);
//    }
//
//    public void acceptCookies() {
//        driver.findElement(By.cssSelector(".consent_prompt_footer #consent_prompt_submit")).click();
//    }
//
//    public void enterSearchTerm(String string){
//        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(string, Keys.RETURN);
//    }
//    public void collectProducts(){
//        isElementDisplayed(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0 .ProductCardstyles__Title-h52kot-12"));
////        List<WebElement> products = driver.findElements(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0"));
////        System.out.println("Number of products is: " + products.size());
////        for (WebElement product : products) {
////            WebElement link = product.findElement(By.cssSelector(".ProductCardstyles__Title-h52kot-12"));
////            WebElement button = product.findElement(By.cssSelector(".Buttonstyles__Button-sc-42scm2-2"));
////
////            String linkTextUrl = link.getAttribute("href");
////            String buttonText = button.getText();
////
////            System.out.println(linkTextUrl);
////            System.out.println(buttonText);
////        }
//    }
//
//    @After
//    public void quit (){
//        driver.quit();
//    }
//}
