package test;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class UploadFile1 {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://the-internet.herokuapp.com/upload");

        // Select one file
        Locator dosyaSecButton = page.locator("input[id='file-upload']");
        String dosyaYolu = System.getProperty("user.home")+ "/Downloads/Sticker (6).pdf";

        dosyaSecButton.setInputFiles(Paths.get(dosyaYolu));

        page.locator("input[id='file-submit']").click();


        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();


    }
}