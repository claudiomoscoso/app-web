DROP FUNCTION IF EXISTS fBusinessDate;
DROP FUNCTION IF EXISTS fHoliday;

DELIMITER $$

/* Funcion que retorma la fecha que resulta de la fecha inicial más los días que se pasan por parámetro */
CREATE FUNCTION fBusinessDate(vStartDay DATE, vCountDays INTEGER) RETURNS DATE
BEGIN
	DECLARE vIndexDay DATE;
	DECLARE vIndex INTEGER DEFAULT 0;
	DECLARE vDayOfWeek INTEGER DEFAULT -1;
	
	SET vCountDays = vCountDays - 1;
	
	SET vIndexDay = vStartDay;
	WHILE vIndex < vCountDays DO
		SET vIndexDay = vIndexDay + INTERVAL 1 DAY;
		SET vIndex = vIndex + 1;
		
		SET vDayOfWeek = DAYOFWEEK(vIndexDay);
		
		IF(vDayOfWeek = 1 OR vDayOfWeek = 7 OR fHoliday(vIndexDay)) THEN
			SET vIndex = vIndex - 1;
		END IF;
	END WHILE;
	
	RETURN vIndexDay;
END$$

/* Funcion que retorna si una fecha es feriada o no */
CREATE FUNCTION fHoliday(vDate DATE) RETURNS BOOLEAN
BEGIN
	DECLARE vOut BOOLEAN DEFAULT FALSE;
	
	IF(EXISTS(SELECT cDate FROM tFiscalDate WHERE cDate = vDate)) THEN
		SET vOut = TRUE; 
	END IF;
	
	RETURN vOut;
END$$

DELIMITER ;