package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository  extends JpaRepository<Problem, Long> {
    @Override
    List<Problem> findAll();
}
