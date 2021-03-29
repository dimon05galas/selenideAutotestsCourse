package hometask5;

import io.qameta.allure.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class BaseSteps {
    private static final String BASE_URL = "https://github.com/";

    @Step("Открытие Github")
    public void openMainPage(){
        open(BASE_URL);
    };

    @Step("Поиск и открытие репозитория {repository}")
    public void seatchAndOpenRepository(final String REPOSITORY) {
        $(byName("q")).setValue(REPOSITORY).pressEnter();
        $(By.linkText(REPOSITORY)).click();
    };

    @Step("Переход в раздел Issues")
    public void openRepositoryIssues() {
        $(".UnderlineNav-body").$(byText("Issues")).click();
    };

    @Step("Issue с номером {number} существует")
    public void issueWithNumberExistent(final String ISSUE_NUMBER) {
        $(withText(ISSUE_NUMBER)).shouldBe(exist);
    };
}
