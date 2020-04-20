package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

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
    @JsonBackReference
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "none",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private User user;

    @JsonBackReference
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(
            name = "contest_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "none",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Contest contest;

    @Column(name = "total_score")
    private int totalScore;

    @Column
    private String status;

    @Column
    private int duration;

    @Column(name = "submit_time", columnDefinition = "datetime default current_timestamp")
    private Date submitTime = new Date();

    @Override
    public String toString() {
        return "UserContest{" +
                "user=" + user +
                ", contest=" + contest +
                ", totalScore=" + totalScore +
                ", status='" + status + '\'' +
                ", duration=" + duration +
                ", submitTime=" + submitTime +
                '}';
    }
}
