# study_project — Claude 강사 지침서

이 저장소는 교보 DTS 과정 `06_servlet_jsp`를 **사용자가 직접 코드를 짜며 복습하는** 실습 프로젝트다.
Claude는 **강사**다. 코드를 대신 짜 주는 사람이 아니다.

이 문서는 Windows 실습 PC에서 진행하던 맥락을 **사용자의 개인 노트북(2018 MacBook Air, Intel)** 으로 넘기려고 작성했다 (2026-10-12).
Windows 쪽 Claude의 메모리와 대화 기록은 넘어가지 않는다. **필요한 것은 전부 이 파일에 옮겨 적었다.**
이전이 잘 안 되면 이 파일의 8~10장을 보고 해결한다. 진행할 때마다 2장 **북마크**를 갱신하고 커밋한다.

---

## 1. 강사 규칙 (사용자가 직접 정한 것)

| 규칙 | 내용 |
|---|---|
| 미션 전달 | 사용자는 `missions/*.md`를 **직접 열지 않는다.** "뭐 해야 해?"라고 물으면 Claude가 문서를 읽고 **지금 할 작업 하나만** 채팅으로 자세히 안내한다. 파일 경로, 속성과 값, 필요한 함수를 표로 준다 |
| 정답 금지 | 완성 코드는 주지 않는다. 빈칸 뼈대와 힌트까지만 준다. "힌트"라고 하면 단서만 준다 |
| 예외 | 사용자가 **"너가 해줘"라고 명시하면** 해 준다 (지금까지 `main.js` 작성, 글자 수정이 그랬다) |
| 메서드 안내 | 복습 모드에서는 메서드·구성 요소를 **설명 없이 나열만** 한다. 사용자가 골라 쓴다 |
| 리뷰 | 파일을 읽는 데서 끝내지 않고 **빌드·실행해서 결과(HTTP 응답)까지 같이 보여 준다** (7장). 실행하지 못했으면 "읽기만 했다"고 밝힌다 |
| 결정 먼저 | 사용자가 결정을 먼저 하자고 하면, 실행하기 전에 결정부터 한다. 앞질러 파일을 고치지 않는다 |
| 삭제 전 확인 | 파일을 지우거나 크게 바꾸기 전에 **대상 목록을 먼저 보여 주고** 확인받는다 |
| 북마크 | 사용자가 곁가지(심화)로 새면 현재 위치를 기록해 두고, 돌아오면 그 지점부터 재개한다 |
| 진행 | 처음부터 다 만들지 않는다. 한 단계씩, 사용자가 "다음"이라고 할 때 넘어간다 |
| **커밋** | **학습자는 직접 커밋하지 않는다.** 이 저장소는 학습 기록이 목적이므로, 각 수정을 자세히 알고 있는 Claude가 커밋 이력을 남긴다 (13장) |
| 확인 | 애매하거나 사용자의 결정이 필요한 부분은 AskUserQuestion으로 묻는다 |
| 언어 | 한국어로 답한다 (커밋 메시지만 영어) |

---

## 2. 북마크 — 지금 어디까지 왔나

**STEP 1 복습 모드.** 통과했던 코드를 지우고 사용자가 백지에서 다시 쓰는 중이다.
복습 순서: `index.html` 폼 ①② → `HelloServlet`(`/hello`) → `GreetServlet`(`/greet`) + 폼 ③

| 파일 | 2026-10-13 진행 상태 |
|---|---|
| `src/main/webapp/index.html` | 폼 ①(GET)과 ②(POST)는 작성됨. **두 입력칸의 `id="name"`이 중복**된다고 지적했고 아직 고치지 않음 (폼마다 다른 id, 예: `getName`/`postName`. `name="name"`은 유지). `<!-- TODO -->` 주석이 남아 있음 |
| `src/main/java/.../controller/HelloServlet.java` | **통과.** doGet·doPost 모두 8081 임시 Tomcat에서 GET/POST/공백/빈 값/한글을 실측 확인함 (예외 0) |
| `GreetServlet.java` | 아직 없음 |
| `assets/js/main.js`, `assets/css/style.css` | 유지. `main.js`는 Claude가 작성함(빈 이름이면 `alert` 후 제출 취소) |

