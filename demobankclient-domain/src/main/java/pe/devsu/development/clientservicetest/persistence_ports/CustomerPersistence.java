package pe.devsu.development.clientservicetest.persistence_ports;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.devsu.development.clientservicetest.dto.Customer;

@Repository
public interface CustomerPersistence {
    public List<Customer> findAll() throws Exception;

    public Customer findById(Integer id) throws Exception;

    public List<Customer> findByDocId(String docId) throws Exception;

    public Customer save(Customer c) throws Exception;

    public Customer update(Customer c) throws Exception;

    public void delete(Customer a) throws Exception;
}