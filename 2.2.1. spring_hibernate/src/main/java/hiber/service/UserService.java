package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user, Car car);
    void dropUsersTable();
    List<User> listUsers();
    User UsersAndCars (String model, int series);
}
