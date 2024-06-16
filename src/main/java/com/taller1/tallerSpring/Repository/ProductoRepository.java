package com.taller1.tallerSpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taller1.tallerSpring.Repository.Entity.ProductoEntity;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoEntity,Integer> {
    @Query("SELECT p FROM ProductoEntity p WHERE p.nombre LIKE %:nombre%")
    List<ProductoEntity> findByNombreQuery(@Param("nombre") String nombre);
}
