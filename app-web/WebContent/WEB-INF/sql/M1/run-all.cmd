cls
@echo off
IF "%1" == "" GOTO error
@echo on

rem mysql -D%1 -t -u root -padmin --default-character-set=utf8 < ..\M2\erase-tables.sql.txt
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < create-bsee.sql.txt

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
rem mysql -D%1 -t -u root -padmin --default-character-set=utf8 < rules-remcon.sql.txt

for %%i in (fn-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i
for %%i in (sp-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < data-bsee.sql.txt
call data-bsframework.sql.cmd %%1
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < data-menu-bsee.sql.txt

rem mysql -D%1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt

@echo off
goto fin

:error
@echo off
echo No se indico nombre de la base de datos, ejecute: 
echo $ run-all remcon

:fin
@echo off
echo *** FIN ***