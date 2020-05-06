package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Contest extends BaseEntity {
    @Column()
    private String title;

    @Column()
    private String clarification;

    @Column(name = "start_time",nullable = true)
    private Date startTime;

    @Column(name = "end_time",nullable = true)
    private Date endTime;

    @Column(name = "time_limit", columnDefinition = "int default 0")
    private int timeLimit = 0;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private ContestStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "int default 0")
    private ContestAccessType access = ContestAccessType.PUBLIC;

    @Column(name = "invitaion_code")
    private String invitaionCode = Integer.toHexString((int) (Math.random() * 1000)) + Long.toHexString(1) + Integer.toHexString((int) (Math.random() * 1000));

    @JsonBackReference
    @JoinColumn(name = "maker_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User maker;

//    @JsonManagedReference
//    @JoinColumn(name = "contest_id")
//    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
//    private List<Problem> Problems = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "contest")
    private List<UserContest> userContests;

    @JsonManagedReference
    @OneToMany(mappedBy = "contest")
    private List<ContestProblem> contestProblems;

    @Override
    public String toString() {
        return "Contest{" +
                "title='" + title + '\'' +
                ", clarification='" + clarification + '\'' +
                ", startTiem=" + startTime +
                ", endTime=" + endTime +
                ", timeLimit=" + timeLimit +
                ", status='" + status + '\'' +
                ", access=" + access +
                ", invitaionCode='" + invitaionCode + '\'' +
                '}';
    }
}
