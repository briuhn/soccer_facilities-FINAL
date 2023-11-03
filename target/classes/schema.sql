CREATE TABLE soccer_facility(
	soccer_facility_id int NOT NULL AUTO_INCREMENT,
	name varchar(256) NOT NULL,
	street_address varchar(128) NOT NULL,
	city varchar(60),
	state varchar(60),
	zip varchar(60),
	phone varchar(30),
	PRIMARY KEY (soccer_facility_id)
);

CREATE TABLE booking(
	booking_id int NOT NULL AUTO_INCREMENT,
	soccer_facility_id int NOT NULL,
	dates varchar(60) NOT NULL,
	start_time varchar(60),
	end_time varchar(60)
	PRIMARY KEY (booking_id),
	FOREIGN KEY (soccer_facility_id) REFERENCES soccer_facility(soccer_facility_id) ON DELETE CASCADE
);
 
CREATE TABLE player(
	player_id int NOT NULL AUTO_INCREMENT,
	soccer_facility_id int NULL,
	first_name varchar(60) NOT NULL,
	last_name varchar(60) NOT NULL,
	player_email varchar(120),
	PRIMARY KEY(player_id),
);
