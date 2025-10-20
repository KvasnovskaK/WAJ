package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Meno je povinné")
    @Size(min = 2, max = 50, message = "Meno musí mať 2-50 znakov")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné")
    @Size(min = 2, max = 50, message = "Priezvisko musí mať 2-50 znakov")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Dátum narodenia je povinný.")
    @Past(message = "Dátum narodenia musí byť v minulosti.")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Email je povinný.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Zadajte platný formát emailovej adresy."
    )
    private String email;

    @NotBlank(message = "Telefónne číslo je povinné.")
    @Pattern(
            regexp = "^\\+421\\d{9}$",
            message = "Telefónne číslo musí byť vo formáte +4219XXXXXXXX (9 číslic)."
    )
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @NotBlank(message = "Názov pracovnej pozície je povinný.")
    @Size(min = 2, max = 100, message = "Názov pracovnej pozície musí mať 2-100 znakov.")
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotNull(message = "Plat je povinný.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Plat musí byť kladné číslo.")
    @Digits(integer = 8, fraction = 2, message = "Plat môže mať maximálne 8 číslic a 2 desatinné miesta.")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "full_time", nullable = false)
    private boolean fullTime;

    // --- Constructors ---

    public Employee() {
    }

    public Employee(String firstName, String lastName, LocalDate birthDate, String email,
                    String phone, String jobTitle, BigDecimal salary, boolean fullTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", fullTime=" + fullTime +
                '}';
    }
}
