package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

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
}
