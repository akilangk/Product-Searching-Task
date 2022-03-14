package atmecsTraining.productSearchingTask.testCases;

import atmecsTraining.productSearchingReUsables.ActionReusables;
import atmecsTraining.productSearchingReUsables.ProductSearchingReusables;
import atmecsTraining.productSearchingReUsables.VerificationReusables;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.*;

public class TestProductSearch extends BrowserActions {
    List<WebElement> searchResults = new ArrayList<>();
    List<WebElement> matchedProducts = new ArrayList<>();
    List<Float> priceOfTheMatchedProducts = new ArrayList<>();
    WebElement finalProduct;

    String url = getTestData("url");
    String searchText = getTestData("searchText");
    String expectedLandingPageTitle = getTestData("expectedLandingPageTitle");
    String expectedPageTitleOnSearchPage = getTestData("expectedPageTitleOnSearchPage");
    String searchBoxLocator = getLocator("searchBox");
    String searchButtonLocator = getLocator("searchButton");
    String searchResultsLocator = getLocator("searchResults");
    String nameLocator = getLocator("productName");
    String priceLocator = getLocator("productPrice");
    String availabilityStatusLocator = getLocator("productAvailabilityStatus");

    @Test
    public void testSearch() {
        ActionReusables actionReusables = new ActionReusables(driver);
        VerificationReusables verificationReusables = new VerificationReusables(driver);
        ProductSearchingReusables productSearchingReusables = new ProductSearchingReusables(driver);
        actionReusables.openUrl(url);
        verificationReusables.verifyPageTitle(expectedLandingPageTitle);
        productSearchingReusables.searchProduct(actionReusables.findElementById
                (searchBoxLocator), actionReusables.findElementByName(searchButtonLocator), searchText);
        verificationReusables.verifyPageTitle(expectedPageTitleOnSearchPage);
        searchResults = actionReusables.findElementsByXpath(searchResultsLocator);
        if (verificationReusables.verifySearchResultsIsNonEmpty(searchResults)) {
            matchedProducts = productSearchingReusables.
                    storeTheMatchedProductsFromTheSearchResults(searchResults, nameLocator, searchText);
            priceOfTheMatchedProducts = productSearchingReusables.
                    storeThePriceOfTheMatchedProducts(matchedProducts, priceLocator);
            finalProduct = productSearchingReusables.
                    findTheProductWithLowestPrice(matchedProducts, priceOfTheMatchedProducts);
            productSearchingReusables.printProductDetails(finalProduct, nameLocator, priceLocator, availabilityStatusLocator);
            productSearchingReusables.printUnMatchedProducts(searchResults, nameLocator, searchText);
        }else {
            System.out.println("No results were found for your search '"+searchText+"'.");
        }
    }

    public String getLocator(String key) {
        return locatorsFile.getProperty(key);
    }

    public String getTestData(String key) {
        return testDataFile.getProperty(key);
    }
}
