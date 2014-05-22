#!/bin/sh

mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDomain(cName, cAlias) VALUES('$1', '$1');SET @domainId = LAST_INSERT_ID();INSERT INTO bsframework.tUser(cMail, cName, cPassword, cAdmin) VALUES('admin', 'Administrador', md5('admin'), 1);SET @userId = LAST_INSERT_ID();INSERT INTO bsframework.tR_UserDomain(cUser, cDomain) VALUES(@userId, @domainId);INSERT INTO bsframework.tDomainAttribute(cDomain, cKey, cName, cValue) VALUES(@domainId, 'database.driver', 'Driver', 'org.gjt.mm.mysql.Driver');INSERT INTO bsframework.tDomainAttribute(cDomain, cKey, cName, cValue) VALUES(@domainId, 'database.server', 'Server', 'localhost');INSERT INTO bsframework.tDomainAttribute(cDomain, cKey, cName, cValue) VALUES(@domainId, 'database.database', 'Database', '$1');INSERT INTO bsframework.tDomainAttribute(cDomain, cKey, cName, cValue) VALUES(@domainId, 'database.username', 'User', 'root');INSERT INTO bsframework.tDomainAttribute(cDomain, cKey, cName, cValue) VALUES(@domainId, 'database.password', 'Password', 'admin');"

mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('STRING', 'String');"
mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('DOUBLE', 'Double');"
mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('INTEGER', 'Integer');"
mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('TIMESTAMP', 'Timestamp');"
mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('BOOLEAN', 'Boolean');"
mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "INSERT INTO bsframework.tDataType(cKey, cName) VALUES('LONG', 'Long');"

mysql -D$1 -t -u root -padmin --default-character-set=utf8 -e "SELECT cId INTO @booleanId FROM bsframework.tDataType WHERE cKey = 'BOOLEAN';INSERT INTO bsframework.tConfig(cKey, cLabel, cValue, cDataType) VALUES('USE_BOOTSTRAP', 'Indica el uso de Bootrsrat en el sitio', 'true', @booleanId);"


