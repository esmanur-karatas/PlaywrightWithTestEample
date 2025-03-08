package test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PageAssertion {
    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        // page assertions

        // hasUrl url eşleşiyor mu
        assertThat(page).hasURL("https://www.ebay.com/");

        // hasTitle başlığın tammamına sahip mi onu dpğrular
        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");

        // not () başlık eşleşiyor mu aynı mı ona sahip değilse yani test kelimesine sahip değil
        assertThat(page).not().hasTitle("test");

        page.close();
        browser.close();
        playwright.close();


    }

}

