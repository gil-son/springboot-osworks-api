package com.algaworks.osworks.adapters.outbound.repositories;

import com.algaworks.osworks.adapters.outbound.entities.JpaClientEntity;
import com.algaworks.osworks.domain.event.ClientRepository;

import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public List<JpaClientEntity> findAll() {
        return jpaClientRepository.findAll();
    }

    @Override
    public JpaClientEntity save(JpaClientEntity jpaClientEntity) {
        return jpaClientRepository.save(jpaClientEntity);
    }

    @Override
    public JpaClientEntity findById(Long id) {
        if(jpaClientRepository.findById(id).isPresent()){
            return jpaClientRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public JpaClientEntity deleteById(Long id) {
        if (jpaClientRepository.findById(id).isPresent()) {
            return jpaClientRepository.findById(id).get();
        }
        return null;
    }
}
