import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;


public class DragAndDrop {
    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://demoqa.com/droppable");

        //Drag and Drop
       // page.getByText("Drag me").first().dragTo(page.getByText("Drop here").first());


        // manually
        page.getByText("Drag me").first().hover();
        page.mouse().down();
        page.getByText("Drop here").first().hover();
        page.mouse().up();



        Thread.sleep(3000);
        page.close();
        browser.close();
        playwright.close();


    }
}
