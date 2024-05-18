package com.booking.tests;

import com.booking.utils.ExcelUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.booking.pages.HomePage;
import com.booking.pages.DetailsPage;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends BaseTest{
    @DataProvider(name="testData")
    public Iterator<Object[]> getTestData() throws IOException {
        return ExcelUtils.getTestData();
    }

    @Test(dataProvider = "testData")
    public void successfulSearchFlightTest(String departureCity, String destinationCity, String departureDate, String returnDate, String nationality,
                                           String passportID, String expiryDay, String expiryMonth, String expiryYear, String givenName, String surname,
                                           String gender, String birthDay, String birthMonth, String birthYear, String contactName, String email, String phone) throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        DetailsPage detailsPage = new DetailsPage(driver);
        driver.get("https://hk.trip.com/flights/hong-kong-to-tokyo/tickets-hkg-tyo?dcity="+departureCity+"&acity="+destinationCity+"&ddate="+ departureDate+"&rdate="+ returnDate+"&flighttype=rt&class=y&lowpricesource=searchform&quantity=1&searchboxarg=t&nonstoponly=off&locale=en-HK&curr=HKD");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.clickSortBarCheapest();
        homePage.selectFlights();
        homePage.bookFlight();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> set = driver.getWindowHandles();
        System.out.println(set);
        driver.switchTo().window((String) set.toArray()[1]);
        detailsPage.enterPassportDetails(nationality, passportID,expiryDay,expiryMonth,expiryYear);
        detailsPage.enterPassengerDetails(givenName, surname, gender, birthDay,birthMonth,birthYear);
        detailsPage.enterContactDetails(contactName, email, phone);
        detailsPage.proceedToAddons();
        detailsPage.proceedToPayment();
    }
}
