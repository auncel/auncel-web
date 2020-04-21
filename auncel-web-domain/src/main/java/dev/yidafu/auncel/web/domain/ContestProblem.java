package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@IdClass(ContestProblemId.class)
public class ContestProblem {
    @JsonBackReference
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(
            name = "contest_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "contest_id", value = ConstraintMode.NO_CONSTRAINT))
    private Contest contest;

    @JsonManagedReference
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(
            name = "problem_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "problem_id", value = ConstraintMode.NO_CONSTRAINT)
    )
    private Problem problem;

    @Column
    private int score = 0;


    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createdAt;

    @Override
    public String toString() {
        return "ContestProblem{" +
                "contest=" + contest +
                ", problem=" + problem +
                ", score=" + score +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
