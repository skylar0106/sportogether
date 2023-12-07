DROP TABLE spouser CASCADE CONSTRAINTS;
DROP TABLE spoleader CASCADE CONSTRAINTS;
DROP TABLE teamscore CASCADE CONSTRAINTS;
DROP TABLE team CASCADE CONSTRAINTS;
CREATE TABLE team
(
   teamID       INTEGER  NOT NULL ,
   name         VARCHAR2(30)  NOT NULL ,
   spoleader        VARCHAR2(18)  NOT NULL ,
   tlevel      INTEGER  NULL ,
   sport        VARCHAR2(18)  NOT NULL ,
   location     VARCHAR2(100)  NULL ,
   membership       INTEGER  NULL ,
   rival        VARCHAR2(18)  NULL,
   tcomment      VARCHAR2(255) NULL
);

ALTER TABLE team
   ADD (
CONSTRAINT R_1 FOREIGN KEY (spoleader) REFERENCES spoleader (USERID));

CREATE UNIQUE INDEX XPKteam ON team
(teamID   ASC);

ALTER TABLE team
   ADD CONSTRAINT  XPKteam PRIMARY KEY (teamID);

CREATE TABLE teamScore
(
   teamID               INTEGER  NOT NULL ,
   matches              INTEGER  NULL ,
   win                  INTEGER  NULL ,
   lose                 INTEGER  NULL ,
   ranking              INTEGER  NULL ,
   draw                 INTEGER  NULL ,
   rate                 FLOAT  NULL 
);

CREATE UNIQUE INDEX XPKteamScore ON teamScore
(teamID   ASC);

ALTER TABLE teamScore
   ADD CONSTRAINT  XPKteamScore PRIMARY KEY (teamID);

CREATE TABLE match
(
   teamID                INTEGER   NOT NULL ,
   sport                VARCHAR2(18)  NOT NULL ,
   "DATE"                 TIMESTAMP  NOT NULL ,
   winner               CHAR(18)  NULL ,
   loser                CHAR(18)  NULL ,
   matchID              CHAR(18)  NOT NULL ,
   winpoint             integer  NOT NULL ,
   losepoint            integer  NOT NULL 
);

CREATE UNIQUE INDEX XPKmatch ON match
(matchID   ASC);

ALTER TABLE match
   ADD CONSTRAINT  XPKmatch PRIMARY KEY (matchID);
    
CREATE TABLE spoleader
(
   userID               VARCHAR2(18)  NOT NULL,
    message      VARCHAR2(255) NULL   
);

CREATE TABLE spouser
(
   userID                VARCHAR2(18)  NOT NULL ,
   name                 VARCHAR2(15)  NOT NULL ,
   nickname             VARCHAR2(30)  NOT NULL ,
   sex                  char(7)  NOT NULL ,
   birth                NCLOB  NOT NULL ,
   password             VARCHAR2(18)  NOT NULL ,
   picture              DATE  NULL
);

ALTER TABLE spouser
   ADD comment NVARCHAR(255) NULL,
   ADD interests NVARCHAR2(255) NULL,
   ADD career NVARCHAR2(255) NULL;

CREATE UNIQUE INDEX XPKuser ON spouser
(userID   ASC);

ALTER TABLE spouser
   ADD CONSTRAINT  XPKuser PRIMARY KEY (userID);

CREATE UNIQUE INDEX XPKleader ON spoleader
(userID   ASC);

ALTER TABLE spoleader
   ADD CONSTRAINT  XPKleader PRIMARY KEY (userID);

CREATE TABLE spomember
(
   userID               VARCHAR2(18)  NOT NULL ,
   message            NVARCHAR2(255)  NULL, 
   teamID              INTEGER NOT NULL,
   FOREIGN KEY (teamID) REFERENCES team (teamID) on DELETE CASCADE
);

CREATE UNIQUE INDEX XPKmember ON spomember
(userID   ASC);

