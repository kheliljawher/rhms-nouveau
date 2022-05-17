package io.saslab.spring.rhms.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="conge")

public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_debut")
    private Date date_debut;

    @Column(name = "date_fin")
    private Date date_fin;

    @Column(name = "Type")
    private String Type;

    @Column(name = "Description")
    private String Description;

   //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
}
