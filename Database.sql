SET SCHEMA 'TaskStatistics';

DROP TABLE Task CASCADE;
CREATE TABLE Task(
	  Name_			VARCHAR(60) PRIMARY KEY
	, AverageUsage	FLOAT(24)
);

DROP TABLE Usage CASCADE;
CREATE TABLE Usage(
	  UserID		VARCHAR(256)	NOT NULL
	, TaskName		VARCHAR(64)		NOT NULL REFERENCES Task(Name_)
	, Hours			FLOAT(24)   NOT NULL
);


CREATE TABLE BugReport(
	  UserID		VARCHAR(256)	NOT NULL
	, report		VARCHAR(1024) 
);

CREATE FUNCTION AverageHours()
	RETURNS TRIGGER AS $$
	BEGIN
		UPDATE Task r SET AverageUsage = (SELECT AVG(Hours) FROM Usage u JOIN Task s ON u.TaskName=s.Name_ WHERE r.Name_ = u.TaskName);
		RETURN NULL;
	END;
	$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateAverageHours
AFTER INSERT OR UPDATE ON Usage
FOR EACH ROW
EXECUTE PROCEDURE AverageHours();

SELECT * FROM Usage;
SELECT * FROM Task;

INSERT INTO Usage VALUES ('edsffsdf', 'TestAVG', 23.4);
INSERT INTO Task VALUES('TestAVG');