package dev.yidafu.auncel.user.center.dal;

import dev.yidafu.auncel.user.center.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
