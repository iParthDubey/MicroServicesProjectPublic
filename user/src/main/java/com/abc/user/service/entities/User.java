package com.abc.user.service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="micro_users")
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="NAME", length = 20)
    private String name;

    @Column(name="EMAIL")
    private  String email;

    @Column(name="ABOUT")
    private String about;

    @Transient //This field is not saved in database
    private List<Rating>  ratings= new ArrayList<>();

}
