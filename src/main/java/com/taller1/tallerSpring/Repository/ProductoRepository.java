package com.taller1.tallerSpring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.tallerSpring.Repository.Entity.ProductoEntity;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoEntity,Integer> {

}
