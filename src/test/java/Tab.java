
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class Tab {
    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://the-internet.herokuapp.com/windows");

        // Get page after a specific action (e.g. clicking a link)
        Page newPage = page.context().waitForPage(() -> {
            page.getByText("Click Here").click(); // Opens a new tab
        });
        newPage.waitForLoadState();
        newPage.setViewportSize(width, height);
        System.out.println("yeni sayfa basligi " + newPage.title());

        Thread.sleep(5000);

        // Eski sekmeye geri dön
        page.bringToFront();


        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();

    }

}