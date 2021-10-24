package edu.neiu.tweak.data;

import edu.neiu.tweak.model.CreateHackPost;
import org.springframework.data.repository.CrudRepository;

public interface HackPostRepository extends CrudRepository<CreateHackPost, Long>
{

}
