-- 관리자 로그인(aId, aPw)
SELECT*FROM ADMIN WHERE aID='admin' AND aPW='111';

-- 관리자 등록
INSERT INTO ADMIN (aID, aPW, aName) VALUES ('admin1','111', '관리자');

-- 관리자 리스트 조회
SELECT*FROM ADMIN;

-- aID로 dto 가져오기
SELECT*FROM ADMIN WHERE aID='admin';

-- 관리자 수정
UPDATE ADMIN SET aNAME='관리자' WHERE AID='admin3';

DELETE ADMIN WHERE AID='admin';

COMMIT;
