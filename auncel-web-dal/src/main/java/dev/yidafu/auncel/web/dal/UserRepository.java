package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findById(Long id);

    public User findByUsername(String username);
}
