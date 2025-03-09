package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class TabbarPage {
    private Page page;
    public Locator phoneNumber;


    public TabbarPage(Page page){
        this.page = page;
    //Locatorlar yazılır
    this.phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon"));
}}
