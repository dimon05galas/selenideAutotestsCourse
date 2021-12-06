package hometask2;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.cssValue;
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


    @Test
    void allFieldsCheck(){

        String firstName = "FirstName";
        String lastName = "LastName";
        String studentEmail = "testUser@mail.com";
        String mobileNumber = "1234567890";
        String testAddress = "testCity, testStreet, 100-150";
        String testState = "NCR";
        String testCity = "Noida";

        String testYear = "1990";
        String testMonth = "June";
        String testDay = "12";

        open(demoqaCom+testFormPage);

        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        $("#userEmail").setValue(studentEmail);
        genderMaleRadio.click();
        mobileNumberField.setValue(mobileNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $x("//select[@class='react-datepicker__year-select']/option[@value='"+testYear+"']").click();
        $x("//select[@class='react-datepicker__month-select']/option[.='"+testMonth+"']").click();
        $x("//div[contains(@class, 'react-datepicker__day')][.='"+testDay+"']").click();
        //$("#dateOfBirthInput").setValue("30 Jan 2003");

        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#subjectsInput").setValue("En").pressEnter();
        $x("//div[@id='subjectsContainer']//div[.='English']").shouldBe(visible);

        $x("//div[@id='hobbiesWrapper']//label[.='Sports']/parent::div[1]").click();
        $x("//div[@id='hobbiesWrapper']//label[.='Music']/parent::div[1]").click();

        File testImg = new File("./src/test/java/hometask2/testImg.jpg");
        $("#uploadPicture").uploadFile(testImg);

        $x("//textarea[@placeholder='Current Address']").setValue(testAddress);
        $x("//div[@id='state']").scrollTo().click();
        $x("//div[@id='state']//div[.='"+testState+"']").click();
        $x("//div[@id='city']").scrollTo().click();
        $x("//div[@id='city']//div[.='"+testCity+"']").click();

        submitButton.scrollTo().click();

        $x("//td[.='Student Name']/following::td[1]").shouldHave(text(firstName + " " + lastName));
        $x("//td[.='Student Email']/following::td[1]").shouldHave(text(studentEmail));
        $x("//td[.='Gender']/following::td[1]").shouldHave(text("Male"));
        $x("//td[.='Mobile']/following::td[1]").shouldHave(text(mobileNumber));
        $x("//td[.='Date of Birth']/following::td[1]").getText().equals(testDay+" "+testMonth+","+testYear);
        $x("//td[.='Subjects']/following::td[1]").getText().equals("Maths, Arts, English" );
        $x("//td[.='Hobbies']/following::td[1]").getText().equals("Sports, Music" );
        $x("//td[.='Picture']/following::td[1]").getText().equals(testImg.getName());
        $x("//td[.='Address']/following::td[1]").getText().equals(testAddress);
        $x("//td[.='State and City']/following::td[1]").getText().equals(testState + " " + testCity);

        $("#closeLargeModal").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNot(visible);

    }

    @Test
    void validationCheck(){

        String colorRed = "rgb(220, 53, 69)";
        String colorRedRadio = "rgba(220, 53, 69, 1)";

        open(demoqaCom+testFormPage);
        $("#submit").scrollTo().click();

        firstNameField.shouldHave(cssValue("border-color", colorRed));
        lastNameField.shouldHave(cssValue("border-color", colorRed));
        mobileNumberField.shouldHave(cssValue("border-color", colorRed));

        $x("//div//label[.='Male']").shouldHave(cssValue("color", colorRedRadio));
        $x("//div//label[.='Female']").shouldHave(cssValue("color", colorRedRadio));
        $x("//div//label[.='Other']").shouldHave(cssValue("color", colorRedRadio));

    }


}
