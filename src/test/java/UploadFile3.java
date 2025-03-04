import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class UploadFile3 {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("http://autopract.com/selenium/upload2/");

        // uploadad single file with file chooser(without input tag)
        Locator dosyaSecButton = page.locator("a[id='pickfiles']");

        String dosyaYolu = System.getProperty("user.home") + "/Downloads/Sticker (6).pdf";

        FileChooser fileChooser = page.waitForFileChooser(() -> {
            dosyaSecButton.click();
        });
        fileChooser.setFiles(Paths.get(dosyaYolu));

        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();


    }
}