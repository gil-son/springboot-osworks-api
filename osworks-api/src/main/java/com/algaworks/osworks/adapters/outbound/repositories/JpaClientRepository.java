package com.algaworks.osworks.adapters.outbound.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.algaworks.osworks.adapters.outbound.entities.JpaClientEntity;

@Repository // Component from Spring
public interface JpaClientRepository extends JpaRepository<JpaClientEntity, Long>{

	List<JpaClientEntity> findByName(String name);
	List<JpaClientEntity> findByNameContaining(String name);
}
