# STEP 1 — 인사 서블릿 만들기

> 원본: `practice/06_01/source_code/custom_project` · 실습 지시서: `practice/06_01/06_01_06~08`
> **원본 `HelloServlet.java`는 다 쓸 때까지 열지 않습니다.**

## 목표

브라우저 폼에서 보낸 이름을 서블릿이 받아 `<h1>Hello, 이름</h1>`으로 응답하게 만듭니다.
이 과정에서 다섯 요소를 직접 써 봅니다: **URL 매핑 / GET·POST 분기 / 파라미터 계약 / 인코딩 / 응답 생성**

## 만들 파일

```
src/main/java/com/kyobo/web/controller/HelloServlet.java   ← 1-2
src/main/java/com/kyobo/web/controller/GreetServlet.java   ← 1-3
src/main/webapp/index.html                                  ← 1-1, 1-3
```

---

## 1-1. `index.html` — 폼 두 개

| 항목 | 값 |
|---|---|
| `<title>` | `Servlet Practice` |
| `<h1>` | `첫 Servlet 호출` |
| 폼 ① | `action="hello"`, `method="get"`, 입력칸 `name="name"`, 버튼 `GET 요청` |
| 폼 ② | `action="hello"`, `method="post"`, 입력칸 `name="name"`, 버튼 `POST 요청` |

- `<meta charset="UTF-8">`과 `<html lang="ko">`를 넣습니다.
- 두 폼은 **`method`만 다르고 나머지는 같아야 합니다.** 8교시에 GET과 POST를 비교하는 실험 조건이기 때문입니다.

## 1-2. `HelloServlet` — `/hello`

| 메서드 | 할 일 (이 순서대로) |
|---|---|
| `doGet` | ① 응답 타입을 `text/html; charset=UTF-8`로 설정 → ② 파라미터 `name` 읽기 → ③ `null`이거나 공백뿐이면 `"Servlet"` 대입 → ④ `<h1>Hello, 이름</h1>` 출력 |
| `doPost` | ① 요청 인코딩을 `UTF-8`로 설정 → ② `doGet`을 호출해 처리를 넘긴다 |

## 1-3. `GreetServlet` — `/greet` (6·7교시)

`HelloServlet`과 같은 구조로 **백지에서** 만듭니다. 차이점은 세 가지입니다.

1. 매핑은 `/greet`입니다. `/hello`를 쓰면 두 서블릿이 충돌해 **배포가 실패합니다.**
2. 이름의 기본값은 `"Guest"`입니다. 화면만 보고 어느 서블릿이 응답했는지 구분하기 위해서입니다.
3. 파라미터 `email`도 받습니다. 빈 값일 때의 기본값은 직접 정하고, 출력에 덧붙입니다.

`GreetServlet`은 `doGet`만 만듭니다. `index.html`에 세 번째 폼을 추가합니다.

| 폼 ③ | `action="greet"`, `method="get"`, 입력칸 `name="name"`과 `name="email"`, 버튼 `GREET 요청` |
|---|---|

---

## 필요한 함수

**import할 클래스 (5개)** — 패키지만 알려 드립니다. 클래스 이름은 아래 표에서 찾으세요.

| 패키지 | 개수 |
|---|---|
| `jakarta.servlet` | 1 (예외) |
| `jakarta.servlet.annotation` | 1 (애너테이션) |
| `jakarta.servlet.http` | 3 |
| `java.io` | 1 (예외) |

> 합이 6입니다. `HelloServlet`처럼 `doGet`과 `doPost`가 모두 있으면 6개가 필요합니다. 원본은 import가 5개라고 적혀 있지만 실제로 세어 보세요.

| 종류 | 이름 | 쓰임 |
|---|---|---|
| 애너테이션 | `@WebServlet("/경로")` | 클래스 위에 붙여 URL과 서블릿을 연결한다 |
| 상속 | `extends HttpServlet` | GET·POST를 나눠 받는 서블릿의 부모 클래스 |
| 재정의 | `protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException` | GET 요청을 처리한다. `@Override`를 붙인다 |
| 재정의 | `protected void doPost(...)` | POST 요청을 처리한다. 매개변수와 `throws`는 `doGet`과 같다 |
| 응답 | `response.setContentType(String type)` | 응답의 종류와 문자 인코딩을 정한다. **`getWriter()`보다 먼저** 호출한다 |
| 응답 | `response.getWriter()` → `PrintWriter` | 브라우저로 글자를 보내는 출력 통로 |
| 응답 | `PrintWriter.println(String s)` | HTML 문자열 한 줄을 응답 본문에 쓴다 |
| 요청 | `request.getParameter(String name)` → `String` | 폼 입력칸의 `name` 속성으로 값을 꺼낸다. 키가 없으면 **`null`** 을 준다 |
| 요청 | `request.setCharacterEncoding(String enc)` | POST 본문을 어떤 인코딩으로 읽을지 정한다. **파라미터를 읽기 전에** 호출한다 |
| 문자열 | `String.isBlank()` | 빈 문자열이거나 공백뿐이면 `true`를 준다. `null`에서 부르면 `NullPointerException`이 난다 |

---

## 확인

서버를 실행하고 **먼저 예측을 적은 뒤** 확인합니다.

| 요청 | 예측 | 관측 |
|---|---|---|
| `http://localhost:8080/` 에서 GET 폼에 `홍길동` 입력 |  |  |
| 같은 이름을 POST 폼으로 입력 |  |  |
| 두 폼 모두 빈 값으로 입력 |  |  |
| `http://localhost:8080/hello` 직접 열기 |  |  |
| `http://localhost:8080/greet` 직접 열기 |  |  |
| GREET 폼에 이름만 입력하고 이메일은 비우기 |  |  |

**실험 한 가지** — `doPost`의 `setCharacterEncoding` 줄을 주석 처리하고 **서버를 재시작한 뒤** POST로 `홍길동`을 보냅니다. 본 결과를 그대로 적고, 확인이 끝나면 **주석을 풀어 원래대로 되돌립니다.** Tomcat 설정에 따라 깨지지 않을 수도 있습니다.

## 생각해 볼 질문

STEP 1 리뷰 때 물어봅니다.

1. 주소창에 이름이 보이는 것은 GET과 POST 중 어느 쪽입니까? 그 이유는 무엇입니까?
2. 빈 값을 보냈을 때 GET과 POST의 결과가 같은 이유를 `doPost`의 마지막 줄로 설명해 보세요.
3. `name == null || name.isBlank()`에서 두 조건의 순서를 바꾸면 어떻게 됩니까?
4. `email` 파라미터 하나를 늘리려고 고친 파일은 몇 개입니까? 파라미터 계약은 누구와 누구 사이의 약속입니까?
5. `doGet`을 아예 만들지 않으면 컴파일이 됩니까? 브라우저로 열면 무엇이 보일까요?

## 완료 기준

- [ ] 폼 세 개가 모두 의도한 서블릿을 호출한다
- [ ] 한글 이름이 GET과 POST 모두에서 깨지지 않는다
- [ ] 빈 값을 보내면 기본값(`Servlet`, `Guest`)이 나온다
- [ ] 확인 표의 예측과 관측 칸을 채웠다
- [ ] 다 쓴 뒤 원본 `HelloServlet.java`, `index.html`과 대조해 다른 점을 찾았다

끝나면 **"STEP 1 끝"** 이라고 말하세요. 코드를 읽고 리뷰한 뒤 STEP 2(JSP)를 드립니다.
