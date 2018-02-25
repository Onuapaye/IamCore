/*delete from tbluser;
drop table tbluser;
drop table tblIdentityBismark;*/

CREATE table tblIdentityBismark(
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT ID_PK PRIMARY KEY,
	firstName VARCHAR(45) NOT NULL,
	lastName VARCHAR(45) NOT NULL,
	uniqueID VARCHAR(10) NOT NULL,
	email VARCHAR(45)
);

CREATE table tblUser(
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT uID_PK PRIMARY KEY,
	userName VARCHAR(15) NOT NULL,
	userPassword VARCHAR(15) NOT NULL,
	userGroup SMALLINT NOT NULL,
	userEnableLogin SMALLINT NOT NULL
);