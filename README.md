# Transaction Fraud Project


El proyecto utiliza Kafka Streams para abordar la detección de fraude en tiempo real en un entorno bancario. 

Kafka Streams es una biblioteca cliente que permite crear aplicaciones donde los datos de entrada y salida se gestionan a través de clústeres de Kafka. Esta solución combina la facilidad de implementar aplicaciones en lenguajes como Java con las ventajas de Kafka.

Para obtener más detalles sobre Kafka Streams y la API del procesador, puede consultar la documentación en el siguiente enlace: https://kafka.apache.org/documentation/streams/developer-guide/dsl-api.html.
##### Requeriments

- Docker (to build the associated infrastructure)
- JDK 17+

## Cómo está estructurado este repositorio?

En este repositorio encontrarás estas carpetas:

- **apps**: Contiene dos aplicaciones Spring Boot
  - **ms-spring-kafka-monitor-prevention:** esta aplicación expone una API Rest para ayudarnos a publicar los movimientos bancarios/transacciones en Kafka
  - **ms-spring-kafka-streams-monitor-prevention:** Aplicación Kafka Streams (Processor API) que examina el flujo de movimientos y la detección de fraudes con algunas topologia establecidas.
- **docker-compose**: contiene el archivo *docker-compose.yml* para levantar la plataforma de Kafka. La plataforma está compuesta por:
  - Zookeeper
  - Cluster Kafka 1
  - Cluster Kafka 2 
  - kafka-ui (Herramienta para visualizar los cluster, topicos, particiones, etc)
  - init-kafka (Comando iniciales para la creación de topicos)
- **demo**: esta carpeta contiene los scripts necesarios para ejecutar la demostración
  - *init.sh*: este script crea las imágenes de las 2 aplicaciones y las despliega con Docker.
  - *producerTransaction.sh*: este script llama a la API Rest *ms-spring-kafka-monitor-prevention*, publicando en Kafka múltiples transacciones y en la aplicación *ms-spring-kafka-streams-monitor-prevention* se puede detectar los fraudes.
  - *stop.sh*: estos scripts destruir los componentes en *docker-compose.yml*.

## Executing the demo

Ejecutar la demostración es muy fácil. Como hemos visto en el apartado anterior, existen algunos scripts que nos ayudarán a ejecutar todas las tareas necesarias.

El primer paso es ir a la carpeta de demostración porque todos los scripts se ejecutarán desde allí:
```bash
cd demo
```

Una vez en la carpeta demo, debemos ejecutar el script creando e inicializando el entorno. Para ello basta con escribir (terminal bash):
```bash
sh init.sh
```

Cuando finalice el script, veremos algo como esto:



1. Success create image of ms-spring-kafka-monitor-prevention

