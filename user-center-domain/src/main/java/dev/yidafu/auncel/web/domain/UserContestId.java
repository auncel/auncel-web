package dev.yidafu.auncel.web.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserContestId implements Serializable {
    private long user;
    private long contest;
    public int hashCode() {
        return (new Long(user).toString() + '-' + new Long(contest).toString()).hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof UserContestId) {
            UserContestId otherId = (UserContestId) object;
            return (otherId.user == this.user) && (otherId.contest == this.contest);
        }
        return false;
    }
}
