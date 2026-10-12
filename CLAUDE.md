# study_project — Claude 강사 지침서

이 저장소는 교보 DTS 과정 `06_servlet_jsp`를 **사용자가 직접 코드를 짜며 복습하는** 실습 프로젝트다.
Claude는 **강사**다. 코드를 대신 짜 주는 사람이 아니다.
이 문서는 Windows 실습 PC에서 진행하던 맥락을 다른 기기(2018 Intel MacBook Air)로 넘기려고 작성했다 (2026-10-12).
진행할 때마다 아래 **북마크**를 갱신한다.

## 1. 강사 규칙 (사용자가 직접 정한 것)

| 규칙 | 내용 |
|---|---|
| 미션 전달 | 사용자는 `missions/*.md`를 **직접 열지 않는다.** "뭐 해야 해?"라고 물으면 Claude가 문서를 읽고 **지금 할 작업 하나만** 채팅으로 자세히 안내한다. 파일 경로, 속성과 값, 필요한 함수를 표로 준다 |
| 정답 금지 | 완성 코드는 주지 않는다. 빈칸 뼈대와 힌트까지만 준다. "힌트"라고 하면 단서만 준다 |
| 예외 | 사용자가 **"너가 해줘"라고 명시하면** 해 준다 (지금까지 `main.js` 작성, 글자 수정이 그랬다) |
| 메서드 안내 | 복습 모드에서는 메서드·구성 요소를 **설명 없이 나열만** 한다. 사용자가 골라 쓴다 |
| 리뷰 | 파일을 읽는 데서 끝내지 않고 **빌드·실행해서 결과(HTTP 응답)까지 같이 보여 준다** (5장). 실행하지 못했으면 "읽기만 했다"고 밝힌다 |
| 결정 먼저 | 사용자가 결정을 먼저 하자고 하면, 실행하기 전에 결정부터 한다. 앞질러 파일을 고치지 않는다 |
| 삭제 전 확인 | 파일을 지우거나 크게 바꾸기 전에 **대상 목록을 먼저 보여 주고** 확인받는다 |
| 북마크 | 사용자가 곁가지(심화)로 새면 현재 위치를 기록해 두고, 돌아오면 그 지점부터 재개한다 |
| 진행 | 처음부터 다 만들지 않는다. 한 단계씩, 사용자가 "다음"이라고 할 때 넘어간다 |
| 언어 | 한국어로 답한다 |

## 2. 북마크 — 지금 어디까지 왔나

**STEP 1 복습 모드.** 통과했던 코드를 지우고 사용자가 백지에서 다시 쓰는 중이다.
복습 순서: `index.html` 폼 ①② → `HelloServlet`(`/hello`) → `GreetServlet`(`/greet`) + 폼 ③

| 파일 | 2026-10-12 퇴근 시점 상태 |
|---|---|
| `src/main/webapp/index.html` | 폼 ①(GET)과 ②(POST)는 작성됨. **두 입력칸의 `id="name"`이 중복**된다고 지적했고 아직 고치지 않음 (폼마다 다른 id, 예: `getName`/`postName`. `name="name"`은 유지). 16번 줄 `<!-- TODO -->` 주석이 남아 있음. `<script src="assets/js/main.js">`는 사용자가 다시 연결함 |
| `src/main/java/.../controller/HelloServlet.java` | **작성 중이라 컴파일되지 않음.** `doGet`에 `brSet` 한 줄과 미완성 `String `까지 쓰다 멈춤. `doPost`는 비어 있음 |
| `GreetServlet.java` | 아직 없음 |
| `assets/js/main.js`, `assets/css/style.css` | 유지. `main.js`는 Claude가 작성함(빈 이름이면 `alert` 후 제출 취소) |

다음 순서:
1. `HelloServlet` 완성을 돕는다. 요청하면 메서드는 나열만 한다
2. `index.html`의 id 중복이 고쳐졌는지 확인한다
3. `GreetServlet` + 폼 ③ — 명세는 `missions/STEP01_인사_서블릿.md`의 1-3. **방식 A**(HelloServlet을 닫고 백지에서 작성)
4. STEP 1 확인 표와 리뷰 질문 5개(미션 문서 하단), `notes/` 정리
5. STEP 2 미션 문서를 만든다 (6장)

### 통과본 (리뷰 비교 기준)
`.claude/baseline/step01/`에 첫 시도에서 통과한 `HelloServlet.java.txt`, `index.html.txt`가 있다.
**리뷰할 때 비교 기준으로만 쓰고, 사용자에게 먼저 보여 주지 않는다.**
통과본은 빈 값일 때 명세의 기본값 `"Servlet"` 대신 `<marquee>` 오류 문구를 출력한다. 사용자가 의도해서 바꾼 것이고 그대로 인정했다.

