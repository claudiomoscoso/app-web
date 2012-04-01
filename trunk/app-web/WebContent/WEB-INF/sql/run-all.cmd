mysql -u root -p12870668 < create-bsframework.sql.txt
mysql -u root -p12870668 < create-bscommon.sql.txt

for %%i in (create-sys*.sql.txt) do mysql -u root -p12870668 < %%i
