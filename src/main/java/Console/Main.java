package Console;

import UI.UI;
import domain.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.Repository;
import repository.database.*;
import repository.file.FriendshipFileRepository;
import repository.file.UserFileRepository;
import service.*;
import validators.FriendshipValidator;
import validators.UserValidator;

import java.io.IOException;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws IOException{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("console-view.fxml"));
            Service service = new Service();
            Scene scene = new Scene(fxmlLoader.load(), 997, 495);
            ConsoleController consoleController = fxmlLoader.getController();
            consoleController.setService(service);
            stage.setTitle("Strive");
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args){
    launch();}
}

/*
public class Main{

    public static void main(String[] args){

            String fileName = "data/users.csv";
            String fileName2 = "data/friendships.csv";
            Repository<Long, User> userFileRepository = new UserFileRepository(fileName, new UserValidator());
            Repository<Long, Friendship> friendshipFileRepository = new FriendshipFileRepository(fileName2, new FriendshipValidator());
            Repository<Long, User> userDbRepository = new UserDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new UserValidator());
            Repository<Long, Friendship> friendshipDbRepository = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new FriendshipValidator());
            Repository<Long, Message> messageDbRepository = new MessageDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
            Repository<Long, FriendRequest> friendRequestDbRepository = new FriendRequestDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
            Repository<Long, Password> passwordDbRepository = new PasswordDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
            UserService us = new UserService(userDbRepository, passwordDbRepository);
            FriendshipService fs = new FriendshipService(friendshipDbRepository);
            MessageService ms = new MessageService(messageDbRepository);
            FriendRequestService frs= new FriendRequestService(friendRequestDbRepository);
            UI ui = new UI(us, fs, ms,frs);
            Console console = new Console(ui);
            console.run_console();

    }
}
*/