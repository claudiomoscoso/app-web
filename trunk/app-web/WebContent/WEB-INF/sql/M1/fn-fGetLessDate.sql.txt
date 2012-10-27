DROP FUNCTION IF EXISTS fGetLessDate;

DELIMITER $$

CREATE FUNCTION fGetLessDate(date1 DATE, date2 DATE) RETURNS DATE
BEGIN
	DECLARE vOut DATE;
	IF (date1 < date2) THEN
		SET vOut = date1;
	ELSE
		SET vOut = date2;
	END IF;

	RETURN vOut;
END$$
DELIMITER ;