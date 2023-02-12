package ge.pozdniakov.kameleoonTest.repositories;

import ge.pozdniakov.kameleoonTest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
