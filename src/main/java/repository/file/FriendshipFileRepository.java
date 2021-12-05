package repository.file;

import domain.Friendship;
import domain.User;
import validators.Validator;

import java.util.List;

public class FriendshipFileRepository extends AbstractFileRepository<Long, Friendship> {

    public FriendshipFileRepository(String filename, Validator<Friendship> validator) {super(filename, validator);}

    @Override
    public Friendship extractEntity(List<String> attributes){
        String user[] = attributes.get(1).split(" ");
        User usr = new User(user[0], user[1]);
        String user2[] = attributes.get(2).split(" ");
        User usr2 = new User(user2[0], user2[1]);
        Friendship friendship = new Friendship(usr, usr2);
        friendship.setId(Long.parseLong(attributes.get(0)));
        return friendship;
    }

    @Override
    protected String createEntityAsString(Friendship entity){
        return entity.getId()+";"+entity.getUser1()+";"+entity.getUser2();
    }
}
