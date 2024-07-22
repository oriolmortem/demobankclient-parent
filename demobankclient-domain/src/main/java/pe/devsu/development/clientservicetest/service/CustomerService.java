package pe.devsu.development.clientservicetest.service;

import java.util.List;

import pe.devsu.development.clientservicetest.dto.Customer;

public interface CustomerService {

    public List<Customer> findAll() throws Exception;

    public Customer findById(Integer id) throws Exception;

    public Customer save(Customer c) throws Exception;

    public Customer update(Customer c) throws Exception;

    public Customer partialUpdate(Customer c) throws Exception;

    public void delete(Customer a) throws Exception;

}
