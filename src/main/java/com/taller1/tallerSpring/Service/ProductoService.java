package com.taller1.tallerSpring.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.tallerSpring.DTO.ProductoDTO;
import com.taller1.tallerSpring.Repository.ProductoRepository;
import com.taller1.tallerSpring.Repository.Entity.ProductoEntity;
import com.taller1.tallerSpring.Repository.Entity.VentasEntity;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<ProductoEntity> findAll(){
        return (List<ProductoEntity>) productoRepository.findAll();
    }
    public ProductoDTO findById(int id){
       try {
        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        ProductoEntity productoEntity = productoOptional.get();
        
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(productoEntity.getNombre());
        productoDTO.setCantidad(productoEntity.getCantidad());
        productoDTO.setPrecio(productoEntity.getPrecio());
        
        return productoDTO;
        
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        
    }
    public List<ProductoEntity> findByName(String nombre){
        return productoRepository.findByNombreQuery(nombre);
        
    }

    public ProductoEntity save(ProductoEntity productoEntity){
        return productoRepository.save(productoEntity);
    }

    public ProductoEntity update(int id,ProductoEntity productoActualizado){
        ProductoEntity productoExistente;
        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        
        if (productoOptional.isPresent()) {
            productoExistente = productoOptional.get();
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setCantidad(productoActualizado.getCantidad());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            return productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    public String deleteById(int id){
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "El producto ha sido eliminado";
        } else {
            return "No se encontró ningún producto con id: " + id;
        }
    }

    public String actualizarProductoPostVenta(VentasEntity ventasEntity){
        for (ProductoEntity productoVendido : ventasEntity.getProductosVendidos()) {
            List<ProductoEntity> productos = productoRepository.findByNombreQuery(productoVendido.getNombre());

            if (!productos.isEmpty()) {
                ProductoEntity productoExistente = productos.get(0);

                int nuevaCantidad = productoExistente.getCantidad() - productoVendido.getCantidad();
                productoExistente.setCantidad(nuevaCantidad);

                update(productoExistente.getId(), productoExistente);
            } else {
                throw new RuntimeException("Producto no encontrado con nombre: " + productoVendido.getNombre());
            }
        }
        return "Productos actualizados después de la venta";
    }


}
