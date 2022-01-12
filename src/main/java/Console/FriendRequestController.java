package Console;

import domain.FriendRequest;
import domain.Friendship;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class FriendRequestController{

    @FXML
    private TableView<FriendRequest> friendRequests;
    @FXML
    private TableColumn<FriendRequest, User> user1;
    @FXML
    private TableColumn<FriendRequest, String> status;
    private Service service = new Service();

    public Service getService() {
        return service;
    }

    public void setService(Service myservice) {
        this.service = myservice;
    }




    public void initialize() {
        user1.setCellValueFactory(new PropertyValueFactory<FriendRequest, User>("user1"));
        status.setCellValueFactory(new PropertyValueFactory<FriendRequest, String>("status"));
        friendRequests.setItems(getFriendReq(getAllItems()));
    }

    public List<FriendRequest> getAllItems(){
        List<FriendRequest> friendRequests = new LinkedList<FriendRequest>();
        for (FriendRequest fr: this.service.getFrs().getAll())
        {
            if (fr.getUser2().getFirstName().equals(this.service.getLoggedUser().getFirstName()) && fr.getUser2().getLastName().equals(this.service.getLoggedUser().getLastName()))
                friendRequests.add(fr);
        }
        return friendRequests;
    }

    public ObservableList<FriendRequest> getFriendReq(List<FriendRequest> fr)
    {
        ObservableList<FriendRequest> friendRequest = FXCollections.observableArrayList(fr);
        return friendRequest;
    }



    @FXML
    public void handleAccept()
    {
        for(FriendRequest fr:this.service.getFrs().getAll()){
            if (fr.getUser2().getFirstName().equals(this.service.getLoggedUser().getFirstName()) && fr.getUser2().getLastName().equals(this.service.getLoggedUser().getLastName()) && fr.getStatus().equals("Pending")){
                fr.setStatus("Accepted");
                this.service.getFrs().updateFriendRequest(fr);
                Friendship friendship=new Friendship(fr.getUser1(),fr.getUser2());
                this.service.getFs().addFriendship(friendship);
            }
        }
    }

    @FXML
    public void handleDecline(){
        for(FriendRequest fr:this.service.getFrs().getAll()){
            if (fr.getUser2().getFirstName().equals(this.service.getLoggedUser().getFirstName()) && fr.getUser2().getLastName().equals(this.service.getLoggedUser().getLastName()) && fr.getStatus().equals("Pending")){
                fr.setStatus("Declined");
                this.service.getFrs().updateFriendRequest(fr);
            }
        }
    }
}
