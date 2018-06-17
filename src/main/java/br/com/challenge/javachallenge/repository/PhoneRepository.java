package br.com.challenge.javachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.javachallenge.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
