DROP INDEX users_nameIdx;
/
DROP INDEX user_group_dateIdx;
/
CREATE INDEX users_nameIdx ON users(lastName,firstName);
/
CREATE INDEX user_group_dateIdx ON user_group(dateAdded DESC);
/
