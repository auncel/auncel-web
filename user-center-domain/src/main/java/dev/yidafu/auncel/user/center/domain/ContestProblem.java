package dev.yidafu.auncel.user.center.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ContestProblemId.class)
public class ContestProblem {
    @Id
    @ManyToOne
    @JoinColumn(name = "contest_id", referencedColumnName = "id")
    private Contest contest;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "problem_id", referencedColumnName = "id")
    private Problem problem;

    @Column
    private int score = 0;
}
