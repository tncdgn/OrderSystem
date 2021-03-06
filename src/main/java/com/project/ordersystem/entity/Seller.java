package com.project.ordersystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SELLER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String companyName;

    @Column(name = "EMAIL")
    private String email;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
