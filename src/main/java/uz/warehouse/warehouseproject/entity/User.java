package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean isActive = true;
    @ManyToMany
    private Set<Warehouse> warehouses;

    public User(String firstName, String lastName, String phoneNumber, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActive = isActive;
    }

    public User(String firstName, String lastName, String phoneNumber, String code, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.password = password;
        this.isActive = isActive;
    }
}
