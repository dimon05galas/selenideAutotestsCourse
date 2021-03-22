package hometask4;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class hometask4 {
    private String testPage = "https://demoqa.com/automation-practice-form";

    SelenideElement firstNameField = $("#firstName");
    SelenideElement lastNameField = $("#lastName");
    SelenideElement mobileNumberField = $("#userNumber");
    SelenideElement genderMaleRadio = $x("//div//input[@value='Male']/parent::div[1]");
    SelenideElement submitButton = $("#submit");


    @Test
    void validationCheck(){

        String colorRed = "rgb(220, 53, 69)";
        String colorRedRadio = "rgba(220, 53, 69, 1)";

        open(testPage);
        $("#submit").scrollTo().click();

        firstNameField.shouldHave(cssValue("border-color", colorRed));
        lastNameField.shouldHave(cssValue("border-color", colorRed));
        mobileNumberField.shouldHave(cssValue("border-color", colorRed));

        $x("//div//label[.='Male']").shouldHave(cssValue("color", colorRedRadio));
        $x("//div//label[.='Female']").shouldHave(cssValue("color", colorRedRadio));
        $x("//div//label[.='Other']").shouldHave(cssValue("color", colorRedRadio));

    }

    @Test
    void checkRequiredFields(){

        Faker faker = new Faker();

        String  firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                mobileNumber = faker.phoneNumber().subscriberNumber(10);

        open(testPage);

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
    void checkAllFields(){
        Faker faker = new Faker();

        String  firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                studentEmail = faker.internet().emailAddress(),
                testAddress = faker.address().country()+", "+faker.address().city()+", "+faker.address().streetAddress()+", "+faker.address().buildingNumber(),
                mobileNumber = faker.phoneNumber().subscriberNumber(10),
                testYear = String.valueOf(faker.number().numberBetween(1900,2020)),
                testDay = String.valueOf(faker.number().numberBetween(1,25)),
                testMonth = randomMonth(),
                testState = randomState(),
                testCity = randomCity(testState);



        open(testPage);

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
        $("#subjectsInput").setValue("En");
        $x("//div[contains(@class, 'subjects-auto-complete__menu')]//div[.='English']").click();
        $x("//div[@id='hobbiesWrapper']//label[.='Sports']/parent::div[1]").click();
        $x("//div[@id='hobbiesWrapper']//label[.='Music']/parent::div[1]").click();

        File testImg = new File("./src/test/java/hometask4/testImg.jpg");
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



    public String randomFromArray(String[] string){
        Faker faker= new Faker();
        return string[faker.number().numberBetween(0, string.length-1)];
    }

    public String randomMonth(){
        String[] seasons  = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return randomFromArray(seasons);
    }

    public String randomState(){
        String[] states  = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return randomFromArray(states);
    }

    public String randomCity(String state){
        String[] cities;

        switch (state){
            case "NCR":
                cities = new String[]{"Delhi", "Gurgaon", "Noida"};
                break;

            case "Uttar Pradesh":
                cities = new String[]{"Agra", "Lucknow", "Merrut"};
                break;

            case "Haryana":
                cities = new String[]{"Karnal", "Panipat"};
                break;

            case "Rajasthan":
                cities = new String[]{"Jaipur", "Jaiselmer"};
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }

        return randomFromArray(cities);
    }

}