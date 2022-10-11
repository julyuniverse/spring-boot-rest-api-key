# REST API 서버의 X-API-KEY 생성 및 설정
> REST API 서버에 요청할 때 X-API-KEY를 통해 인증 받은 다음 값을 받을 수 있도록 설정한다.
> 
> 이유는 누구든지 서버에 요청할 경우 보안 문제와 트래픽 문제가 발생할 수 있다.

## 프로젝트 형태
- Project: Maven Project
- Language: Java 17
- Spring Boot: 2.7.4
- Packaging: Jar
- Dependencies
  - Spring Web
  - Lombok
  - Spring Security