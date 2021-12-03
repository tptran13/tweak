package edu.neiu.tweak.security;

import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner
{
    private CreateProfileRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataLoader(CreateProfileRepository userRepo, PasswordEncoder passwordEncoder)
    {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {
//        CreateProfile user = new CreateProfile("Naruto", "Uzumaki", "04/15/1996", "user1@yahoo.com",
//                                                "user1", passwordEncoder.encode("Password1"));
//        user.setRoles(Set.of(CreateProfile.Role.ROLE_ADMIN));
//        user.setEnabled(true);
//        userRepo.save(user);

//        CreateProfile user1 = new CreateProfile("Hinata", "Hyuga", "04/16/1996", "user2@yahoo.com",
//                "user2", passwordEncoder.encode("Password2"));
//        user1.setRoles(Set.of(CreateProfile.Role.ROLE_USER));
//        user1.setEnabled(true);
//        userRepo.save(user1);

//        CreateProfile user3 = new CreateProfile("Itachi", "Uchiha", "04/04/1990", "user3@yahoo.com",
//                "user3", passwordEncoder.encode("Password3"));
//        user3.setRoles(Set.of(CreateProfile.Role.ROLE_USER));
//        user3.setEnabled(true);
//        userRepo.save(user3);
    }
}
