package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Tag extends BaseEntity {

    @Column
    private String value = "None";

    @JsonBackReference
    @JoinTable(
            name = "problem_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id")
    )
    @ManyToMany(targetEntity = Problem.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Problem> problems;
}
