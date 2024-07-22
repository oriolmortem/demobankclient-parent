package pe.devsu.development.clientservicetest.adapters.postgres.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.clientservicetest.adapters.postgres.daos.CustomerRepository;
import pe.devsu.development.clientservicetest.adapters.postgres.daos.PersonRepository;
import pe.devsu.development.clientservicetest.adapters.postgres.entities.CustomerEntity;
import pe.devsu.development.clientservicetest.dto.Customer;
import pe.devsu.development.clientservicetest.persistence_ports.CustomerPersistence;

@RequiredArgsConstructor
@Repository("brandPersistence")
public class CustomerPersistencePostgres implements CustomerPersistence {

    private static final Logger log = LogManager.getLogger(CustomerPersistencePostgres.class);

    private final CustomerRepository customerRepository;

    private final PersonRepository personRepository;

    @Override
    public void delete(Customer a) throws Exception {
        try {
            customerRepository.delete(toEntity(a));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = null;
        try {
            List<CustomerEntity> customerEntityList = customerRepository.findByDeleteTimeIsNull();
            if (!CollectionUtils.isEmpty(customerEntityList)) {
                customerList = customerEntityList.stream().map(entity -> entity.toDomain())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return customerList;
    }

    @Override
    public List<Customer> findByDocId(String docId) throws Exception {
        List<Customer> customerList = null;
        try {
            List<CustomerEntity> customerEntityList = customerRepository.findByDocIdIgnoreCase(docId);
            if (!CollectionUtils.isEmpty(customerEntityList)) {
                customerList = customerEntityList.stream().map(entity -> entity.toDomain())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return customerList;
    }

    @Override
    public Customer findById(Integer id) throws Exception {
        try {
            return customerRepository.findById(id)
                    .map(CustomerEntity::toDomain)
                    .orElseThrow(() -> new Exception("Api: Customer with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Customer save(Customer c) throws Exception {
        try {
            return customerRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Customer update(Customer c) throws Exception {
        try {
            if (!personRepository.existsById(c.getId())) {
                throw new Exception("Customer not found");
            }
            return personRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    private CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setActive(customer.isActive());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setAge(customer.getAge());
        customerEntity.setDocId(customer.getDocId());
        customerEntity.setGender(customer.getGender());
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        customerEntity.setCreationTime(customer.getCreationTime());
        customerEntity.setUpdateTime(customer.getUpdateTime());
        customerEntity.setDeleteTime(customer.getDeleteTime());
        return customerEntity;
    }
}