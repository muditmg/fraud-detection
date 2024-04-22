# Transaction Fraud Project


El proyecto utiliza Kafka Streams para abordar la detección de fraude en tiempo real en un entorno bancario. 

Kafka Streams es una biblioteca cliente que permite crear aplicaciones donde los datos de entrada y salida se gestionan a través de clústeres de Kafka. Esta solución combina la facilidad de implementar aplicaciones en lenguajes como Java con las ventajas de Kafka.

Para obtener más detalles sobre Kafka Streams y la API del procesador, puede consultar la documentación en el siguiente enlace: https://kafka.apache.org/documentation/streams/developer-guide/dsl-api.html.
##### Requeriments

- Docker (to build the associated infrastructure)
- JDK 17+

## The use case

La organización bancaria enfrenta dificultades para controlar el fraude en las transacciones con tarjeta debido a su enfoque basado en lotes. Los movimientos de tarjetas se generan a través de tres canales: cajero automático, en línea y comerciantes. La solución actual recopila movimientos de estas fuentes varias veces al día y luego los analiza para detectar comportamientos sospechosos.

Sin embargo, esta solución es ineficiente ya que algunos casos no se detectan y otros se identifican demasiado tarde. Por lo tanto, la empresa necesita implementar la detección de fraudes en tiempo real para mejorar la seguridad, prevenir problemas asociados y mantener la confianza de los clientes.

Para abordar este problema, se puede implementar un sistema de detección de fraudes en tiempo real utilizando tecnologías como Kafka Streams. Al capturar y analizar las transacciones con tarjeta en tiempo real, se pueden identificar anomalías y patrones sospechosos de manera oportuna, lo que permite tomar medidas inmediatas para mitigar los riesgos de fraude. Este enfoque garantiza la detección y respuesta rápida a actividades fraudulentas, mejorando la seguridad en general y manteniendo la confianza de los clientes.


## The solution: streaming

Para desarrollar una solución de detección de fraudes en tiempo real, es crucial abordar dos aspectos principales:

Acceder a todos los movimientos financieros en tiempo real.
Analizar estos movimientos en tiempo real para identificar patrones fraudulentos.
Para abordar el primer punto, se propone implementar una herramienta Change Data Capture (CDC) que publique cada cambio en las fuentes de movimientos casi en tiempo real en un bus de eventos como Kafka. Una vez que los movimientos están en el bus de eventos, se empleará la API del procesador Kafka Streams para cumplir con el segundo punto.

El repositorio no se enfocará en la implementación de la solución CDC, por lo que se asumirá que los movimientos se publican en un tema de Kafka, como "movimientos", en tiempo real.

![PARTIAL_SCENARIO](doc/scenarios/partial_scenario.png)

We do learn how to analyze this stream of movements to find suspicious patterns. Once all the data is accessible and centralized in a Kafka topic, we implement a topology to detect two patterns within the stream:

- Two or more movements from different ATM or merchants within a sort period of time
- More than 4 movements from ATM or merchants within a sort period of time independently of its device (same ATM or not
- Multiple online movements exceeding a defined amount

If these patterns are detected, a fraud case is generated. That means that an event will be published in another Kafka topic (for instance, "fraud-cases") in order to make the pertinent decisions with that revealed data and confirm or not the fraudulent behaviour.

![POST_SCENARIO](doc/scenarios/post_scenario.png)

## How is structured this repo?

In this repo you'll find these folders:

- **apps**: it contains two Spring Boot applications
  - **Movements Generator:** this application exposes a Rest API to help us to publish movements to Kafka
  - **Fraud Checker:** Kafka Streams (Processor API) application that examines the stream of movements looking for the patterns explained before. This is the most important application of this repo
- **platform**: it contains the *docker-compose* file to launch a demo environment to really understand how all the components works and fraud cases are detected. The platform is composed of:
  - Zookeeper
  - Kafka
  - Kafka UI
  - Movements Generator App
  - Fraud Checker App
- **demo**: this folder contains the necessary scripts to execute the demo
  - *1-init.sh*: this script builds the docker images and launch the different docker containers
  - *2-generate-movements.sh*: this scripts calls to the Rest API exposes by the Movement Generator app, publishing to kafka multiple cards movements to the Fraud Checker app can detect fraud cases
  - *3-stop.sh*: this scripts destroy the environment.
- **doc**: this folder only contains document assets

## Executing the demo

Executing the demo is very easy. As we've seen in the previous section, there are some scripts to help us to execute all the necessary taks.

The first step is going to demo folder because all the scripts are going to be executed from there:

```bash
cd demo
```

Once in the demo folder, we have to execute the script creating and initializing the environment. To do that, just write:

```bash
sh 1-init.sh
```

When the script ends, we'll see something like this:

```bash
[+] Running 7/7
 ⠿ Network platform_default           Created                                                                                                                                                                                                                                
 ⠿ Container zookeeper                Healthy                                                                                                
 ⠿ Container broker                   Healthy                                                                                                
 ⠿ Container fraud-checker            Started                                                                                                
 ⠿ Container fraud-checker-generator  Started                                                                                                
 ⠿ Container kafka-ui                 Started                                                                                                
 ⠿ Container init                     Started                                                                                                

Waiting for platform to be ready....
Platform ready!
Now you can execute the next script to send movements automatically to the broker or sending manually
To execute the script:
    sh 2-generate-movements.sh


```

Now, the environment is up and running. So, the next step is generating multiple movements in order to detect fraud cases:

```bash
sh 2-generate-movements.sh
```

Once the movements are published, the script will show the following links:

```
-------------------------------
Movements topic: http://localhost:9081/ui/clusters/local/topics/movements
Fraud case topics: http://localhost:9081/ui/clusters/local/topics/fraud-cases
-------------------------------
```

Now, if we go to the first link (Kafka UI - movements topic) we'll check that multiple movements have been published to the topic ***movements*** to be used as data to test our fraud detector.

![MOVEMENTS_IN_KAFKA](doc/scenarios/movements.png)

And, if we go to the second link (Kafka UI - fraud cases topic) we'll check that two fraud cases have been detected and published to the topic **fraud-cases**:

![FRAUD_IN_KAFKA](doc/scenarios/fraud.png)

## How to destroy the environment

Finally, to destroy the environment, we simply have to run the following script:

```bash
sh 3-stop.sh
```
