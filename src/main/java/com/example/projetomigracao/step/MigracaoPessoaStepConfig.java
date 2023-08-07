package com.example.projetomigracao.step;

import com.example.projetomigracao.model.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigracaoPessoaStepConfig {

    @Bean
    public Step migracaoPessoaStep(ItemReader<Pessoa> arquivoPessoaReader, ItemWriter<Pessoa> writer, JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("migracaoPessoaStep", jobRepository)
                .<Pessoa, Pessoa>chunk(200, transactionManager)
                .reader(arquivoPessoaReader)
                .writer(writer)
                .transactionManager(transactionManager)
                .build();

    }
}
