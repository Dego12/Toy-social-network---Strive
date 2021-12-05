package service;

import domain.Friendship;
import repository.Repository;

public class FriendshipService {
    private Repository<Long, Friendship> repo;

    public FriendshipService(Repository<Long, Friendship> repo) {this.repo = repo;}

    public Friendship addFriendship(Friendship friendship)
    {
        Friendship fr = this.repo.save(friendship);;
        return fr;
    }

    public void deleteFriendship(Friendship friendship)
    {
        repo.delete(friendship);
    }

    public Iterable<Friendship> getAll()
    {
        return repo.findAll();
    }

    public Friendship updateFriendship(Friendship friendship)
    {
        Friendship fr = this.repo.save(friendship);
        return fr;
    }
}
