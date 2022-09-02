# MSA 강의

### 라이브러리

Spring Cloud 구성 
• Distributed/versioned configuration  (설정정보 관리)
  - Spring Cloud Config

• Service Registration and Discovery 
  - Netflix Eureka

• Routing 
  - Netflix Zuul, Spring Cloud Gateway

• Service to service calls  (동기방식)
  - RestTemplate, Netflix OpenFeign

• Load Balancing 
  - Netflix Ribbon

• Circuit Breakers (응답시간이 오래걸릴 때 요청을 계속 기다리지 않고 예외를 던져서 처리한다.)
  - Netflix Hystrix, Resilience4j

•Visibility and monitoring 
  - Zipkin Distributed Tracing


 • Distributed Messaging (비동기방식)
  - Kafka, RabbitMQ


### Spring Cloud
분산 시스템을 사용하는 데 있어서 좀 더 빨리 작업할 수 있다.

### Spring Boot
Spring Boot 가 내부적으로 포함하고 있는 third-party libraries
- DBCP (Connection Pooling) : HikariCP   https://github.com/brettwooldridge/HikariCP
- JSON Parser : Jackson  https://github.com/FasterXML/jackson
- Logging : Slf4j (Logging Facade),  Logback (Logging 구현체)

### Reactive Stream 표준스펙
- 반응형 스트림
  - 스트림에서 처리하는 데이터가 바뀌면 즉각적으로 반응한다.
- 관련 라이브러리
  - Rx-Java (Nexflix), Project Reactor (Spring)

- Spring MVC & Spring WebFlux 비교
  - Spring MVC 는 Synchronous / Blocking
  - Spring WebFlux 는 Asynchronous / Non-Blocking
    - 리액티브 스택을 기반으로 만들었다.
    - 작은 수의 스레드로 동시 서비스를 제공하기 위해 만들었다.
    - 웹플럭스는 리액터를 기반으로 만든 웹 프레임워크이다.

### ORM이란
- ORM(Object Relational Mapping)
  - Entity Class  ⇔ Table
  - Object(Instance) ⇔ Row(Record)
  - Variable ⇔ Column

- JPA(Java Persistence API) : javax.persistence.*
  - : J2EE에서 제공하는 ORM 기능을 제공하는 표준 스펙
- Spring Data JPA
  - : Repository 를 인터페이스로 작성해도 됨
- Hibernate
  - : JPA 구현체
  - : Repository를 클래스형태로 직접 구현을 해야 한다.


### GateWay를 통해서 서버를 호출하는 장점
- 랜덤 포트를 자주 사용하는데 고정 포트인 게이트웨이를 호출하면 랜덤포트의 서버를 연결시켜주는 장점이 있다.

### JWT
- JWT의 장점
  - 서버는 클라이언트와의 세션을 유지할 필요가 없다.
  - 쿠키를 전달하지 않아도 되므로 쿠키를 사용함으로써 발생하는 취약점이 사라진다. 
  - 클라이언트가 보낸 토큰만 확인하면 된다.

### 마이크로 서비스 간 통신
  - RestTemplate
    - 동기방식

  RestTemplate 클래스는 REST 서비스를 호출하도록 설계되어 HTTP 프로토콜의 메서드 (ex. GET, POST, DELETE, PUT)에 맞는 여러 메서드를 제공합니다.

    - Spring 3.0 부터 지원하는 Spring의 HTTP 통신 템플릿
    - HTTP요청시JSON,XML,String과같은응답을받을수있는템플릿
    - Blocking I/O 기반의 동기방식에 사용되는 템플릿
    - MicroService간의 통신에 사용

### kafka
kafka는 메시지브로커 프로젝트이다.

- producer/consumer 분리
- 메시지를 여러 consumer에게 허용

#### kafka Cluster
Zookeeper 서버에 여러 메타정보를 담아서 브로커로 처리한다.

#### 사용 목적
MSA는 각각의 DB를 가지고 있기 때문에 각 DB별 데이터처리를 해야하는데 비동기 방식의 MQ(kafka)를 이용하여 요청처리를 진행한다.

#### kafka 실행 명령어

#### Spring From kafka 
https://spring.io/projects/spring-kafka

- zookeeper 서버 띄우기
- kafka 서버 띄우기

#### kafka offset
Consumer가 메시지를 읽어들인 위치를 나타냄.

오토커밋이 디폴트 값으로 되어있어서 수동으로 처리하는 게 좋음

https://skywingzz.github.io/kafka/kafka-offset/