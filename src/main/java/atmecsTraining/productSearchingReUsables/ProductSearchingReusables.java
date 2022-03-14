package atmecsTraining.productSearchingReUsables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductSearchingReusables {
    public WebDriver driver;

    public ProductSearchingReusables(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(WebElement searchBox, WebElement searchButton, String searchText) {
        ActionReusables actionReusables = new ActionReusables(driver);
        actionReusables.sendText(searchBox, searchText);
        actionReusables.clickUsingJavaScriptExecutor(searchButton);
    }

    public List<WebElement> storeTheMatchedProductsFromTheSearchResults
            (List<WebElement> searchResults, String nameLocator, String searchText) {
        ActionReusables actionReusables = new ActionReusables(driver);
        List<WebElement> matchedProducts = new ArrayList<>();
        for (WebElement productElement : searchResults) {
            String productName = actionReusables.getTextFromElement
                    (findInnerElementByTagName(productElement, nameLocator));
            if (productName.equals(searchText)) {
                matchedProducts.add(productElement);
            }
        }
        return matchedProducts;
    }

    public List<Float> storeThePriceOfTheMatchedProducts
            (List<WebElement> matchedProducts, String priceLocator) {
        ActionReusables actionReusables = new ActionReusables(driver);
        List<Float> priceOfMatchedProducts = new ArrayList<>();
        if (!actionReusables.isListEmpty(matchedProducts)) {
            for (WebElement productElement : matchedProducts) {
                Float price = Float.parseFloat(actionReusables.
                        getTextFromElement(findInnerElementByCssSelector
                                (productElement, priceLocator)).replace("$", ""));
                priceOfMatchedProducts.add(price);
            }
        } else
            System.out.println("Search results not matched with your search item");
        return priceOfMatchedProducts;
    }

    public WebElement findTheProductWithLowestPrice
            (List<WebElement> matchedProducts, List<Float> priceOfMatchedProducts) {
        ActionReusables actionReusables = new ActionReusables(driver);
        WebElement finalProduct = null;
        if (!actionReusables.isListEmpty(matchedProducts)) {
            float lowestPrice = priceOfMatchedProducts.get(0);
            for (int productIndex = 0; productIndex < matchedProducts.size(); productIndex++) {
                if (priceOfMatchedProducts.get(productIndex) < lowestPrice) {
                    lowestPrice = priceOfMatchedProducts.get(productIndex);
                    finalProduct = matchedProducts.get(productIndex);
                }
            }
        }
        return finalProduct;
    }

    public void printProductDetails(WebElement finalProduct, String nameLocator,
                                    String priceLocator, String availabilityLocator) {
        if(finalProduct!=null) {
            ActionReusables actionReusables = new ActionReusables(driver);
            Map<String, String> productWithLowestPrice = new LinkedHashMap<>();
            productWithLowestPrice.put("Product Name", actionReusables.
                    getTextFromElement(findInnerElementByTagName(finalProduct, nameLocator)));
            productWithLowestPrice.put("Price", actionReusables.
                    getTextFromElement(findInnerElementByCssSelector(finalProduct, priceLocator)));
            productWithLowestPrice.put("Availability Status", actionReusables.
                    getTextFromElement(findInnerElementByCssSelector(finalProduct, availabilityLocator)));
            System.out.println(productWithLowestPrice);
        }
    }

    public void printUnMatchedProducts
            (List<WebElement> searchResults, String nameLocator, String searchText) {
        ActionReusables actionReusables = new ActionReusables(driver);
        System.out.println("\nUnmatched Products:");
        for (WebElement productElement : searchResults) {
            String productName = actionReusables.getTextFromElement
                    (findInnerElementByTagName(productElement, nameLocator));
            if (!productName.equals(searchText)) {
                System.out.println(productName);
            }
        }
    }

    public WebElement findInnerElementByCssSelector(WebElement element, String cssSelector) {
        return element.findElement(By.cssSelector(cssSelector));
    }

    public WebElement findInnerElementByTagName(WebElement element, String tagName) {
        return element.findElement(By.tagName(tagName));
    }
}
