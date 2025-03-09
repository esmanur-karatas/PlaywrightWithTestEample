package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;

public class BrowserFactory {
    private Playwright playwright;

    // Constructor içinde Playwright başlatılıyor
    public BrowserFactory() {
        playwright = Playwright.create();
    }

    // Browser nesnesini başlatan metot
    public Browser getBrowser(String browserName) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        BrowserType browserType;

        switch (browserName.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "safari":
                browserType = playwright.webkit();
                break;
            case "edge":
                browserType = playwright.chromium();
                launchOptions.setChannel("msedge"); // Edge tarayıcısını doğru başlat
                break;
            default:
                throw new IllegalArgumentException(
                        "Invalid browser name: " + browserName +
                                ". Please specify one of the supported browsers: firefox, safari, chrome, edge."
                );
        }
        return browserType.launch(launchOptions);
    }

    // Tarayıcıyı açıp test ismiyle birlikte Playwright tracing başlatan metot
    public BrowserContext createPageAndGetContext(Browser browser, ITestResult result) {
        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
                .setName(result.getMethod().getMethodName()));
        return context;
    }

    // Playwright nesnesini kapatan metot
    public void close() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
