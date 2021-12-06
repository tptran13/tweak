package edu.neiu.tweak.data;

import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface HackPostRepository extends CrudRepository<CreateHackPost, Long>
{
    Set<CreateHackPost> findAllByProfile(CreateProfile profile);
}
