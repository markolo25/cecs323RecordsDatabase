CREATE TABLE recordinggroup
  (
  groupname VARCHAR(20) NOT NULL ,
  leadsinger VARCHAR(20) NOT NULL ,
  yearformed    VARCHAR(20)  NOT NULL ,
  genre  VARCHAR(20)  NOT NULL ,
 CONSTRAINT pk_recordinggroup PRIMARY KEY (groupname)
  );

CREATE TABLE recordingstudio
  (
  studioname VARCHAR(20) NOT NULL ,
  studioaddress VARCHAR(40) NOT NULL ,
  studioowner    VARCHAR(20)  NOT NULL ,
  studiophone  VARCHAR(20)  NOT NULL  ,
  CONSTRAINT pk_recordingstudio PRIMARY KEY (studioname)
  );

CREATE TABLE albums
  (
  studioname VARCHAR(30) NOT NULL ,
  groupname VARCHAR(30) NOT NULL ,
  albumtitle VARCHAR(30) NOT NULL ,
  daterecorded VARCHAR(30) NOT NULL ,
  length    VARCHAR(30)  NOT NULL ,
  numberofsongs  VARCHAR(30)  NOT NULL  ,
  CONSTRAINT pk_album PRIMARY KEY (studioname, groupname, albumtitle)
  );


--ALTER TABLE albums
  --        ADD CONSTRAINT rg_fk, rs_fk
    --      FOREIGN KEY (groupname, studioname)
      --    REFERENCES recordinggroup (groupname),recordingstudio (studioname);

INSERT INTO albums VALUES('Arkham Studio','MNM', 'Julius', '01/10/1988', '3:33', '5');
INSERT INTO albums VALUES('Party Studio','jammie', 'Sunday', '12/12/1961', '3:03', '2');
INSERT INTO albums VALUES('Vega Studio','stinger', 'JD', '01/07/1961', '12:55', '8');
INSERT INTO albums VALUES('Vega Studio','ragae boys', 'Jam', '01/07/1971', '22:55', '18');
INSERT INTO albums VALUES('Gotham Studio','batman', 'Kill Superman', '02/11/2016', '5:15', '1');

INSERT INTO recordingstudio VALUES('Arkham Studio','13 eastshore,
irvine','sam','949-123-4567'); INSERT INTO recordingstudio VALUES('Party
Studio','11 tam road, long beach','mr party','909-989-0000'); INSERT INTO
recordingstudio VALUES('Vega Studio','236 wicker, san juan capistrano','mr
vegas','999-999-9999'); insert into recordingstudio values ('New Studio',
'1-luminous, beverly hills' ,'mr wayne' ,'111-111-1111');

insert into recordinggroup values('mnm','zoro','1966','classic');
INSERT INTO recordinggroup VALUES('jammie','jimm','1955','pop');
insert into recordinggroup values ('stinger','capistrano','1999', 'hiphop');
insert into recordinggroup values ('ragae boys','smith', '1980', 'indie');
insert into recordinggroup Values ('batman', 'robin', '2016','trance');

select * from recordinggroup;
select * from recordingstudio;
select * from albums;
--delete from recordinggroup;
--delete from recordingstudio;
--delete from albums;