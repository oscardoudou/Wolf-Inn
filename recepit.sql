drop table if exists Hotel;

CREATE TABLE Hotel (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(10) NOT NULL,
    phone INTEGER UNIQUE,
    manager_id INTEGER NOT NULL,
    FOREIGN KEY (manager_id)
        REFERENCES Staff (id)
);


CREATE TABLE Room (
	room_id integer PRIMARY KEY,
	max_occu integer(40) NOT NULL, 
	rate float NOT NULL, 
	avai boolean NOT NULL,
    hotel_id integer NOT NULL,
    type varchar(20) NOT NULL,
    foreign key(hotel_id) references Hotel(id)
    );



insert into Hotel values('2342112', 'hotel1', '1010 billing st', 'Cary', '324231311', '10021');
insert into Hotel values('2127686', 'hotel2', '221 Caroll Dr', 'Memphis', '345423652', '23251');
insert into Hotel values('6574222', 'hotel3', '5542 Dell St.', 'Austin', '234531763', '23251');
insert into Hotel values('7677832', 'hotel4', '1121 Portland Ave', 'Seattle', '645654112', '10021');
INSERT INTO Room VALUES ('101', 4, 100.00, 1,'7677832','Economy');
INSERT INTO Room VALUES ('102', 2, 50.00, 1,'7677832','Deluxe');
INSERT INTO Room VALUES ('201', 4, 100.00, 0,'2127686','Economy');
INSERT INTO Room VALUES ('202', 2, 50.00, 0, '6574222','Deluxe');
INSERT INTO Room VALUES ('301', 1, 35.00, 0, '6574222','Deluxe');




create table Check_in(
	id INTEGER PRIMARY KEY NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    guestCnt INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    hotel_id INTEGER NOT NULL,
    foreign key(room_id) REFERENCES Room(room_id),
    foreign key(hotel_id) REFERENCES Hotel(id)
);

insert into Check_in values('13142345', '2019-01-03', '2019-01-05', 2, '101', '7677832');
insert into Check_in values('53645243', '2019-02-09', '2019-02-12', 3, '102', '7677832');
insert into Check_in values('24315564', '2017-09-12', '2017-09-13', 1, '201', '2127686');
insert into Check_in values('78653235', '2017-11-02', '2017-11-07', 1, '301', '6574222');




CREATE TABLE Service_Request (
   service_request_id integer NOT NULL PRIMARY KEY, 
   checkin_id integer NOT NULL, 
   type varchar(10) NOT NULL, 
   cost float NOT NULL, 
  FOREIGN KEY(checkin_id)
    REFERENCES Check_in(id)
  
  );

insert into Service_Request values('1','13142345','romser','30');
insert into Service_Request values('2','13142345','clean','20');
insert into Service_Request values('3','53645243','clean','20');
insert into Service_Request values('4','53645243','romser','30');
  



select sum(revenue) from(
select sum(cost) as revenue from Service_Request 
UNION ALL
select sum(rate) as revenue from Room where room_id IN (select room_id from  Check_in  )

);

select type, cost from Service_Request where  checkin_id='13142345';
select type, rate from Room where room_id=(select room_id from  Check_in where id='13142345' )
