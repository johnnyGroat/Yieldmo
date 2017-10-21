
create database yieldmo;

create table yieldmo.location(
	LocationId INT auto_increment PRIMARY KEY,
	Country CHAR(3),
    State VARCHAR(300),
    City VARCHAR(300),
    ZipCode VARCHAR(30)
);

create table yieldmo.site(
	SiteId INT auto_increment PRIMARY KEY,
    SiteName VARCHAR(300),
    SiteVertical VARCHAR(300)
);

create table yieldmo.logs(
	LogId INT auto_increment PRIMARY KEY,
    ViewTimeStamp TIMESTAMP,
    CookieID VARCHAR(255),
    SiteID INT,
    LocationID INT,
    FOREIGN KEY (SiteId) REFERENCES yieldmo.site(SiteId),
    FOREIGN KEY (LocationID) REFERENCES yieldmo.location(LocationID)
);



-- We need to create a table and populate it with users (CookieID) who is from the New York state and has been to a 
-- News Site in Augst 2017 but has not visited CNN ever along with their first and last visit time ever . The structure of the table is as follows :

-- CookieID	VARCHAR	
-- FirstVisit	TIMESTAMP	First Visit EVER to any Site
-- LastVisit	TIMESTAMP	Last Vist EVER to any site

create table yieldmo.users (
	userId INT AUTO_INCREMENT PRIMARY KEY,
	CookieId VARCHAR(255),
	FirstVisit TIMESTAMP,
    LastVisit TIMESTAMP
);


-- wish we had some test data to work with
INSERT INTO yieldmo.users (CookieId, FirstVisit, LastVisit)
SELECT CookieId, MIN(ViewTimeStamp), MAX(ViewTimeStamp)
FROM yieldmo.logs yl
WHERE EXISTS (
	SELECT 1
    FROM yieldmo.location yloc
    WHERE yl.LocationId = yloc.LocationId
    and yloc.state = 'New York'
)
AND EXISTS (
	SELECT 1
    FROM yieldmo.site ys
    WHERE ys.SiteId = yl.SiteId
    AND ys.SiteVertical = 'news'
    AND yl.ViewTimeStamp >= '2017-08-01'
    AND yl.ViewTimeStamp <= '2017-08-31'
)
AND NOT EXISTS (
	SELECT 1
    FROM yieldmo.site ys
    WHERE ys.SiteId = yl.SiteId
    AND ys.SiteName = 'CNN'
)
GROUP BY CookieId;


-- UserAddress			
-- UserID	Int	Not Null	User ID is a FK to the users table 
-- City	Varchar(300)		City of Residence 
-- State	Varchar(300)		State of Residence
-- Country	Varchar(3)		Country of Residence
-- Start_DateTime	DateTime	Not Null	Start Date from which the User was residing at this address
-- End_DateTime	DateTime	Default ('9999-01-01')	End Date to which the User was residing at this address
-- 
-- How can we find the List of users who were staying in New York State for the period of Q2 -2017 ? 
create table yieldmo.userAddress (
	userAddressId BIGINT AUTO_INCREMENT PRIMARY KEY,
    UserId INT,
    City VARCHAR(300),
    State VARCHAR(300),
    Country VARCHAR(3),
    StartDateTime DateTime NOT NULL,
    EndDateTime DateTime DEFAULT '9999-01-01',
);

SELECT *
FROM yieldmo.users yu
WHERE EXISTS (
	SELECT 1 
    FROM yieldmo.userAddress yua
    WHERE yu.State = 'New York'
    AND StartDateTime >= '2017-04-01'
    AND EndDateTime <= '2017-06-30'
);


-- "Please write sql (do not use stored procedure, you can use temp tables) to generate the following result:
-- - session_id, start time, end time, session count
-- 
-- Input:
-- - Stream of sessions with data points:
--  - Page id
--  - Cid
--  - Pid
--  - current_ts
--  - initial_ts
-- 
-- Sessionization rules:
-- - create a new session when difference in consecutive current_ts is > = 3000 
-- - all the sessions that are of difference between 0-3000 sec belong to same session
-- - name the new sessions_id as concat of : page_id, pid, cid, current_ts
-- - count all the sessions with the same sessions id
-- - start time = current_ts at the start of the session set
-- - end time = current_ts at the end of the session set"
-- 

