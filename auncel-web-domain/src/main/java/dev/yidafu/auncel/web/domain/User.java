package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Mapping()
    @Column(name="realname", nullable = true, length = 50)
    private String realname;

    @Lob
    @Column(name="avatar", columnDefinition = "text")
    private  String avatar = "http://www.gravatar.com/avatar/" + this.username + "?s=200&d=identicon&r=PG";

    @Column(name = "slogan", columnDefinition = "text")
    private String slogan;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRoleType role = UserRoleType.ORDINARY;

    @Column(name="status")
    private String status;

    // https://stackoverflow.com/questions/197045/setting-default-values-for-columns-in-jpa
    @Column(name="register_ip", columnDefinition="varchar(15) default '0.0.0.0'")
    private String registerIp = "0.0.0.0";

    @Column(name ="school")
    private String school;
    /**
     *在使用Json来序列化对象时，会产生无限递归（Infinite recursion）的错误。这里有2个解决方法：
     *    a. 在@ManyToOne下面使用@JsonIgnore.
     *    b. 在@OneToMany下面使用@JsonManagedReference,在@ManyToOne下面使用@JsonBackReference
     * @JsonBackReference和@JsonManagedReference：@JsonBackReference标注的属性在序列化（serialization)时，
     * 会被忽略。@JsonManagedReference标注的属性则会被序列化。在序列化时，@JsonBackReference的作用相当于
     * @JsonIgnore，此时可以没有@JsonManagedReference。但在反序列化（deserialization）时，如果没有
     * @JsonManagedReference，则不会自动注入@JsonBackReference标注的属性；如果有@JsonManagedReference，则
     * 会自动注入@JsonBackReference标注的属性。
     */
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<AuthLog> authLogs = new ArrayList<AuthLog>();


    @JsonManagedReference
    @JoinColumn(name = "maker_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Problem> problems;


    @JsonManagedReference
    @JoinColumn(name = "submiter_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Submission> submissions;

    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<UserAuth> userAuths;

    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<UserContest> userContests;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", slogan='" + slogan + '\'' +
                ", role=" + role +
                ", status='" + status + '\'' +
                ", registerIp='" + registerIp + '\'' +
                ", school='" + school + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
        this.avatar = "http://www.gravatar.com/avatar/" + this.username + "?s=200&d=identicon&r=PG";
    }
}
