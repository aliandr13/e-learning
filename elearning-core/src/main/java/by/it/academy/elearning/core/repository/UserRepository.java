package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.ELUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ELUser, Long> {

    Optional<ELUser> findByUsername(String username);
}
