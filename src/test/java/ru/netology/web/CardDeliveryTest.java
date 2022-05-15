package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CardDeliveryTest {
    public String generateDate(int planningDate) {
        return LocalDate.now().plusDays(planningDate).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
}

    @Test
    void shouldRegistredDeliveryOfTheCardIfDateAutomatically(){
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Благовещенск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[name=\"name\"]").setValue("Ильф Петров");
        $("[name=\"phone\"]").setValue("+79146551203");
        $("[data-test-id=\"agreement\"]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void shouldRegistredDeliveryOfTheCardIfInputDateManually(){
        String planningDate = "25.05.2022";
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Благовещенск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[name=\"name\"]").setValue("Ильф Петров");
        $("[name=\"phone\"]").setValue("+79146551203");
        $("[data-test-id=\"agreement\"]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}
