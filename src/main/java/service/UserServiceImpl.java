package service;

import dao.UserDao;
import model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getByUserName(username);
        if (user == null) throw new UsernameNotFoundException(String.format("User '%s' не найден", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public User findByLastName(String lastName) {
        return userDAO.getByUserName(lastName);
    }
}
