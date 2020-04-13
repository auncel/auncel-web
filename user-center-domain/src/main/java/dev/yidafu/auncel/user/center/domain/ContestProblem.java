package dev.yidafu.auncel.user.center.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ContestProblemId.class)
public class ContestProblem {
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(
            name = "contest_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "contest_id", value = ConstraintMode.NO_CONSTRAINT))
    private Contest contest;

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