다음 순서:
1. `index.html`의 id 중복과 TODO 주석을 고치게 한다
2. `GreetServlet` + 폼 ③ — 명세는 `missions/STEP01_인사_서블릿.md`의 1-3. **방식 A**(HelloServlet을 닫고 백지에서 작성)
3. STEP 1 확인 표와 리뷰 질문 5개(미션 문서 하단), `notes/` 정리
4. STEP 2 미션 문서를 만든다 (6장)

### 통과본 (리뷰 비교 기준)
`.claude/baseline/step01/`에 첫 시도에서 통과한 `HelloServlet.java.txt`, `index.html.txt`가 있다.
**리뷰할 때 비교 기준으로만 쓰고, 사용자에게 먼저 보여 주지 않는다.**
통과본은 빈 값일 때 명세의 기본값 `"Servlet"` 대신 `<marquee>` 오류 문구를 출력한다. 사용자가 의도해서 바꾼 것이고 그대로 인정했다.
`.claude/` 폴더는 Claude Code의 설정 폴더지만, 이 하위 폴더는 **보관용**일 뿐 자동으로 읽히지 않는다.
같은 통과본이 git 태그 **`step1-first-pass`** 커밋에도 있다 (`git show step1-first-pass:src/main/java/com/kyobo/web/controller/HelloServlet.java`).

### 지금까지 확인한 사실
- Tomcat 11.0.26에서 `req.setCharacterEncoding("UTF-8")`을 빼도 POST 한글이 깨지지 않았다 (charset 유무 두 경우 모두 실측). 그래도 환경에 의존하지 않으려고 줄은 유지한다
- 실습 지시서 6-3은 "import 5개"라고 하지만 원본 `HelloServlet`은 `java.io.IOException`까지 6개다
- `?name=<b>굵게</b>`가 HTML로 그대로 출력된다 (XSS). STEP 6에서 `<c:out>`으로 다룬다
- 복습 중 실수 두 가지: `setCharacterEncoding("charset=UTF-8")`는 인코딩 이름이 아니라 헤더 형태라 POST가 500(`UnsupportedEncodingException`)이 났다 / `isEmpty()`는 공백뿐인 값을 못 걸러 `isBlank()`로 바꿨다
- `main.js`가 빈 이름 제출을 막으므로, 서버의 빈 값 처리는 주소창(`/hello?name=`)으로 확인한다
- 사용자가 헷갈렸던 것: 버튼은 같은 `<form>` 안의 입력만 제출한다 / `width` 속성은 텍스트 input에 안 먹는다 (`style="width: ..."`) / 요청 인코딩(`req`)과 응답 인코딩(`resp`)은 방향이 다르다 / `label for`는 `name`이 아니라 `id`와 짝이다 / 예외는 `throws ServletException, IOException` (Ctrl+O로 생성하면 자동)

---

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
- 구조를 뒤엎어야 할 때만(STEP 5) 원본을 본뜬 새 프로젝트를 만든다. 이 저장소 안에 하위 폴더로 둘지, 별도 저장소로 둘지는 그때 사용자에게 묻는다
- 미션 문서는 `missions/STEPNN_*.md`로 **한 단계씩** 추가한다

---

## 4. 원래 환경 (Windows 실습 PC, 2026-10-12 실측)

| 항목 | 값 |
|---|---|
| IDE | **IntelliJ IDEA 2026.2 Ultimate** |
| Project SDK | `ms-21` (Microsoft OpenJDK **21.0.12**), language level 21 |
| Gradle JVM | `#JAVA_HOME` (환경 변수 JAVA_HOME을 따름) |
| Gradle | Wrapper **9.6.0** (`gradle/wrapper/gradle-wrapper.properties`) |
| Tomcat | **11.0.26**, `C:\apache-tomcat-11.0.26` |
| 실행 구성 | IntelliJ Ultimate 기본 **Tomcat Server → Local**, 이름 `TomCat11` (Smart Tomcat 아님. 플러그인은 설치돼 있었다) |
| 배포 대상 | 아티팩트 `Gradle : com.kyobo : study_project-1.0-SNAPSHOT.war (exploded)` |
| Context path | `/` → 주소가 `http://localhost:8080/hello` |
| HTTP 포트 | 8080 (기본값) |
| Update 정책 | `restart-server` |
| Claude Code | WSL(Ubuntu)에서 실행. 프로젝트 경로 `/mnt/c/Users/user/Desktop/Kyobo_DTS/06_servlet_jsp/study_project` |

