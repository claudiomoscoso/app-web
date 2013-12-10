#!/bin/sh

if [ -n "$1" ]; then
	for i in $(ls fn*.sql.txt); do echo "Running $i..."; mysql -D$1 -u root -padmin < $i; done
	for i in $(ls sp*.sql.txt); do echo "Running $i..."; mysql -D$1 -u root -padmin < $i; done
	
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
	
	
else
	echo "error"
fi

