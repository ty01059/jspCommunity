<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function validateForm() {
		if (document.forms["joinForm"]["name"].value == "") {
			alert("이름을 입력해주세요.");
			return false;
		} else if (document.forms["joinForm"]["nickname"].value == "") {
			alert("별명을 입력해주세요.");
			return false;
		} else if (document.forms["joinForm"]["email"].value == "") {
			alert("이메일을 입력해주세요.");
			return false;
		}
	}
</script>