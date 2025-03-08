package test;

import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LocatorAssertion {
    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        //Locator assertions
        // containsText (locatodaki metin Sign kelimesini içeriyor mu?)
        Locator signin = page.getByText("Sign in").first();
        assertThat(signin).containsText("Sign");

        // has Attribute attirubute şu valueya (değere sahip mi)
        Locator searchBox = page.getByPlaceholder("Search for anything");
        assertThat(searchBox).hasAttribute("type","text");

        // hasText metnin taamamı vermemiz gerekiyor bu sayede doğrulama true döner
        Locator register = page.getByText("register").first();
        assertThat(register).hasText("register");

        // isEditable düzenleniliyor mu
        assertThat(searchBox).isEditable();
        System.out.println(searchBox.isEditable());

        // isEmpty boş mu değil mi onu kontrol ediyor
        assertThat(searchBox).isEmpty();

        // isVisible searvhbox görünür mü yani elementlerinn görünür olup olmadığını bununla kontrol ederiz.
        assertThat(searchBox).isVisible();


        page.close();
        browser.close();
        playwright.close();

    }
}