ALTER TABLE spomember
   ADD CONSTRAINT  XPKmember PRIMARY KEY (userID);

ALTER TABLE teamScore
   ADD (
CONSTRAINT R_5 FOREIGN KEY (teamID) REFERENCES team (teamID));

ALTER TABLE match
   ADD (
CONSTRAINT R_13 FOREIGN KEY (teamID) REFERENCES team (teamID));

ALTER TABLE spouser
   ADD (
CONSTRAINT R_4 FOREIGN KEY (team) REFERENCES team (teamID) ON DELETE SET NULL);

ALTER TABLE spoleader
   ADD (
CONSTRAINT R_14 FOREIGN KEY (userID) REFERENCES spouser (userID) ON DELETE CASCADE);

ALTER TABLE spomember
   ADD (
CONSTRAINT R_15 FOREIGN KEY (userID) REFERENCES spouser (userID) ON DELETE CASCADE);

CREATE TABLE REQUEST
(
   teamId               INTEGER  NOT NULL ,
   userId               VARCHAR2(18)  NOT NULL ,
   massage              NVARCHAR2(255)  NULL ,
   date                 DATE  NOT NULL
);

ALTER TABLE REQUEST
   ADD CONSTRAINT FK_REQUEST_TEAM FOREIGN KEY (teamId) REFERENCES TEAM (teamId) ON DELETE CASCADE;

ALTER TABLE REQUEST
   ADD CONSTRAINT FK_REQUEST_USER FOREIGN KEY (userId) REFERENCES USER (userid) ON DELETE CASCADE;

CREATE INDEX IX_REQUEST_TEAM_USER ON REQUEST
(teamId   ASC, userId   ASC);

-- spouser ���̺� ������ ����
INSERT INTO spouser (userID, name, nickname, sex, birth, password, picture)
VALUES 
  ('user1', 'John Doe', 'Johnny', 'Male', '19900101', 'password1', null);
INSERT INTO spouser (userID, name, nickname, sex, birth, password, picture)
VALUES 
  ('user2', 'Jane Doe', 'Janie', 'Female', '19910201', 'password2', null);
INSERT INTO spouser (userID, name, nickname, sex, birth, password, picture)
VALUES 
  ('user3', 'Alice Smith', 'Ally', 'Female', '19920301', 'password3', null);

-- spoleader ���̺� ������ ����
INSERT INTO spoleader (userID, message)
VALUES 
  ('user1', 'Message for leader1');
INSERT INTO spoleader (userID, message)
VALUES
  ('user2', 'Message for leader2');
INSERT INTO spoleader (userID, message)
VALUES
  ('user3', 'Message for leader3');

-- team ���̺� ������ ����
INSERT INTO team (teamID, name, spoleader, tlevel, sport, location, membership, rival, tcomment)
VALUES 
  (1, 'TeamA', 'user1', 1, 'Football', 'City Stadium', 50, 'Team B', 'Comment for Team A');
INSERT INTO team (teamID, name, spoleader, tlevel, sport, location, membership, rival, tcomment)
VALUES 
  (2, 'TeamB', 'user2', 1, 'Basketball', 'Arena Center', 40, 'Team A', 'Comment for Team B');
INSERT INTO team (teamID, name, spoleader, tlevel, sport, location, membership, rival, tcomment)
VALUES 
  (3, 'TeamC', 'user3', 1, 'Soccer', 'Field Park', 30, 'Team D', 'Comment for Team C');

-- teamScore ���̺� ������ ����
INSERT INTO teamScore (teamID, matches, win, lose, ranking, draw, rate)
VALUES 
  (1, 10, 6, 4, 1, 0, 60.0);
INSERT INTO teamScore (teamID, matches, win, lose, ranking, draw, rate)
VALUES 
  (2, 10, 5, 5, 2, 0, 50.0);
INSERT INTO teamScore (teamID, matches, win, lose, ranking, draw, rate)
VALUES 
  (3, 10, 4, 6, 3, 0, 40.0);
