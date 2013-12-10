#!/bin/sh

if [ -n "$1" ]; then
echo 'Borrando tablas... (erase-tables.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
echo 'Borrando tablas desestimadas... (erase-deprecated-object.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
echo 'Creando tablas de plataforma... (create-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
echo 'Creando tablas de sistema... (create-timectrl.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-timectrl.sql.txt
	
echo 'Creando reglas de plataforma...(rules-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
echo 'Creando reglas de sistema...(rules-timectrl.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-timectrl.sql.txt
	
echo 'Creando funciones de sistema...'
	for i in $(ls fn*.sql.txt); do echo "> Running $i..."; mysql -D$1 -u root -padmin < $i; done
echo 'Creando procedimientos de sistema...'
	for i in $(ls sp*.sql.txt); do echo "> Running $i..."; mysql -D$1 -u root -padmin < $i; done
	
echo 'Creando datos básicos de plataforma...(data-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt
	
echo 'Creando datos básicos del sistema...(data-timectrl.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-timectrl.sql.txt
	
echo 'Creando opciones de menú...(data-menu.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-menu.sql.txt
	
#	mysql -D$1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
	
else
	echo "Error, falta especificar nombre de la base de datos como parametro"
fi

