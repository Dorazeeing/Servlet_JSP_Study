// 폼을 제출하기 전에 이름 입력칸이 비어 있는지 검사한다.
// 서버의 name == null || name.isBlank() 검사를 대신하지 못한다. 사용자 편의용이다.

document.querySelectorAll("form").forEach(function (form) {
    form.addEventListener("submit", function (e) {
        const input = form.querySelector("input[name='name']");

        // 공백만 입력한 경우도 빈 값으로 본다
        if (input.value.trim() === "") {
            e.preventDefault();          // 제출 취소: 서버로 요청이 가지 않는다
            alert("이름을 입력해 주세요.");
            input.focus();               // 커서를 입력칸으로 돌려 준다
        }
    });
});
