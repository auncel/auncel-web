package dev.yidafu.auncel.user.center.dal;

import dev.yidafu.auncel.user.center.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
