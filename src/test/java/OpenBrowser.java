import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class OpenBrowser {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            System.out.println(page.title());

            page.close();//belirli bir sayfayı kapatıyor
            browser.close();//tarayıcıyı kapatıyor
            playwright.close();//testin bitmesini sağlamak için
        }
    }
}
