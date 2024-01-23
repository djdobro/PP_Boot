package courses.PP_Spring.dao;


import courses.PP_Spring.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from courses.PP_Spring.model.User u", User.class).getResultList();
    }

    @Override
    public User update(User user) {
        if (getById(user.getId()) != null) {
            return entityManager.merge(user);
        } else {
            throw new EntityNotFoundException("Пользователь с таким ID " + user.getId() + " не найден");
        }

    }

    @Override
    public void delete(User user) {
        if (getById(user.getId()) != null) {
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        } else {
            throw new EntityNotFoundException("Пользователь с таким ID " + user.getId() + " не найден");
        }

    }

    @Override
    public User getById(Integer id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Пользователь с таким ID " + id + " не найден");
        }
        return user;
    }
}
