package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DetailsPage extends BasePage{
    public DetailsPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//input[@data-testid='u_givenname_adult_0_input']")
    WebElement GivenName;
    @FindBy(xpath = "//input[@data-testid='u_surname_adult_0_input']")
    WebElement SurName;
    @FindBy(xpath = "//input[@data-testid='u_gender_adult_0_input']")
    WebElement GenderField;
    @FindBy(xpath = "//ul[@data-testid='u_gender_adult_0_list']/li[2]")
    WebElement Male_Gender;
    @FindBy(xpath = "//input[@data-testid='u_birthday_adult_0_input']")
    WebElement DateField;
    @FindBy(xpath = "//input[@data-testid='u_nationality_adult_0_input']")
    WebElement NationalityField;
    @FindBy(xpath= "//input[@data-testid='u_idnum_adult_0_input']")
    WebElement IDField;
    @FindBy(xpath = "//input[@data-testid='u_iddate_adult_0_input']")
    WebElement IDExpField;
    @FindBy(xpath = "//div[text()='I am aware of the risks of not protecting my trip']")
    WebElement NoInsurance;
    @FindBy(xpath="//input[@data-testid='u_contact_name_input']")
    WebElement ContactName;
    @FindBy(xpath="//input[@data-testid='email-input']")
    WebElement Email;
    @FindBy(xpath="//input[@data-testid='u_contact_phone_input']")
    WebElement Phone;
    @FindBy(xpath = "//button[@data-testid='u_next_btn']")
    WebElement NextButton;
    @FindBy(xpath="//div[@data-testid='refundProtectPkg__content-notAddProtect']")
    WebElement NoRefundProtect;
    @FindBy(xpath="//button[@data-testid='u_pay_now_btn']")
    WebElement FinalConfirmation;

    public void enterPassengerDetails(String givenName, String surname, String gender, String bDay, String bMonth, String bYear) throws InterruptedException{
        By birthday = By.xpath("//div[text()='"+bDay+"']");
        By birthMonth = By.xpath("//span[text()='"+bMonth+"']");
        By birthYear = By.xpath("//span[text()='"+bYear+"']");

        GenderField.click();
        if(gender.equals("Male")){
            Male_Gender.click();
        }

        Thread.sleep(1500);
        DateField.click();

        WebElement date = driver.findElement(birthday);
        date.click();
        WebElement month  = driver.findElement(birthMonth);
        month.click();

        Thread.sleep(1500);
        WebElement year = driver.findElement(birthYear);
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", year);
        wait1.until(ExpectedConditions.elementToBeClickable(year));
        year.click();

        Thread.sleep(1500);
        GivenName.sendKeys(givenName);
        SurName.sendKeys(surname);
    }

    public void enterPassportDetails(String Nationality, String id, String expDay, String expMonth, String expYear){
        By nationality = By.xpath("//li[@class='list-item']/span[contains(text(),'"+Nationality+"')]");
        By expday = By.xpath("//div[text()='"+expDay+"']");
        By expmonth = By.xpath("//span[text()='"+expMonth+"']");
        By expyear = By.xpath("//span[text()='"+expYear+"']");

        try{
            NationalityField.click();
            WebElement country = driver.findElement(nationality);
            System.out.println(country);
            WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", country);
            wait2.until(ExpectedConditions.elementToBeClickable(country));
            country.click();
            Thread.sleep(1500);

            IDField.sendKeys(id);
            Thread.sleep(1500);
            IDExpField.click();
            WebElement edate = driver.findElement(expday);
            edate.click();
            WebElement emonth  = driver.findElement(expmonth);
            emonth.click();

            Thread.sleep(1500);
            WebElement eyear = driver.findElement(expyear);
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eyear);
            wait3.until(ExpectedConditions.elementToBeClickable(eyear));
            eyear.click();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Continue");
        }
    }

    public void enterContactDetails(String contactName, String email, String phoneNum) throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        NoInsurance.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ContactName.sendKeys(contactName);
        Email.sendKeys(email);
        Phone.sendKeys(phoneNum);
        Thread.sleep(1000);
    }

    public void proceedToAddons(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        NextButton.click();
        driver.findElement(By.xpath("//div[text()='No Thanks']")).click();

    }

    public void proceedToPayment(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        try{NoRefundProtect.click();}
        catch (Exception e){}
        finally {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            NextButton.click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            FinalConfirmation.click();

    }

}}
