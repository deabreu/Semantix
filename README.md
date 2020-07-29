# Semantix

HTTP requests to the NASA Kennedy Space Center WWW server

Dataset oficial: https://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html

## Requisitos:

* Intellij IDEA ou Scala SBT

## Exame
* .1. Qual o objetivo do comando cache em Spark?

- R) O comando cache() do Spark tem como objetivo armazenar os RDDs - Resilient Distribuited Dataset em memória, aumentando o desempenho. Diferente do persist() que faz o cache em memória, no disco ou memória off-heap dependendo da estratégia de cache definida no comando.

* .2. O mesmo código implementado em Spark é normalmente mais ráído que a implementação equivalente em MapReduce. Por quê?
R) Algumas das razões pelo qual o Spark é mais rápido que o MapReduce:
- O core do Spark é o RDD - Resilient Distribuited Dataset que nada mais é do que uma coleção distribuida de objetos mutáveis para processar os dados em diferentes nós com partições lógicas.
- A melhor forma que o MapReduce processa os dados é no modo offline. O Spark tem vantagens distintas desde que não persista os dados no disco e, sim, em memória para leitura e gravação rápida.
- O Spark provê cache em memória, o que é ideal para multiplas operações que necessitam acessar o mesmo dado de entrada.
- O MapReduce inia uma JVM - Java Virtual Machine para cada tarefa enquanto o Spark tem um JVM próprio em cada nó, tornando simples a tarefa com RPC - Remote procedure Call e tornando, assim, o Spark extremamente rápido.
- O Spark utiliza o DAG - Direct Acyclic Graph que ajuda na otimização e cálculos em um único estágio ao invés de multiplos estágios como no modelo MapReduce.

* .3. Qual a função do SparkContext?

R) SparkContext é o objeto central do Spark que tem como função coordenar diferentes aplicações e clusters, provendo acesso a funcionalidades e recursos do Spark.

* .4. Explique com suas palavras o que é Resilient Distributed Datasets (RDD).

R) RDD é a unidade fundamental de dados no Apache Spark, sendo uma coleção distribuida de objetos imutáveis nos nós e que podem realizar operações em paralelo. Uma obervação é que o Spark RDDs, apesar de imutáveis, podem gerar novos RDDs à partir dos RDDs existentes.

* .5. GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por quê?

R) O reduceByKey agrupa as keys antes de realizar o processo shuffle e o groupBykey realize o processo shuffle em todos os pares de chaves para, somente após esse processo, agrupá-los.

* .6. Explique o que o código Scala abaixo faz.
```
val textFile = sc.textFile("hdfs://...")
val counts = textFile.flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_+_)
counts.saveAsTextFile("hdfs://...")
```

   - R) Dado um input de um external storage de um sistema distribuído, os mesmos são separados (pelos espaços) e transformados em uma sequência de caracteres (método flatMap). Feito isso, com essa sequência de caracteres são criados uma nova coleção do tipo chave/valor (tupla) sendo atribuido um valor (1) para cada chave (método map). Em seguida, é realizado a somatória de ocorrências pelo Spark (método reduceByKey) e, ao final, essa coleção é armazenada em um sistema distribuído.

*  .7. Responda as seguintes questôes. Devem ser desenvolvidas em Spark utilizando a sua linguagem de preferência.

7.1) Número de hosts únicos.
    - R) 137978

7.2) O total de erros 404.
    - R) 20901

7.3) Os 5 URLs que mais causaram erro 404.
    - R)
```
-- no data
```

7.4) Quantidade de erros 404 por dia.
    - R)
```
-- no data
```

7.5) O total de bytes retornados.
    - ) -- no data