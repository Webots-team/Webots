package com.jscorp.webots.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Entity
@Table(name = "users_images")
@Data
@NoArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Profile profile;

    @Column(name = "path")
    private String path;

    @Override
    public String toString() {
        return "UserImage";
    }
}