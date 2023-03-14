/*
Q1: List all information about the company’s customers.
*/
SELECT * FROM customers;

/*
Q2: List all of the customer names from Frankfurt.
*/
SELECT customerName FROM customers WHERE city = 'Frankfurt';

/*
Q3: Find the name, city and phone number of all customers from Germany.
*/
SELECT customerName, city, phone FROM customers WHERE country = 'Germany';

/*
Q4: List all of the products in each product line.
*/
SELECT productLine, productName FROM products;

/*
Q5: List all the cities that the company has offices in.
*/
SELECT DISTINCT city FROM offices;

/*
Q6: List all of the job titles that are available in the company (each job title should only be listed once).
*/
SELECT DISTINCT jobTitle FROM employees;

/*
Q7: Find the name of the company president and the city that her office is in.
*/
SELECT lastName, firstName, city
FROM employees AS e, offices AS o 
WHERE e.jobTitle = 'President' AND e.officeCode = o.officeCode;

/*
Q8: Show the order number and any comments for all orders that have comments.
*/
SELECT orderNumber, comments
FROM orders
WHERE comments IS NOT NULL;

/*
Q9: For every order, show the order number and the name of the customer that placed the order.
*/
SELECT orderNumber,  customerName
FROM orders AS o, customers AS c
WHERE o.customernumber = c.customerNumber;

/*
Q10: List the names of all customers who have never placed an order.
*/
SELECT customerName
FROM customers
WHERE customerName NOT IN (
  SELECT customerName
  FROM customers AS c, orders AS o
  WHERE c.customerNumber = o.customerNumber
);

/*
Q11: Find all the customer names that end with “Co” or “Co.”.
*/
SELECT customerName
FROM customers
WHERE customerName LIKE '%Co' or customerName LIKE '%Co.';

/*
Q12: List the names of all employees who work for Gerard Bondur
*/
SELECT e1.lastName, e1.firstName
FROM employees AS e1, employees AS e2
WHERE e1.reportsTo = e2.employeeNumber AND e2.lastName = 'Bondur' AND e2.firstName = 'Gerard';

/*
Q13: Insert two new product lines
*/
INSERT INTO productlines (productLine, textDescription) VALUES ('Computers', 'We now sell models of classic computers too!');
INSERT INTO productlines (productLine, textDescription) VALUES ('Helicopters', 'All kinds of choppers.');

/*
Q14: The employee named George Vanauf has moved to the Paris office. Make a change to the database to record this information.
*/
UPDATE employees SET officeCode = (
  SELECT officeCode
  FROM offices
  WHERE city = 'Paris'
)
WHERE lastName = 'Vanauf' AND firstName = 'George';