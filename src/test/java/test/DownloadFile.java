package test;

import com.microsoft.playwright.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFile {

    public static void main(String[] args) throws InterruptedException, IOException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://demoqa.com/upload-download");

        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();

            // page.locator("a[id='downloadButton']").click();
        });


        // download.cancel();
        /*
        System.out.println(download.url());
        System.out.println(download.page().title());
        System.out.println(download.path().toString());


        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String farkliKisim = System.getProperty("user.home");
        String ortakKisim = "/Downloads/" + tarih + ".jpg";
        // Wait for the download process to complete and save the downloaded file somewhere
        download.saveAs(Paths.get(farkliKisim+ortakKisim));

        System.out.println(download.path().toString());

         */
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String farkliKisim = System.getProperty("user.home");
        String ortakKisim = "/Downloads/";

        System.out.println(download.url());
        System.out.println(download.page().title());
        download.saveAs(Paths.get(farkliKisim+ortakKisim, download.suggestedFilename()));

        Thread.sleep(5000);
        boolean isFileDownloaded = Files.exists(Paths.get(farkliKisim+ortakKisim));
        assert isFileDownloaded;
        System.out.println(isFileDownloaded);
        download.delete();
        System.out.println(Files.exists(Paths.get(farkliKisim+ortakKisim, download.suggestedFilename())));

        Files.deleteIfExists(Paths.get(farkliKisim+ortakKisim, download.suggestedFilename()));


        page.close();
        browser.close();
        playwright.close();

    }
}