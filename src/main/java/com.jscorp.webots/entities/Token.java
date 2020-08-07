package com.jscorp.webots.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * @author airat_f17@mail.ru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tokens")
public class Token {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column (name = "token")
    private String token;

    @OneToOne(cascade = CascadeType.MERGE)//, cascade = CascadeType.REFRESH
    @JoinColumn(name = "user_id")
    private User user;


}
