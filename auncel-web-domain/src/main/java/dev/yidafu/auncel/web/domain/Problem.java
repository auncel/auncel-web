package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

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

    @Enumerated(EnumType.ORDINAL)
    @Column
    private ProblemDifficulty difficulty;

    /**
     * 通过数
     */
    @Column(columnDefinition = "int default 0")
    private int acceptance = 0;

    /**
     * 总提交数
     */
    @Column(columnDefinition = "int default 0")
    private int submission = 0;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private ProblemAccessType access;

    @JsonManagedReference
    @JoinTable(
            name = "problem_tag",
            joinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    @ManyToMany(targetEntity = Tag.class,cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Tag> tags;


    @JsonManagedReference
    @JoinColumn(name = "problem_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Submission> submissions = new ArrayList<>();

    @JsonBackReference
    @JoinColumn(name = "maker_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = User.class)
    private User maker;

    @JsonBackReference
    @OneToMany(mappedBy = "problem",cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<ContestProblem> contests = new ArrayList<>();

    @Override
    public String toString() {
        return "Problem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", qHtml='" + qHtml + '\'' +
                ", qCss='" + qCss + '\'' +
                ", renderTree='" + renderTree + '\'' +
                ", stars=" + stars +
                ", difficulty=" + difficulty +
                ", acceptance=" + acceptance +
                ", submission=" + submission +
                ", access=" + access +
                ", tags=" + tags +
                '}';
    }
}
