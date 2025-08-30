# 특정 경로 별도 로거 설정 시 해당 로거 미적용/root 로거 적용
___

## 문제
___

* 클래스내 로거 선언 후 debug 를 사용함.
* 로거 설정파일에 root 로거 레벨을 info, 해당 클래스 패키지 로거는 debug 설정.
* 클래스 내 info 로그는 출력되나, debug 로그는 미출력.

> class 내 로그 생성

```java
private static final Logger LOGGER = LoggerFactory.getLogger(BeanManager.class.getSimpleName());
```

> class 내 로그

```java
LOGGER.info("Initialized Beans size : {} ", beanNames.length);
LOGGER.debug("BeanName : {}", beanName);
```

> logback 설정파일

```xml
<configuration>
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
    <logger name="com.dev.sbWebapp.global.config" level="DEBUG" additivity="false">
        <appender-ref ref="CONSOLE"/>
    </logger>
</configuration>
```

## 원인/분석
___

* 클래스내 로거 생성 시 getLogger 메서드 파라미터는 logger 이름임.
* class 내 사용 하는 로거의 이름과 application.yml 또는 logback-spring.xml 에서 설정한 로거의 이름이 동일해야 설정파일에서 설정한 로그레벨이 적용됨.
* class 내 사용하는 로거는 getSimpleName() 메서드를 사용해서 해당 클래스 명(ex.BeanManager)이 로거의 이름임.
* logback 설정 내 해당 패키지 내 클래스에 debug 로깅레벨이 적용되는 로거의 이름은 풀 경로(com.dev.sbWebapp.global.config.beans.BeanManager)임.
  - 로거명 BeanManager : 로깅레벨 null -> root 로깅레벨을 따름 
  - 로거명 com.dev.sbWebapp.global.config.beans.BeanManager : 로깅레벨 debug
* class 내 로거와 설정파일의 로거를 일치시키면 정상적으로 debug 레벨의 로그가 출력됨.

## 해결책
___

> class 내 logger 생성.

```java
private static final Logger LOGGER = LoggerFactory.getLogger(BeanManager.class);
```
> logback 설정파일 일부

```xml
<logger name="com.dev.sbWebapp.global.config" level="DEBUG" additivity="false">
    ...
</logger>
```