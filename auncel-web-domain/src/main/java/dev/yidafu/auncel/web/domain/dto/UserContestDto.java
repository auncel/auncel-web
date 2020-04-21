package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.UserContestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserContestDto implements Serializable {
    private long userId;
    private long contestId;
    private int totalScore;

    private UserContestStatus status;

    private int duration;

    private ContestDto contest;

}
