CREATE TABLE q1 (
  id INT PRIMARY KEY,
  message CHAR(144) NOT NULL
);

ALTER TABLE q1 ADD atime TIME NOT NULL;

CREATE TABLE item (
  itemid INT PRIMARY KEY,
  itemname CHAR(25) UNIQUE NOT NULL,
  itemprice DOUBLE NOT NULL
);

CREATE TABLE stocklist (
  itemid INT REFERENCES item(itemid) ON UPDATE CASCADE ON DELETE CASCADE,
  shoplocation CHAR(40) NOT NULL,
  count INT NOT NULL,
  PRIMARY KEY (itemid, shoplocation)
);

INSERT INTO item (itemid, itemname, itemprice) VALUES (1, "Macbook Pro 2019", 3000.00);
INSERT INTO item (itemid, itemname, itemprice) VALUES (2, "iMac 2020", 4422.53);

UPDATE item SET itemprice = itemprice * 1.1 WHERE itemname = "iMac 2020";
