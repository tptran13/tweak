package edu.neiu.tweak.data;

import edu.neiu.tweak.model.CreateProfile;
import org.springframework.data.repository.CrudRepository;

public interface CreateProfileRepository extends CrudRepository<CreateProfile, Long>
{
    CreateProfile findByUsername(String username);
}
