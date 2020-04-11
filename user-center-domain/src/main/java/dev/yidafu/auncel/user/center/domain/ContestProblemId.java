package dev.yidafu.auncel.user.center.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContestProblemId implements Serializable {
    private long contest;
    private long problem;
    public int hashCode() {
        return (new Long(contest).toString() + '-' + new Long(problem).toString()).hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof ContestProblemId) {
            ContestProblemId otherId = (ContestProblemId) object;
            return (otherId.contest == this.contest) && (otherId.problem == this.problem);
        }
        return false;
    }
}