drop table yieldmo.sessions;
create table yieldmo.sessions (
	rowId int AUTO_INCREMENT PRIMARY KEY,
	PageId int,
    PID int,
    CID int,
    Current_ts BIGINT,
    id BIGINT
);

insert into yieldmo.sessions (PageId, PID, CID, Current_ts, id)
values (1, 1, 1, 1502844294625, 1502844294623),
(1,	1,	1,	1502844294625,	1502844294623),
(1,	1,	1,	1502844296511,	1502844294623),
(1,	1,	1,	1502844296540,	1502844294623),
(1,	1,	1,	1502844297478,	1502844294623),
(1,	1,	1,	1502844302481,	1502844294623),
(1,	1,	1,	1502844303477,	1502844294623),
(1,	1,	1,	1502844304486,	1502844294623),
(1,	1,	1,	1502844305484,	1502844294623),
(1,	1,	1,	1502844306476,	1502844294623),
(1,	1,	1,	1502844307486,	1502844294623),
(1,	1,	1,	1502844308481,	1502844294623),
(1,	1,	1,	1502844309608,	1502844294623),
(1,	1,	1,	1502844310477,	1502844294623),
(1,	1,	1,	1502844311491,	1502844294623),
(1,	1,	1,	1502844312476,	1502844294623),
(1,	1,	1,	1502844313477,	1502844294623),
(1,	1,	1,	1502844314490,	1502844294623),
(1,	1,	1,	1502844315477,	1502844294623),
(1,	1,	1,	1502844316485,	1502844294623),
(1,	1,	1,	1502844321892,	1502844294623),
(1,	1,	1,	1502844362001,	1502844294623),
(1,	1,	1,	1502844363039,	1502844294623),
(1,	1,	1,	1502844366706,	1502844294623),
(1,	1,	1,	1502844367716,	1502844294623),
(1,	1,	1,	1502844411441,	1502844294623),
(1,	1,	1,	1502844412056,	1502844294623);


-- since they're already ordered by current_ts, don't have to do any additional ordering
-- Sessionization rules:
-- - create a new session when difference in consecutive current_ts is > = 3000 
-- - all the sessions that are of difference between 0-3000 sec belong to same session
-- - name the new sessions_id as concat of : page_id, pid, cid, current_ts
-- - count all the sessions with the same sessions id
-- - start time = current_ts at the start of the session set
-- - end time = current_ts at the end of the session set"
--
drop table  yieldmo.rowKeys ;
create table yieldmo.rowKeys (
	rowId int,
    rowKey int AUTO_INCREMENT PRIMARY KEY,
    sessionId VARCHAR(300)
);

insert into yieldmo.rowKeys(rowId, sessionId)
select ys1.rowId, current_ts -- concat(ys1.PageId, '_', ys1.PID, '_', ys1.CID, '_', ys1.current_ts) as session_id
from yieldmo.sessions ys1
where rowId = 1;

insert into yieldmo.rowKeys(rowId, sessionId)
select ys1.rowId, ys1.current_ts as newSessionId
from yieldmo.sessions ys1
inner join yieldmo.sessions ys2
	on ys1.rowid = ys2.rowId+1
where ys1.Current_ts - ys2.Current_ts > 3000;

select *
from  yieldmo.rowKeys;

select *,  concat(difference.PageId, '_', difference.PID, '_', difference.CID, '_', difference.current_ts) as session_id
from (
	select ys1.*, ys1.Current_ts - ys2.Current_ts as 'diff'
	from yieldmo.sessions ys1
	inner join yieldmo.sessions ys2
		on ys1.rowId = ys2.rowId + 1
    ) as difference
left outer join yieldmo.rowKeys rk
	on rk.rowId = difference.rowId
order by difference.rowId;

-- running low on time but in short what I'd do to populate the session_id for "null" values would be to group keys by the rowId where it is between each key
-- that would allow a population of sessionId for each session.

-- to get the ping count, we could group by session_id and utilizing rowCount 