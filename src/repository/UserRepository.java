package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by username
     * @param username - The username to search for
     * @return Optional<User>
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by email
     * @param email - The email to search for
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
} 