package pageobjects;

import initializer.PageInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.WaitingMethods;

public class ContactUsPageObjects extends PageInitializer {

    WaitingMethods waiting = new WaitingMethods(getDriver());

    @FindBy(xpath = "//div[@id='areasOfInterest_chosen']/a")
    private WebElement areaOfInterestLocator;

    @FindBy(id = "firstName")
    private WebElement firstNameLocator;

    @FindBy(id = "lastName")
    private WebElement lastNameLocator;

    @FindBy(id = "phNum")
    private WebElement phoneNumberLocator;

    @FindBy(id = "ContactEmail")
    private WebElement emailAddressLocator;

    @FindBy(id = "companyName")
    private WebElement companyNameLocator;

    @FindBy(xpath = "//div[@id='countrySelectShip_chosen']/a")
    private WebElement countryLocator;

    @FindBy(xpath = "//div[@id='stateSelectShip_chosen']/a")
    private WebElement stateLocator;

    @FindBy(id = "city")
    private WebElement cityLocator;

    @FindBy(id = "zip")
    private WebElement zipCodeLocator;

    @FindBy(id = "add1")
    private WebElement address1Locator;

    @FindBy(id = "add2")
    private WebElement address2Locator;

    @FindBy(id = "comments")
    private WebElement questionsAndCommentsLocator;

    @FindBy(id = "contactUsSubmit")
    private WebElement contactUsSubmitButtonLocator;

    @FindBy(id = "popup_message")
    private WebElement successMessageLocator;

    @FindBy(id = "errorMsg")
    private WebElement errorMessageLocator;

    public ContactUsPageObjects selectAreaOfInterest(String areaOfInterest) throws InterruptedException {
        Thread.sleep(3500);
        areaOfInterestLocator.click();
        switch (areaOfInterest) {
            case "Open Account":
                getDriver()
                        .findElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'Open Account')]"))
                        .click();
                break;
            case "Product Information":
                getDriver()
                        .findElement(By
                                .xpath("//ul[@class='chosen-results']/li[contains(text(),'Product Information')]"))
                        .click();
                break;
            case "Credit / Accounting Inquiry":
                getDriver()
                        .findElement(By
                                .xpath("//ul[@class='chosen-results']/li[contains(text(),'Credit / Accounting Inquiry')]"))
                        .click();
                break;
            case "Other":
                getDriver()
                        .findElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'Other')]"))
                        .click();
                break;
        }
        return this;
    }

    public ContactUsPageObjects enterFirstName(String firstName) {
        firstNameLocator.sendKeys(firstName);
        return this;
    }

    public ContactUsPageObjects enterLastName(String lastName) {
        lastNameLocator.sendKeys(lastName);
        return this;
    }

    public ContactUsPageObjects enterPhoneNumber(String phoneNumber) {
        phoneNumberLocator.sendKeys(phoneNumber);
        return this;
    }

    public ContactUsPageObjects enterEmailAddress(String emailAddress) {
        emailAddressLocator.sendKeys(emailAddress);
        return this;
    }

    public ContactUsPageObjects enterCompanyName(String companyName) {
        companyNameLocator.sendKeys(companyName);
        return this;
    }

    public ContactUsPageObjects selectCountry(String country) {
        countryLocator.click();
        switch (country) {
            case "United States":
                getDriver()
                        .findElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'United States')]"))
                        .click();
                break;
        }
        return this;
    }

    public ContactUsPageObjects selectState(String state) {
        stateLocator.click();
        getDriver()
                .findElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + state + "')]"))
                .click();
        return this;
    }

    public ContactUsPageObjects enterCity(String city) {
        cityLocator.sendKeys(city);
        return this;
    }

    public ContactUsPageObjects enterZipCode(String zipCode) {
        zipCodeLocator.sendKeys(zipCode);
        return this;
    }

    public ContactUsPageObjects enterAddress1(String address) {
        address1Locator.sendKeys(address);
        return this;
    }

    public ContactUsPageObjects enterQuestionsOrComments(String questionsOrComments) {
        questionsAndCommentsLocator.sendKeys(questionsOrComments);
        return this;
    }

    public ContactUsPageObjects choosePreferredMethodOfCommunication(String preferredMethodOfCommunication) {
        switch (preferredMethodOfCommunication) {
            case "Email":
                getDriver()
                        .findElement(By.xpath("//label[@for='contactByEmail']"))
                        .click();
                break;
            case "Phone":
                getDriver()
                        .findElement(By.xpath("//label[@for='contactByPhone']"))
                        .click();
                break;
        }
        return this;
    }

    public ContactUsPageObjects clickOnSubmitRequest() throws InterruptedException {
        contactUsSubmitButtonLocator.click();
        return this;
    }

    public ContactUsPageObjects verifySuccessCustomAlertMessage(String expectedSuccessMessage) {
        waiting.waitForVisibilityOfElement(successMessageLocator, 5);
        Assert.assertEquals(successMessageLocator
                .getText()
                .trim(), expectedSuccessMessage, "pop up message is wrong");
        return this;
    }

    public ContactUsPageObjects enterAddress2(String address2) {
        address2Locator.sendKeys(address2);
        return this;
    }

    public ContactUsPageObjects verifyErrorMessage(String expectedErrorMessage) {
        waiting.waitForVisibilityOfElement(errorMessageLocator, 3);
        Assert.assertEquals(errorMessageLocator
                        .getText()
                        .replace("\n", "")
                        .trim(), expectedErrorMessage,
                "pop up message is wrong");
        return this;
    }

}
