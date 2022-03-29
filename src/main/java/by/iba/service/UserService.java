package by.iba.service;

import by.iba.entity.Role;
import by.iba.entity.User;
import by.iba.repository.RoleRepository;
import by.iba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User userForm) {
        User userFromDB = userRepository.findByUserName(userForm.getUsername());
        if (userFromDB != null) {
            return false;
        }

        userForm.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userForm.setPass(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userRepository.save(userForm);
        return true;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
