DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  empID VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  state VARCHAR(100) NOT NULL,
  zip VARCHAR(100) NOT NULL,
  hireDate VARCHAR(100) NOT NULL
);

INSERT INTO employee(empID,name, address, city, state, zip, hireDate)values("emp001","emp1","asd","asd","asd","asd","asd");
INSERT INTO employee(empID,name, address, city, state, zip, hireDate)values("emp002","emp2","dhh","sdfgs","Fasdf","asdfsdfsd","sdfasd");
