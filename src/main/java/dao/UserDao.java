package dao;

import model.UserOrm;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserDao {

    private HibernateTemplate hibernateTemplate;

    public UserDao(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional // important annotation for writing to db
    public int saveUser(UserOrm user) {
        int savedUserId = (Integer) this.hibernateTemplate.save(user);
        return savedUserId;
    }
}
