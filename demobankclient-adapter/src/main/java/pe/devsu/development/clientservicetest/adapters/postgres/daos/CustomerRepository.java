package pe.devsu.development.clientservicetest.adapters.postgres.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.devsu.development.clientservicetest.adapters.postgres.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    public List<CustomerEntity> findByDocIdIgnoreCase(String docId) throws Exception;

    public List<CustomerEntity> findByDeleteTimeIsNull() throws Exception;

}