``` 
[INFO] Scanning for projects...
[INFO] 
[INFO] --< com.zomeli.spring.kafka.streams:ms-spring-kafka-monitor-prevention >--
[INFO] Building ms-spring-kafka-monitor-prevention 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.3.2:clean (default-clean) @ ms-spring-kafka-monitor-prevention ---
[INFO] Deleting C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-monitor-prevention\target
[INFO]
[INFO] >>> spring-boot:3.2.4:build-image (default-cli) > package @ ms-spring-kafka-monitor-prevention >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ ms-spring-kafka-monitor-prevention ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ ms-spring-kafka-monitor-prevention ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 17 source files with javac [debug release 17] to target\classes[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ ms-spring-kafka-monitor-prevention ---
[INFO] skip non existing resourceDirectory C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-monitor-prevention\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ ms-spring-kafka-monitor-prevention ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] Compiling 1 source file with javac [debug release 17] to target\test-classes
[INFO] 
[INFO] --- surefire:3.1.2:test (default-test) @ ms-spring-kafka-monitor-prevention ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ ms-spring-kafka-monitor-prevention ---
[INFO] Building jar: C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-monitor-prevention\target\ms-spring-kafka-monitor-prevention-1.0.0.jar
[INFO] 
[INFO] --- spring-boot:3.2.4:repackage (repackage) @ ms-spring-kafka-monitor-prevention ---
[INFO] Replacing main artifact C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-monitor-prevention\target\ms-spring-kafka-monitor-prevention-1.0.0.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-monitor-prevention\target\ms-spring-kafka-monitor-prevention-1.0.0.jar.original
[INFO]
[INFO] <<< spring-boot:3.2.4:build-image (default-cli) < package @ ms-spring-kafka-monitor-prevention <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.2.4:build-image (default-cli) @ ms-spring-kafka-monitor-prevention ---
[WARNING]  Parameter 'imageName' (user property 'spring-boot.build-image.imageName') is read-only, must not be used in configuration
[INFO] Building image 'docker.io/library/ms-spring-kafka-monitor-prevention:1.0.0'
[INFO]
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-base:latest' 100%
[INFO]  > Pulled builder image 'paketobuildpacks/builder-jammy-base@sha256:7c1fd4372a65bc6ef56433ca883621707d35c51a5f41bb02ccdfd2a453de0953'
[INFO]  > Pulling run image 'docker.io/paketobuildpacks/run-jammy-base:latest' 100%
[INFO]  > Pulled run image 'paketobuildpacks/run-jammy-base@sha256:05fa7df09b5c3e3fb85a33954fc939eb96ccd865b71dc0b007e5385c4ef137c7'
[INFO]  > Executing lifecycle version v0.19.3
[INFO]  > Using build cache volume 'pack-cache-490190f26bc7.build'
[INFO]
[INFO]  > Running creator
[INFO]     [creator]     ===> ANALYZING
[INFO]     [creator]     Image with name "docker.io/library/ms-spring-kafka-monitor-prevention:1.0.0" not found
[INFO]     [creator]     ===> DETECTING
[INFO]     [creator]     6 of 26 buildpacks participating
[INFO]     [creator]     paketo-buildpacks/ca-certificates   3.6.8
[INFO]     [creator]     paketo-buildpacks/bellsoft-liberica 10.5.5
[INFO]     [creator]     paketo-buildpacks/syft              1.45.0
[INFO]     [creator]     paketo-buildpacks/executable-jar    6.8.5
[INFO]     [creator]     paketo-buildpacks/dist-zip          5.6.10
[INFO]     [creator]     paketo-buildpacks/spring-boot       5.27.11
[INFO]     [creator]     ===> RESTORING
[INFO]     [creator]     ===> BUILDING
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for CA Certificates 3.6.8
[INFO]     [creator]       https://github.com/paketo-buildpacks/ca-certificates
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_ca-certificates/helper/exec.d/ca-certificates-helper
[INFO]     [creator]     
[INFO]     [creator]     Paketo Buildpack for BellSoft Liberica 10.5.5
[INFO]     [creator]       https://github.com/paketo-buildpacks/bellsoft-liberica
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_JVM_JLINK_ARGS           --no-man-pages --no-header-files --strip-debug --compress=1  configure custom link arguments (--output must be omitted)
[INFO]     [creator]         $BP_JVM_JLINK_ENABLED        false                                                        enables running jlink tool to generate custom JRE
[INFO]     [creator]         $BP_JVM_TYPE                 JRE                                                          the JVM type - JDK or JRE
[INFO]     [creator]         $BP_JVM_VERSION              17                                                           the Java version
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_DEBUG_ENABLED           false                                                        enables Java remote debugging support
[INFO]     [creator]         $BPL_DEBUG_PORT              8000                                                         configure the remote debugging port
[INFO]     [creator]         $BPL_DEBUG_SUSPEND           false                                                        configure whether to suspend execution until a debugger has attached
[INFO]     [creator]         $BPL_HEAP_DUMP_PATH                                                                       write heap dumps on error to this path
[INFO]     [creator]         $BPL_JAVA_NMT_ENABLED        true                                                         enables Java Native Memory Tracking (NMT)
[INFO]     [creator]         $BPL_JAVA_NMT_LEVEL          summary                                                      configure level of NMT, summary or detail
[INFO]     [creator]         $BPL_JFR_ARGS                                                                             configure custom Java Flight Recording (JFR) arguments
[INFO]     [creator]         $BPL_JFR_ENABLED             false                                                        enables Java Flight Recording (JFR)
[INFO]     [creator]         $BPL_JMX_ENABLED             false                                                        enables Java Management Extensions (JMX)
[INFO]     [creator]         $BPL_JMX_PORT                5000                                                         configure the JMX port
[INFO]     [creator]         $BPL_JVM_HEAD_ROOM           0                                                            the headroom in memory calculation
[INFO]     [creator]         $BPL_JVM_LOADED_CLASS_COUNT  35% of classes                                               the number of loaded classes in memory calculation
[INFO]     [creator]         $BPL_JVM_THREAD_COUNT        250                                                          the number of threads in memory calculation
[INFO]     [creator]         $JAVA_TOOL_OPTIONS                                                                        the JVM launch flags
[INFO]     [creator]         Using Java version 17 from BP_JVM_VERSION
[INFO]     [creator]       BellSoft Liberica JRE 17.0.10: Contributing to layer
[INFO]     [creator]         Downloading from https://github.com/bell-sw/Liberica/releases/download/17.0.10+13/bellsoft-jre17.0.10+13-linux-amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Expanding to /layers/paketo-buildpacks_bellsoft-liberica/jre
[INFO]     [creator]         Adding 137 container CA certificates to JVM truststore
[INFO]     [creator]         Writing env.launch/BPI_APPLICATION_PATH.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CACERTS.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CLASS_COUNT.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_SECURITY_PROVIDERS.default
[INFO]     [creator]         Writing env.launch/JAVA_HOME.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]         Writing env.launch/MALLOC_ARENA_MAX.default
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/active-processor-count
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/java-opts
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jvm-heap
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/link-local-dns
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/memory-calculator
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-configurer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jmx
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jfr
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/openssl-certificate-loader
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-classpath-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/debug-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/nmt
[INFO]     [creator]       Java Security Properties: Contributing to layer
[INFO]     [creator]         Writing env.launch/JAVA_SECURITY_PROPERTIES.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]     
[INFO]     [creator]     Paketo Buildpack for Syft 1.45.0
[INFO]     [creator]       https://github.com/paketo-buildpacks/syft
[INFO]     [creator]         Downloading from https://github.com/anchore/syft/releases/download/v0.105.0/syft_0.105.0_linux_amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Writing env.build/SYFT_CHECK_FOR_APP_UPDATE.default
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Executable JAR 6.8.5
[INFO]     [creator]       https://github.com/paketo-buildpacks/executable-jar
[INFO]     [creator]       Command "packages" is deprecated, use `syft scan` instead
[INFO]     [creator]       Class Path: Contributing to layer
[INFO]     [creator]         Writing env/CLASSPATH.delim
[INFO]     [creator]         Writing env/CLASSPATH.prepend
[INFO]     [creator]       Process types:
[INFO]     [creator]         executable-jar: java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         task:           java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         web:            java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Spring Boot 5.27.11
[INFO]     [creator]       https://github.com/paketo-buildpacks/spring-boot
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_DISABLED   false  whether to contribute Spring Boot cloud bindings support
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_VERSION    1      default version of Spring Cloud Bindings library to contribute
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_DISABLED  false  whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_ENABLED   true   Deprecated - whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]       Creating slices from layers index
[INFO]     [creator]         dependencies (38.8 MB)
[INFO]     [creator]         spring-boot-loader (446.0 KB)
[INFO]     [creator]         snapshot-dependencies (0.0 B)
[INFO]     [creator]         application (128.8 KB)
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_spring-boot/helper/exec.d/spring-cloud-bindings
[INFO]     [creator]       Spring Cloud Bindings 2.0.3: Contributing to layer
[INFO]     [creator]         Downloading from https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-bindings/2.0.3/spring-cloud-bindings-2.0.3.jar
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Copying to /layers/paketo-buildpacks_spring-boot/spring-cloud-bindings
[INFO]     [creator]       Web Application Type: Contributing to layer
[INFO]     [creator]         Reactive web application detected
[INFO]     [creator]         Writing env.launch/BPL_JVM_THREAD_COUNT.default
[INFO]     [creator]       4 application slices
[INFO]     [creator]       Image labels:
[INFO]     [creator]         org.opencontainers.image.title
[INFO]     [creator]         org.opencontainers.image.version
[INFO]     [creator]         org.springframework.boot.version
[INFO]     [creator]     ===> EXPORTING
[INFO]     [creator]     Adding layer 'paketo-buildpacks/ca-certificates:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:java-security-properties'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:jre'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/executable-jar:classpath'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:spring-cloud-bindings'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:web-application-type'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launch.sbom'
[INFO]     [creator]     Adding 5/5 app layer(s)
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launcher'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:config'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:process-types'
[INFO]     [creator]     Adding label 'io.buildpacks.lifecycle.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.build.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.project.metadata'
[INFO]     [creator]     Adding label 'org.opencontainers.image.title'
[INFO]     [creator]     Adding label 'org.opencontainers.image.version'
[INFO]     [creator]     Adding label 'org.springframework.boot.version'
[INFO]     [creator]     Setting default process type 'web'
[INFO]     [creator]     Saving docker.io/library/ms-spring-kafka-monitor-prevention:1.0.0...
[INFO]     [creator]     *** Images (48f816f109eb):
[INFO]     [creator]           docker.io/library/ms-spring-kafka-monitor-prevention:1.0.0
[INFO]     [creator]     Adding cache layer 'paketo-buildpacks/syft:syft'
[INFO]     [creator]     Adding cache layer 'buildpacksio/lifecycle:cache.sbom'
[INFO] 
[INFO] Successfully built image 'docker.io/library/ms-spring-kafka-monitor-prevention:1.0.0'
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  34.221 s
[INFO] Finished at: 2024-04-21T21:04:11-05:00
[INFO] ------------------------------------------------------------------------

``` 

