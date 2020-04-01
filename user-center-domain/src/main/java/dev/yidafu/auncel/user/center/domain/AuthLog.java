package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthLog extends BaseEntity {
    @Column(name = "login_ip")
    private String loginIp = "0.0.0.0";

    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY,targetEntity = User.class)
    private User logUser;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
