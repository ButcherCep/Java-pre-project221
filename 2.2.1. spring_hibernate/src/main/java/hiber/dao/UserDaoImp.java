package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   @Autowired
   private SessionFactory sessionFactory;
   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User UsersAndCars(String model, int series) {
         TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(
                 "FROM User user WHERE car.series=:series and car.model=:model", User.class)
                 .setParameter("series", 200).setParameter("model","Masseratti");
         return query.getSingleResult();
   }

   @Override
   public void dropUsersTable() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("DELETE FROM User");
      query.executeUpdate();
      TypedQuery<Car> query2=sessionFactory.getCurrentSession().createQuery("DELETE FROM Car");
      query2.executeUpdate();
   }
}
