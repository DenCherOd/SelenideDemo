package selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLoginDemo {

    @BeforeClass
    public void setUp() {
        Configuration.browser = "firefox";
    }

    @Test
    public void testLogin() {
        open("https://the-internet.herokuapp.com/login");

        $("[id='username']").setValue("tomsmith");
        $("[id='password']").setValue("SuperSecretPassword!");
        $("[type='submit']").click();

        $("#flash").shouldHave(text("You logged into a secure area!"));
    }
}
