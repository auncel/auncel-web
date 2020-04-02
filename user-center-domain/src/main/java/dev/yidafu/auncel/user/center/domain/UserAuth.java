package dev.yidafu.auncel.user.center.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"credential"})
public class UserAuth extends BaseEntity {

    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional =  false, fetch = FetchType.LAZY, targetEntity = User.class)
    private User authUser;

    /**
     * 登录方式
     */
    @Column(name = "identity_type", nullable = false)
    private String identityType;

    /**
     * 登录名
     */
    @Column(name = "identifier", nullable = false)
    private String identifier;

    /**
     * 凭证，如：密码
     */
    @Column(name = "credential", nullable = false)
    private String credential;

    @Column(name = "verified")
    private String verifiled;
}
