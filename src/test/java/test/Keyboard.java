package test;

import com.microsoft.playwright.*;

import java.awt.*;

public class Keyboard {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://demoqa.com/login");

        Locator userName = page.getByPlaceholder("UserName");
        userName.click();

        // insertText
        page.keyboard().insertText("gokhan");
        Thread.sleep(2000);

        // press
        page.keyboard().press("Meta+A");
        page.keyboard().press("Backspace");
        Thread.sleep(2000);

        // type
        page.keyboard().type("agar");

        // down
        page.keyboard().down("Shift");
        page.keyboard().press("A");

        // up
        page.keyboard().up("Shift");
        page.keyboard().press("b");




        Thread.sleep(3000);
        page.close();
        browser.close();
        playwright.close();

    }
}