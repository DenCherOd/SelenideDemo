package selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideParallelTests {

    @BeforeMethod
    public void setup() {
        Configuration.browser = "firefox";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void validLoginTest() {
        open("https://the-internet.herokuapp.com/login");
        $("#username").setValue("tomsmith");
        $("#password").setValue("SuperSecretPassword!");
        $("button[type='submit']").click();

        $(".flash.success").shouldHave(text("You logged into a secure area!"));
    }

    @Test
    public void invalidLoginTest() {
        open("https://the-internet.herokuapp.com/login");
        $("#username").setValue("wronguser");
        $("#password").setValue("wrongpassword");
        $("button[type='submit']").click();

        $(".flash.error").shouldHave(text("Your username is invalid!"));
    }
}
