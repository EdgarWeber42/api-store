package com.supinfo.proj.retailr.apistore.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message = "Username may not be empty")
    @NotBlank(message = "Username may not be blank")
    @ApiModelProperty(required = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    @NotEmpty(message = "Password may not be empty")
    @NotBlank(message = "Password may not be blank")
    @ApiModelProperty(required = true)
    private String password;

    @Column(name = "role")
    private String role;

    @Nullable
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Nullable
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    public User(){
    }

    private User(String username, String password, String role, Customer customer, Staff staff) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.customer = customer;
        this.staff = staff;
    }

    public static class Builder {
        private String username;
        private String password;
        private String role;
        private Customer customer;
        private Staff staff;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public Builder withCustomer(Customer customer){
            this.customer = customer;
            return this;
        }

        public Builder withStaff(Staff staff) {
            this.staff = staff;
            return this;
        }

        public User build(){
            return new User(
                    username,
                    password,
                    role,
                    customer,
                    staff
            );
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", customer=" + customer +
                ", staff=" + staff +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
