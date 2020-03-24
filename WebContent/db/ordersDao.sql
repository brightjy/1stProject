-- 전체 오더 리스트 보기(startRow, endRow) - 관리자용
SELECT*FROM ORDERS ORDER BY oDATE DESC;
SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM ORDERS ORDER BY oDATE DESC)A) WHERE RN BETWEEN 2 AND 4;

-- 전체 오더 리스트 보기(startRow, endRow) - 사용자용
SELECT*FROM ORDERS WHERE mID='aaa' ORDER BY oDATE DESC; -- 주문을 여러개 했을 수 있으니까
SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM ORDERS WHERE mID='aaa' ORDER BY oDATE DESC)A) WHERE RN BETWEEN 2 AND 4;

-- 전체 오더 개수 - 관리자용
SELECT COUNT(*) TOTCNT FROM ORDERS;

-- 전체 오더 개수 - 사용자용
SELECT COUNT(*) TOTCNT FROM ORDERS WHERE mID='aaa';

-- 주문하기(신규 오더 넣기)
INSERT INTO ORDERS (oID, oDate, mID, oNAME, oPHONE, oADDRESS, oPAYMENT)
    VALUES (ORDERS_SEQ.NEXTVAL, SYSDATE, 'aaa', '받는사람', '010-1111-1111', '서울시', 'CARD');

-- 오더 수정(송장 번호만 추가 가능, 오더번호로 조회) - 관리자용
UPDATE ORDERS
    SET oINVOICE = '1234567'
    WHERE oID='1';
    
-- mId로 오더 디티오 가져오기
SELECT * FROM ORDERS WHERE mID='bbarkji';


-- 오더 취소 - 사용자용 (송장이 없는 오더만 취소 가능)
DELETE ORDERS WHERE mID='aaa' AND oINVOICE=NULL;

SELECT*FROM ORDERS WHERE oID='1';

COMMIT;

SELECT ORDERS_SEQ.CURRVAL oID FROM DUAL;