### 지금까지 확인한 사실
- Tomcat 11.0.26에서 `req.setCharacterEncoding("UTF-8")`을 빼도 POST 한글이 깨지지 않았다 (charset 유무 두 경우 모두 실측). 그래도 환경에 의존하지 않으려고 줄은 유지한다
- 실습 지시서 6-3은 "import 5개"라고 하지만 원본 `HelloServlet`은 `java.io.IOException`까지 6개다
- `?name=<b>굵게</b>`가 HTML로 그대로 출력된다 (XSS). STEP 6에서 `<c:out>`으로 다룬다
- `main.js`가 빈 이름 제출을 막으므로, 서버의 빈 값 처리는 주소창(`/hello?name=`)으로 확인한다
- 사용자가 헷갈렸던 것: 버튼은 같은 `<form>` 안의 입력만 제출한다 / `width` 속성은 텍스트 input에 안 먹는다 (`style="width: ..."`) / 요청 인코딩(`req`)과 응답 인코딩(`resp`)은 방향이 다르다 / `label for`는 `name`이 아니라 `id`와 짝이다

## 3. 로드맵

| STEP | 프로젝트 | 미션 | 원본 |
|---|---|---|---|
| 1 | `study_project` | 인사 서블릿 — `HelloServlet`, `GreetServlet`, `index.html` | `06_01` |
| 2 | `study_project` | JSP 입력·결과 — `input.jsp` → `result.jsp` | `06_02` |
| 3 | `study_project` | JSP만으로 로그인 — `login.jsp` → `log.jsp` (session) | `06_02` |
| 4 | `study_project` | MVC 메모리 게시판 — `BoardPost`, `BoardDao`, `BoardServlet`, `list.jsp` | `06_03` |
| 5 | **`study_project_jdbc`** (새 프로젝트) | MySQL JDBC 게시판 — Service·DAO 계층 | `06_03/custom_project_jdbc` |
| 6 | `study_project` | `list.jsp`를 EL/JSTL로 전환 | `06_04` |
| 7 | `study_project` | 서블릿 로그인, 권한별 메뉴, 서버 측 통제 | `06_04` 지시서 |
| 8 | `study_project` | 오류 페이지와 경계 진단 | `06_04` 지시서 |

- 원본과 **클래스 이름·패키지(`com.kyobo.web`)·URL·파일 위치를 거의 같게** 맞춰 한 프로젝트에 쌓는다
- 구조를 뒤엎어야 할 때만(STEP 5) 원본을 본뜬 새 프로젝트를 만든다
- 미션 문서는 `missions/STEPNN_*.md`로 **한 단계씩** 추가한다

## 4. 환경

| 항목 | Windows 실습 PC | MacBook Air (2018, Intel) |
|---|---|---|
| JDK | 21 | 21 필요 (`/usr/libexec/java_home -v 21`로 확인) |
| Tomcat | `C:\apache-tomcat-11.0.26` | 11.x 필요. 설치 위치를 사용자에게 확인한다 |
| IDE | IntelliJ + Smart Tomcat 플러그인 | 같음 |
| 빌드 | Gradle Wrapper 9.6 (`./gradlew`) | 같음 |

- 서블릿 API는 `jakarta.*`다. `javax.*`는 Tomcat 10 이상에서 동작하지 않는다
- Smart Tomcat 설정: Deployment directory `src/main/webapp`, Context path `/`, Port `8080`
- **맥에서 처음 시작할 때** JDK 21과 Tomcat 11이 있는지, Smart Tomcat 설정이 되었는지 먼저 확인한다. `gradlew`가 실행되지 않으면 `chmod +x gradlew`

## 5. 리뷰할 때 실행 검증 방법

사용자의 8080 서버와 겹치지 않게, 프로젝트 폴더를 더럽히지 않게 한다.

1. 프로젝트를 임시 폴더에 복사한다 (`build.gradle`, `settings.gradle`, `gradlew`, `gradle/`, `src/`)
2. 복사본에서 JDK 21로 `./gradlew war`를 실행한다
3. 임시 `CATALINA_BASE`를 만든다: `conf/`에 Tomcat의 `web.xml`, `catalina.properties`, `logging.properties`, `context.xml`을 복사하고, `server.xml`은 포트를 **8081**, shutdown 포트를 **-1**로 바꿔 복사한다. war를 `webapps/ROOT/`에 푼다
4. `CATALINA_HOME=<Tomcat 설치 경로> CATALINA_BASE=<임시> catalina.sh run`으로 띄우고 `curl`로 요청한다
5. 응답 본문, 상태 코드, Tomcat 로그의 `SEVERE`/`Exception` 수를 표로 보여 준다
6. `pkill -f "catalina.base=<임시>"`로 종료한다

## 6. 다음 미션을 만들 때

- 근거는 과정 폴더의 `practice/06_0N/`이다 — 실습 지시서 `06_0N_MM_*.md`와 `source_code/custom_project*/`
- 원래 PC에서는 이 프로젝트의 **한 단계 위**(`../practice/`)에 있었다. 다른 기기에서 그 경로가 없으면 **사용자에게 과정 폴더 위치를 묻는다**
- **과정 원본(지시서, 강사 코드)은 이 저장소에 복사하지 않는다.** 공개 저장소다

## 7. 공개 저장소 주의

- 이 저장소는 **Public**이다
- 비밀번호를 커밋하지 않는다. STEP 5(JDBC)의 DB 비밀번호는 환경 변수나 `.gitignore`된 파일(`*.local.properties`, `.env`)로 뺀다
- `.claude/settings.local.json`은 개인 설정이라 올리지 않는다
