package com.example.projetomigracao.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigracaoDadosJobConfig {


    @Bean
    public Job migracaoDadosJob(JobRepository jobRepository, @Qualifier("migracaoPessoaStep") Step migracaoPessoaStep, @Qualifier("migracaoDadosBancariosStep") Step migracaoDadosBancariosStep) {

        return new JobBuilder("migracaoDadosJob", jobRepository)
                .start(stepsParalelos(migracaoPessoaStep, migracaoDadosBancariosStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();

    }


    private Flow stepsParalelos(Step migracaoPessoaStep, Step migracaoDadosBancariosStep) {

        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosFlow")
                .start(migracaoDadosBancariosStep)
                .build();

        return new FlowBuilder<Flow>("stepsParalelosFlow")
                .start(migracaoPessoaStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();

    }
}