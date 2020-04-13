package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Problem extends BaseEntity {
    @Column
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String qHtml;

    @Column(columnDefinition = "text")
    private String qCss;

    @Column(columnDefinition = "text")
    private String renderTree;

    @Column
    private int stars;

    @Column
    private String diffculty;

    @Column
    private int rate;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private ProblemAccessType access;

    @JsonManagedReference
    @JoinColumn(name = "problem_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();


    @JsonManagedReference
    @JoinColumn(name = "problem_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Submission> submissions = new ArrayList<>();

    @JsonBackReference
    @JoinColumn(name = "maker_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = User.class)
    private User maker;

    @JsonManagedReference
    @OneToMany(mappedBy = "problem",cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<ContestProblem> contests = new ArrayList<>();
}
