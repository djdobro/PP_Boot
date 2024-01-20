package courses.PP_Spring.service;


import courses.PP_Spring.dao.UserDao;
import courses.PP_Spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);

    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return userDao.getById(id);
    }

}
