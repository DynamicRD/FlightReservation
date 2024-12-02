# Flight-Reservation-Project
자바 + Oracle SQL 비행기 예약 관리 프로그램


## 🛩 프로젝트 소개
비행기 예약,비행편,항송사,고객을 관리하는 프로그램


## 🕰️ 개발 기간
24.11.28목 - 24.12.02월


### ⚙️ 개발 환경
- `Java`
- `JDK-21.0.4`
- **IDE** : Eclipse
- **Database**: Oracle DB (23c Express Edition)
- **SQL Developer**: Version 23.1


### 📌 주요 기능
- 데이터 정렬, 수정, 입력 ,삭제 기능
- DB와 자바 데이터 연동
- 삭제시 자동 백업하는 테이블 생성
- 항공사 이름과 등급을 출력하는 펑션 생성
- 구매한 티켓수에 따라 마일리지를 증가시키는 트리거 생성
- 입력한 항공사번호에 따라 금액을 인상하는 프로시저 생성
- 마일리지에 따라 고객정보를 출력하는 펑션 생성
- 테이블들을 조인해서 출력하는 기능


### 📊 ERD 다이어그램
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/FlightReservationERD.png" width="800"/>


### 💻 주요 실행 화면
메인 화면</br>
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%891.png"/>
</br></br>
마일리지에 따라 회원등급을 출력
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%892.png"/>
</br></br>
해당되는 항공사번호의 비행편들 10% 인상
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%893.png"/>
</br></br>
모든 테이블을 조인해서 출력
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%894.png"/>
