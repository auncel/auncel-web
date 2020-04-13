package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
