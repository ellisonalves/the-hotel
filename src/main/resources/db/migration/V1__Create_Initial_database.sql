-- CREATING TABLES
CREATE TABLE ROOM(
    ID UUID NOT NULL,
    DOOR_NUMBER VACHAR NOT NULL,
    TYPE VARCHAR NOT NULL,
    PRICE_PER_DAY DECIMAL NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE GUEST (
    ID UUID NOT NULL,
    DOCUMENT_NUMBER VARCHAR NOT NULL,
    NAME VARCHAR NOT NULL,
    NATIONALITY VARCHAR NOT NULL,
    GENDER VARCHAR NOT NULL,
    EMAIL VARCHAR NOT NULL,
    ADDRESS VARCHAR NOT NULL,
    PHONE VARCHAR NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE BOOKING (
    ID UUID NOT NULL,
    GUEST_ID BIGINT NOT NULL REFERENCES GUEST(ID),
    ROOM_ID BIGINT NOT NULL REFERENCES ROOM(ID),
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);


-- INSERTING DATA
INSERT INTO ROOM VALUES (100, 'STANDARD', 50.0);
INSERT INTO ROOM VALUES (101, 'STANDARD', 70.0);
INSERT INTO ROOM VALUES (102, 'DOUBLE', 100.0);
INSERT INTO ROOM VALUES (103, 'DOUBLE', 140.0);
INSERT INTO ROOM VALUES (104, 'PRESIDENT', 200.0);

-- (DOCUMENT_NUMBER, NAME, NATIONALITY, GENDER, EMAIL, ADDRESS, PHONE)
INSERT INTO GUEST VALUES(1, '1234567890', 'JOSE ANTUNES', 'BRAZIL', 'MALE', 'JOSEANTUNES@EMAIL.COM', 'JA STREET', '12345678');

INSERT INTO BOOKING VALUES (1, 1, '100', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());