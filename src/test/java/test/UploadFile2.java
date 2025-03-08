package test;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile2 {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://demo.automationtesting.in/FileUpload.html");

        // Select multiple files
        Locator dosyaSecButton = page.locator("input[id='input-4']");

        String dosyaYolu = System.getProperty("user.home")+ "/Downloads/Sticker (6).pdf";
        String dosyaYolu2 = System.getProperty("user.home")+ "/Downloads/Sticker (6).pdf";

        dosyaSecButton.setInputFiles(new Path[] {Paths.get(dosyaYolu), Paths.get(dosyaYolu2)});

        Locator uploadButton = page.getByTitle("Upload selected files");
        uploadButton.click();


        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();


    }
}