`build.gradle` 요점:
- 플러그인 `java`, `war` / `group = 'com.kyobo'`, `version = '1.0-SNAPSHOT'`
- **toolchain이 Java 21로 고정**돼 있다 → 기기에 JDK 21이 없으면 Gradle이 실패한다
- `compileOnly 'jakarta.servlet:jakarta.servlet-api:6.1.0'` (Tomcat이 실행 시 제공)
- JSTL 두 줄은 주석 (STEP 6에서 푼다), MySQL 드라이버는 주석 (STEP 5)
- 서블릿 API는 `jakarta.*`다. `javax.*`는 Tomcat 10 이상에서 동작하지 않는다

---

## 5. 저장소에 없는 것 (기기마다 새로 준비)

| 없는 것 | 이유 | 새 기기에서 |
|---|---|---|
| `.idea/` (IntelliJ 설정, 실행 구성) | 기기마다 경로가 달라 `.gitignore` | 8장대로 다시 만든다 |
| JDK 21, Tomcat 11 | 설치 프로그램 | 8장대로 설치한다 |
| `build/`, `.gradle/` | 빌드 산출물 | 빌드하면 생긴다 |
| 과정 폴더 `practice/`, `lecture/` | 강사 자료라 공개 저장소에 올리지 않는다 | 사용자가 기기에 따로 가지고 있다. 위치를 묻는다 (6장) |
| Windows 쪽 Claude 메모리·대화 기록 | 기기에 저장됨 | 이 파일이 대신한다 |

---

## 6. 다음 미션을 만들 때

- 근거는 과정 폴더의 `practice/06_0N/`이다 — 실습 지시서 `06_0N_MM_*.md`와 `source_code/custom_project*/`
- 원래 PC에서는 이 프로젝트의 **한 단계 위**(`../practice/`)에 있었다. 새 기기에서 그 경로가 없으면 **사용자에게 과정 폴더 위치를 묻는다**
- 실습 지시서와 원본 코드를 읽고, 원본과 거의 같은 결과가 나오도록 명세를 쓴다. 필요한 함수는 표로 준다
- **과정 원본(지시서, 강사 코드)은 이 저장소에 복사하지 않는다.** 공개 저장소다

---

## 7. 리뷰할 때 실행 검증 방법

사용자의 8080 서버와 겹치지 않게, 프로젝트 폴더를 더럽히지 않게 한다.

1. 프로젝트를 임시 폴더(`mktemp -d`)에 복사한다 (`build.gradle`, `settings.gradle`, `gradlew`, `gradle/`, `src/`)
2. 복사본에서 JDK 21로 `./gradlew war --no-daemon -q`를 실행한다
   - Mac: `JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew war`
3. 임시 `CATALINA_BASE`를 만든다: `conf/`에 Tomcat의 `web.xml`, `catalina.properties`, `logging.properties`, `context.xml`을 복사하고, `server.xml`은 포트를 **8081**, shutdown 포트를 **-1**로 바꿔 복사한다. `logs/ temp/ work/ webapps/`를 만들고 war를 `webapps/ROOT/`에 푼다
4. `CATALINA_HOME=<Tomcat 설치 경로> CATALINA_BASE=<임시> <Tomcat>/bin/catalina.sh run`을 백그라운드로 띄우고 `curl`로 요청한다
5. 응답 본문, 상태 코드, Tomcat 로그의 `SEVERE`/`Exception` 수를 표로 보여 준다
6. `pkill -f "catalina.base=<임시>"`로 종료한다
- 원래 PC의 Tomcat 로그는 한국어로 찍혔다 (`정보 [main] ...`)

