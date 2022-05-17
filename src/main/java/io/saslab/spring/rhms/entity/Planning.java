package io.saslab.spring.rhms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="planning")
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_Debut")
    private Date date_Debut;

    @Column(name = "date_Fin")
    private Date date_Fin;


    @ManyToOne
    private Employee employee;

    @OneToMany (mappedBy = "planning")

    private List<Departement> departements;

    @JsonIgnore
    @ManyToOne
    private Equipe equipe;

}
