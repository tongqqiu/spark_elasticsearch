# Spark and Elasticsearch 

This is an example to use Spark to load large csv file and save into elasticsearch


It is based on Spark [standalone scala example](http://spark.apache.org/docs/latest/quick-start.html) .


## Spark 

Apache Spark is a fast and general-purpose cluster computing system. In this example, spark will automatic split the records and parallelize the loading process.


## Elasticsearch hadoop plugin
Elasticsearch support Spark read and write via elasticsearch-hadoop plugin. The plugin provides the "saveToES" method to load maps into elasticsearch easily, using bulk loading

Note: Need to download the elasticsearch-hadoop jar and add into spark classpath (e.g. `compute-classpath.sh`)

## Run

- Change the file path that you want to load
- Run elasticsearch locally
- Install spark locally

```
sbt package
```
Follow the spark scala standalone application guide to run the generated jar.


## Performance
1 million rows (with 10 attributes) will takes less than 1 minute to load on a single node mode