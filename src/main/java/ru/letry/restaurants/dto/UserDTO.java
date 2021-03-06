package ru.letry.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.letry.restaurants.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private int restaurantId;

    private String votedForRestaurant;

    private Set<Role> roles;

    public UserDTO(int id, String name, String email, Set<Role> roles, int restaurantId, String votedForRestaurant) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = new HashSet<>(roles);
        this.restaurantId = restaurantId;
        this.votedForRestaurant = votedForRestaurant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getVotedForRestaurant() {
        return votedForRestaurant;
    }

    public void setVotedForRestaurant(String votedForRestaurant) {
        this.votedForRestaurant = votedForRestaurant;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = new HashSet<>(roles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) &&
                restaurantId == userDTO.restaurantId &&
                Objects.equals(votedForRestaurant, userDTO.votedForRestaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, votedForRestaurant);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + votedForRestaurant + '\'' +
                '}';
    }
}
