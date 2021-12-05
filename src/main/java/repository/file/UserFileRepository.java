package repository.file;

import domain.User;
import validators.Validator;

import java.util.LinkedList;
import java.util.List;

public class UserFileRepository extends AbstractFileRepository<Long, User> {

    public UserFileRepository(String fileName, Validator<User> validator) {
        super(fileName, validator);
    }

    @Override
    public User extractEntity(List<String> attributes){
        User user = new User(attributes.get(1), attributes.get(2));
        user.setId(Long.parseLong(attributes.get(0)));
        return user;
    }


    @Override
    protected String createEntityAsString(User entity){
        return entity.getId()+";"+entity.getFirstName()+";"+entity.getLastName();
    }

    public List<User> stringToUser(String[] Frendz)
    {
        int i = 0;
        List<User> fre = new LinkedList<User>();
        for (User e: findAll()) {
            String[] flName = Frendz[i].split(" ");
            String firstName = flName[0];
            String lastName = flName[1];
            if (firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName()));
            {
                fre.add(e);
            }
        }
        return fre;

    }
}
