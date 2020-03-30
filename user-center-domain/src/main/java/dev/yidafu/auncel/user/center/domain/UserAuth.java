package dev.yidafu.auncel.user.center.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAuth {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", columnDefinition = "long")
    private Long userId;

    @Column(name = "identity_type")
    private String identityType;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "credential")
    private String credential;

    @Column(name = "verified")
    private String verifiled;

    public UserAuth() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getCredential() {
        return credential;
    }

    public String getVerifiled() {
        return verifiled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public void setVerifiled(String verifiled) {
        this.verifiled = verifiled;
    }
}
