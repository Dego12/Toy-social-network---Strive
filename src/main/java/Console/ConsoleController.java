package Console;

import domain.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repository.Repository;
import repository.database.*;
import service.*;
import validators.FriendshipValidator;
import validators.UserValidator;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsoleController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField us_s;
    @FXML
    private PasswordField password_s;

    private Service service = new Service();

    public void setService(Service service){
        this.service = service;
    }

    public User getLoggedUser() {
        return service.getLoggedUser();
    }

    public void setLoggedUser(User loggedUser) {
        this.service.setLoggedUser(loggedUser);
    }


    @FXML
    public void handleLogin(ActionEvent a) throws IOException {

        String usrname = username.getText();
        Random rand = new Random();
        String fname = usrname.split(" ")[0];
        String lname = usrname.split(" ")[1];
        String pass = password.getText();
        for (User u: this.service.getUs().getAllU()) {
            for (Password p: this.service.getUs().getAllP())
            {
                if (p.getUser().getFirstName().equals(fname) && p.getUser().getLastName().equals(lname))
                    if (u.getFirstName().equals(fname) && u.getLastName().equals(lname))
                    {
                        if (p.getPassword().equals(pass))
                        {
                            User user = u;
                            this.setLoggedUser(user);
                        }
                    }
            }
        }
        System.out.println("logged in");
        System.out.println(pass);

        Stage home = (Stage) ((Node)a.getSource()).getScene().getWindow();
        FXMLLoader hm = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
        Scene scene = new Scene(hm.load(), 891, 508);

        WelcomeController welcomeController = hm.getController();
        welcomeController.setService(this.service);
        home.setScene(scene);
        home.setTitle("Hi there!");
        home.show();
    }

    @FXML
    public void handleSignUp(ActionEvent a) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage sign = new Stage();
        sign.setTitle("Sign up");
        sign.setScene(scene);
        sign.show();
    }

    @FXML
    public void handleCreate(ActionEvent a){
        String usrnm = us_s.getText();
        Random rand = new Random();
        String ps = password_s.getText();
        String[] usr = usrnm.split(" ");
        User user = new User(usr[0], usr[1]);
        Password pass = new Password(user, ps);
        this.service.getUs().addUser(user, pass);
        System.out.println("The account has been created!");
        Stage home = (Stage) ((Node)a.getSource()).getScene().getWindow();
        home.close();
    }



    @FXML
    public void handleExit()
    {
        System.exit(0);
    }


}
