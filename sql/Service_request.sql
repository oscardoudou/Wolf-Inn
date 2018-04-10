drop table if exists Hotel;
drop table if exists Staff;
drop table if exists Billing_info;
drop table if exists Customer;
drop table if exists Room;


CREATE TABLE Staff (
	id   INTEGER NOT NULL PRIMARY KEY,
	name    VARCHAR(20) NOT NULL,
	rank     VARCHAR(20) NOT NULL
);

insert into Staff values('10021', 'Jon Doe', 'manager');
insert into Staff values('23251', 'Jack Chris', 'manager');
insert into Staff values('18007', 'Jim Blarr', 'front desk');
insert into Staff values('23212', 'Mary Lynch', 'support');
insert into Staff values('43231', 'Jane DeVoe', 'billing');


CREATE TABLE Room (
	room_id integer PRIMARY KEY,
	max_occu integer(40) NOT NULL, 
	rate float NOT NULL, 
	avai boolean NOT NULL);

INSERT INTO Room VALUES ('101', 4, 100.00, 1);
INSERT INTO Room VALUES ('102', 2, 50.00, 1);
INSERT INTO Room VALUES ('201', 4, 100.00, 0);
INSERT INTO Room VALUES ('202', 2, 50.00, 0);
INSERT INTO Room VALUES ('301', 1, 35.00, 0);

create table Customer (
	id  INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    dob  DATE NOT NULL,
    phone INTEGER,
    email VARCHAR(20) 
);

insert into Customer values('24235667', 'James Harrison', '1992-01-21', '435234114', 'james24@ro.com');
insert into Customer values('45345511', 'Ford Rex', '1987-04-30', '923211432', 'fox@fox.com');
insert into Customer values('87543123', 'Bill Fredson', '1987-11-28', '902343451', 'soing@haha.com');
insert into Customer values('23467572', 'Henry Lacon', '1965-12-03', '245351120', 'urf23@aol.com');



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
 
 insert into Service_Request values('001','101','10021','24235667','room_service', '1', '23', '54.5');
  insert into Service_Request values('002','102','23251','45345511','dining', '1', '24', '24.5');
  insert into Service_Request values('003','201','18007','87543123','cleaning', '0', '25', '34.5');
  insert into Service_Request values('004','301','23212','23467572','cleaning', '0', '26', '44.5'); 
 update Service_Request set cost='23.3' where service_request_id='001';
  
