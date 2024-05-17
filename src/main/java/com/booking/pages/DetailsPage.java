package com.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsPage extends BasePage{
    public DetailsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@data-testid='u_givenname_adult_0_input']")
    WebElement GivenName;
    @FindBy(xpath = "//input[@data-testid='u_surname_adult_0_input']")
    WebElement SurName;
    @FindBy(xpath = "//input[@data-testid='u_gender_adult_0_input']")
    WebElement Gender;

    public void enterPassengerDetails(String givenName, String surname, String gender){
        GivenName.sendKeys(givenName);
        SurName.click();
        SurName.sendKeys(surname);
        Gender.sendKeys(gender);
    }

}
