package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.Submission;
import dev.yidafu.auncel.web.domain.User;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // https://blog.csdn.net/liuchuanhong1/article/details/69950886
    @Query(value = "SELECT id, created_at, updated_at, a_css, a_html, logs, problem_id, score, screenshot, status, submiter_id FROM submission WHERE problem_id=:problem_id AND submiter_id=:submiter_id LIMIT 1", nativeQuery = true)
    public Submission selectByProblemIdAndUserId(@Param("problem_id") Long ProblmeId, @Param("submiter_id") Long userId);

    public Submission getFirstByProblem(Problem problem);
}
