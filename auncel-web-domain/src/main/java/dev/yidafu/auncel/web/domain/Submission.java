package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Submission extends BaseEntity {
    @Column
    private String aHtml;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private  SubmissionStatus aCss = SubmissionStatus.PADDING;

    @Column
    private String status;

    @Column(columnDefinition = "int default 0")
    private int score = 0;

    @Column(columnDefinition = "text")
    private String logs;

    @Column(columnDefinition = "text")
    private String screenshot;

    @JsonBackReference
    @JoinColumn(name = "problem_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = Problem.class)
    private Problem problem;
}
