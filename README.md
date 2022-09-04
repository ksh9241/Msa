# MSA 강의
마이크로 서비스는 독립적인 배포와 기술적인 다양성을 통해 모듈간 뚜렷한 경계가 가능하다.

### 기술매핑
- 스프링 클라우드
  - 다수의 통합된 자바 라이브러리를 제공한다.
  - 마이크로 서비스들은 클라이언트 사이드 서비스 디스커버리, 로드밸런싱, 설정 업데이트 등을 행하는 라이브러리와 실행시간의 행위자를 가진다. 싱글톤과 같은 패턴과 배치 등은 JVM에서 관리한다.

  - 장점
    - 스프링 플랫폼과 스프링 부트의 빠른 어플리케이션 생성 능력에 의해 제공되는 통합 프로그래밍 모델은 개발자에게 훌륭한 마이크로 서비스 개발 경험을 제공한다.
    - 실행시간의 주요한 것들을 다룰 수 있는 다양한 선택지의 라이브러리들이 있다.
    - 다른 스프링 클라우드 라이브러리들은 다른 라이브러리와도 쉽게 통합된다. (FeignClient, Hystrix, Ribbon)

  - 단점
    - 자바에 한정적이다. (범용성이 떨어진다.)
    - 개발자 및 자바 어플리케이션에 너무 많은 책임이 주어진다.
    - 스프링 클라우드는 MSA 세계에 있어서 일부분의 기능만을 제공한다.

- 쿠버네티스
자바 플래폼만을 위한 것이 아닌 다양한 언어를 지원한다. 그리고 모든 언어에 대해 보편적인 방식으로 분산 컴퓨팅 문제들의 답을 제시한다.

  - 자바 한정이 아닌 모든 언어에 적용 가능
  - 분산 컴퓨팅 문제 해결을 위한 전반적인 기능 제공
  - platform 수준
  - application 외부에서 기능

  - 장점
    - 다양한 언어 지원
    - 스프링 클라우드와 비교해서 더 광범위한 MSA 문제를 해결한다. 런타임 서비스를 제공할 뿐만 아니라 쿠버네티스는 당신이 환경을 프로비져닝하도록 하며, 자원 제약을 세팅하고, 어플리케이션 생명주기를 관리하고, 오토 스케일링과 자가치유를 가능하게 한다.

  - 단점
    - 다양한 언어를 지원하기 때문에 언어별 최적화가 되어 있지 않다.
    - 개발자 친화적인 플랫폼이 아니다.

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