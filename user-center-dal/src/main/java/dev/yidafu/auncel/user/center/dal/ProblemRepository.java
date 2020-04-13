package dev.yidafu.auncel.user.center.dal;

import dev.yidafu.auncel.user.center.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository  extends JpaRepository<Problem, Long> {
}
