


CREATE TABLE IF NOT EXISTS  dept (
	groupName VARCHAR(100),
	departmentName VARCHAR(100),
	title VARCHAR(100),
 	recruitment VARCHAR(1000),
 	recruitmentDetail VARCHAR(1000),
 	recruitee VARCHAR(100),
 	skills VARCHAR(100),
 	contact VARCHAR(100),
 	PRIMARY KEY (departmentName, recruitment)
 );

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  email varchar(70) NOT NULL,
  password varchar(60) NOT NULL,
  enabled tinyint(1) NOT NULL,
  authority varchar(50) NOT NULL,
  tempkey varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS profile(
 id INT Primary KEY,
 name VARCHAR(50),
 appealing_point VARCHAR(200),
 duties VARCHAR(200),
 challenge VARCHAR(200),
 work_history VARCHAR(200),
 skill VARCHAR(200),
 hobby VARCHAR(200)
);

