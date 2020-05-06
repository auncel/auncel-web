package dev.yidafu.auncel.web.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class Notification extends  BaseEntity {
    @Column
    private  String title;

    @Column(nullable = true)
    private String content;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private NotificationLevel level = NotificationLevel.NOTICE;

    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", level=" + level +
                '}';
    }
}
