package com.example.projetomigracao.step;

import com.example.projetomigracao.model.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigracaoDadosBancariosStepConfig {

    @Bean
    public Step migracaoDadosBancariosStep(ItemReader<DadosBancarios> reader, ItemWriter<DadosBancarios> writerBancoPessoa, JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("migracaoDadosBancariosStep", jobRepository)
                .<DadosBancarios, DadosBancarios>chunk(200, transactionManager)
                .reader(reader)
                .writer(writerBancoPessoa)
                .transactionManager(transactionManager)
                .build();

    }
}
