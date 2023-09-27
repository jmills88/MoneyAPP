BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

drop table if exists transaction;
CREATE table transaction(
	transaction_id serial not null,
	sender_account_id int not null,
	recipient_account_id int not null,
	transfer_amount DECIMAL (13,2) not null,
	transaction_status VARCHAR,
	constraint PK_Transaction_id primary key (transaction_id),
	constraint FK_sender_account_id FOREIGN key (sender_account_id) REFERENCES account (account_id),
	constraint FK_recipient_account_id FOREIGN key (recipient_account_id) REFERENCES account (account_id)
);
COMMIT;
