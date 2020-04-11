package dev.yidafu.auncel.user.center.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @url https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
 * @url https://stackoverflow.com/questions/23837561/jpa-2-0-many-to-many-with-extra-column
 */
@Data
@Entity
@IdClass(UserContestId.class)
public class UserContest {
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contest_id", referencedColumnName = "id")
    private Contest contest;

    @Column(name = "total_score")
    private int totalScore;

    @Column
    private String status;

    @Column
    private int duration;

    @Column(name = "submit_time", columnDefinition = "datetime default current_timestamp")
    private Date submitTime = new Date();
}
