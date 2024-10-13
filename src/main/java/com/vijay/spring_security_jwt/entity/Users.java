package com.vijay.spring_security_jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vijay.spring_security_jwt.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotEmpty(message = "User Name can't be empty")
    @NotNull(message = "User Name can't be null")
    @NotBlank(message = "User Name can't be blank")
    private  String userName;

    @NotEmpty(message = "Password can't be empty")
    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    @CurrentTimestamp
    private Date createdAt;

    public Users(String userName, String password, Optional<UserRole> role) {
        this.userName = userName;
        this.password = password;
        this.role = role.orElse(UserRole.ROLE_USER);
    }


    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
