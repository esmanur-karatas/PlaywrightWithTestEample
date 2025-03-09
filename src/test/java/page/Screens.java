package page;

import com.microsoft.playwright.Page;

public class Screens {
    private Page page;
    private TabbarPage tabbarPage;

    public Screens(){

    }

    public Screens(Page page){
        this.page = page;
    }

    public TabbarPage tabbarPage(){
        if (tabbarPage == null){
            tabbarPage = new TabbarPage(page);
        }
        return tabbarPage;
    }
}
