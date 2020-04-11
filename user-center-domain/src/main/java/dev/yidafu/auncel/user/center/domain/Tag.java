package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tag extends BaseEntity {

    @Column
    private String value = "None";

    @JsonBackReference
    @JoinColumn(name = "problem_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = Problem.class)
    private Problem problem;
}
