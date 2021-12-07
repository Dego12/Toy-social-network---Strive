package repository.file;

import domain.FriendRequest;
import domain.Friendship;
import domain.User;
import validators.Validator;

import java.util.List;

public class FriendRequestFileRepository extends AbstractFileRepository<Long, FriendRequest> {

    public FriendRequestFileRepository(String filename, Validator<FriendRequest> validator) {super(filename, validator);}

    @Override
    public FriendRequest extractEntity(List<String> attributes){
        String user[] = attributes.get(1).split(" ");
        User usr = new User(user[0], user[1]);
        String user2[] = attributes.get(2).split(" ");
        User usr2 = new User(user2[0], user2[1]);
        FriendRequest friendrequest = new FriendRequest(usr, usr2,"Pending");
        friendrequest.setId(Long.parseLong(attributes.get(0)));
        return friendrequest;
    }

    @Override
    protected String createEntityAsString(FriendRequest entity){
        return entity.getId()+";"+entity.getUser1()+";"+entity.getUser2();
    }
}
