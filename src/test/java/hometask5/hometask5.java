package hometask5;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class hometask5 {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";
    private static final String BASE_URL = "https://github.com/";

    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Поиск Issue по номеру в репозитории (чистый Selenide)")
    @Owner("Dmitry Galas")
    @Tags({@Tag("web"), @Tag("critical")})
    public void issueSearchingSelenide(){

        open(BASE_URL);
        $(byName("q")).setValue(REPOSITORY).pressEnter();
        $(By.linkText(REPOSITORY)).click();
        $(".UnderlineNav-body").$(byText("Issues")).click();
        $(withText(ISSUE_NUMBER)).shouldBe(exist);

    }

    @Test
    @DisplayName("Поиск Issue по номеру в репозитории (лямбда-степы)")
    @Owner("Dmitry Galas")
    @Link(name = "BASE_URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue по номеру в репозитории")
    public void issueSearchingSteps(){
        parameter("Repository", REPOSITORY);
        parameter("Issue number", ISSUE_NUMBER);
        step("Открытие Github", () -> {
            open(BASE_URL);
        });

        step("Поиск и открытие репозитория" + REPOSITORY, () -> {
            $(byName("q")).setValue(REPOSITORY).pressEnter();
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переход в раздел Issues", () ->{
            $(".UnderlineNav-body").$(byText("Issues")).click();
        });

        step("Поиск Issue с номером "+ ISSUE_NUMBER, () ->{
            $(withText(ISSUE_NUMBER)).shouldBe(exist);
        });
    }


    @Test
    @DisplayName("Поиск Issue по номеру в репозитории (Стэпы с аннотациями)")
    @Owner("Dmitry Galas")
    @Link(name = "BASE_URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue по номеру в репозитории")
    public void issueSearchingStepsAnnotations(){
        parameter("Repository", REPOSITORY);
        parameter("Issue number", ISSUE_NUMBER);

        steps.openMainPage();
        steps.seatchAndOpenRepository(REPOSITORY);
        steps.openRepositoryIssues();
        steps.issueWithNumberExistent(ISSUE_NUMBER);


    }





}
