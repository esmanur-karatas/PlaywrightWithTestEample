import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class Actions {
      public static void main(String[] args) {


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        // Text input
        Locator searchBox = page.getByPlaceholder("Search for anything");
        searchBox.fill("bicycle");

        // Keys and shortcuts
        // searchBox.press("Enter");
        page.keyboard().press("Enter");//entera basmak için
        Thread.sleep(3000);

        Locator register = page.getByText("register").first();
        register.click();


        // Checkboxes and radio buttons
        Locator businessAccount = page.getByText("Business account");
       /*
        businessAccount.check();
        assertThat(businessAccount).isChecked();
        Thread.sleep(3000);

        */

        businessAccount.setChecked(true);
        assertThat(businessAccount).isChecked();

        page.goBack();

        // Select options
        Locator selectCategory = page.getByLabel("Select a category for search");

        // select by value
        selectCategory.selectOption("2984");


        // select by label
        selectCategory.selectOption(new SelectOption().setLabel("Consumer Electronics"));



        page.close();
        browser.close();
        playwright.close();

    }
}

