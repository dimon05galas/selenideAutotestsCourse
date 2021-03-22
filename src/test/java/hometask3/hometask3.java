package hometask3;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class hometask3 {


    @Test
    void checkGitHubPage() {

        //open("https://github.com/selenide/selenide");

        open("https://github.com/");
        $x("//input[@name='q']").setValue("Selenide").pressEnter();
        $x("//a[@href='/selenide/selenide']").click();

        $x("//span[@itemprop='author']/a").shouldHave(text("selenide"));
        $x("//strong[@itemprop='name']/a").shouldHave(text("selenide"));

        $x("//li[@class='d-flex']//span[.='Wiki']//parent::a").click();
        $x("//ul[@data-filterable-for='wiki-pages-filter']").$(byText("SoftAssertions")).shouldBe(enabled);

        $x("//ul[@data-filterable-for='wiki-pages-filter']").$(byText("SoftAssertions")).scrollTo().click();
//        $(withText("JUnit5 extension - ")).shouldBe(exist);
        $(withText("com.codeborne.selenide.junit5.SoftAssertsExtension")).shouldBe(exist);

        $(withText("Using JUnit5 extend test class:")).shouldBe(exist);
    }

    @Test
    void dragAndDrop(){
        open("https://the-internet.herokuapp.com/drag_and_drop");

        SelenideElement columnA = $("#column-a");
        SelenideElement columnB = $("#column-b");

        columnA.shouldHave(text("A"));
        columnB.shouldHave(text("B"));

        sleep(3000);
        actions().moveToElement(columnA).clickAndHold().moveByOffset(200,0).release().perform();
        //actions().dragAndDropBy(columnA, 200, 0).perform();

        columnA.shouldHave(text("B"));
        columnA.shouldHave(text("A"));

    }

}
