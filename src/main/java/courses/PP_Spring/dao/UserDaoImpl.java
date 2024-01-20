package courses.PP_Spring.dao;


import courses.PP_Spring.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> allUsers() {
        return entityManager.createQuery("select u from courses.PP_Spring.model.User u", User.class).getResultList();
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }
}
