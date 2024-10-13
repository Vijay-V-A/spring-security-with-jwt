package com.vijay.spring_security_jwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Task should not blank")
    @NotNull(message = "Task should not null")
    @NotEmpty(message = "Task should not empty")
    private String task;

    @NotNull(message = "Description should not null")
    private String description;

    private Boolean isCompleted;

    @CreationTimestamp
    private Date createdAt;

    private Date completedAt;

    public Todo(String task, String desc) {
        this.task = task;
        this.description = desc;
    }
}
