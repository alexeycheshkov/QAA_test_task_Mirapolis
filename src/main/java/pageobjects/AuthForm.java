package pageobjects;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthForm extends Form {
    private IButton btnSubmit = getElementFactory().getButton(By.id("button_submit_login_form"), "Submit button");
    private IButton btnShowPass = getElementFactory().getButton(By.id("show_password"), "Show password button");
    private ITextBox txbLogin = getElementFactory().getTextBox(By.name("user"), "User input field");
    private ITextBox txbPassword = getElementFactory().getTextBox(By.name("password"), "Password input field");
    private ILink lnkRemindPass = getElementFactory().getLink(By.xpath("//form[@id='login_form_panel']//table[contains(@class,'links-container')]//a[contains(@href,'remindpassword')]"), "Remind pass link");
    private ILabel lblGuestPage = getElementFactory().getLabel(By.xpath("//div[contains(@class,'mira-page-login')]//div[@class='head']/a[contains(@href,'GuestPage')]"), "Guest page label");

    public AuthForm() {
        super(By.id("login_form_panel"), "Login form");
    }

    public void signIn(String login, String password) {
        typeLogin(login);
        typePassword(password);
        btnSubmit.click();
    }

    public void typeLogin(String login) {
        txbLogin.clearAndType(login);
    }

    public void typePassword(String password) {
        txbPassword.clearAndTypeSecret(password);
    }

    public void clickShowPassBtn() {
        btnShowPass.click();
    }

    public String getPassFieldAttr(String attr) {
        return txbPassword.getAttribute(attr);
    }

    public String getLoginFieldAttr(String attr) {
        return txbLogin.getAttribute(attr);
    }

    public String getLoginFieldText() {
        return txbLogin.getValue();
    }

    public void clickRemandPassLnk() {
        lnkRemindPass.click();
    }

    public void clickGuestPageLbl() {
        lblGuestPage.click();
    }
}
