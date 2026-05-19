package com.example.spring_bach.bach.reader;

import com.example.spring_bach.dto.ProductoDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ProductoReaderConfig {

    @Bean
    public FlatFileItemReader<ProductoDto> reader() {

        return new FlatFileItemReaderBuilder<ProductoDto>()
                .name("productoReader")
                .resource(
                        new FileSystemResource(
                                "src/main/resources/producto.csv"
                        )
                )
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names(
                        "cantidad",
                        "descripcion",
                        "imagen",
                        "nombre",
                        "precio"
                )
                .targetType(ProductoDto.class)
                .build();
    }


}
