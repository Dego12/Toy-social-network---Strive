package Console;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repository.Repository;
import repository.database.*;
import service.*;
import validators.FriendshipValidator;
import validators.UserValidator;

import java.io.IOException;

public class ConsoleController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField us_s;
    @FXML
    private PasswordField password_s;
    @FXML
    private Label name;

    private Repository<Long, User> userDbRepository = new UserDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new UserValidator());
    private Repository<Long, Friendship> friendshipDbRepository = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new FriendshipValidator());
    private Repository<Long, Message> messageDbRepository = new MessageDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
    private Repository<Long, FriendRequest> friendRequestDbRepository = new FriendRequestDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
    private Repository<Long, Password> passwordDbRepository = new PasswordDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
    private UserService us = new UserService(userDbRepository, passwordDbRepository);
    private FriendshipService fs = new FriendshipService(friendshipDbRepository);
    private MessageService ms = new MessageService(messageDbRepository);
    private FriendRequestService frs= new FriendRequestService(friendRequestDbRepository);
    private Stage dialogStage;
    private User logged_user;


    public void setUser(User user){
        this.logged_user = user;
    }

    @FXML
    public void handleLogin(ActionEvent a) throws IOException {

        String usrname = username.getText();
        String pass = password.getText();
        User logged_usr = us.Login(usrname, pass);
        this.setUser(logged_usr);
        System.out.println("logged in");
        System.out.println(pass);

        Stage home = (Stage) ((Node)a.getSource()).getScene().getWindow();
        FXMLLoader hm = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
        Scene homepage = new Scene(hm.load(), 891, 508);
        home.setTitle("Hi there!");
        home.setScene(homepage);
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
        String ps = password_s.getText();
        String[] usr = usrnm.split(" ");
        User user = new User(usr[0], usr[1]);
        Password pass = new Password(user, ps);
        us.addUser(user, pass);
        System.out.println("The account has been created!");
        Stage home = (Stage) ((Node)a.getSource()).getScene().getWindow();
        home.close();
    }

    @FXML
    public void handleLoggout(ActionEvent a) throws IOException {
        Stage home = (Stage) ((Node)a.getSource()).getScene().getWindow();
        FXMLLoader home_window = new FXMLLoader(getClass().getResource("console-view.fxml"));
        Scene hm_pg = new Scene(home_window.load(), 997, 495);
        home.setTitle("Strive");
        home.setScene(hm_pg);
        home.show();
    }

    @FXML
    public void handleExit()
    {
        System.exit(0);
    }
}
