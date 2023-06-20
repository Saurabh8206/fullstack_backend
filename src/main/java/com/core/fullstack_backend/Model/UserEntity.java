package com.core.fullstack_backend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
@Table(name = "USER_ENTITY")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "EMAIL_ID", length = 100)
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{8,10}", message = "Invalid contact number")
    @Column(name = "CONTACT_NO", length = 10)
    private String contactNo;


}
