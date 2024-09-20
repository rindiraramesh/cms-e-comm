package pageobjects;

import initializer.PageInitializer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.TestDataPropertyFile;
import utility.WaitingMethods;

public class QuickOrderPadPreviewPageObjects extends PageInitializer {

    TestDataPropertyFile data = new TestDataPropertyFile();

    Actions action = new Actions(getDriver());

    WaitingMethods waiting = new WaitingMethods(getDriver());

    @FindBy(xpath = "//a[text()='Show More Entry Fields']")
    private WebElement showMoreEntryFieldsLink;

    /*public QuickOrderPageObjects clickOnShowEntryFieldsLink() {
        //waiting.explicitWaitElementToBeClickable(showMoreEntryFieldsLink, 6);
        showMoreEntryFieldsLink.click();
        return quickOrderPadPage();
    }*/

}
