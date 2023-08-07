package com.example.projetomigracao.writer;

import com.example.projetomigracao.model.DadosBancarios;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BancoDadosBancariosWriterConfig {

    @Bean
    public ItemWriter<DadosBancarios> writer(@Qualifier("appDS") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) VALUES (:id, :pessoa_id, :agencia, :conta, :banco)")
                .beanMapped()
                .build();
    }
}
