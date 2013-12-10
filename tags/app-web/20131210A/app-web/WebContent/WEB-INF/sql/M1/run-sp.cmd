@echo off
IF "%1" == "" GOTO error

@echo on
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
for %%i in (fn-*.sql.txt) do mysql -D%1 -u root -t -padmin --default-character-set=utf8 < %%i
for %%i in (sp-*.sql.txt) do mysql -D%1 -u root -t -padmin --default-character-set=utf8 < %%i

mysql -D%1 -u root -t -padmin --default-character-set=utf8 < testSP.sql.txt

@echo off
goto fin

:error
@echo off
echo No se indico nombre de la base de datos, ejecute: 
echo $ run-all remcon


:fin
@echo off
echo *** FIN ***