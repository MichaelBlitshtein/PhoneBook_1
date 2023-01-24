package tests;

import model.Contact;
import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(User.builder().email("franky@gmail.com").password("FrankY123$").build());
        }
    }

    @Test
    public void addContactSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Adam"+i)
                .lastName("Sandler")
                .address("New York")
                .phone("8546456341")
                .email("adamsend@mail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

    @Test
    public void addContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Boris"+i)
                .lastName("Jonson")
                .address("Washington")
                .phone("2654525897")
                .email("jonson@gmail.com")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        app.getHelperContact().click(By.xpath("//h3[text()='2654525897']"));

     Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));

    }

    @Test
    public void addContactWrongNameFormat() {

        Contact contact = Contact.builder()
                .name("")
                .lastName("Brown")
                .address("California")
                .phone("74125896325")
                .email("davidbrown@mail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(50000);
        app.getHelperContact().submitContactForm();

        Assert.assertTrue(app.getHelperContact().isAddPageOpen());
    }

    @Test
    public void addContactWrongLastNameFormat() {

        Contact contact = Contact.builder()
                .name("David")
                .lastName("")
                .address("California")
                .phone("74125896325")
                .email("davidbrown@gmail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();

        Assert.assertTrue(app.getHelperContact().isAddPageOpen());
    }


    @Test
    public void addContactWrongPhoneLength() {

        Contact contact = Contact.builder()
                .name("David")
                .lastName("Brown")
                .address("California")
                .phone("7412")
                .email("davidbrown@gmail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();


       Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test
    public void addContactWrongEmailFormat() {

        Contact contact = Contact.builder()
                .name("David")
                .lastName("Brown")
                .address("California")
                .phone("74125896325")
                .email("davidbrowngmail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();

        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Email not valid:"));
    }

    @Test
    public void addContactWrongAddressFormat() {

        Contact contact = Contact.builder()
                .name("David")
                .lastName("Brown")
                .address("")
                .phone("74125896325")
                .email("davidbrown@gmail.com")
                .description("Friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();

        Assert.assertTrue(app.getHelperContact().isAddPageOpen());
    }

}