USE classicmodels;

/*
Q1: Write an SQL statement to show only 5 employee’s data.
*/
SELECT * FROM employees LIMIT 5;

/*
Q2: Write an SQL statement to display the detail of the payment with the
    highest amount.
*/
SELECT * FROM payments ORDER BY amount DESC LIMIT 1;

/*
Q3: Write an SQL statement to output the number of products the company
    has in the ‘Classic Cars’ product line?
*/
SELECT COUNT(DISTINCT productName) FROM products WHERE productLine = 'Classic Cars';

/*
Q4: Write an SQL statement to output the average buying price of a model
    Planes.
*/
SELECT AVG(priceEach) FROM orderdetails
WHERE productCode = ANY(SELECT productCode FROM products WHERE productLine = 'Planes');

/*
Q5: If I want to buy the complete set of model vintage cars, how much would
    it cost? The MSRP (Manufacturer's Suggested Retail Price) is the price
    that the company would sell me a product for.
*/
SELECT SUM(MSRP) FROM products WHERE productLine = 'Classic Cars';

/*
Q6: How much money did the company earn from orders of Classic Car
    models in 2004?
*/
SELECT  SUM(priceEach * quantityOrdered) 
FROM orders JOIN orderdetails USING(orderNumber) JOIN products USING(productCode) 
WHERE YEAR(orderDAte) = 2004 AND productCode = ANY(
  SELECT productCode FROM products WHERE productLine = 'Classic Cars');

/*
Q7: Name the customer(s), product and quantity ordered which represents
    the largest quantity of any item ordered?
*/
SELECT customerName, productName, quantityOrdered FROM customers 
JOIN orders USING(customerNumber)
JOIN orderdetails USING(orderNumber)
JOIN products USING(productCode)
ORDER BY quantityOrdered DESC
LIMIT 1;

/*
Q8: Name the customer(s), product and quantity ordered which represents
    the smallest quantity greater than zero of any item ordered?
*/
SELECT customerName, productName, quantityOrdered FROM customers 
JOIN orders USING(customerNumber)
JOIN orderdetails USING(orderNumber)
JOIN products USING(productCode)
ORDER BY quantityOrdered
LIMIT 1;

/*
Q9: List the number of products in each product line.
*/
SELECT productLine, COUNT(*) FROM products GROUP BY productLine;

/*
Q10: What is the average quantity of model ordered for each product line?
*/
SELECT productLine, AVG(quantityOrdered) 
FROM products JOIN orderdetails USING(productCode)
GROUP BY productLine;

/*
Q11: List those product lines, and the number of products they have, who have
     more than ten products in the line.
*/
SELECT productLIne, COUNT(*) FROM products GROUP BY productLine HAVING COUNT(*) > 10;

/*
Q12: List the name and price of the 10 most expensive products (according to
     the MSRP price), in order.
*/
SELECT productName, MSRP FROM products ORDER BY MSRP DESC LIMIT 10;

/*
Q13: Create a view, based on the products table, that only contains model
     ships. This can be named ‘ships’.
*/
CREATE VIEW ship AS
SELECT * FROM products
WHERE productLine = 'Ships';

/*
Q14: List the customer name and the total amount paid by any customer who
     has paid more than 250000 in total.
*/
SELECT customerName, SUM(priceEach * quantityOrdered)
FROM customers JOIN orders USING(customerNumber) JOIN orderdetails USING(orderNumber)
GROUP BY customerNumber
HAVING SUM(priceEach * quantityOrdered) > 250000;

/*
Q15: Using a nested query, list the name and city of any customer that is based
     in a city that the company has an office in.
*/
SELECT customerName, city
FROM customers
WHERE city = ANY(
  SELECT city FROM offices
);

/*
Q16: Repeat question 15, using a join instead of a nested query.
*/
SELECT customerName, city
FROM customers JOIN offices USING(city);