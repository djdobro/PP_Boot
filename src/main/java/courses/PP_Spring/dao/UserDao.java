package courses.PP_Spring.dao;



import courses.PP_Spring.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    List<User> allUsers();
    User update(User user);
    void delete(User user);
    User getById(Integer id);


}
