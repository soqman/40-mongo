import java.util.Set;

public interface UserDao {
    public Set<User> getAllUsers();

    public User getByName(String name);

    public void updateByName(User user);

    public void deleteByName(User user);

    public void add(User user);
}
