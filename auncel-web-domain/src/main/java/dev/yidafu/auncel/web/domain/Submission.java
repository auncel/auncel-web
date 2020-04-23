package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Submission extends BaseEntity {
    @Column
    private String aHtml;

    @Column
    private  String aCss;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private SubmissionStatus status = SubmissionStatus.PADDING;

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

    @JoinColumn(name = "submiter_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = User.class)
    private User submiter;

    @Override
    public String toString() {
        return "Submission{" +
                "aHtml='" + aHtml + '\'' +
                ", aCss=" + aCss +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", logs='" + logs + '\'' +
                ", screenshot='" + screenshot + '\'' +
                '}';
    }
}
