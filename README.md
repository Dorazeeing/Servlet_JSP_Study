# study_project — Servlet → JSP → MVC → EL/JSTL 단계별 실습

이 프로젝트는 **비어 있는 상태로 시작합니다.** 코드는 직접 작성합니다.
미션은 `missions/` 폴더에 **한 단계씩** 추가됩니다. 단계를 끝내면 Claude에게 "STEP N 끝"이라고 말하세요.
코드 리뷰를 받은 뒤 다음 미션을 받습니다.

## 진행 규칙

1. 미션 문서의 **필요한 함수** 표만 보고 작성합니다. `practice/` 원본 코드는 **다 만든 뒤에** 비교용으로만 엽니다.
2. 실행하기 전에 결과를 **먼저 예측**하고, 그다음 실행해서 확인합니다.
3. 막히면 정답을 받지 말고 **힌트**를 요청하세요. 예: "STEP 1 힌트".
4. 이전 단계 코드는 지우지 않습니다. 패키지와 URL을 나눠서 계속 쌓아 갑니다.

## 로드맵

| STEP | 범위 | 구현 미션 | 대응 실습 |
|---|---|---|---|
| 1 | Servlet | 이름을 받아 인사하는 서블릿 (GET/POST) | 06_01 |
| 2 | Servlet | 회원가입 폼 처리 서블릿 (여러 파라미터, 검증) | 06_01 |
| 3 | JSP | JSP로 다시 만드는 입력·결과 화면과 4가지 scope | 06_02 |
| 4 | JSP | JSP만으로 만드는 로그인 (session, forward와 redirect) | 06_02 |
| 5 | MVC | 메모리 게시판 목록 (DTO, DAO, Servlet, JSP) | 06_03 |
| 6 | MVC | 게시글 등록과 상세 보기 (PRG 패턴) | 06_03 |
| 7 | MVC + JDBC | 메모리 DAO를 MySQL DAO로 교체 (선택) | 06_03 |
| 8 | EL/JSTL | 게시판 JSP에서 스크립틀릿 없애기 | 06_04 |
| 9 | EL/JSTL + 세션 | 서블릿 로그인과 권한별 메뉴, 서버 측 통제 | 06_04 |
| 10 | 마무리 | 오류 페이지와 경계 진단 | 06_04 |

## 환경

| 항목 | 값 |
|---|---|
| JDK | 21 (IntelliJ의 `ms-21`) |
| Tomcat | `C:\apache-tomcat-11.0.26` (Jakarta EE 11, 패키지는 `jakarta.*`) |
| 빌드 | Gradle 9.6 Wrapper |
| 기본 패키지 | `com.kyobo.web` |

## IntelliJ에서 열고 실행하기

1. **File → Open**에서 `study_project` 폴더를 선택합니다. Gradle 프로젝트로 열립니다.
2. Gradle 동기화가 끝날 때까지 기다립니다. 오른쪽 Gradle 탭의 코끼리 아이콘이 새로고침입니다.
3. **Run → Edit Configurations → + → Smart Tomcat**을 선택하고 아래처럼 설정합니다.
   - Tomcat server: `Apache Tomcat/11.0.26`
   - Deployment directory: `study_project/src/main/webapp`
   - Context path: `/`
   - Server port: `8080`
4. 실행한 뒤 `http://localhost:8080/`를 엽니다.

> Context path를 `/`로 두면 주소가 `http://localhost:8080/greet`처럼 짧아집니다.
> `/study`로 바꾸면 `http://localhost:8080/study/greet`가 됩니다. 이 차이는 STEP 6의 redirect 주소에서 다시 다룹니다.

**자바 코드를 바꾸면 서버를 재시작해야 합니다.** JSP는 저장만 해도 대부분 반영됩니다.
