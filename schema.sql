drop table if exists Hotel;
drop table if exists Service_Request;
drop table if exists Check_in;
drop table if exists Staff;
drop table if exists Billing_info;
drop table if exists Customer;
drop table if exists Room;

CREATE TABLE Staff (
	id   INTEGER NOT NULL PRIMARY KEY,
	name    VARCHAR(20) NOT NULL,
	rank     VARCHAR(20) NOT NULL
);

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

create table Customer (
	id  INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    dob  DATE NOT NULL,
    phone INTEGER,
    email VARCHAR(20) 
);

CREATE TABLE Room (
	room_id integer PRIMARY KEY,
	max_occu integer(40) NOT NULL, 
	rate float NOT NULL, 
	avai boolean NOT NULL);

INSERT INTO Room VALUES ('101', 4, 100.00, TRUE);
INSERT INTO Room VALUES ('102', 2, 50.00, TRUE);
INSERT INTO Room VALUES ('201', 4, 100.00, FALSE);
INSERT INTO Room VALUES ('202', 2, 50.00, FALSE);
INSERT INTO Room VALUES ('301', 1, 35.00, FALSE);

create table Billing_info
(
 id INTEGER NOT NULL PRIMARY KEY,
 ssn INTEGER NOT NULL,
 payment_type VARCHAR(20) NOT NULL,
 card_number  INTEGER NOT NULL,
 hotel_card BOOLEAN NOT NULL,
 check_in BOOLEAN NOT NULL,
 room_id INTEGER NOT NULL,
 customer_id INTEGER NOT NULL,
  FOREIGN KEY(room_id)
    REFERENCES Room(room_id),
   FOREIGN KEY(customer_id)
    REFERENCES Customer(id)    
);

insert into Customer values('24235667', 'James Harrison', '1992-01-21', '435234114', 'james24@ro.com');
insert into Customer values('45345511', 'Ford Rex', '1987-04-30', '923211432', 'fox@fox.com');
insert into Customer values('87543123', 'Bill Fredson', '1987-11-28', '902343451', 'soing@haha.com');
insert into Customer values('23467572', 'Henry Lacon', '1965-12-03', '245351120', 'urf23@aol.com');
insert into Staff values('10021', 'Jon Doe', 'manager');
insert into Staff values('23251', 'Jack Chris', 'manager');
insert into Staff values('18007', 'Jim Blarr', 'front desk');
insert into Staff values('23212', 'Mary Lynch', 'support');
insert into Staff values('43231', 'Jane DeVoe', 'billing');
insert into Hotel values('2342112', 'hotel1', '1010 billing st', 'Cary', '324231311', '10021');
insert into Hotel values('2127686', 'hotel2', '221 Caroll Dr', 'Memphis', '345423652', '23251');
insert into Hotel values('6574222', 'hotel3', '5542 Dell St.', 'Austin', '234531763', '23251');
insert into Hotel values('7677832', 'hotel4', '1121 Portland Ave', 'Seattle', '645654112', '10021');
insert into Billing_info values('2334235','283434', 'visa', '4533454', 0, 1, '101', '24235667');
insert into Billing_info values('3224345', '213434', 'master', '5644543', 1, 1, '102', '45345511' );
insert into Billing_info values('2343454', '372132', 'visa', '5675545', 0, 1, '201','87543123');
insert into Billing_info values('2454233', '452134', 'master', '5434231', 1, 0, '301', '23467572');
#select * from Staff;
#select * from Hotel;
#select * from Customer;
#select * from Room;
#select * from Billing_info;
#INSERT INTO Room VALUES ('161', 6, 120.00, False);
#update Room set avai=True where room_id='161';
#delete from Room where room_id='161';
#select * from Room where avai = true;
#select * from Room where rate < 100 and avai = true;
create table Check_in(
	id INTEGER PRIMARY KEY NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    guestCnt INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    foreign key(room_id) REFERENCES Room(room_id),
    foreign key(customer_id) REFERENCES Customer(id)
);

CREATE TABLE Service_Request (
   service_request_id integer NOT NULL PRIMARY KEY, 
   room_id integer NOT NULL, 
  submitter_id integer NOT NULL, 
  customer_id integer NOT NULL, 
  type varchar(10) NOT NULL, 
  complete boolean NOT NULL, 
  date varchar(40) NOT NULL, 
  cost float NOT NULL, 
  FOREIGN KEY(room_id)
    REFERENCES Room(room_id),
  FOREIGN KEY (submitter_id )
    REFERENCES Staff(id),
  FOREIGN KEY (customer_id)
    REFERENCES Customer (id)
 
  );
 
 insert into Service_Request values('001','101','10021','24235667','room_ser', '1', '23', '54.5');
 update Service_Request set cost='23.3' where service_request_id='001';
insert into Check_in values('13142345', '2019-01-03', '2019-01-05', 2, '23467572', '201');
insert into Check_in values('53645243', '2019-02-09', '2019-02-12', 3, '23467572', '202');
insert into Check_in values('24315564', '2017-09-12', '2017-09-13', 1, '87543123', '102');
insert into Check_in values('78653235', '2017-11-02', '2017-11-07', 1, '45345511', '101');
#select * from Check_in;
insert into Check_in values('24542523', '2019-03-18', '2019-03-20',2, '45345511', '102');
