package com.taller1.tallerSpring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.tallerSpring.Repository.Entity.VentasEntity;

@Repository
public interface VentasRepository extends CrudRepository<VentasEntity,Integer> {

}
