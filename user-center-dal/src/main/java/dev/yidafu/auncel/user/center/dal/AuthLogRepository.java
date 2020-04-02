package dev.yidafu.auncel.user.center.dal;

import dev.yidafu.auncel.user.center.domain.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
}
