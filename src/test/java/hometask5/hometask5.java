package hometask5;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class hometask5 {
    private String repository = "eroshenkoam/allure-example";
    private String issueNumber = "#68";

    @Test
    public void searchingForIssue(){


        open("https://github.com/");
        $(byName("q")).setValue(repository).pressEnter();
        $(By.linkText(repository)).click();
        $(".UnderlineNav-body").$(byText("Issues")).click();
        $(withText(issueNumber)).shouldBe(exist);
    }
}
