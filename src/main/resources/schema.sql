

CREATE TABLE dept (
	groupName VARCHAR(100),
	departmentName VARCHAR(100),
 	recruitment VARCHAR(1000),
 	recruitee VARCHAR(100),
 	skills VARCHAR(100),
 	contact VARCHAR(100),
 	PRIMARY KEY (departmentName, recruitment)
 );