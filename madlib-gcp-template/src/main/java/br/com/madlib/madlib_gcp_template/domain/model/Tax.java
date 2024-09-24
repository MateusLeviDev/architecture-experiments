package br.com.madlib.madlib_gcp_template.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tax")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

}
