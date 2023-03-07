DROP TABLE student;

CREATE TABLE student (
  id int(8) PRIMARY KEY,
  first_name CHAR(20) NOT NULL,
  second_name CHAR(20) NOT NULL,
  age int(2) DEFAULT 18 NOT NULL
);

INSERT INTO student (id, first_name, second_name, age) VALUES (21372101, 'Kelvin', 'Zhao', 20);
INSERT INTO student (id, first_name, second_name, age) VALUES (21372102, 'Lydia', 'Zhao', 20);

UPDATE student SET second_name = 'Qiu' WHERE id = 21372101;
