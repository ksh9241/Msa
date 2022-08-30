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

• Circuit Breakers 
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