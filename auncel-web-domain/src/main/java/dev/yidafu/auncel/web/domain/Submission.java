package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Submission extends BaseEntity {
    @Column
    private String html;

    @Column
    private  String style;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private SubmissionStatus status = SubmissionStatus.PADDING;

    @Column(columnDefinition = "int default 0")
    private int score = 0;

    @Column(columnDefinition = "text")
    private String renderTree;

    @Column(columnDefinition = "text")
    private String logs;

    @Column(columnDefinition = "text")
    private String screenshot;

    @JsonBackReference
    @JoinColumn(name = "problem_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = Problem.class)
    private Problem problem;

    @JoinColumn(name = "submiter_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = User.class)
    private User submiter;

    @Override
    public String toString() {
        return "Submission{" +
                "html='" + html + '\'' +
                ", style=" + style +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", renderTree=" + renderTree +
                ", logs='" + logs + '\'' +
                ", screenshot='" + screenshot + '\'' +
                '}';
    }
}
