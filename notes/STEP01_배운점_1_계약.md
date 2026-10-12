# STEP 1 배운 점 ① — 브라우저와 서버는 문자열 약속(계약)으로만 이어진다

> 내가 직접 짠 `index.html`, `HelloServlet.java`를 기준으로 정리했다.

## 한 줄 요약
브라우저와 서버는 서로의 코드를 보지 못한다. **약속한 이름이 같아야** 값이 전달되고, 약속이 어긋나도 **오류 없이** 조용히 실패한다.

## 계약은 두 개다

| # | 브라우저 쪽 | 서버 쪽 | 어긋나면 |
|---|---|---|---|
| ① | `<input name="name">` | `req.getParameter("name")` | 오류 없이 `null`이 온다 |
| ② | `<form action="hello">` | `@WebServlet("/hello")` | 404 |

```html
<!-- index.html -->
<form action="hello" method="get">
    <label>이름: <input name="name"></label>
    <button>GET 요청</button>
</form>
```

```java
// HelloServlet.java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) ... {
        String name = req.getParameter("name");
```

### 계약 ① 파라미터 이름
- 서버로 가는 값의 **키**는 `<input>`의 **`name` 속성**이다. `id`가 아니다.
- `name` 속성이 없는 입력칸은 제출해도 **아예 전달되지 않는다.** 서버는 `null`을 받는다.
- 서버의 `getParameter("name")`은 문자열로 약속한다. 오타가 나도 컴파일러가 잡아 주지 못한다.
- 이름을 바꾸려면 **HTML과 Java 양쪽**을 같이 고친다. 파라미터를 하나 늘려도 마찬가지다.

### 계약 ② 요청 주소
- `action="hello"`는 앞에 `/`가 없는 **상대 경로**다. 현재 페이지 주소 기준으로 붙는다.
- `@WebServlet("/hello")`는 앞에 `/`가 **있어야** 한다. 빼면 배포 단계에서 오류가 난다.
- 둘이 이어져야 폼이 서블릿에 닿는다. 어긋나면 404다.

## 버튼은 어떤 입력칸을 아는가
버튼과 입력칸은 직접 연결되어 있지 않다. 같은 `<form>` **안에 있는지**로 묶인다.

1. 버튼을 누르면 브라우저가 그 버튼이 속한 `<form>`을 제출한다.
2. 그 폼 안에서 **`name` 속성이 있는** 입력 컨트롤을 모아 `name=값` 쌍으로 만든다.
3. 그 쌍을 `action` 주소로 `method` 방식으로 보낸다.

내 실수: 버튼을 `</form>` 바깥에 두었더니 눌러도 아무 일도 일어나지 않았다.

## 값이 서버에서 어떤 모양으로 오는가
`getParameter`가 돌려주는 값은 세 종류다. 구분해서 검사해야 한다.

| 요청 | `getParameter("name")` | 의미 |
|---|---|---|
| `/hello` | `null` | 키 자체가 없다 |
| `/hello?name=` | `""` | 키는 있는데 값이 비었다 |
| `/hello?name=%20%20` | `"  "` | 공백뿐이다 |

```java
if (name == null || name.isBlank()) { ... }
```

- `null`을 먼저 검사한다. `null`에 `isBlank()`를 부르면 `NullPointerException`이 난다.
- `||`는 앞이 `true`면 뒤를 실행하지 않는다(단락 평가). 이 성질 덕에 순서가 안전장치가 된다.

## 계약이 깨졌을 때 잡는 방법
오류가 나지 않으므로 **눈으로 확인하는 순서**가 필요하다.

| 증상 | 확인할 곳 |
|---|---|
| 404 | `action` 값, `@WebServlet` 값, 앞의 `/` |
| 서블릿은 실행되는데 값이 `null` | `<input>`의 `name` 속성(오타, 누락), 버튼이 폼 안에 있는지 |
| 화면이 비어 있다 | 서블릿 `if/else` 구조, `println` 위치 |

## 정리
- 폼 ↔ 서블릿은 **이름이 같다는 약속**뿐이다. 컴파일러도 브라우저도 대신 검사해 주지 않는다.
- 한쪽만 바꾸면 깨진다. 항상 **양쪽을 함께** 바꾼다.
- 틀려도 오류가 나지 않는 경우가 많으니 **예측 → 실행 → 비교**로 확인한다.
