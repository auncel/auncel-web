package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.AuthLog;
import dev.yidafu.auncel.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
    public List<AuthLog> findByLogUserIs(User logUser);
}
