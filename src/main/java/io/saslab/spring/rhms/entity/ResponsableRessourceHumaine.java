package io.saslab.spring.rhms.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="responsableRessourceHumaine")
public class ResponsableRessourceHumaine extends Utilisateur{

 private String responsable;

}
