package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.login = :login AND user.password = :password")
    public User getUser(String login, String password);
    @Query("SELECT user FROM User user WHERE user.login = :login")
    User findByLogin(String login);
}
