package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class SelenideGridDownloadDemo {

    @Parameters({"browser", "hubUrl"})
    @BeforeClass
    public void setUp(String browser, String hubUrl) {
        Configuration.remote = hubUrl;
        Configuration.browser = browser;
        Configuration.downloadsFolder = "C:\\Users\\denys.cheran\\Desktop\\TestFiles";
        Configuration.fileDownload = FileDownloadMode.HTTPGET;
    }

    @Test
    public void testFileDownload() throws IOException {
        open("https://the-internet.herokuapp.com/download");

        File downloadedFile = $x("//a[text()='some-file.txt']").download();

        assertTrue(downloadedFile.exists(), "File should be downloaded");
        assertTrue(downloadedFile.length() > 0, "File should not be empty");
    }
}
