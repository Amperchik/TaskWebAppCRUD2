package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDAO;

    public UserServiceImpl(UserDao userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    public void addUser(User user) {
        userDAO.create(user);
    }

    @Override
    public User getUserId(long id) {
        return userDAO.read(id);
    }

    @Override
    public void deleteById(long id) {
        userDAO.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

}
