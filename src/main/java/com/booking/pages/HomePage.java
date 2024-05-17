package com.booking.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    By SortBarCheapest = By.cssSelector("div.sort-type-item[data-testid='sort_type_item_1']");
    @FindBy(xpath = "//div[@data-testid='u-flight-card-1']/div[1]/div[1]/div[2]/div[2]/div[2]/span[1]/button[1]")
    WebElement SelectButton;
    @FindBy(xpath = "//div[@data-testid='u_policy_wrapper_1-0']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/span[1]/button[1]")
    WebElement BookButton;

    public void clickSortBarCheapest() throws InterruptedException{
        Thread.sleep(12000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement cheapestButton = wait.until(ExpectedConditions.elementToBeClickable(SortBarCheapest));
        cheapestButton.click();

    }

    public void selectFlights(){
        SelectButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SelectButton.click();
    }

    public void bookFlight(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BookButton.click();
    }

}
