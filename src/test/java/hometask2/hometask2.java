package hometask2;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class hometask2 {
    private String demoqaCom = "https://demoqa.com";
    private String testFormPage = "/automation-practice-form";

    SelenideElement firstNameField = $("#firstName");
    SelenideElement lastNameField = $("#lastName");
    SelenideElement mobileNumberField = $("#userNumber");

    SelenideElement genderMaleRadio = $x("//div//input[@value='Male']/parent::div[1]");
    SelenideElement genderFemaleRadio = $x("//div//input[@value='Female']/parent::div[1]");
    SelenideElement genderOtherRadio = $x("//div//input[@value='Other']/parent::div[1]");

    SelenideElement submitButton = $("#submit");


    @Test
    void requiredFieldsCheck(){

        String firstName = "FirstName";
        String lastName = "LastName";
        String mobileNumber = "1234567890";

        open(demoqaCom+testFormPage);

        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        genderMaleRadio.click();
        mobileNumberField.setValue(mobileNumber);
        String dateOfBirth = $("#dateOfBirthInput").getValue();
        dateOfBirth = dateOfBirth.substring(0, dateOfBirth.length()-5) + "," +dateOfBirth.substring(dateOfBirth.length()-4);

        submitButton.scrollTo().click();


        $x("//td[.='Student Name']/following::td[1]").shouldHave(text(firstName + " " + lastName));
        $x("//td[.='Gender']/following::td[1]").shouldHave(text("Male"));
        $x("//td[.='Mobile']/following::td[1]").shouldHave(text(mobileNumber));
        $x("//td[.='Date of Birth']/following::td[1]").getText().equals(dateOfBirth);

    }


}
