package manager;

import model.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm(){
        click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactForm(Contact contact){
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void submitContactForm() {
        click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
       List<WebElement> list =  wd.findElements(By.cssSelector("h2"));
       for(WebElement el: list){
           if(el.getText().equals(name)){
               return true;
           }
       }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list =  wd.findElements(By.cssSelector("h3"));
        for(WebElement el: list){
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageOpen() {
        List <WebElement> list = wd.findElements(By.xpath("//b[text()='Save']"));
        return list.size()>0;
    }

    public boolean isContactAddedByEmail(String email) {
        WebElement emailFromSite = wd.findElement(By.cssSelector("//*[text()='jonson@gmail.com']"));

        return emailFromSite.getText().equals(email);
    }


}
