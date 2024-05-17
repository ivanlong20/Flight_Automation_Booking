package com.booking.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.booking.pages.HomePage;
import com.booking.pages.DetailsPage;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends BaseTest{
    @Test
    public void successfulSearchFlightTest() throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        DetailsPage detailsPage = new DetailsPage(driver);
        String dcity = "hkg";
        String acity = "tyo";
        String ddate = "2024-06-01";
        String rdate = "2024-06-07";
        driver.get("https://hk.trip.com/flights/hong-kong-to-tokyo/tickets-hkg-tyo?dcity="+dcity+"&acity="+acity+"&ddate="+ ddate+"&rdate="+ rdate+"&flighttype=rt&class=y&lowpricesource=searchform&quantity=1&searchboxarg=t&nonstoponly=off&locale=en-HK&curr=HKD");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.clickSortBarCheapest();
        homePage.selectFlights();
        homePage.bookFlight();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> set = driver.getWindowHandles();
        System.out.println(set);
        driver.switchTo().window((String) set.toArray()[1]);
        detailsPage.enterPassengerDetails("Tai Man", "Chan", "Male");
    }
}
