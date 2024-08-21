package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SeleniumGridDownloadDemo {
    private WebDriver driver;
    private static final String DOWNLOAD_DIR = "C:\\Users\\denys.cheran\\Desktop\\TestFiles";

    @Parameters({"browser", "hubUrl"})
    @BeforeClass
    public void setUp(String browser, String hubUrl) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);

        if (browser.equals("chrome")) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", DOWNLOAD_DIR);
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.prompt_for_download", false);

            org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            capabilities.setCapability(org.openqa.selenium.chrome.ChromeOptions.CAPABILITY, options);
        }

        driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
    }

    @Test
    public void testFileDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");

        WebElement link = driver.findElement(By.xpath("//a[text()='some-file.txt']"));
        link.click();

        Thread.sleep(5000);

        File downloadedFile = Paths.get(DOWNLOAD_DIR, "some-file.txt").toFile();
        Assert.assertTrue(downloadedFile.exists(), "File should be downloaded");
        Assert.assertTrue(downloadedFile.length() > 0, "File should not be empty");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
