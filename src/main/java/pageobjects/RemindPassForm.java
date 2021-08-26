package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RemindPassForm extends Form {
    private ILink lnkBackToAuthForm = getElementFactory().getLink(By.xpath("//form[@name='entity' and contains(@class,'mira-forgot-password-form')]//a[contains(@href,'GuestPage')]"), "Go back to auth form link");
    private IButton btnSendLoginToRestorePass = getElementFactory().getButton(By.xpath("//form[@name='entity' and contains(@class,'mira-forgot-password-form')]//button[contains(@class,'mira-page-forgot-password-button')]"), "Send to restore button");
    private ITextBox txbLoginOrEmailField = getElementFactory().getTextBox(By.name("loginOrEmail"), "Login or email field");
    private ILabel lblWrongUserAlert = getElementFactory().getLabel(By.className("alert"), "Wrong user alert");
    private ILabel lblSuccessUserAlert = getElementFactory().getLabel(By.className("success"), "Success user alert");
    private ILabel lblGuestPage = getElementFactory().getLabel(By.xpath("//div[contains(@class,'mira-page-forgot-password-form')]//div[@class='head']/a[contains(@href,'GuestPage')]"), "Guest page label");

    public RemindPassForm() {
        super(By.xpath("//form[@name='entity' and contains(@class,'mira-forgot-password-form')]"), "Remind password form");
    }

    public void clickBackToAuthFormLink() {
        lnkBackToAuthForm.click();
    }

    public void typeLoginOrEmail(String loginOrEmail) {
        txbLoginOrEmailField.clearAndType(loginOrEmail);
        btnSendLoginToRestorePass.click();
    }

    public String getWrongUserAlertText() {
        return lblWrongUserAlert.getText();
    }

    public String getSuccessUserAlertText() {
        return lblSuccessUserAlert.getText();
    }

    public void clickGuestPageLbl() {
        lblGuestPage.click();
    }
}
