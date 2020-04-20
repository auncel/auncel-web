package dev.yidafu.auncel.web.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", level=" + level +
                '}';
    }
}
