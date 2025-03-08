package test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class Window {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();


        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/browser-windows");

        // Get popup after a specific action (e.g., click)
        Page popup = page.waitForPopup(() -> {
            page.getByText("New Window").first().click();
        });
        popup.waitForLoadState();
        System.out.println("sayfanin basligi " + popup.title());

        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();

    }
}