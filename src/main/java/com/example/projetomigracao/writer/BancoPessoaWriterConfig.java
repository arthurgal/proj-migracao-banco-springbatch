package com.example.projetomigracao.writer;

import com.example.projetomigracao.model.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BancoPessoaWriterConfig {

    @Bean
    public ItemWriter<Pessoa> writerBancoPessoa(@Qualifier("appDS") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (:id, :nome, :email, :data_nascimento, :idade)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
