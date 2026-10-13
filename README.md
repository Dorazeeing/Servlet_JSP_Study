# study_project — Servlet → JSP → MVC → EL/JSTL 단계별 실습

`practice/06_0N/source_code/`의 실습 프로젝트를 **거의 그대로 다시 만들어 보는** 프로젝트입니다.
빌드 설정과 CSS처럼 학습 대상이 아닌 파일만 미리 넣어 두었습니다. **Java와 JSP는 직접 작성합니다.**

## AI 에이전트를 활용한 학습 기록

이 저장소는 **AI 코딩 에이전트(Claude Code)를 강사로 두고** 공부한 과정을 그대로 남긴 기록입니다.

| 역할 | 하는 일 |
|---|---|
| 학습자(나) | 미션을 받아 **코드를 직접 작성**하고, 결과를 예측한 뒤 실행해 확인합니다 |
| AI 에이전트 (Claude Code) | 실습 자료를 읽고 단계별 **미션과 필요한 함수 목록**을 냅니다. 작성한 코드를 **실제로 빌드·실행해서 리뷰**하고, 정답 대신 힌트를 줍니다. 학습 노트와 문서를 정리하고 **커밋 이력을 기록**합니다 |

학습 흐름: 미션 → 직접 작성 → AI 리뷰(빌드·실행 결과 포함) → 수정 → 통과 → **통과한 코드를 지우고 백지에서 다시 작성(복습)**

### 커밋 이력 읽는 법
- 학습자는 직접 커밋하지 않습니다. 각 수정을 알고 있는 AI 에이전트가 기록하므로 커밋한 사람(committer)은 `Claude`입니다.
- 실수한 버전 → 리뷰 → 고친 버전이 모두 커밋으로 남아 있습니다.
- 커밋 본문 끝의 표시로 누가 썼는지 구분합니다.
  - `Written-by: learner` — 학습자가 직접 작성
  - `Written-by: Claude Code (AI agent)` + `Co-authored-by: Claude` — AI 에이전트가 작성 (미션·노트·문서, 또는 학습자가 요청한 코드)
  - `Reviewed-by: Claude Code (AI agent)` — AI 리뷰를 거친 학습자 코드
- 단계를 통과한 지점에는 태그가 있습니다 (예: `step1-first-pass`).
- 첫날(2026-10-12)의 이력은 그날 저녁 세션 기록으로 재구성했습니다. 각 커밋 시각은 그 상태를 실제로 확인한 시각입니다.

## 진행 방식

- **한 프로젝트에 계속 쌓아 갑니다.** 클래스 이름, 패키지, URL 매핑, 파일 위치는 원본과 같게 맞춥니다.
- 구조를 뒤엎어야 하는 단계가 오면, 그때 원본을 본뜬 **새 프로젝트**를 따로 만들지 정합니다.
- 미션은 `missions/`에 **한 단계씩** 추가됩니다. 끝나면 Claude에게 "STEP N 끝"이라고 말하세요. 코드 리뷰를 받은 뒤 다음 단계로 넘어갑니다.
- 미션 문서의 **필요한 함수** 표만 보고 작성합니다. 원본 코드는 **다 쓴 뒤에** 대조용으로만 엽니다.
- 막히면 "STEP N 힌트"라고 요청하세요. 정답 대신 힌트를 줍니다.

## 로드맵

최종 목표는 **로그인, 게시판, 댓글**을 직접 구현하는 것입니다. forward와 redirect는 STEP 3에서 따로 떼어 배우고, 뒤의 STEP에서 강의 흐름대로 다시 씁니다.

| STEP | 프로젝트 | 구현 미션 | 원본 |
|---|---|---|---|
| 1 | `study_project` | 인사 서블릿 — `index.html`, `HelloServlet`, `GreetServlet` | `06_01` |
| 2 | `study_project` | JSP 입력·결과 화면 — `input.jsp` → `result.jsp` | `06_02` 5·6교시 |
| 3 | `study_project` | session과 페이지 이동 — `session_check.jsp`, `scope_view.jsp`, `go_forward.jsp`, `go_redirect.jsp` | `06_02` 4·7·8교시 |
| 4 | `study_project` | MVC 메모리 게시판 (목록·등록, PRG) — `BoardPost`, `BoardDao`, `BoardServlet`, `list.jsp` | `06_03` |
| 5 | `study_project` | 게시판 `list.jsp`를 EL/JSTL로 전환 | `06_04` |
| 6 | `study_project` | 로그인·로그아웃, 권한별 메뉴, 서버 측 통제 | `06_04` |
| 7 | `study_project` | 오류 페이지와 경계 진단 | `06_04` |
| 8 | `study_project` | 게시판 CRUD 완성 (상세·검증·수정·삭제, 로그인 연동) | `06_05` |
| 9 | `study_project` | 댓글 (과정 밖 확장) | `06_05` 패턴 응용 |
| 10 | `study_project` | JDBC 교체 — 메모리 DAO를 DB DAO로 | `06_03/custom_project_jdbc`, `06_05` |

`06_04`·`06_05` 원본이 메모리 게시판에서 이어지기 때문에, 로그인·게시판·댓글을 메모리 DAO로 먼저 완성하고 JDBC 교체는 마지막에 합니다.

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
3. Tomcat 실행 구성을 만듭니다. 둘 중 하나를 씁니다.
   - **IntelliJ Ultimate (Windows 실습 PC에서 쓴 방식)**: Run → Edit Configurations → + → **Tomcat Server → Local**
     - Deployment 탭: + → Artifact → `Gradle : com.kyobo : study_project-1.0-SNAPSHOT.war (exploded)`
     - Application context: `/`, HTTP port: `8080`
   - **Community 버전**: Smart Tomcat 플러그인 → Tomcat server 지정, Deployment directory `src/main/webapp`, Context path `/`, Port `8080`
4. 실행한 뒤 `http://localhost:8080/`를 엽니다.

다른 기기(Mac 등)에서 처음 여는 방법과 문제 해결은 `CLAUDE.md`의 8~10장에 있습니다.

**Java 코드를 바꾸면 서버를 재시작해야 합니다.** JSP는 저장만 해도 대부분 반영됩니다.
