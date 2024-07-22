package pe.devsu.development.clientservicetest.adapters.postgres.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.devsu.development.clientservicetest.adapters.postgres.entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{

}