DROP TABLE users;

CREATE TABLE users(
	user_id					 VARCHAR2(30) NOT NULL PRIMARY KEY,					
	name 					 VARCHAR2(50),		
	password 				 VARCHAR2(30),		
	role 					 VARCHAR2(20),		
	gender 					 VARCHAR2(10),		
	phone 					 VARCHAR2(30),		
	birth 					 VARCHAR2(30),		
	PROFILE_IMAGE_FILE		 VARCHAR2(500),
	email 					 VARCHAR2(50),
	children 				 NUMERIC,
	qr_code					 VARCHAR2(500),
	reg_date 				 DATE
);


COMMIT;



