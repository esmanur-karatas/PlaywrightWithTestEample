import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class OpeningFullScreen {
    public static void main(String[] args) throws InterruptedException {

        //sayfayı otomatik tam ekran yapma
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //genişlik ve yükseklik değeri aldık aşağıda
        int widht = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        Page page = browser.newPage();
        page.navigate("https://www.hepsiburada.com/");
        //sayfayı tam ekran yapma page.setViewportSize(1829, 1143);
        page.setViewportSize(widht,height);
        Thread.sleep(2000);
        page.close();
        browser.close();
        playwright.close();
    }
}