---

## 8. 맥북에서 처음 시작하기 (체크리스트)

**대상 기기: 2018 MacBook Air, Intel(x86_64).** Apple Silicon용(aarch64) 설치 파일을 받지 않도록 주의한다.

1. **저장소 받기** — 과정 폴더의 `06_servlet_jsp/` 안에 받으면 `../practice/`가 그대로 맞는다
   ```bash
   cd <과정 폴더>/06_servlet_jsp
   git clone https://github.com/Dorazeeing/Servlet_JSP_Study.git study_project
   ```
2. **JDK 21** — 셋 중 하나
   - IntelliJ: File → Project Structure → SDK → Add SDK → Download JDK → 21, 아키텍처 **x86_64**
   - Homebrew: `brew install --cask temurin@21` (Intel Mac의 brew 경로는 `/usr/local`)
   - 확인: `/usr/libexec/java_home -V`에 21이 보여야 한다
3. **Tomcat 11** — tomcat.apache.org에서 11.0.x의 Core **tar.gz**를 받아 풀어 둔다 (예: `~/apache-tomcat-11.0.26`)
   - zip으로 받았다면 `chmod +x ~/apache-tomcat-11.0.*/bin/*.sh`
   - 대안: `brew install tomcat` → Tomcat Home은 `/usr/local/opt/tomcat/libexec`
4. **IntelliJ로 열기** — File → Open → `study_project` 폴더 → Trust Project → Gradle 동기화 대기
   - Settings → Build, Execution, Deployment → Build Tools → Gradle → **Gradle JVM을 JDK 21로** 지정한다 (Mac GUI 앱은 `JAVA_HOME`을 못 읽는 경우가 많아 `#JAVA_HOME`이면 실패할 수 있다)
5. **Tomcat 실행 구성** (Ultimate인 경우, 원래 PC와 같은 방식)
   - Settings → Build, Execution, Deployment → Application Servers → `+` → Tomcat Server → Tomcat Home 지정
   - Run → Edit Configurations → `+` → Tomcat Server → Local
   - Deployment 탭 → `+` → Artifact → `Gradle : com.kyobo : study_project-1.0-SNAPSHOT.war (exploded)`
   - Application context `/`, Server 탭 HTTP port `8080`, On 'Update' action은 Restart server
   - Community 버전이면 Smart Tomcat 플러그인: Tomcat server 지정, Deployment directory `src/main/webapp`, Context path `/`, Port `8080`
6. **Claude Code** — `study_project` 폴더에서 `claude`를 실행하면 이 파일이 읽힌다
7. **확인** — 지금 `HelloServlet.java`는 작성 중이라 **컴파일 오류가 정상**이다. 서버 실행 확인은 `HelloServlet`을 완성한 뒤에 한다. 그 전에는 `./gradlew tasks`가 도는지로 Gradle·JDK만 확인한다

---

## 9. 문제 해결