2. Success create image of ms-spring-kafka-streams-monitor-prevention
``` 
[INFO] Scanning for projects...
[INFO] 
[INFO] --< com.zomeli.spring.kafka.streams:ms-spring-kafka-streams-monitor-prevention >--
[INFO] Building ms-spring-kafka-streams-monitor-prevention 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] 
[INFO] >>> spring-boot:3.2.4:build-image (default-cli) > package @ ms-spring-kafka-streams-monitor-prevention >>>
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 28 source files with javac [debug release 17] to target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] skip non existing resourceDirectory C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-streams-monitor-prevention\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] Compiling 1 source file with javac [debug release 17] to target\test-classes
[INFO] 
[INFO] --- surefire:3.1.2:test (default-test) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Building jar: C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-streams-monitor-prevention\target\ms-spring-kafka-streams-monitor-prevention-1.0.0.jar
[INFO] 
[INFO] --- spring-boot:3.2.4:repackage (repackage) @ ms-spring-kafka-streams-monitor-prevention ---
[INFO] Replacing main artifact C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-streams-monitor-prevention\target\ms-spring-kafka-streams-monitor-prevention-1.0.0.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\Zomeli\Desktop\bank-fraud transaction-project\apps\ms-spring-kafka-streams-monitor-prevention\target\ms-spring-kafka-streams-monitor-prevention-1.0.0.jar.original        
[INFO]
[INFO] <<< spring-boot:3.2.4:build-image (default-cli) < package @ ms-spring-kafka-streams-monitor-prevention <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.2.4:build-image (default-cli) @ ms-spring-kafka-streams-monitor-prevention ---
[WARNING]  Parameter 'imageName' (user property 'spring-boot.build-image.imageName') is read-only, must not be used in configuration
[INFO] Building image 'docker.io/library/ms-spring-kafka-streams-monitor-prevention:1.0.0'
[INFO]
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-base:latest' 100%
[INFO]  > Pulled builder image 'paketobuildpacks/builder-jammy-base@sha256:7c1fd4372a65bc6ef56433ca883621707d35c51a5f41bb02ccdfd2a453de0953'
[INFO]  > Pulling run image 'docker.io/paketobuildpacks/run-jammy-base:latest' 100%
[INFO]  > Pulled run image 'paketobuildpacks/run-jammy-base@sha256:05fa7df09b5c3e3fb85a33954fc939eb96ccd865b71dc0b007e5385c4ef137c7'
[INFO]  > Executing lifecycle version v0.19.3
[INFO]  > Using build cache volume 'pack-cache-09bacdb99a7b.build'
[INFO]
[INFO]  > Running creator
[INFO]     [creator]     ===> ANALYZING
[INFO]     [creator]     Image with name "docker.io/library/ms-spring-kafka-streams-monitor-prevention:1.0.0" not found
[INFO]     [creator]     ===> DETECTING
[INFO]     [creator]     6 of 26 buildpacks participating
[INFO]     [creator]     paketo-buildpacks/ca-certificates   3.6.8
[INFO]     [creator]     paketo-buildpacks/bellsoft-liberica 10.5.5
[INFO]     [creator]     paketo-buildpacks/syft              1.45.0
[INFO]     [creator]     paketo-buildpacks/executable-jar    6.8.5
[INFO]     [creator]     paketo-buildpacks/dist-zip          5.6.10
[INFO]     [creator]     paketo-buildpacks/spring-boot       5.27.11
[INFO]     [creator]     ===> RESTORING
[INFO]     [creator]     ===> BUILDING
[INFO]     [creator]     
[INFO]     [creator]     Paketo Buildpack for CA Certificates 3.6.8
[INFO]     [creator]       https://github.com/paketo-buildpacks/ca-certificates
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_ca-certificates/helper/exec.d/ca-certificates-helper
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for BellSoft Liberica 10.5.5
[INFO]     [creator]       https://github.com/paketo-buildpacks/bellsoft-liberica
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_JVM_JLINK_ARGS           --no-man-pages --no-header-files --strip-debug --compress=1  configure custom link arguments (--output must be omitted)
[INFO]     [creator]         $BP_JVM_JLINK_ENABLED        false                                                        enables running jlink tool to generate custom JRE
[INFO]     [creator]         $BP_JVM_TYPE                 JRE                                                          the JVM type - JDK or JRE
[INFO]     [creator]         $BP_JVM_VERSION              17                                                           the Java version
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_DEBUG_ENABLED           false                                                        enables Java remote debugging support
[INFO]     [creator]         $BPL_DEBUG_PORT              8000                                                         configure the remote debugging port
[INFO]     [creator]         $BPL_DEBUG_SUSPEND           false                                                        configure whether to suspend execution until a debugger has attached
[INFO]     [creator]         $BPL_HEAP_DUMP_PATH                                                                       write heap dumps on error to this path
[INFO]     [creator]         $BPL_JAVA_NMT_ENABLED        true                                                         enables Java Native Memory Tracking (NMT)
[INFO]     [creator]         $BPL_JAVA_NMT_LEVEL          summary                                                      configure level of NMT, summary or detail
[INFO]     [creator]         $BPL_JFR_ARGS                                                                             configure custom Java Flight Recording (JFR) arguments
[INFO]     [creator]         $BPL_JFR_ENABLED             false                                                        enables Java Flight Recording (JFR)
[INFO]     [creator]         $BPL_JMX_ENABLED             false                                                        enables Java Management Extensions (JMX)
[INFO]     [creator]         $BPL_JMX_PORT                5000                                                         configure the JMX port
[INFO]     [creator]         $BPL_JVM_HEAD_ROOM           0                                                            the headroom in memory calculation
[INFO]     [creator]         $BPL_JVM_LOADED_CLASS_COUNT  35% of classes                                               the number of loaded classes in memory calculation
[INFO]     [creator]         $BPL_JVM_THREAD_COUNT        250                                                          the number of threads in memory calculation
[INFO]     [creator]         $JAVA_TOOL_OPTIONS                                                                        the JVM launch flags
[INFO]     [creator]         Using Java version 17 from BP_JVM_VERSION
[INFO]     [creator]       BellSoft Liberica JRE 17.0.10: Contributing to layer
[INFO]     [creator]         Downloading from https://github.com/bell-sw/Liberica/releases/download/17.0.10+13/bellsoft-jre17.0.10+13-linux-amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Expanding to /layers/paketo-buildpacks_bellsoft-liberica/jre
[INFO]     [creator]         Adding 137 container CA certificates to JVM truststore
[INFO]     [creator]         Writing env.launch/BPI_APPLICATION_PATH.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CACERTS.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CLASS_COUNT.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_SECURITY_PROVIDERS.default
[INFO]     [creator]         Writing env.launch/JAVA_HOME.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]         Writing env.launch/MALLOC_ARENA_MAX.default
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/active-processor-count
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/java-opts
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jvm-heap
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/link-local-dns
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/memory-calculator
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-configurer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jmx
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jfr
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/openssl-certificate-loader
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-classpath-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/debug-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/nmt
[INFO]     [creator]       Java Security Properties: Contributing to layer
[INFO]     [creator]         Writing env.launch/JAVA_SECURITY_PROPERTIES.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Syft 1.45.0
[INFO]     [creator]       https://github.com/paketo-buildpacks/syft
[INFO]     [creator]         Downloading from https://github.com/anchore/syft/releases/download/v0.105.0/syft_0.105.0_linux_amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Writing env.build/SYFT_CHECK_FOR_APP_UPDATE.default
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Executable JAR 6.8.5
[INFO]     [creator]       https://github.com/paketo-buildpacks/executable-jar
[INFO]     [creator]       Command "packages" is deprecated, use `syft scan` instead
[INFO]     [creator]       Class Path: Contributing to layer
[INFO]     [creator]         Writing env/CLASSPATH.delim
[INFO]     [creator]         Writing env/CLASSPATH.prepend
[INFO]     [creator]       Process types:
[INFO]     [creator]         executable-jar: java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         task:           java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         web:            java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Spring Boot 5.27.11
[INFO]     [creator]       https://github.com/paketo-buildpacks/spring-boot
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_DISABLED   false  whether to contribute Spring Boot cloud bindings support
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_VERSION    1      default version of Spring Cloud Bindings library to contribute
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_DISABLED  false  whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_ENABLED   true   Deprecated - whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]       Creating slices from layers index
[INFO]     [creator]         dependencies (93.3 MB)
[INFO]     [creator]         spring-boot-loader (446.0 KB)
[INFO]     [creator]         snapshot-dependencies (0.0 B)
[INFO]     [creator]         application (255.2 KB)
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_spring-boot/helper/exec.d/spring-cloud-bindings
[INFO]     [creator]       Spring Cloud Bindings 2.0.3: Contributing to layer
[INFO]     [creator]         Downloading from https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-bindings/2.0.3/spring-cloud-bindings-2.0.3.jar
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Copying to /layers/paketo-buildpacks_spring-boot/spring-cloud-bindings
[INFO]     [creator]       Web Application Type: Contributing to layer
[INFO]     [creator]         Servlet web application detected
[INFO]     [creator]         Writing env.launch/BPL_JVM_THREAD_COUNT.default
[INFO]     [creator]       4 application slices
[INFO]     [creator]       Image labels:
[INFO]     [creator]         org.opencontainers.image.title
[INFO]     [creator]         org.opencontainers.image.version
[INFO]     [creator]         org.springframework.boot.version
[INFO]     [creator]     ===> EXPORTING
[INFO]     [creator]     Adding layer 'paketo-buildpacks/ca-certificates:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:java-security-properties'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:jre'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/executable-jar:classpath'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:spring-cloud-bindings'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:web-application-type'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launch.sbom'
[INFO]     [creator]     Adding 5/5 app layer(s)
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launcher'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:config'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:process-types'
[INFO]     [creator]     Adding label 'io.buildpacks.lifecycle.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.build.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.project.metadata'
[INFO]     [creator]     Adding label 'org.opencontainers.image.title'
[INFO]     [creator]     Adding label 'org.opencontainers.image.version'
[INFO]     [creator]     Adding label 'org.springframework.boot.version'
[INFO]     [creator]     Setting default process type 'web'
[INFO]     [creator]     Saving docker.io/library/ms-spring-kafka-streams-monitor-prevention:1.0.0...
[INFO]     [creator]     *** Images (1bcf2ad07d9b):
[INFO]     [creator]           docker.io/library/ms-spring-kafka-streams-monitor-prevention:1.0.0
[INFO]     [creator]     Adding cache layer 'paketo-buildpacks/syft:syft'
[INFO]     [creator]     Adding cache layer 'buildpacksio/lifecycle:cache.sbom'
[INFO] 
[INFO] Successfully built image 'docker.io/library/ms-spring-kafka-streams-monitor-prevention:1.0.0'
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  41.934 s
[INFO] Finished at: 2024-04-21T21:04:56-05:00
[INFO] ------------------------------------------------------------------------

```
3. Success create Components of **docker-compose.yml**
```
[+] Running 8/8
 ✔ Network bank-fraudtransaction-project_default         Created                                                                                                                                                               0.1s 
 ✔ Container zookeeper                                   Healthy                                                                                                                                                               1.3s 
 ✔ Container kafka1                                      Healthy                                                                                                                                                               0.2s 
 ✔ Container kafka2                                      Started                                                                                                                                                               0.2s 
 ✔ Container kafka-ui                                    Started                                                                                                                                                               0.3s 
 ✔ Container ms-spring-kafka-streams-monitor-prevention  Started                                                                                                                                                               0.3s 
 ✔ Container ms-spring-kafka-monitor-prevention          Started                                                                                                                                                               0.3s 
 ✔ Container init                                        Started                                                                                                                                                               0.3s 

Waiting for platform to be ready....
4. Now you can execute the next script to send Transaction automatically to the broker kafka
To execute the script:
Generating Transactions.....

```

Ahora, el entorno está en funcionamiento. Entonces, el siguiente paso es generar múltiples transacciones para detectar casos de fraude:
```bash
sh producerTransaction.sh
```

Once the movements are published, the script will show the following links:

Kafka UI
```
-------------------------------
Transactions topic: http://localhost:8080/ui/clusters/localhost/all-topics/input-monitor-trx-stream-topic/messages
Fraud Transactions topic: http://localhost:8080/ui/clusters/localhost/all-topics/output-monitor-trx-stream-topic/messages
-------------------------------

```

Ahora, si vamos al primer enlace (Kafka UI - Transactions topic), comprobaremos que se hayan publicado varios movimientos en el topic ***input-monitor-trx-stream-topic*** para usarlos como datos para probar nuestro detector de fraude.



Y, si vamos al segundo enlace (Kafka UI - Fraud Transactions topic), comprobaremos que se han detectado y publicado algunos casos de fraude en el topic **output-monitor-trx-stream-topic**


## How to destroy the environment

Finalmente, para destruir el entorno simplemente debemos ejecutar el siguiente script:

```bash
sh ../demo/stop.sh
```
