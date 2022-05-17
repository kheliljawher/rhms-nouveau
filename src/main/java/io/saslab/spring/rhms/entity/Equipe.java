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
@Table(name ="equipe")
public class Equipe {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;


    @OneToMany (mappedBy = "equipe")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Employee> employees;

    @OneToMany (mappedBy = "equipe")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Planning> plannings;

}
