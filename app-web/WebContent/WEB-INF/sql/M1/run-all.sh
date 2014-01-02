#!/bin/sh

if [ -n "$1" ]; then
echo 'Borrando tablas... (erase-tables.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
echo 'Borrando tablas desestimadas... (erase-deprecated-object.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
echo 'Creando tablas de plataforma... (create-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
#echo 'Creando tablas de plataforma... (create-bsframework.sql.sh)'	
#	./create-bsframework.sql.sh $1
	
echo 'Creando tablas de sistema... (create-bsee.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-bsee.sql.txt
	
echo 'Creando reglas de plataforma...(rules-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
echo 'Creando reglas de sistema...(rules-bsee.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-bsee.sql.txt
	
echo 'Creando funciones de sistema...'
	for i in $(ls fn*.sql.txt); do echo "> Running $i..."; mysql -D$1 -u root -padmin < $i; done
echo 'Creando procedimientos de sistema...'
	for i in $(ls sp*.sql.txt); do echo "> Running $i..."; mysql -D$1 -u root -padmin < $i; done
	
echo 'Creando datos básicos de plataforma...(data-bsframework.sql.sh)'
	#mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt
	./data-bsframework.sql.sh $1
	
echo 'Creando datos básicos del sistema...(data-bsee.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-bsee.sql.txt
	
echo 'Creando opciones de menú...(data-menu-bsee.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-menu-bsee.sql.txt
	
echo 'Creando opciones de menú...(data-menu-bsframework.sql.txt)'
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-menu-bsframework.sql.txt

#	mysql -D$1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
	
else
	echo "Error, falta especificar nombre de la base de datos como parametro"
fi

