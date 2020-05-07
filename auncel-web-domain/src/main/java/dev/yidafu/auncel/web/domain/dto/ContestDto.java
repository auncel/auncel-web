package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.Contest;
import dev.yidafu.auncel.web.domain.ContestAccessType;
import dev.yidafu.auncel.web.domain.ContestProblem;
import dev.yidafu.auncel.web.domain.ContestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContestDto implements Serializable {
    private long id;

    private String title;

    private String clarification;

    private Date startTime;

    private Date endTime;

    private int timeLimit = 0;

    private ContestStatus status;

    private ContestAccessType access = ContestAccessType.PUBLIC;

    private String invitaionCode;

    private UserDto maker;

    private List<ProblemDto> problems;

    private Date createdAt;

    private Date updatedAt;
}
