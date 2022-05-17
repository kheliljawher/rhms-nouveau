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
@Table(name ="poste")
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "cv")
    private String cv;

    @Column(name = "lettreMotivation")
    private String lettreMotivation;

    @Column(name = "offre")
    private String offre;

    @Column(name = "date")
    private Date date;

    @Column(name = "position")
    private String position;

    @JsonIgnore
    @ManyToOne
    private Employee employee;

}
