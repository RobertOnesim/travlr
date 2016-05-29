DROP TABLE user_group cascade constraints;
/
DROP TABLE user_history;
/
DROP TABLE users cascade constraints;
/
DROP TABLE groups cascade constraints;
/
DROP TABLE search_history cascade constraints;
/

CREATE TABLE users(
  ID_user VARCHAR2(30) PRIMARY KEY,
  firstName VARCHAR2(30) NOT NULL,
  lastName VARCHAR2(30) NOT NULL,
  facebook VARCHAR2(30),
  google VARCHAR2(30),
  lastActivity DATE NOT NULL
  )
/
CREATE TABLE groups(
  ID_group NUMBER PRIMARY KEY,
  groupName VARCHAR2(30) NOT NULL,
  ID_user_admin VARCHAR2(30) NOT NULL,
  numberOfMembers NUMBER NOT NULL,
  lastActivity DATE NOT NULL,
  FOREIGN KEY(ID_user_admin) REFERENCES users(ID_user)
  )
/
CREATE TABLE user_group(
  ID_user VARCHAR2(30) NOT NULL,
  ID_group NUMBER not null,
  dateAdded DATE NOT NULL,
  primary key (id_user,id_group),
  FOREIGN KEY(ID_user) REFERENCES users(ID_user),
  FOREIGN KEY(ID_group) REFERENCES groups(ID_group)
  )
/

CREATE TABLE user_history(
  ID_history NUMBER PRIMARY KEY,
  ID_user VARCHAR2(30) NOT NULL,
  departure_id VARCHAR2(30),
  arrival_id VARCHAR2(30),
  count INTEGER,
  FOREIGN KEY(ID_user) REFERENCES users(ID_user)
  )
/

CREATE TABLE search_history(
  ID_history NUMBER PRIMARY KEY,
  departure_id VARCHAR2(30),
  arrival_id VARCHAR2(30),
  searched_month number(2),
  count INTEGER
  )
/

