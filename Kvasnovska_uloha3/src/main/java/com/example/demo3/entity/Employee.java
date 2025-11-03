package com.example.demo3.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Meno nesmie byť prázdne.")
    @Size(min = 2, max = 100, message = "Meno musí mať 2 až 100 znakov.")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Priezvisko nesmie byť prázdne.")
    @Size(min = 2, max = 100, message = "Priezvisko musí mať 2 až 100 znakov.")
    @Column(nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Email nesmie byť prázdny.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email nemá platný formát (napr. meno@firma.sk)."
    )
    @Column(nullable = false, unique = true, length = 150)
    private String email;


    @NotBlank(message = "Pozícia (Job Title) musí byť zvolená.")
    @Column(nullable = false, length = 100)
    private String jobTitle;

    @NotBlank(message = "Úväzok musí byť zvolený.")
    @Column(nullable = false, length = 30)
    private String fullTime;

    @Positive(message = "Plat musí byť kladné číslo.")
    @Column
    private Double salary;

    @Past(message = "Dátum narodenia musí byť v minulosti.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Telefón nesmie byť prázdny.")
    @Pattern(
            regexp = "^[0-9+\\- ]{8,20}$",
            message = "Telefónne číslo má nesprávny formát."
    )
    @Column(nullable = false, length = 20)
    private String phone;


    public Employee() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getFullTime() { return fullTime; }
    public void setFullTime(String fullTime) { this.fullTime = fullTime; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

}
