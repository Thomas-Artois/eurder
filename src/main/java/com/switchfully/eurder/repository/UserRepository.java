package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Address adminAddress = new Address("adminStreet", "4DM1N", "3000", "adminCity");
        User adminUser = new User("admin", "adminLast", "admin@eurder.com", adminAddress, "0496334455", Role.ADMIN, "adminPassword");

        this.createUser(adminUser);

        Address customerOneAddress = new Address("Bondgenotenlaan", "100", "3000", "Leuven");
        Address customerTwoAddress = new Address("Mariastraat", "007", "1000", "Brussel");
        Address customerThreeAddress = new Address("Jozeflaan", "3030", "5050", "Timboektoe");

        List<User> listOfUsers = List.of(
                new User("Bart", "Bartmans", "bart@hotmail.com", customerOneAddress, "0474112233", Role.CUSTOMER, "bartPassword"),
                new User("Maria", "Mariavrouw", "maria@hotmail.com", customerTwoAddress, "0546334455", Role.CUSTOMER, "mariaPassword"),
                new User("Jozef", "Timmermans", "jozef@hotmail.com", customerThreeAddress, "0482007007", Role.CUSTOMER, "jozefPassword")
        );

        listOfUsers.forEach(this::createUser);
    }

    public User createUser(User user) {
        users.put(user.getId(), user);

        return user;
    }

    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

}
