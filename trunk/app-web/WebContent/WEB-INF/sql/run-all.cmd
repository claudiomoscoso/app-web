@echo off
rem borrar tablas OK
rem crear tablas OK 
rem crear vistas, constrains e indices OK
rem crear procedimientos almacenados OK
rem cargar datos escenciales OK
rem crear menu para todos los módulos
rem cargar datos para prueba
@echo on

cls
mysql -u root -padmin < delete-tables.sql.txt
mysql -u root -padmin < create-bsframework.sql.txt
mysql -u root -padmin < create-bscommon.sql.txt

for %%i in (create-db*.sql.txt) do mysql -u root -padmin < %%i
for %%i in (create-rules*.sql.txt) do mysql -u root -padmin < %%i
for %%i in (create-sp*.sql.txt) do mysql -u root -padmin < %%i
for %%i in (create-data*.sql.txt) do mysql -u root -padmin < %%i

mysql -u root -padmin < create-menu.sql.txt

