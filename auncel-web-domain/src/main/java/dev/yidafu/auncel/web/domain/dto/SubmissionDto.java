package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubmissionDto implements Serializable {
    private long id;

    private String html;

    private  String style;

    private SubmissionStatus status = SubmissionStatus.PADDING;

    private int score = 0;

    private String logs;

    private String screenshot;

    private ProblemDto problem;

    private UserDto submiter;

    private Date createdAt;

    private Date updatedAt;

}
