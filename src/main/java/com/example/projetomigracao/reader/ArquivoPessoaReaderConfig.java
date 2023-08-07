package com.example.projetomigracao.reader;

import com.example.projetomigracao.model.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import javax.xml.crypto.Data;
import java.util.Date;

@Configuration
public class ArquivoPessoaReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> pessoaReader(){
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("pessoaReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Pessoa> fieldSetMapper(){
        return new FieldSetMapper<Pessoa>() {
            @Override
            public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(fieldSet.readString("nome"));
                pessoa.setEmail(fieldSet.readString("email"));
                pessoa.setData_nascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));
                pessoa.setIdade(fieldSet.readLong("idade"));
                pessoa.setId(fieldSet.readLong("id"));
                return pessoa;

            }
        };
    }
}
