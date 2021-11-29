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
    }
}
