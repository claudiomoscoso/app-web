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
mysql -u root -p12870668 < delete-tables.sql.txt
mysql -u root -p12870668 < create-bsframework.sql.txt
mysql -u root -p12870668 < create-bscommon.sql.txt

for %%i in (create-db*.sql.txt) do mysql -u root -p12870668 < %%i
for %%i in (create-rules*.sql.txt) do mysql -u root -p12870668 < %%i
for %%i in (create-sp*.sql.txt) do mysql -u root -p12870668 < %%i
for %%i in (create-data*.sql.txt) do mysql -u root -p12870668 < %%i

mysql -u root -p12870668 < create-menu.sql.txt

