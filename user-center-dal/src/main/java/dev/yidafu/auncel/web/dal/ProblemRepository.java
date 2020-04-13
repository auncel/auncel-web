package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository  extends JpaRepository<Problem, Long> {
}
