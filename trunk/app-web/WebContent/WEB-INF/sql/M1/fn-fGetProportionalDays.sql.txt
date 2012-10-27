DROP FUNCTION IF EXISTS fGetProportionalDays;

DELIMITER $$
CREATE FUNCTION fGetProportionalDays(vFrom DATE, vTo DATE) RETURNS DOUBLE
BEGIN
	DECLARE vOut DOUBLE DEFAULT 0;
	DECLARE vDaysForYear, vDaysDiff INTEGER DEFAULT 15;
	DECLARE vDaysOfYear INTEGER DEFAULT 364;
	
	SELECT	cValue
	INTO	vDaysForYear
	FROM	tParameter
	WHERE	cKey = 'DAYS_FOR_YEAR';	
	
	SET		vDaysDiff = DateDiff(vTo, vFrom);
	
	IF(YEAR(vFrom) = YEAR(vTo)) THEN
		IF(YEAR(vFrom) MOD 4 = 0) THEN
			SET vDaysOfYear = 365;
		END IF;
	END IF;
	
	SET	vOut = (vDaysDiff * vDaysForYear) / vDaysOfYear;
	
	RETURN vOut;
END$$ 

DELIMITER ;