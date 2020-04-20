package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

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

    @Override
    public String toString() {
        return "Tag{" +
                "value='" + value + '\'' +
                '}';
    }
}
