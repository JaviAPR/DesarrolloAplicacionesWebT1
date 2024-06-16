package com.taller1.tallerSpring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.tallerSpring.Repository.VentasRepository;
import com.taller1.tallerSpring.Repository.Entity.VentasEntity;

@Service
public class VentaService {
    @Autowired
    VentasRepository ventasRepository;
    @Autowired
    ProductoService productoService;

    public VentasEntity venta(VentasEntity ventasEntity){
        VentasEntity nuevaVenta = ventasRepository.save(ventasEntity);
        productoService.actualizarProductoPostVenta(ventasEntity);
        return nuevaVenta;
    }

}
