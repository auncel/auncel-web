package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.ContestProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestProblemRepo extends JpaRepository<ContestProblem, Long> {

    List<ContestProblem> findAllByContest_Id(Long contestId);
}
