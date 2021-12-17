package com.sanfosx.ApiRestJavaInfo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    //clave
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //username es el email y debe ser unico
    @NotEmpty(message = "El email no puede ser vacio")
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")//expresion para validar mail
    @Column(unique = true, length = 45)
    @Size(min = 6, max = 50)
    private String username;
    //tipo de usuario
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
    //nombre
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Size(min = 3, max = 30)
    private String firstname;
    //apellido
    @NotEmpty(message = "El apellido no puede ser vacio")
    @Size(min = 3, max = 30)
    private String lastname;
    //ciudad
    @NotEmpty(message = "La ciudad no puede ser vacio")
    @Size(min = 3, max = 30)
    private String city;
    //provincia
    @NotEmpty(message = "La provincia no puede ser vacio")
    @Size(min = 3, max = 30)
    private String province;
    //pais
    @NotEmpty(message = "El pais no puede ser vacio")
    @Size(min = 3, max = 30)
    private String country;
    //password
    @NotEmpty(message = "El password no puede ser vacio")
    @Size(min = 8, max = 30)
    private String password;
    //fecha de creacion
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createdDate;
    //fecha ultima actualizacion
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime lasUpdateDate;
    //getters y setters
    public Long getId() {
        return id;
    }

    /*public void setIdUser(Long id) { // no se deberia setear el id ya que es autogenerado
        this.idUser = id;
    }*/

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

   /* public void setCreatedDate(LocalDateTime createdDate) { //no se debe setear la fecha de creacion
        this.createdDate = createdDate;
    }*/

    public LocalDateTime getLasUpdateDate() {
        return lasUpdateDate;
    }

    public void setLasUpdateDate(LocalDateTime lasUpdateDate) {
        this.lasUpdateDate = lasUpdateDate;
    }

}

