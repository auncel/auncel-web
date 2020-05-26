package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.UserContest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestRepo extends JpaRepository<UserContest, Long> {
}
