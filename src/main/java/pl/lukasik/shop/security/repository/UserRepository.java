package pl.lukasik.shop.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
