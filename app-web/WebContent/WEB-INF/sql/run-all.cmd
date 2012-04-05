mysql -u root -p12870668 < create-bsframework.sql.txt

for %%i in (create-sys*.sql.txt) do mysql -u root -p12870668 < %%i

mysql -u root -p12870668 < create-bscommon.sql.txt
