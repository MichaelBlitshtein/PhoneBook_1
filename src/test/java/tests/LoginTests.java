package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("franky@gmail.com","FrankY123$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginSuccessNew(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("michael+1@gmail.com","Michael12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("michil+1@gmail.com","Michael12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));

    }
    @Test
    public void loginWrongPassword(){
       app.getHelperUser().openLoginRegistrationForm();
       app.getHelperUser().fillLoginRegistrationForm("michael+1@gmail.com","Mihael12345$");
       app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));

    }
    @Test
    public void loginUnregisteredUser(){

    }
}
