package hometask5;


import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



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
        $("#issues-tab").click();

    };

    @Step("Issue с номером {number} существует")
    public void issueWithNumberExistent(final String ISSUE_NUMBER) {
        $(withText(ISSUE_NUMBER)).shouldBe(exist);
    };
}
