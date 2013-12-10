DROP FUNCTION IF EXISTS fGetEndContract;

DELIMITER $$

CREATE FUNCTION fGetEndContract(vEmployee BIGINT) RETURNS DATE
BEGIN
	DECLARE	 vOut DATE;
	
	SELECT	CASE c.cKey
			WHEN 'UND' THEN NOW()
			ELSE cEndContract END
	INTO	vOut
	FROM	tEmployee AS a
	LEFT JOIN tAgreement AS b ON b.cEmployee = a.cId
	LEFT JOIN tContractType AS c ON b.cContractType = c.cId
	WHERE	a.cId = vEmployee;
	
	RETURN vOut;
END$$

DELIMITER ;