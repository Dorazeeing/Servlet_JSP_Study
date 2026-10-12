# study_project — Servlet → JSP → MVC → EL/JSTL 단계별 실습

`practice/06_0N/source_code/`의 실습 프로젝트를 **거의 그대로 다시 만들어 보는** 프로젝트입니다.
빌드 설정과 CSS처럼 학습 대상이 아닌 파일만 미리 넣어 두었습니다. **Java와 JSP는 직접 작성합니다.**

## 진행 방식

- **한 프로젝트에 계속 쌓아 갑니다.** 클래스 이름, 패키지, URL 매핑, 파일 위치는 원본과 같게 맞춥니다.
- 구조를 뒤엎어야 하는 단계(예: 메모리 DAO → JDBC 계층 구조)에서는 원본을 본뜬 **새 프로젝트**를 따로 만듭니다.
- 미션은 `missions/`에 **한 단계씩** 추가됩니다. 끝나면 Claude에게 "STEP N 끝"이라고 말하세요. 코드 리뷰를 받은 뒤 다음 단계로 넘어갑니다.
- 미션 문서의 **필요한 함수** 표만 보고 작성합니다. 원본 코드는 **다 쓴 뒤에** 대조용으로만 엽니다.
- 막히면 "STEP N 힌트"라고 요청하세요. 정답 대신 힌트를 줍니다.

## 로드맵

| STEP | 프로젝트 | 구현 미션 | 원본 |
|---|---|---|---|
| 1 | `study_project` | 인사 서블릿 — `HelloServlet`, `GreetServlet`, `index.html` | `06_01/custom_project` |
| 2 | `study_project` | JSP 입력·결과 화면 — `input.jsp` → `result.jsp` | `06_02/custom_project` |
| 3 | `study_project` | JSP만으로 만드는 로그인 — `login.jsp` → `log.jsp` (session) | `06_02/custom_project` |
| 4 | `study_project` | MVC 메모리 게시판 — `BoardPost`, `BoardDao`, `BoardServlet`, `list.jsp` | `06_03/custom_project` |
| 5 | **`study_project_jdbc`** (새 프로젝트) | MySQL JDBC 게시판 — Service·DAO 계층, 목록·등록·상세 | `06_03/custom_project_jdbc` |
| 6 | `study_project` | 게시판 `list.jsp`를 EL/JSTL로 전환 | `06_04/custom_project` |
| 7 | `study_project` | 서블릿 로그인, 권한별 메뉴, 서버 측 통제 | `06_04` 실습 지시서 |
| 8 | `study_project` | 오류 페이지와 경계 진단 | `06_04` 실습 지시서 |

STEP 5는 **별도 프로젝트**에서 진행합니다. STEP 6부터는 다시 `study_project`로 돌아옵니다. `06_04` 원본도 메모리 게시판에서 이어지기 때문입니다.

## 환경

| 항목 | 값 |
|---|---|
| JDK | 21 (IntelliJ의 `ms-21`) |
| Tomcat | `C:\apache-tomcat-11.0.26` (패키지는 `javax.*`가 아니라 `jakarta.*`) |
| 빌드 | Gradle 9.6 Wrapper (원본에서 복사) |
| 기본 패키지 | `com.kyobo.web` |

## IntelliJ에서 열고 실행하기

1. **File → Open**에서 `study_project` 폴더를 선택합니다. Gradle 프로젝트로 열립니다.
2. Gradle 동기화가 끝날 때까지 기다립니다.
3. **Run → Edit Configurations → + → Smart Tomcat**을 선택하고 아래처럼 설정합니다. 원본 실습과 같은 설정입니다.
   - Tomcat server: `Apache Tomcat/11.0.26`
   - Deployment directory: `study_project/src/main/webapp`
   - Context path: `/`
   - Server port: `8080`
4. 실행한 뒤 `http://localhost:8080/`를 엽니다.

**Java 코드를 바꾸면 서버를 재시작해야 합니다.** JSP는 저장만 해도 대부분 반영됩니다.
