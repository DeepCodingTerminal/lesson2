package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadTimeout = 60000;
    }

    @Test
    void successFillTest() throws InterruptedException {
        open("/automation-practice-form");

        $x("//input[@id='firstName']").setValue("Mihail");
        $x("//input[@id='lastName']").setValue("Krylov");
        $x("//input[@id='userEmail']").setValue("Mihail@inbox.ru");

        executeJavaScript("arguments[0].click()", $(By.id("gender-radio-1")));

        $x("//input[@id='userNumber']").setValue("9053135955");
        //$("#dateOfBirthInput").clear();

        $x("//input[@id='dateOfBirthInput']").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("10");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("1990");
        $x("//div[@class='react-datepicker__month-container']//div[@aria-label='Choose Saturday, November 10th, 1990']").click();

        $x("//div[@id='subjectsContainer']//input").setValue("Maths").pressEnter();
        $x("//div[@id='subjectsContainer']//input").setValue("History").pressEnter();
        $x("//div[@id='subjectsContainer']//input").setValue("Economics").pressEnter();

        executeJavaScript("arguments[0].click()", $(By.id("hobbies-checkbox-1")));
        executeJavaScript("arguments[0].click()", $(By.id("hobbies-checkbox-2")));
        executeJavaScript("arguments[0].click()", $(By.id("hobbies-checkbox-3")));
        $x("//input[@id='uploadPicture']").uploadFile(new File("src/test/resources/picture.jpg"));

        $x("//textarea[@placeholder='Current Address']").setValue("Moscow");

        $x("//input[@id='react-select-3-input']").setValue("NCR").pressEnter();
        $x("//input[@id='react-select-4-input']").setValue("Delhi").pressEnter();
        $x("//button[@id='submit']").click();

        $("#example-modal-sizes-title-lg").shouldHave((textCaseSensitive("Thanks for submitting the form")));
        $(".table-responsive").shouldHave(
                textCaseSensitive("Student Name"),    textCaseSensitive("Mihail Krylov"),
                textCaseSensitive("Student Email"),   textCaseSensitive("Mihail@inbox.ru"),
                textCaseSensitive("Gender"),          textCaseSensitive("Male"),
                textCaseSensitive("Mobile"),          textCaseSensitive("9053135955"),
                textCaseSensitive("Date of Birth"),   textCaseSensitive("10 November,1990"),
                textCaseSensitive("Subjects"),        textCaseSensitive("Maths, History, Economics"),
                textCaseSensitive("Hobbies"),         textCaseSensitive("Sports, Reading, Music"),
                textCaseSensitive("Picture"),         textCaseSensitive("picture.jpg"),
                textCaseSensitive("Address"),         textCaseSensitive("Moscow"),
                textCaseSensitive("State and City"),  textCaseSensitive("NCR Delhi")
        );

        $x("//button[@id='closeLargeModal']").click();
    }
}
