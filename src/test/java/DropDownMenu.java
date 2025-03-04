import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.awt.*;

public class DropDownMenu {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        // Select options
        Locator selectCategory = page.getByLabel("Select a category for search");
        Thread.sleep(3000);

        // select by value
        selectCategory.selectOption("2984"); // baby
        Thread.sleep(3000);

        // select by label
        selectCategory.selectOption(new SelectOption().setLabel("Consumer Electronics"));
        Thread.sleep(3000);


        page.close();
        browser.close();
        playwright.close();


    }
}
