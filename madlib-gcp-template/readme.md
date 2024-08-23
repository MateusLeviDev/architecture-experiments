#### first steps
```
- habilitar conta na google cloud

passos que eu fiz em seguida: 

- criar projeto
- ativar API PUBSUB (apenas buscando na /find a API ja é ativada auto)
- Configurar Credenciais
```

<br>

##### IAM & Admin
create a new service account for pub sub publisher

roles: pub/sub publisher, cloud pub/sub service agent

---

##### Properties
spring.cloud.gcp.project-id= id-projeto <br>
spring.cloud.gcp.credentials.location=file:/caminho/para/seu/arquivo-de-credenciais.json
---

##### Using maven
pom
```
<dependencies>
    ...
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-gcp-starter-pubsub</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.integration</groupId>
        <artifactId>spring-integration-core</artifactId>
    </dependency>
    ...
</dependencies>


<properties>
    ...
    <spring-cloud-gcp.version>1.2.5.RELEASE</spring-cloud-gcp.version>
    ...
</properties>

<dependencyManagement>
    <dependencies>
       ...
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-gcp-dependencies</artifactId>
            <version>${spring-cloud-gcp.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        ...
    </dependencies>
</dependencyManagement>
```

- em alguns casos pode funcionar também com.google.cloud
```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>com.google.cloud</groupId>
			<artifactId>spring-cloud-gcp-dependencies</artifactId>
			<version>$5.5.0</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

- a parte da version na properties é bem importante
- You will need a topic and a subscription to send and receive messages from Google Cloud Pub/Sub. You can create them in the Google Cloud Console or, programatically, with the PubSubAdmin class.



The Spring Cloud GCP Core Boot starter can auto-configure these two properties and make them optional. Properties from the properties file always have precedence over the Spring Boot configuration. The Spring Cloud GCP Core Boot starter is bundled with the Spring Cloud GCP Pub/Sub Boot starter.

The GCP project ID is auto-configured from the GOOGLE_CLOUD_PROJECT environment variable, among several other sources. The OAuth2 credentials are auto-configured from the GOOGLE_APPLICATION_CREDENTIALS environment variable. If the Google Cloud SDK is installed, this environment variable is easily configured by running the gcloud auth application-default login command in the same process of the app, or a parent one.

`mvn spring-boot:run`

- https://spring.io/guides/gs/messaging-gcp-pubsub
- https://www.youtube.com/watch?v=84gX_WdUEMI&t=1027s
- https://medium.com/@gcbrandao/google-cloud-pubsub-mensageria-no-gcp-com-java-springboot-e-spring-cloud-8319d37df977
