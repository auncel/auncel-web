package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.ProblemAccessType;
import dev.yidafu.auncel.web.domain.ProblemDifficulty;
import dev.yidafu.auncel.web.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemDto implements Serializable {
    private long id;
    private String title;

    private String description;

    private String qHtml;

    private String qCss;

    private String renderTree;

    private int stars;

    private ProblemDifficulty difficulty;

    private int acceptance = 0;

    private int submission = 0;

    private ProblemAccessType access;

    private List<Tag> tags;

    private Date createdAt;

    private Date updatedAt;
}
