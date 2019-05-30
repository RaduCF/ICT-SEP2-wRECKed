SET SCHEMA 'TaskStatistics';

CREATE TABLE Task(
	, Name_			VARCHAR(60) PRIMARY KEY
	, AverageUsage	DECIMAL(10,2)
);


CREATE TABLE Usage(
	  UserID		VARCHAR(256)	NOT NULL
	, TaskName		VARCHAR(16)		NOT NULL REFERENCES Task(Name_)
	, Hours			DECIMAL(10,2)   NOT NULL
);


CREATE FUNCTION AverageHours()
	RETURNS TRIGGER AS $$
	BEGIN
		UPDATE Task r SET AverageUsage = (SELECT AVG(Hours) FROM Usage u JOIN Task s ON u.TaskID=s.Name_ WHERE r.Name_ = u.TaskName);
		RETURN NULL;
	END;
	$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateAverageHours
AFTER INSERT OR UPDATE ON Usage
FOR EACH ROW
EXECUTE PROCEDURE AverageHours();

SELECT * FROM Usage;
SELECT * FROM Task;


