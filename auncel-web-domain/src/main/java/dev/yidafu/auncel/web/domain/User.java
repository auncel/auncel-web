package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="realname", nullable = true, length = 50)
    private String realname;

    @Lob
    @Column(name="avatar", columnDefinition = "text")
    private  String avatar = "http://www.gravatar.com/avatar/" + this.username + "?s=55&d=identicon&r=PG";

    @Column(name = "slogan", columnDefinition = "text")
    private String slogan;

    @Column(name = "role", columnDefinition = "int")
    private Integer role;

    @Column(name="status")
    private String status;

    @CreatedDate
    @Column(name = "register_time")
    private Date registerTime;
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
    @JoinColumn(name = "user_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<UserAuth> userAuths = new ArrayList<UserAuth>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<UserContest> userContests = new ArrayList<>();

}
