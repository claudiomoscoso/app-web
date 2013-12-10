DROP FUNCTION IF EXISTS fGetLastDate;

DELIMITER $$


CREATE FUNCTION fGetLastDate(date1 DATE, date2 DATE) RETURNS DATE
BEGIN
	DECLARE vOut DATE;
	IF (date1 > date2) THEN
		SET vOut = date1;
	ELSE
		SET vOut = date2;
	END IF;

	RETURN vOut;
END$$
DELIMITER ;