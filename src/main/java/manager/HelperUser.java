package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {

        super(wd);
    }

    public void openLoginRegistrationForm(){

        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email,String password){
        //for email
        type(By.cssSelector("[name='email']"),email);
        //for password
        type(By.name("password"),password);

    }

    public void submitLogin(){
        click(By.cssSelector("[name='login']"));

    }

    public boolean isLogged() {
       List <WebElement> list = wd.findElements(By.xpath("//button[text()='Sign Out']"));
       return list.size()>0;
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isErrorMessageDisplayed(String message){
        Alert alert = wd.switchTo().alert();
        String text = alert.getText();

        //click 'ok'
        alert.accept();
        //click 'cancel'
        //alert.dismiss();
       // alert.sendKeys("Hello");
        return text.contains(message);
    }

    public void submitRegistration() {
        click(By.cssSelector("[name='registration']"));
    }
}
