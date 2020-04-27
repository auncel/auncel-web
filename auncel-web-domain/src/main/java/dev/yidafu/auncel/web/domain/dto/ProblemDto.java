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

    private String html;

    private String style;

    private String renderTree;

    private int stars;

    /**
     * 额外属性，用户是否做过
     */
    private ProblemStatusType status = ProblemStatusType.NONE;

    private ProblemDifficulty difficulty;

    private int acceptance = 0;

    private int submission = 0;

    private ProblemAccessType access;

    private List<TagDto> tags;

    private Date createdAt;

    private Date updatedAt;
}
