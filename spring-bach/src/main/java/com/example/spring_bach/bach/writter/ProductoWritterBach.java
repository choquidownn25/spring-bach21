package com.example.spring_bach.bach.writter;


import com.example.spring_bach.entity.Producto;
import com.example.spring_bach.repository.ProductoRepository;
import lombok.AllArgsConstructor;


import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductoWritterBach   {

    private final ProductoRepository productoRepository;

    @Bean
    public ItemWriter<Producto> writer() {

        return items -> productoRepository.saveAll(items);
    }
}
