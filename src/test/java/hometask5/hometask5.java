package hometask5;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class hometask5 {
    private String repository = "eroshenkoam/allure-example";
    private String issueNumber = "#68";

    @Test
    public void issueSearchingSelenide(){

        open("https://github.com/");
        $(byName("q")).setValue(repository).pressEnter();
        $(By.linkText(repository)).click();
        $(".UnderlineNav-body").$(byText("Issues")).click();
        $(withText(issueNumber)).shouldBe(exist);

    }

    @Test
    public void issueSearchingSteps(){

        step("Открытие Github", () -> {
            open("https://github.com/");
        });

        step("Поиски открытие репозитория" + repository, () -> {
            $(byName("q")).setValue(repository).pressEnter();
            $(By.linkText(repository)).click();
        });

        step("Переход в раздел Issues", () ->{
            $(".UnderlineNav-body").$(byText("Issues")).click();
        });

        step("Поиск Issue с номером "+issueNumber, () ->{
            $(withText(issueNumber)).shouldBe(exist);
        });
    }

}
