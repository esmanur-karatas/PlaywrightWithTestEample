package test;

import com.microsoft.playwright.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class VideoRecording {
    public static void main(String[] args) throws InterruptedException, IOException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String filePath = System.getProperty("user.dir") + "/src/test/java/utilities/video/" + tarih;

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().
                setRecordVideoDir(Paths.get(filePath)).setRecordVideoSize(640,480));

        Page page = context.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://getir.com/");

        // getByRole
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("2. phone number " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("590-345");

        Thread.sleep(2000);

        Path path = page.video().path();
        System.out.println(path);


        context.close();
        page.close();
        //page.video().saveAs(Paths.get(filePath + "test"));
        //page.video().delete();

        playwright.close();

    }
}