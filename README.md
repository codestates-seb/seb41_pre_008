# seb41_pre_008

### 2인분만 하조
FE : 소다솜 장예은 김동현

BE : 이상유 박지윤

팀장 : 이상유

-------

# 프로젝트 소개
개발자들이 프로그래밍에 대한 질문을 하고 답변을 주고 받는 사이트 'stack overflow' clone Project✨

배포링크 : http://sebpre008.s3-website.ap-northeast-2.amazonaws.com/

# 기술스택
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/styledcomponents-DB7093?style=for-the-badge&logo=styled-components&logoColor=black">

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

# 기능구현 화면
1. 로그인 / 로그아웃 <br/>
![스크린샷 2023-01-02 오후 4 47 15](https://user-images.githubusercontent.com/67542755/210205254-002c6063-ee26-4c0d-94bb-30517e9a8c17.png)
  - 회원 이메일은 중복이 가능하지 않습니다.
  - 비밀번호는 문자와 숫자가 최소 1개 이상이어야 합니다.
  - 로그인에 성공했을 때, 메인페이지로 돌아가며 유저 권한이 필요한 작업은 3분 안에 요청해야 합니다.
  - 로그인에 실패하면, 메인페이지로 돌아가지 않습니다.
2. 회원가입 <br/>
![스크린샷 2023-01-02 오후 4 47 22](https://user-images.githubusercontent.com/67542755/210205261-63d2d0cd-ff0e-49ad-943c-702430622078.png)
  - 닉네임은 중복이 가능합니다.
  - 회원가입에는 제한이 없습니다.
  - 회원가입이 실패하면, 이메일이 중복인지 확인해야 합니다. (유저가)
3. 질문 Create/Update/Find/Delete <br />

 ![화면 기록 2023-01-02 오후 6 45 00](https://user-images.githubusercontent.com/107921099/210215968-5a22450b-e795-497f-ba72-5902f418d2f6.gif)

  - Home, Questions 메뉴에서 All Questions 전체 질문 조회가 가능합니다.
  - 한 페이지에 10개의 질문을 볼 수 있습니다. (페이지네이션)
  - 질문의 제목을 클릭하면 질문 상세 페이지로 이동할 수 있습니다.
  - 로그인 한 사용자만 질문을 등록할 수 있습니다.
  - 삭제된 질문은 조회가 되지 않습니다.
  
  
  ![화면 기록 2023-01-02 오후 6 45 31](https://user-images.githubusercontent.com/107921099/210216361-6ebe404e-94ce-47e0-92f2-dae415d5eef1.gif)  
  
  - 질문 등록 시 정해진 글자 수에 맞게 작성해야 합니다.
  - 질문 수정 시 정해진 글자 수에 맞게 작성해야 합니다.
  - 등록 버튼을 누르면 질문이 생성됩니다.
  - title / problem / expecting / tags이 비었거나 정해진 글자 수 미만일 경우 등록 버튼을 누를 시, 안내 텍스트가 나타납니다.
  - tags 입력 후 엔터 버튼을 누르면 입력한 tag가 입력창에 등록됩니다.
  - Discard draft 버튼을 누르면 입력한 텍스트가 초기화 됩니다.

4. 답변 Create/Update/Find/Delete <br />
![스크린샷 2023-01-02 오후 5 25 32](https://user-images.githubusercontent.com/73947931/210208792-0f6b2180-c459-4c2b-82f4-2149e36c5479.png)
![스크린샷 2023-01-02 오후 5 05 21](https://user-images.githubusercontent.com/73947931/210208851-221d22d1-9f19-4abf-904a-00788b85a9bc.png)
  - 답변 등록 시 정해진 글자 수에 맞게 작성해야 합니다.
  - 답변 수정 시 정해진 글자 수에 맞게 작성해야 합니다.
  - 질문이 삭제되면 자동으로 답변이 삭제됩니다.
5. 태그 Create/Find/Delete <br />
![스크린샷 2023-01-02 오후 5 06 46](https://user-images.githubusercontent.com/73947931/210208659-b38cc51f-2f3d-4e1c-81bb-a165febf485e.png)
  - Tags 페이지에 들어가면 등록된 태그들을 조회할 수 있습니다.
  - 검색창에서 태그를 검색할 수 있습니다.
6. 투표수 <br />
  - 좋아요
      - 질문과 답변에 투표를 할 수 있습니다. 원하는 답변에 위 화살표 버튼을 누르면 전체 좋아요 수가 증가합니다. 
      - 이미 누른 회원도 더 누를 수 있습니다.
  - 싫어요
      - 질문과 답변에 투표를 할 수 있습니다. 원하는 답변에 아래 화살표 버튼을 누르면 전체 좋아요 수가 증가합니다. 
      - 중복에 관계없이 누를 때마다 감소합니다.
