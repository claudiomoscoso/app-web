DROP FUNCTION if exists fInsertPeriod;
DELIMITER $$

/***********************
Crea o actualiza la tabla tPeriod si no existe el registro.
*/
CREATE FUNCTION fInsertPeriod(pDate DATE) RETURNS BIGINT
BEGIN
	DECLARE vCurrentUF, vOvertimeFactor, vGratificationFactor, vMinSalary,
			vLimitIPS, vLimitInsurance, vLimitHealth, vUTM DOUBLE DEFAULT 0;
	DECLARE vDaysForYear INTEGER DEFAULT 0;
	DECLARE vId, vPeriodStatus BIGINT DEFAULT -1;
	DECLARE vDate DATE;

	SET vDate = CONCAT(YEAR(pDate), '-', MONTH(pDate), '-', '1');
	
	IF (EXISTS(SELECT cId FROM tPeriod WHERE cDate = vDate)) THEN
		SELECT	cId
		INTO	vId
		FROM	tPeriod
		WHERE	cDate = vDate;
	ELSE
		SET vCurrentUF =			(SELECT cValue FROM tParameter WHERE cKey = 'CURRENT_UF');
		SET vOvertimeFactor =		(SELECT cValue FROM tParameter WHERE cKey = 'OVERTIME_FACTOR');
		SET vGratificationFactor =	(SELECT cValue FROM tParameter WHERE cKey = 'GRATIFICATION_FACTOR');
		SET vMinSalary =			(SELECT cValue FROM tParameter WHERE cKey = 'BASE_SALARY');
		SET vLimitIPS =				(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_IPS');
		SET vLimitInsurance =		(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_INSURANCE');
		SET vLimitHealth =			(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_HEALTH');
		SET vUTM =					(SELECT cValue FROM tParameter WHERE cKey = 'UTM');
		SET vDaysForYear =			(SELECT cValue FROM tParameter WHERE cKey = 'DAYS_FOR_YEAR');
		
		SELECT	cId, cPeriodStatus
		INTO	vId, vPeriodStatus
		FROM	tPeriod
		WHERE	cDate = vDate;
	
		IF (vId > -1 AND vPeriodStatus = 2) THEN
			SET vId = vId; 
			/*TODO: refresh period */
			
		ELSE
			INSERT INTO tPeriod(cDate, cUF, cOvertimeFactor, cGratificationFactor, cMinSalary,
					cLimitGratification, cLimitIPS, cLimitInsurance, cLimitHealth,
					cUTM, cDaysForYear) 
			VALUES(vDate, vCurrentUF, vOvertimeFactor, vGratificationFactor, vMinSalary,
					cMinSalary * cGratificationFactor, vLimitIPS, vLimitInsurance, vLimitHealth,
					vUTM, vDaysForYear);
			SET vId = LAST_INSERT_ID();
		END IF;

	END IF;
	RETURN vId;
END$$

DELIMITER ;
