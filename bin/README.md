# jspCommunity_y

## 완료 리스트
- [O] 프레임워크 기초
- [O] 게시글 CRUD
- [O] 메인페이지
- [O] 로그인, 로그아웃 상태 관련 권한 체크
- [O] 게시물 추가, 수정, 삭제 관련 권한 체크
- [O] 비밀번호 암호화
- [O] 아이디, 비밀번호 찾기
- [O] 임시 비밀번호 생성
- [O] 메일 전송
- [O] 게시물 검색
- [O] 게시물 페이징
- [O] 임시 비밀번호 사용시 경고창 표시
- [O] 회원정보 수정
- [O] 토스트 UI 에디터 적용
- [O] 임시비번 사용중이면 비번변경 알림창 표시
- [O] 비번 사용일이 90일이 경과하면 비번변경 알림창 표시
- [O] 댓글 작성
- [O] 게시글 좋아요, 싫어요
- [O] 한방 배포
- [O] 댓글 삭제

## 할일 리스트
- 댓글 수정(하이라이팅)
- 댓글 좋아요
- 댓글의 좋아요 개수 표시
- 게시물 조회수
 * 자바스크립트 웹 스토리지
 * 사용자가 페이지에 10초이상 머무른다면
   * /user/article/increaseHit?id=3
   * jquery, ajax

- [] 접속 기종별 페이지 디자인
- [] 커뮤니티 디자인

## setting
- 설정 -> project facets -> dynamic web module(tomcat) 설정
- /META-INF/config.json 생성 -> { "gmainId":"", "gmainPw":"" } 작성

# 메이븐 settings.xml 템플릿
- C/users/user/.m2/settings.xml(없으면 생성)
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>devly__manager_text</id>
            <username>톰캣 웹 관리자의 계정명</username>
            <password>톰캣 웹 관리자의 계정비밀번호</password>
        </server>
    </servers>
</settings>
```

## 명령어
c:\xampp\mysql\bin\mysqldump.exe --databases -u sbsblog -p jspCommunity > C:\work\sts-4.8.0.RELEASE-workspace\jspCommunity_y\current.sql