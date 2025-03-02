package com.algaworks.osworks.domain.event;

import com.algaworks.osworks.adapters.outbound.entities.JpaClientEntity;

import java.util.List;

public interface ClientRepository {

    List<JpaClientEntity> findAll();
    JpaClientEntity save(JpaClientEntity jpaClientEntity);
    JpaClientEntity findById(Long id);
    JpaClientEntity deleteById(Long id);

}
