select
	B.BOARD_SEQ
	 ,B.USER_SEQ
	 ,U.USER_NAME
	 ,B.TITLE
	 ,B.CONTENT
	 ,IFNULL((CASE
		   WHEN INSTR(DATE_FORMAT(B.LAST_UPDATE, '%Y-%m-%d %p %h:%i'), 'PM') > 0
		   THEN REPLACE(DATE_FORMAT(B.LAST_UPDATE, '%Y-%m-%d %p %h:%i'), 'PM', '오후')
		   ELSE REPLACE(DATE_FORMAT(B.LAST_UPDATE, '%Y-%m-%d %p %h:%i'), 'AM', '오전')
		END),
		(CASE
		   WHEN INSTR(DATE_FORMAT(B.FIRST_UPDATE, '%Y-%m-%d %p %h:%i'), 'PM') > 0
		   THEN REPLACE(DATE_FORMAT(B.FIRST_UPDATE, '%Y-%m-%d %p %h:%i'), 'PM', '오후')
		   ELSE REPLACE(DATE_FORMAT(B.FIRST_UPDATE, '%Y-%m-%d %p %h:%i'), 'AM', '오전')
		END)) as UPLOAD_DATE
	 ,B.IP
from
BOARD B
left join USER U
on U.USER_SEQ = B.USER_SEQ
where BOARD_SEQ = 3;


select * from BOARD;