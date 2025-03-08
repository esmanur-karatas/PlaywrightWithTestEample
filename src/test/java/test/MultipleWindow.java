package test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;
import java.util.List;

public class MultipleWindow {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/browser-windows");

        // multiple window
        // Get popup after a specific action (e.g., click)
        Page popup = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p-> p.context().pages().size() == 2), ()->{
            page.getByText("New Window").first().click();
        });

        List<Page> pages = popup.context().pages();
        System.out.println("sayfa sayisi " + pages.size());

        pages.forEach(tab->{
            tab.waitForLoadState();
            System.out.println(tab.title());
        });

        /*
        System.out.println("ilk sayfanin url ' i " + pages.get(0).url());
        System.out.println("ikinci sayfanin url ' i " + pages.get(1).url());

        Page fPage = null;
        Page sPage = null;

        if(pages.get(0).url().equals("https://demoqa.com/browser-windows")){

            fPage = pages.get(0);
        }
        else {
            sPage = pages.get(1);
        }

        Thread.sleep(3000);
        fPage.bringToFront();

         */

        Page fPage = null;
        Page sPage = null;

        if(pages.get(0).title().equals("DEMOQA")){
            fPage = pages.get(0);
        }else{
            sPage = pages.get(1);
        }

        Thread.sleep(3000);
        fPage.bringToFront();


        Thread.sleep(5000);
        page.close();
        browser.close();
        playwright.close();


    }
}