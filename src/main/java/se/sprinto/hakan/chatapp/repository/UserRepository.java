package se.sprinto.hakan.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.sprinto.hakan.chatapp.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
            SELECT use
            FROM User use
            LEFT JOIN FETCH use.messages
            WHERE use.username = :username
            AND use.password = :password
""")
    User findByUsernameAndPassword(String username, String password);
}

