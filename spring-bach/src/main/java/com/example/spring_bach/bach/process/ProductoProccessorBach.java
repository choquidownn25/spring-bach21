package com.example.spring_bach.bach.process;

import com.example.spring_bach.dto.ProductoDto;
import com.example.spring_bach.entity.Producto;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductoProccessorBach implements ItemProcessor<ProductoDto, Producto> {


    @Override
    public Producto  process(ProductoDto item)  {
        return Producto.builder()
                .cantidad(item.getCantidad())
                .descripcion(item.getDescripcion())
                .imagen(item.getImagen())
                .nombre(item.getNombre())
                .precio(item.getPrecio())
                .build();
    }
}