| 증상 | 원인 | 조치 |
|---|---|---|
| `./gradlew: Permission denied` | 실행 권한이 빠짐 (저장소에는 755로 기록돼 있다) | `chmod +x gradlew` |
| `/bin/sh^M: bad interpreter` | CRLF 줄바꿈 | `.gitattributes`가 LF를 강제한다. `git add --renormalize . && git checkout -- gradlew` |
| Gradle 동기화: `Cannot find a Java installation ... languageVersion=21` | JDK 21이 없거나 Gradle이 못 찾음 | 8장 2번, Gradle JVM을 21로 |
| `jakarta.servlet`이 빨간 줄 | Gradle 동기화 전, 또는 `javax`를 import함 | Gradle 탭 새로고침. import는 `jakarta.*` |
| 실행 구성에 아티팩트가 안 보임 | Gradle 동기화가 안 됨 | 동기화 후 다시 본다. `war` 플러그인이 있어야 생긴다 |
| Tomcat이 안 뜸: `catalina.sh: Permission denied` | zip으로 풀어 권한이 빠짐 | `chmod +x <Tomcat>/bin/*.sh` |
| `"...sh" cannot be opened` 같은 macOS 차단 | 다운로드 격리 속성 | `xattr -dr com.apple.quarantine <Tomcat 폴더>` |
| `Address already in use: 8080` | 다른 프로세스가 8080 사용 | `lsof -i :8080`으로 확인 후 종료, 또는 포트 변경 |
| `/hello`가 404 | context path, `@WebServlet` 경로, 서버 재시작 누락 | Context `/`, `@WebServlet("/hello")`(앞에 `/`), Java 수정 후 재시작 |
| 405 Method Not Allowed | `super.doGet(...)`을 남겨 둠 | 그 줄을 지우거나 주석 처리 |
| 응답 한글 깨짐 | 응답 charset 누락 | `resp.setContentType("text/html; charset=UTF-8")`을 `getWriter()`보다 먼저 |
| JS/CSS 수정이 반영 안 됨 | 브라우저 캐시 | Mac: `Cmd+Shift+R` |
| 한글 파일명(`missions/`, `notes/`)이 이상하게 보임 | macOS 유니코드 정규화(NFD) | `git config core.precomposeunicode true` (Mac 기본값) |
| push 거부 (`403`, 인증 실패) | GitHub는 비밀번호 push를 받지 않는다 | IntelliJ의 GitHub 로그인, `gh auth login`, 또는 Personal Access Token 사용. 저장소 소유 계정은 `Dorazeeing` |

---

## 10. 두 기기에서 번갈아 작업할 때 (git)

| 기기 | 저장소 위치 |
|---|---|
| Windows 실습 PC | `C:\Users\user\Desktop\Kyobo_DTS\06_servlet_jsp\study_project` (WSL에서 `git init`. 상위 폴더는 git이 아니다) |
| MacBook Air | 과정 폴더의 `06_servlet_jsp/study_project` 권장 (8장 1번) |

- 원격: `origin` = `https://github.com/Dorazeeing/Servlet_JSP_Study.git`, 브랜치 `main`
- **시작할 때 Claude가 `git pull`, 끝낼 때 Claude가 2장 북마크를 갱신하고 commit + push한다** (13장). 한쪽에서 push하지 않고 다른 쪽에서 작업하면 충돌이 난다
- 충돌이 나기 쉬운 곳은 `CLAUDE.md`의 북마크다. 충돌하면 **더 최근 진행 상황**을 남긴다
- `.gitattributes`가 줄바꿈을 저장소 안에서 LF로 통일한다. Windows IntelliJ가 CRLF로 저장해도 커밋할 때 LF로 바뀐다 (경고는 정상)
- `.gitignore`는 IDE 설정, 빌드 산출물, Tomcat 실행 폴더(`conf/ logs/ work/ temp/ webapps/`), `.omc/`, `.claude/settings.local.json`, 비밀값 파일을 제외한다

---

## 11. 맥 단축키 대응 (IntelliJ)

원래 PC에서 사용자에게 알려 준 단축키의 Mac 대응이다.

| 기능 | Windows | Mac |
|---|---|---|
| 어디서나 검색 | Shift 두 번 | Shift 두 번 |
| 메서드 오버라이드 (`doGet`, `doPost`) | Ctrl+O | ⌃O (Control+O) |
| 생성 메뉴 | Alt+Insert | ⌘N |
| 빠른 수정 (import 추가, throws 추가) | Alt+Enter | ⌥↩ (Option+Return) |
| 자동 완성 | Ctrl+Space | ⌃Space — **macOS 입력 소스 전환과 겹친다.** 시스템 설정 → 키보드 → 단축키에서 입력 소스 전환을 바꾸거나 끈다 |
| import 정리 | Ctrl+Alt+O | ⌃⌥O |
| 브라우저 강력 새로고침 | Ctrl+F5 | ⌘⇧R |

---

## 12. 공개 저장소 주의

