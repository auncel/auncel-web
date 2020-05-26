package dev.yidafu.auncel.web.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContestProblemDto implements Serializable  {
    private long id;

    private ProblemDto problem;

    private Date createdAt;

    private Date updatedAt;
}
