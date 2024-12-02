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
- 구매한 티켓수에 따라 마일리지를 증가시키는 트리거 생성
- 입력한 항공사번호에 따라 금액을 인상하는 프로시저 생성
- 마일리지에 따라 고객정보를 출력하는 펑션 생성
- 테이블들을 조인해서 출력하는 기능


### ⌨ 구현 기능
- **FUNCTION을 이용한 등급 출력**
  - 고객의 마일리지의 크기에 따라 등급을 출력하는 `CUSTOMER_GRADE_FUNC` 펑션 작성
  - 펑션 정의 코드:
    ```sql
   CREATE OR REPLACE FUNCTION CUSTOMER_GRADE_FUNC(VID IN CUSTOMER.ID%TYPE) RETURN VARCHAR2
IS
    VMESSAGE VARCHAR2(100);
    CUSTOMER_RT CUSTOMER%ROWTYPE;
BEGIN
    SELECT * INTO CUSTOMER_RT FROM CUSTOMER WHERE ID = VID;
    IF(CUSTOMER_RT.MILEAGE <= 1000) THEN
    VMESSAGE := CUSTOMER_RT.NAME||' 고객님은 실버등급입니다.';
    ELSIF(CUSTOMER_RT.MILEAGE <= 5000) THEN
    VMESSAGE := CUSTOMER_RT.NAME||' 고객님은 골드등급입니다.';
    ELSIF(CUSTOMER_RT.MILEAGE <= 10000) THEN
    VMESSAGE := CUSTOMER_RT.NAME||' 고객님은 플래티넘등급입니다.';
    ELSE
    VMESSAGE := CUSTOMER_RT.NAME||' 고객님은 다이아등급입니다.';
    END IF;
    RETURN VMESSAGE;
END;
/
    ```

- **PROCEDURE을 이용한 항공편 인상**
  - 지정한 항공번호의 항공편들의 금액을 10% 인상하는 `FLIGHT_PRICE_PROC` 프로시저 작성
  - 프로시저 정의 코드:
    ```sql
    CREATE OR REPLACE PROCEDURE FLIGHT_PRICE_PROC(VANO IN FLIGHT.A_NO%TYPE)
    IS
    BEGIN
    UPDATE FLIGHT SET PRICE = PRICE * 1.1 WHERE A_NO = VANO;
END;
/
    ```
    
- **TRIGGER을 이용한 마일리지 증가**
  - 구매한 비행편의 매수마다 마일리지를 100 증가시키는 `UPDATE_CUSTOMER_MILEAGE_TRIGGER` 트리거 작성
  - 트리거 정의 코드:
    ```sql
    CREATE OR REPLACE TRIGGER UPDATE_CUSTOMER_MILEAGE_TRIGGER
    AFTER INSERT OR UPDATE OF QUANTITY ON RESERVATION
    FOR EACH ROW
    WHEN (NEW.QUANTITY > NVL(OLD.QUANTITY, 0))
    BEGIN
    UPDATE CUSTOMER
    SET MILEAGE = MILEAGE + (:NEW.QUANTITY - NVL(:OLD.QUANTITY,0)) * 100
    WHERE ID = :NEW.ID;
END;
/
    ```

- **JOIN을 이용한 모든 테이블 출력**
  - JOIN을 사용해 모든 테이블을 출력하는 쿼리문인 변수 RESERVATION_JOIN 작성
  - 자바 코드:
    ```java
     public final String RESERVATION_JOIN = "SELECT R.NO AS NO, R.FLIGHT_NO AS FLIGHT_NO,"
			+ " F.A_NO AS A_NO, R.ID AS ID, C.PASSWD AS PASSWD, C.NAME AS NAME, C.PASSPORT_NUMBER AS PASSPORT_NUMBER,"
			+ " C.MILEAGE AS MILEAGE, F.SEAT AS SEAT, F.DEPARTURE_DATE AS DEPARTURE_DATE, F.DEPARTURE_AIRPORT AS DEPARTURE_AIRPORT,"
			+ " F.ARRIVAL_AIRPORT AS ARRIVAL_AIRPORT, R.QUANTITY AS QUANTITY, F.PRICE AS PRICE "
			+ "FROM RESERVATION R INNER JOIN CUSTOMER C ON R.ID=C.ID INNER JOIN FLIGHT F ON R.FLIGHT_NO=F.NO";
    ```


### 📊 ERD 다이어그램
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/FlightReservationERD.png" width="800"/>


### 💻 실행 화면
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%891.png"/>
</br>
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%892.png"/>
</br>
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%893.png"/>
</br>
<img src="https://github.com/DynamicRD/FlightReservation/blob/dev/db/%EC%8B%A4%ED%96%894.png"/>
