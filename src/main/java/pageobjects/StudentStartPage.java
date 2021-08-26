package pageobjects;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class StudentStartPage extends Form {
    public StudentStartPage() {
        super(By.name("StudentStartPage"), "Start page");
    }
}
