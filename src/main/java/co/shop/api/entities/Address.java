package co.shop.api.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String houseNumber;

    @Column(length = 40, nullable = false)
    private String streetName;

    @Column(length = 40, nullable = false)
    private String city;

    @Column(length = 6, nullable = false)
    private String zipcode;

}
