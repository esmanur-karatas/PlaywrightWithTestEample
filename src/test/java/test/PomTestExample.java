package test;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.Screens;


@Listeners(utilities.Hooks.class)
public class PomTestExample {

    private Screens screens; // bunu oluşturma nedeni her seferinde pagelerinn nesnelerini oluşturmak yerine Screns clasında hepsini oluşturup tek satır kodla halledebiliriz.
    @Epic("user intarface test")
    @Feature("Login Feature")
    @Story("Successful login")
    @Severity(SeverityLevel.BLOCKER)//testin ne kadar öneme sahip olduğunu gösteriyor
    @Description("Test for successful login with valid credentials")
    @Test
    void shouldNavigate(){
        screens = new Screens();
        //Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon"));

        //Test adımlarını da belirtmek istersek aşağıdaki gibi kullanabiliriz
        Allure.step("Navigate to the login page", () ->{
            //BURAYA GEREKLİ ADIMLAR YAZILIR

            screens.tabbarPage().phoneNumber.click();
            screens.tabbarPage().phoneNumber.fill("666-888");
//            phoneNumber.click();
//            phoneNumber.fill("456-745");
        });
    }

}
