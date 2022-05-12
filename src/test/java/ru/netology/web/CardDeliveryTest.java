package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CardDeliveryTest {

    LocalDate today = LocalDate.now();
    LocalDate newDate = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String meetingDate = newDate.format(formatter);

    @Test
    void shouldRegistredDeliveryOfTheCard(){
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Благовещенск");
        $("[placeholder=\"Дата встречи\"]").setValue(meetingDate);
        $("[name=\"name\"]").setValue("Ильф Петров");
        $("[name=\"phone\"]").setValue("+79146551203");
        $("[data-test-id=\"agreement\"]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }
}
