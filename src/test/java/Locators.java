import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class Locators {
    public static void main(String[] args) throws InterruptedException {
        //sayfayı otomatik tam ekran yapma
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //genişlik ve yükseklik değeri aldık aşağıda
        int widht = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        Page page = browser.newPage();
        page.navigate("https://getir.com/");
        System.out.println("Site Başlık: " + page.title());
        //sayfayı tam ekran yapma page.setViewportSize(1829, 1143);
        page.setViewportSize(widht, height);

        //locator text ile alma getByText()
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. Text: " + loginText.innerText()); //innerText Sleniumdaki getText gibi locatorun textini alır


        // Locate in Shadow Dom burada elementin adı önce yazılır sonra içindekii test yazılır en sonda yazdığımız last ise selaniumda aynı locatora sahip birden fazla locater varsa indexe göre alıyorduk burda da onun yerine last before after falan gibi kullanıyoruz
        Locator shadowDom = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shadowDom " + shadowDom.innerText());


        // getByRole ile önce roller alınır işte buton mu text box mı intput mu drapdown mu vs gibi setName ile de ismini verdik.
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("2. phone number " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("590-345");

        Thread.sleep(2000);

        // getByPlaceholder elementlerin içinde yazan isimlerle alıyoruz
        Locator phoneNumber2 = page.getByPlaceholder("Telefon Numarası");
        System.out.println("3. phone number " + phoneNumber2.innerText());


        // getByLabel Elementin kabel ismiyle alınır
        Locator phoneContinueButton = page.getByLabel("login button");
        System.out.println("4.phoneContinueButton " + phoneContinueButton.innerText());


        // click login button
        // getByRole
        Locator loginButton = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        // test id element içindeki data-test-id ye karşılık gelen ismi alırız.
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası"); //modal(bir şeye tıklayınca açılan popuplar) içindeki paleceholderı telefon numarası olan elementi getirdi )
        System.out.println("5. login phone number " + loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("6723"); //fill ile elementlerin içini dolduruurz senKeys() gibi

        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();

        // getByAltText görssellerin altında vs yazan o isimler var ya işte o isimlere göre locate alıyor
        Locator beverage = page.getByAltText("Su & İçecek");


        // getByTest and filter options filtreleme yaptık
        Locator beverage2 = page.getByTestId("text").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        System.out.println("6. beverage " + beverage2.innerText());

        // css and filter options
        Locator beverage3 = page.locator("div").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        Locator beverage4 = page.locator(".sc-b6b4847f-7 > .sc-6df7862-1").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        beverage4.click();



        page.close();
        browser.close();
        playwright.close();
    }
}
