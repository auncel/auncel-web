package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
}
