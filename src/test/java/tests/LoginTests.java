package tests;
import manager.DataProviderUser;
import manager.ListenerTestNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(ListenerTestNG.class)
public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }


    @DataProvider
    public Iterator<Object[]> loginData(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"michael+1@gmail.com","Michael12345$"});
        list.add(new Object[]{"franky@gmail.com","FrankY123$"});
        list.add(new Object[]{"michael+1@gmail.com","Michael12345$"});


        return list.iterator();
    }


    @Test(dataProvider = "loginData")
    public void loginSuccess(String email,String password){

        logger.info("Login with valid data: email:"+ email+" & password:"+password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Test success");
    }

    @Test(dataProvider = "loginDataClass",dataProviderClass = DataProviderUser.class)
    public void loginSuccessNew(String email,String password){
        logger.info("Login with valid data: email:"+ email+" & password:"+password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test success");

    }

    @Test(invocationCount = 2)
    public void loginSuccessNew2(String email,String password){
        logger.info("Login with valid data: email:"+ email+" & password:"+password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("michael@gmail.com","Michael12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test success");

    }
    @Test(dataProvider = "loginDataUser",dataProviderClass = DataProviderUser.class)   //из коллекции
    public void loginSuccessModel(User user){

        logger.info("Test starts with user model --->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test(dataProvider = "loginDataUserFromFile",dataProviderClass = DataProviderUser.class)   //из файла
    public void loginSuccessModelFromFile(User user){

        logger.info("Test starts with user model --->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("michaelgmail.com","Michael12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test success");
    }
    @Test
    public void loginWrongPassword(){
       app.getHelperUser().openLoginRegistrationForm();
       app.getHelperUser().fillLoginRegistrationForm("michael+1@gmail.com","Mihael12345");
       app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test success");
    }
    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mimi@gmail.com","PPpp123456$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test success");
    }



}
