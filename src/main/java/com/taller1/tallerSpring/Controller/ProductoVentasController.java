package com.taller1.tallerSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.taller1.tallerSpring.DTO.ProductoDTO;
import com.taller1.tallerSpring.Repository.Entity.ProductoEntity;
import com.taller1.tallerSpring.Repository.Entity.VentasEntity;
import com.taller1.tallerSpring.Service.ProductoService;
import com.taller1.tallerSpring.Service.VentaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/negocio")
public class ProductoVentasController {
    @Autowired
    ProductoService productoService;
    @Autowired
    VentaService ventaService;
    @GetMapping("/productosall")
    public List<ProductoEntity> all(){
        return productoService.findAll();
    }
    
    @GetMapping("/producto/{id}")
    public ProductoDTO getProductById(@PathVariable int id) {
            ProductoDTO productoDTO = productoService.findById(id);
            return productoDTO;
    }

    @GetMapping("/productonombre/{nombre}")
    public List<ProductoEntity> findByName(@PathVariable String nombre){
        return productoService.findByName(nombre);
    }

    @PostMapping("/registrar")
    public ProductoEntity save(@RequestBody ProductoEntity productoEntity){
        return productoService.save(productoEntity);
    }

    @PutMapping("/actualizar/{id}")
    public ProductoEntity update(@PathVariable int id,@RequestBody ProductoEntity productoEntity){
        return productoService.update(id, productoEntity);
    }
    
    @DeleteMapping("/borrar/{id}")
    public String deleteById(@PathVariable int id){
        return productoService.deleteById(id);
    }

    @PutMapping("/venta")
    public VentasEntity ventaHecha(@RequestBody VentasEntity ventasEntity){
        return ventaService.venta(ventasEntity);
    }
    
    
}
