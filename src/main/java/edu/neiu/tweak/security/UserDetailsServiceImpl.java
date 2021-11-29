package edu.neiu.tweak.security;

import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private CreateProfileRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl(CreateProfileRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        CreateProfile user = userRepo.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username " + username + " not found");
        return user;
    }
}
