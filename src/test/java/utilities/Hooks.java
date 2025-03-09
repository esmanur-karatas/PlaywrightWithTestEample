package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends TestListenerAdapter {
    private Playwright playwright;
    private Browser browser;
    public static Page page;
    private BrowserContext context;

    @Override
    public void onTestStart(ITestResult result) {
        try {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) dimension.getWidth();
            int height = (int) dimension.getHeight();

            BrowserFactory browserFactory = new BrowserFactory();
            String browserName = PropertyUtils.getProperty("browser");

            if (browserName == null || browserName.isEmpty()) {
                throw new RuntimeException("Browser property is missing or could not be read!");
            }

            this.browser = browserFactory.getBrowser(browserName);
            if (this.browser == null) {
                throw new RuntimeException("Failed to initialize browser: " + browserName);
            }

            this.context = browserFactory.createPageAndGetContext(this.browser, result);
            if (this.context == null) {
                throw new RuntimeException("Failed to create BrowserContext!");
            }

            this.page = context.newPage();
            page.setViewportSize(width, height);

            String url = PropertyUtils.getProperty("getir");
            if (url == null || url.isEmpty()) {
                throw new RuntimeException("Test URL is missing in properties file!");
            }
            page.navigate(url);

        } catch (Exception e) {
            System.err.println("Error in onTestStart: " + e.getMessage());
            e.printStackTrace();
            throw e;  // TestNG'ye hatayı bildir
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        cleanupOldTraces();
        if (context != null) {
            try {
                context.tracing().stop();
            } catch (Exception e) {
                System.err.println("Error stopping tracing on test success: " + e.getMessage());
            }
        }
        cleanup();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (context != null) {
            try {
                String tracePath = getTraceFilePath(result);
                context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));
            } catch (Exception e) {
                System.err.println("Error stopping tracing on test failure: " + e.getMessage());
            }
        } else {
            System.err.println("Context is null, skipping trace saving.");
        }
        cleanup();
    }

    private String getTraceFilePath(ITestResult result) {
        String baseDir = "src/test/java/utilities/traceViewer/";
        String methodName = result.getMethod().getMethodName();
        String date = new SimpleDateFormat("hh_mm_ss_ddMMyyyy").format(new Date());
        return baseDir + methodName + "_" + date + "-trace.zip";
    }

    private void cleanup() {
        try {
            if (context != null) context.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }

    private void cleanupOldTraces() {
        final long EXPIRATION_TIME = 86400000; // 24 saat
        File dir = new File("src/test/java/utilities/traceViewer/");
        File[] files = dir.listFiles();

        if (files != null) {
            long now = System.currentTimeMillis();
            for (File file : files) {
                if (now - file.lastModified() > EXPIRATION_TIME) {
                    if (!file.delete()) {
                        System.err.println("Failed to delete old trace file: " + file.getPath());
                    }
                }
            }
        }
    }
}
