package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
