package Console;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import repository.Repository;
import repository.database.*;
import service.*;
import validators.FriendshipValidator;
import validators.UserValidator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private TableView<User> users;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;

    private Service service = new Service();

    public Service getService(){
        return this.service;
    }

    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        for (User u: this.service.getUs().getAllU())
        {
            users.add(u);
        }
        return users;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        users.setItems(getUsers());
    }

    @FXML
    public void handleAddFriend(ActionEvent a) throws IOException {
        User selected = users.getSelectionModel().getSelectedItem();
        Random rand = new Random();
        if (selected != null)
        {
            String firstName = selected.getFirstName();
            String lastName = selected.getLastName();

            for (User usr: this.service.getUs().getAllU())
            {
                if (usr.getFirstName().equals(firstName) && usr.getLastName().equals(lastName)) {
                    FriendRequest friendrequest= new FriendRequest(this.service.getLoggedUser(), usr,"Pending");
                    this.service.getFrs().addFriendRequest(friendrequest);
                    System.out.println("Friend request sent!");
                    }
            }
        }
    }

    @FXML
    public void handleCancelRequest(ActionEvent actionEvent) {
        User selected = users.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String firstName = selected.getFirstName();
            String lastName = selected.getLastName();

            for (FriendRequest fr : this.service.getFrs().getAll()) {
                if (fr.getUser1().getFirstName().equals(service.getLoggedUser().getFirstName()) && fr.getUser1().getLastName().equals(service.getLoggedUser().getLastName())) {
                    if (fr.getUser2().getFirstName().equals(firstName) && fr.getUser2().getLastName().equals(lastName)) {
                        if (fr.getStatus().equals("Pending")) {
                            this.service.getFrs().deleteFriendRequest(fr);
                            System.out.println("Friend request deleted!");
                        }
                    }
                }
                else
                {
                    System.out.println("The friend request does not exist!");
                }
            }
        }
    }

    @FXML
    public void handleSeeFriendList(ActionEvent a) throws IOException {
        Stage friendList = new Stage();
        friendList.initModality(Modality.WINDOW_MODAL);
        FXMLLoader home_window = new FXMLLoader();
        home_window.setLocation(getClass().getResource("/Console/messages-page.fxml"));
        AnchorPane root = (AnchorPane) home_window.load();
        FriendListController frCtrl = home_window.getController();
        frCtrl.setService(this.service);
        Scene hm_pg = new Scene(root);
        friendList.setTitle("Friend list");
        friendList.setScene(hm_pg);
        friendList.show();


    }

    @FXML
    private void receiveData(MouseEvent event)
    {

    }

    @FXML
    public void handleCheckFriendRequests() throws IOException {
        Stage friendList = new Stage();
        FXMLLoader home_window = new FXMLLoader();
        home_window.setLocation(getClass().getResource("/Console/friend-requests.fxml"));
        //FriendRequestController frc = new FriendRequestController();
        AnchorPane root = (AnchorPane) home_window.load();
        FriendRequestController frc =home_window.getController();
        frc.setService(this.service);
        Scene hm_pg = new Scene(root);
        friendList.setTitle("Friend requests");
        friendList.setScene(hm_pg);
        friendList.show();
    }

    public void setService(Service service) {
        this.service = service;
    }
}
