package br.com.challenge.javachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.javachallenge.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
