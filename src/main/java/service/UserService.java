package service;

import model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public void addUser(User user);

    public User getUserId(long id);

    public void deleteById(long id);

    public void updateUser(User user);
}
