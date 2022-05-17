package io.saslab.spring.rhms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="employee")
public class Employee  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "num_cin")
    private String num_cin;

    @Column(name = "num_tel")
    private String num_tel;

    @Column(name = "image")
    private String image;

    @OneToMany (mappedBy = "employee")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Conge> conges;

    @OneToMany (mappedBy = "employee")
    private List<Cv> cvs;


    @ManyToOne
    @JoinColumn(name="id_equipe")
    private Equipe equipe;

    @OneToMany (mappedBy = "employee")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Planning> plannings;

    @OneToMany (mappedBy = "employee")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Departement> departements;

}
