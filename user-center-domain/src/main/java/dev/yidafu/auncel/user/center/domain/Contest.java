package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Date startTiem;

    @Column(name = "end_time",nullable = true)
    private Date endTime;

    @Column(name = "time_limit", columnDefinition = "int default 0")
    private int timeLimit = 0;

    @Column
    private String status;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "int default 0")
    private ContestAccessType access = ContestAccessType.PUBLIC;

    @Column(name = "invitaion_code")
    private String invitaionCode;

    @JsonBackReference
    @JoinColumn(name = "maker_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = User.class)
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
}
