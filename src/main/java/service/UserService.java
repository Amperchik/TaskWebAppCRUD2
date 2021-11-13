package service;
import DAO.DAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    private final DAO userDAO;
    @Autowired
    public UserService(DAO dao) {
        this.userDAO = dao;
    }
    @Transactional
    public List<User> getUsers(){
        return userDAO.getAll();
    }
    @Transactional
    public void addUser(User user){
userDAO.create(user);
    }
    @Transactional
    public User getUserId(long id){
        return userDAO.read(id);
    }
    @Transactional
    public void deleteById(long id) {userDAO.delete(id);}
    @Transactional
    public void updateUser(User user) {userDAO.update(user);}

}
