package Console;

import domain.Friendship;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FriendListController implements Initializable {
    @FXML
    private TableView<User> friends;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    private User logged;

    public User getLogged() {
        return logged;
    }

    public void setLogged(User logged) {
        this.logged = logged;
    }



    private Service service = new Service();


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        friends.setItems(getFriends());
    }

    public ObservableList<User> getFriends() {
        ObservableList<User> friends = FXCollections.observableArrayList();
        for (Friendship fr : this.service.getFs().getAll()) {
            if (fr.getUser1().getFirstName().equals(this.service.getLoggedUser().getFirstName()) && fr.getUser1().getLastName().equals(this.service.getLoggedUser().getLastName())) {
                friends.add(fr.getUser2());
            }
            if (fr.getUser2().getFirstName().equals(this.service.getLoggedUser().getFirstName()) && fr.getUser2().getLastName().equals(this.service.getLoggedUser().getLastName())) {
                friends.add(fr.getUser1());
            }
        }
        return friends;
    }
}
