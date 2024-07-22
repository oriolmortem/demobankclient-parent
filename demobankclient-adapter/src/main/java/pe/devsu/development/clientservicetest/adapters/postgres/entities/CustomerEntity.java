package pe.devsu.development.clientservicetest.adapters.postgres.entities;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.devsu.development.clientservicetest.dto.Customer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(schema = "\"public\"", name = "\"Customer\"")
@PrimaryKeyJoinColumn(name = "person_id")
public class CustomerEntity extends PersonEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    public Customer toDomain() {
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
        return customer;
    }
}