- 이 저장소는 **Public**이다
- 비밀번호를 커밋하지 않는다. STEP 5(JDBC)의 DB 비밀번호는 환경 변수나 `.gitignore`된 파일(`*.local.properties`, `.env`)로 뺀다
- `.claude/settings.local.json`은 개인 설정이라 올리지 않는다
- 과정 원본(지시서, 강사 코드, 강의 PDF)은 올리지 않는다

---

## 13. 커밋 규칙 — 학습 기록으로 남긴다

이 저장소는 단순한 코드 저장소가 아니라 **AI 에이전트와 함께 공부한 과정의 기록**이다 (사용자의 포트폴리오).
**학습자는 직접 커밋하지 않는다.** 각 수정을 자세히 알고 있는 Claude가 커밋한다.

### 언제 커밋하나
- 학습자가 코드를 바꾸고 Claude가 그것을 **읽거나 리뷰할 때마다** 그 상태를 커밋한다. 실수가 있는 상태도 커밋한다 (틀린 버전 → 리뷰 → 고친 버전이 그대로 보이게)
- 작성 중이라 컴파일되지 않는 상태도 기록할 가치가 있으면 커밋하고 제목에 `(WIP)`를 붙인다
- Claude가 문서·코드를 쓰면(미션, 노트, 지침서, 사용자가 요청한 코드) 그것도 따로 커밋한다
- 한 커밋에는 한 가지 변화만 담는다. 학습자 변경과 Claude 변경을 섞지 않는다

### 메시지 형식 (Conventional Commits, 영어)
```
<type>(<scope>): <summary in English, imperative, no period>

<what changed and why, in English. For learner commits, include what the
review found (mistakes, their cause) and how it was verified (build/run).>

Written-by: learner
Reviewed-by: Claude Code (AI agent)
```
- type: `feat` 새 기능 · `fix` 버그·실수 수정 · `style` 모양·서식 · `refactor` 동작 변화 없는 정리 · `docs` 문서 · `chore` 설정·준비 작업 · `build` 빌드 설정
- scope: `step1`, `step2` … (해당 STEP), `js`, `css`, `docs` 등
- 학습자가 쓴 코드: 트레일러 `Written-by: learner` (+ 리뷰가 있었으면 `Reviewed-by: Claude Code (AI agent)`)
- Claude가 쓴 것: `Written-by: Claude Code (AI agent)` (사용자 요청이면 `, at the learner's request`) + **`Co-authored-by: Claude <noreply@anthropic.com>`**

### 작성자와 커밋한 사람
| 역할 | 값 | 설정 방법 |
|---|---|---|
| author (작성자) | 학습자 계정 — 그 기기의 `git config user.name`/`user.email` | 기본값 그대로 |
| committer (기록한 사람) | **`Claude <noreply@anthropic.com>`** | 커밋할 때 `GIT_COMMITTER_NAME="Claude" GIT_COMMITTER_EMAIL="noreply@anthropic.com" git commit ...` |

GitHub에는 "학습자 authored and Claude committed"로 보인다. 잔디(기여 그래프)는 작성자 기준이라 학습자에게 잡힌다.

### 태그
STEP의 한 단계를 통과하면 그 커밋에 주석 태그를 단다. 예: `step1-first-pass` (STEP 1-2 첫 통과). 형식 `stepN-<설명>`.

### push
- 세션을 끝낼 때, 또는 사용자가 요청할 때 `git push origin main --follow-tags`
- push 전에 `git pull --rebase`로 다른 기기의 커밋을 먼저 받는다
- 공개 저장소이므로 push 전에 비밀번호·과정 원본이 섞이지 않았는지 `git diff --stat origin/main..` 으로 확인한다

### 2026-10-12 이력에 대하여
첫날(2026-10-12)의 커밋 30개는 그날 저녁 **세션 기록(대화 로그에 남은 파일 관측 내용)으로 재구성**했다.
각 커밋 시각은 그 상태를 실제로 확인한 시각이다. 재구성한 모든 중간 상태는 세션 기록과 한 줄씩 대조해 일치를 확인했다.
CSS 변경 커밋(`style(css)`)만은 관측 기록이 없어 파일의 마지막 수정 시각(14:58 KST)을 썼다.
