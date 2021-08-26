import aquality.selenium.browser.AlertActions;
import main.Config;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pageobjects.AuthForm;
import pageobjects.RemindPassForm;
import pageobjects.StudentStartPage;

import static org.testng.Assert.*;

public class AuthTest extends BaseTest {
    private final AuthForm authForm = new AuthForm();
    private final StudentStartPage studentStartPage = new StudentStartPage();
    private final RemindPassForm remindPassForm = new RemindPassForm();
    private final String correctLogin = Config.get("user_login");
    private final String correctPass = Config.get("user_password");
    private final String incorrectPass = RandomStringUtils.randomAlphanumeric(10);
    private final String incorrectLongLogin = RandomStringUtils.randomAlphanumeric(151);
    private final String wrongAuthDataAlertText = "Неверные данные для авторизации";
    private final String wrongAlertForRestorePass = "Пользователь с таким именем не найден.";
    private final String successAlertForRestorePass = "На ваш электронный адрес отправлена инструкция по восстановлению пароля.";

    @Test
    public void validCredAuthTest() {
        authForm.signIn(correctLogin, correctPass);
        assertTrue(studentStartPage.state().waitForDisplayed(), "Student start pge wasn't opened");
    }

    @Test
    public void invalidCredAuthTest() {
        authForm.signIn(correctLogin, incorrectPass);
        assertEquals(browser.getDriver().switchTo().alert().getText(), wrongAuthDataAlertText,"Alert didn't appear");
        browser.handleAlert(AlertActions.ACCEPT);
        assertTrue(authForm.state().isDisplayed(), "Alert wasn't closed");
    }

    @Test
    public void showPassBtnTest() {
        authForm.clickGuestPageLbl();
        assertTrue(authForm.state().isDisplayed(), "Auth form wasn't opened");
        authForm.typePassword(incorrectPass);
        assertEquals(authForm.getPassFieldAttr("type"), "password", "Password field doesn't have 'password' type");
        authForm.clickShowPassBtn();
        assertEquals(authForm.getPassFieldAttr("type"), "text", "Password field doesn't have 'text' type");
    }

    @Test
    public void loginLengthTest() {
        authForm.typeLogin(incorrectLongLogin);
        String loginFieldText = authForm.getLoginFieldText();
        assertNotEquals(incorrectLongLogin.length(), loginFieldText.length(), "Sent text and text from field are equal");
        assertEquals(Integer.parseInt(authForm.getLoginFieldAttr("maxlength")), loginFieldText.length(), "Field attribute doesn't match with text length");
    }

    @Test
    public void remindPassTest() {
        authForm.clickRemandPassLnk();
        remindPassForm.clickGuestPageLbl();
        assertTrue(authForm.state().isDisplayed(),"Auth form wasn't opened");
        authForm.clickRemandPassLnk();
        assertTrue(remindPassForm.state().isDisplayed(),"Remind password form wasn't opened");
        remindPassForm.typeLoginOrEmail(incorrectLongLogin);
        assertEquals(remindPassForm.getWrongUserAlertText(), wrongAlertForRestorePass, "Wrong user/email alert doesn't appear");
        remindPassForm.typeLoginOrEmail(correctLogin);
        assertEquals(remindPassForm.getSuccessUserAlertText(), successAlertForRestorePass,"Success user/email alert doesn't appear");
        remindPassForm.clickBackToAuthFormLink();
        assertTrue(authForm.state().isDisplayed(),"Auth form wasn't opened");
    }
}
