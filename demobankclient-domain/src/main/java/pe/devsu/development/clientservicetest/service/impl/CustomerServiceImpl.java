package pe.devsu.development.clientservicetest.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.clientservicetest.dto.Customer;
import pe.devsu.development.clientservicetest.persistence_ports.CustomerPersistence;
import pe.devsu.development.clientservicetest.service.CustomerService;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

    private final CustomerPersistence customerPersistence;

    @Override
    public List<Customer> findAll() throws Exception {
        try {
            return customerPersistence.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Customer findById(Integer id) throws Exception {
        try {
            return customerPersistence.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Customer save(Customer c) throws Exception {
        Customer saved = null;
        try {
            if (validate(c)) {
                List<Customer> existingCustomer = customerPersistence.findByDocId(c.getDocId());
                if (existingCustomer != null && !existingCustomer.isEmpty()) {
                    throw new Exception(
                            "API error: Customer ID number already registered.");
                }
                c.setCreationTime(LocalDateTime.now());
                saved = customerPersistence.save(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return saved;
    }

    @Override
    public Customer update(Customer c) throws Exception {
        Customer saved = null;
        try {
            if (validate(c)) {
                List<Customer> existingCustomer = customerPersistence.findByDocId(c.getDocId());
                if (existingCustomer != null && !existingCustomer.isEmpty()
                        && existingCustomer.stream().anyMatch(cc -> !cc.getId().equals(c.getId()))) {
                    throw new Exception(
                            "API error: Customer ID number already registered.");
                }
                c.setUpdateTime(LocalDateTime.now());
                saved = customerPersistence.update(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return saved;
    }

    @Override
    public Customer partialUpdate(Customer c) throws Exception {
        Customer saved = null;
        try {
            if (validatePerson(c)) {
                List<Customer> existingCustomer = customerPersistence.findByDocId(c.getDocId());
                if (existingCustomer != null && !existingCustomer.isEmpty()
                        && existingCustomer.stream().anyMatch(cc -> !cc.getId().equals(c.getId()))) {
                    throw new Exception(
                            "API error: Customer ID number already registered.");
                }
                c.setUpdateTime(LocalDateTime.now());
                saved = customerPersistence.update(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return saved;
    }

    @Override
    public void delete(Customer c) throws Exception {
        try {
            customerPersistence.delete(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    public boolean validate(Customer c) throws Exception {

        if (c.getAddress() != null && c.getAddress().isEmpty()) {
            throw new Exception("API error: Address not found");
        }

        if (c.getAge() != null && c.getAge() <= 0) {
            throw new Exception("API Error: Age not valid");
        }

        if (c.getDocId() != null && c.getDocId().isEmpty()) {
            throw new Exception("API Error: ID not found");
        }

        if (c.getName() != null && c.getName().isEmpty()) {
            throw new Exception("API Error: Name not found");
        }

        if (c.getPassword() != null && c.getPassword().isEmpty()) {
            throw new Exception("API Error: Password not found");
        }

        if (c.getPhoneNumber() != null && c.getPhoneNumber().isEmpty()) {
            throw new Exception("API Error: Phone number not found");
        }
        return true;
    }

    public boolean validatePerson(Customer c) throws Exception {

        if (c.getAddress().isEmpty()) {
            throw new Exception("Error: Address not found");
        }

        if (c.getAge() <= 0) {
            throw new Exception("Error: Age not valid");
        }

        if (c.getName().isEmpty()) {
            throw new Exception("Error: Name not found");
        }

        if (c.getPhoneNumber().isEmpty()) {
            throw new Exception("Error: Phone number not found");
        }
        return true;
    }
}