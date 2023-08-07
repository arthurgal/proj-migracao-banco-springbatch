# Projeto de migração de dados usando Spring Batch

### Ferramaentas utilizadas
  - Spring Batch
  - MySql

### Sobre o projeto
  - O projeto tem como finalidade utilizar o Spring Batch para processar dois arquivos CSV que contém 10.000 registros de pessoas e de dados bancarios. Usando o modulo Batch do framework spring, criei um Job
    que faz a migração desses registros para um banco de dados mysql. Esse job utiliza dois steps, um que migra os dados das pessoas e outro que migra os dados bancarios, cada step foi projetado para utiliza
    um chunk de tamanho 200.
    
