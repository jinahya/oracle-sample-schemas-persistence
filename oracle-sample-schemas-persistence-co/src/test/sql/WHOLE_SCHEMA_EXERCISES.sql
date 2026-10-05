-- #%L
-- co
-- %%
-- Copyright (C) 2024 - 2026 Jinahya, Inc.
-- %%
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--      http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- #L%
---
--
-- Exercises on the whole schema, a problem per query that reads more than one table or view; Basic, then Intermediate,
-- then Advanced.
--
-- These are the SQL solutions of the 'Whole schema' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table or view.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------- CO-SCHEMA-B-01 Who placed an order, and where
-- Customer care has an order number. Show the order with the customer's name and email address and the store it was
-- placed at.
SELECT o.order_id, o.order_tms, o.order_status, c.full_name, c.email_address, s.store_name
FROM orders o
         JOIN customers c ON c.customer_id = o.customer_id
         JOIN stores s ON s.store_id = o.store_id
WHERE o.order_id = :orderId
;


-- -------------------------------------------------------------------------------------- CO-SCHEMA-B-02 Print a receipt
-- A customer asks for a receipt. List the lines of one order: line number, product, unit price, quantity and line
-- total.
SELECT oi.line_item_id,
       p.product_name,
       oi.unit_price,
       oi.quantity,
       oi.unit_price * oi.quantity AS line_total
FROM order_items oi
         JOIN products p ON p.product_id = oi.product_id
WHERE oi.order_id = :orderId
ORDER BY oi.line_item_id
;


-- ------------------------------------------------------------------------------------------ CO-SCHEMA-B-03 Running low
-- The warehouse wants every store and product whose stock is below :threshold, the emptiest shelves first.
SELECT s.store_name, p.product_name, i.product_inventory
FROM inventory i
         JOIN stores s ON s.store_id = i.store_id
         JOIN products p ON p.product_id = i.product_id
WHERE i.product_inventory < :threshold
ORDER BY i.product_inventory, s.store_name, p.product_name
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------------ CO-SCHEMA-I-01 What each order came to
-- Show a customer what each of their orders cost in total, newest first.
SELECT o.order_id,
       o.order_tms,
       o.order_status,
       SUM(oi.unit_price * oi.quantity) AS order_total
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE o.customer_id = :customerId
GROUP BY o.order_id, o.order_tms, o.order_status
ORDER BY o.order_tms DESC
;


-- -------------------------------------------------------------------- CO-SCHEMA-I-02 Stores that pass a revenue target
-- Finance wants the revenue of completed orders per store, keeping only the stores above a target, best first.
SELECT s.store_id, s.store_name, SUM(oi.unit_price * oi.quantity) AS revenue
FROM stores s
         JOIN orders o ON o.store_id = s.store_id
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE o.order_status = 'COMPLETE'
GROUP BY s.store_id, s.store_name
HAVING SUM(oi.unit_price * oi.quantity) > :target
ORDER BY revenue DESC
;


-- ----------------------------------------------------------------------------------------- CO-SCHEMA-I-03 Best sellers
-- Merchandising wants the ten products that sold the most units, counting completed orders only.
SELECT p.product_id, p.product_name, SUM(oi.quantity) AS units_sold
FROM products p
         JOIN order_items oi ON oi.product_id = p.product_id
         JOIN orders o ON o.order_id = oi.order_id
WHERE o.order_status = 'COMPLETE'
GROUP BY p.product_id, p.product_name
ORDER BY units_sold DESC, p.product_name
    FETCH FIRST 10 ROWS ONLY
;


-- ------------------------------------------------------------------------------------ CO-SCHEMA-I-04 Regular customers
-- Marketing wants the customers who have placed at least :minOrders orders, with their order count, most orders first.
SELECT c.customer_id, c.full_name, c.email_address, COUNT(*) AS order_count
FROM customers c
         JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.full_name, c.email_address
HAVING COUNT(*) >= :minOrders
ORDER BY order_count DESC, c.full_name
;


-- ------------------------------------------------------------------------------- CO-SCHEMA-I-05 Has the price drifted?
-- Each order line records the price actually charged. Merchandising wants, per product, the lowest and highest price it
-- was ever sold at, next to today's list price, for the products where the two disagree.
SELECT p.product_id,
       p.product_name,
       p.unit_price       AS list_price,
       MIN(oi.unit_price) AS lowest_sold,
       MAX(oi.unit_price) AS highest_sold
FROM products p
         JOIN order_items oi ON oi.product_id = p.product_id
GROUP BY p.product_id, p.product_name, p.unit_price
HAVING MIN(oi.unit_price) <> p.unit_price
    OR MAX(oi.unit_price) <> p.unit_price
ORDER BY p.product_name
;


-- ------------------------------------------------------------------------------ CO-SCHEMA-I-06 Out of stock everywhere
-- Purchasing wants the products that cannot be bought anywhere: the total stock across all stores is zero, or no store
-- carries them at all.
SELECT p.product_id, p.product_name, COALESCE(SUM(i.product_inventory), 0) AS total_stock
FROM products p
         LEFT JOIN inventory i ON i.product_id = p.product_id
GROUP BY p.product_id, p.product_name
HAVING COALESCE(SUM(i.product_inventory), 0) = 0
ORDER BY p.product_name
;


-- ---------------------------------------------------------------------- CO-SCHEMA-I-07 Small, medium and large baskets
-- Marketing sorts orders into bands by their total: under 100 is small, under 300 is medium, anything else is large.
-- List each order with its total and band.
SELECT o.order_id,
       SUM(oi.unit_price * oi.quantity) AS order_total,
       CASE
           WHEN SUM(oi.unit_price * oi.quantity) < 100 THEN 'SMALL'
           WHEN SUM(oi.unit_price * oi.quantity) < 300 THEN 'MEDIUM'
           ELSE 'LARGE'
           END                          AS basket
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY o.order_id
ORDER BY o.order_id
;


-- ----------------------------------------------------------------------------- CO-SCHEMA-I-08 Orders not fully shipped
-- Logistics wants the orders that still have lines with no shipment assigned, and how many such lines each has.
SELECT o.order_id, o.order_status, COUNT(*) AS unshipped_lines
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE oi.shipment_id IS NULL
GROUP BY o.order_id, o.order_status
ORDER BY unshipped_lines DESC, o.order_id
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------------------- CO-SCHEMA-A-01 Customers who never ordered
-- Marketing wants to send a first-purchase voucher to every registered customer who has never placed an order.
SELECT c.customer_id, c.full_name, c.email_address
FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.customer_id)
ORDER BY c.full_name
;


-- ------------------------------------------------------------------------------- CO-SCHEMA-A-02 Products nobody bought
-- Merchandising wants to delist products that have never appeared on any order.
SELECT p.product_id, p.product_name
FROM products p
WHERE NOT EXISTS (SELECT 1 FROM order_items oi WHERE oi.product_id = p.product_id)
ORDER BY p.product_name
;


-- ---------------------------------------------------------------------------- CO-SCHEMA-A-03 Win back lapsed customers
-- Marketing wants to win back the customers who used to order but have placed nothing since :cutoff. Show the date of
-- their last order.
SELECT c.customer_id, c.full_name, c.email_address, MAX(o.order_tms) AS last_order_tms
FROM customers c
         JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.full_name, c.email_address
HAVING MAX(o.order_tms) < :cutoff
ORDER BY last_order_tms
;


-- ----------------------------------------------------------------------------- CO-SCHEMA-A-04 Each store's best seller
-- Each store manager wants to know their store's best-selling product by units, over all orders. A tie gives a store
-- more than one row.
SELECT store_name, product_name, units_sold
FROM (SELECT s.store_name,
             p.product_name,
             SUM(oi.quantity)                                                     AS units_sold,
             RANK() OVER (PARTITION BY s.store_id ORDER BY SUM(oi.quantity) DESC) AS rnk
      FROM stores s
               JOIN orders o ON o.store_id = s.store_id
               JOIN order_items oi ON oi.order_id = o.order_id
               JOIN products p ON p.product_id = oi.product_id
      GROUP BY s.store_id, s.store_name, p.product_id, p.product_name)
WHERE rnk = 1
ORDER BY store_name, product_name
;


-- ----------------------------------------------------------------------- CO-SCHEMA-A-05 Rebalance stock between stores
-- Some stores have run out of a product while others hold plenty of it. Before ordering more from suppliers, operations
-- wants to move stock between stores — inventory rebalancing. A store is short of a product below :minStock units, and
-- needs enough to reach :targetStock; a store holding more than :targetStock can give away the excess. For every
-- shortage, propose the nearest store with a surplus of the same product, and how many units to move.
WITH short AS (SELECT store_id, product_id, :targetStock - product_inventory AS need
               FROM inventory
               WHERE product_inventory < :minStock),
     spare AS (SELECT store_id, product_id, product_inventory - :targetStock AS surplus
               FROM inventory
               WHERE product_inventory > :targetStock),
     pairs AS (SELECT sh.store_id                                                                        AS to_store_id,
                      sp.store_id                                                                        AS from_store_id,
                      sh.product_id,
                      sh.need,
                      sp.surplus,
                      ROUND(6371 * ACOS(LEAST(1,
                                              SIN(t.latitude * ACOS(-1) / 180) * SIN(f.latitude * ACOS(-1) / 180)
                                                  + COS(t.latitude * ACOS(-1) / 180) * COS(f.latitude * ACOS(-1) / 180)
                                                  * COS((t.longitude - f.longitude) * ACOS(-1) / 180)))) AS km
               FROM short sh
                        JOIN spare sp ON sp.product_id = sh.product_id AND sp.store_id <> sh.store_id
                        JOIN stores t ON t.store_id = sh.store_id
                        JOIN stores f ON f.store_id = sp.store_id),
     ranked AS (SELECT p.*,
                       ROW_NUMBER() OVER (PARTITION BY to_store_id, product_id
                           ORDER BY km ASC NULLS LAST, surplus DESC) AS rn
                FROM pairs p)
SELECT t.store_name             AS to_store,
       f.store_name             AS from_store,
       r.product_id,
       r.km,
       LEAST(r.need, r.surplus) AS transfer_qty
FROM ranked r
         JOIN stores t ON t.store_id = r.to_store_id
         JOIN stores f ON f.store_id = r.from_store_id
WHERE r.rn = 1
ORDER BY r.km NULLS LAST, t.store_name, r.product_id
;


-- --------------------------------------------------------- CO-SCHEMA-A-06 Customers who come back for the same product
-- Merchandising wants the customers who bought the same product in more than one order -- a sign of a consumable or a
-- favourite.
SELECT c.full_name, p.product_name, COUNT(DISTINCT o.order_id) AS times_ordered
FROM customers c
         JOIN orders o ON o.customer_id = c.customer_id
         JOIN order_items oi ON oi.order_id = o.order_id
         JOIN products p ON p.product_id = oi.product_id
GROUP BY c.customer_id, c.full_name, p.product_id, p.product_name
HAVING COUNT(DISTINCT o.order_id) > 1
ORDER BY times_ordered DESC, c.full_name, p.product_name
;


-- ------------------------------------------------------------- CO-SCHEMA-A-07 Shipments that do not match their orders
-- Logistics audits order lines whose shipment goes to a different customer, or leaves from a different store, than the
-- order they belong to.
SELECT oi.order_id,
       oi.line_item_id,
       oi.shipment_id,
       o.customer_id  AS order_customer_id,
       sh.customer_id AS shipment_customer_id,
       o.store_id     AS order_store_id,
       sh.store_id    AS shipment_store_id
FROM order_items oi
         JOIN orders o ON o.order_id = oi.order_id
         JOIN shipments sh ON sh.shipment_id = oi.shipment_id
WHERE sh.customer_id <> o.customer_id
   OR sh.store_id <> o.store_id
ORDER BY oi.order_id, oi.line_item_id
;


-- ------------------------------------------------------------ CO-SCHEMA-A-08 Complete orders that were never delivered
-- An order is marked COMPLETE when the customer has received it. Customer care wants the complete orders that have at
-- least one line whose shipment is not DELIVERED, or that has no shipment at all.
SELECT o.order_id, o.order_tms, o.customer_id
FROM orders o
WHERE o.order_status = 'COMPLETE'
  AND EXISTS (SELECT 1
              FROM order_items oi
                       LEFT JOIN shipments sh ON sh.shipment_id = oi.shipment_id
              WHERE oi.order_id = o.order_id
                AND (sh.shipment_id IS NULL OR sh.shipment_status <> 'DELIVERED'))
ORDER BY o.order_tms
;


-- --------------------------------------------------------------------------- CO-SCHEMA-A-09 Orders with a premium line
-- Finance wants the orders containing at least one line charged above :price per unit, and, separately, the products
-- whose list price is now above every price they were ever sold at.
SELECT o.order_id, o.order_tms
FROM orders o
WHERE :price < ANY (SELECT oi.unit_price FROM order_items oi WHERE oi.order_id = o.order_id)
ORDER BY o.order_id
;

SELECT p.product_id, p.product_name, p.unit_price
FROM products p
WHERE p.unit_price > ALL (SELECT oi.unit_price FROM order_items oi WHERE oi.product_id = p.product_id)
  AND EXISTS (SELECT 1 FROM order_items oi WHERE oi.product_id = p.product_id)
ORDER BY p.product_name
;


-- ---------------------------------------------------------- CO-SCHEMA-A-10 Masked contact details and order references
-- Customer service shows a masked email on screen -- the first three characters, ***, then the domain -- and quotes
-- orders as ORD-<id>/<year>.
SELECT c.full_name,
       SUBSTR(c.email_address, 1, 3) || '***' || SUBSTR(c.email_address, INSTR(c.email_address, '@'))
                                                                     AS masked_email,
       'ORD-' || o.order_id || '/' || EXTRACT(YEAR FROM o.order_tms) AS order_reference
FROM orders o
         JOIN customers c ON c.customer_id = o.customer_id
WHERE o.order_id = :orderId
;


-- ---------------------------------------------------------------------------- CO-SCHEMA-A-11 This year's orders so far
-- Finance wants the number and value of the orders placed since the start of the current year, by month.
SELECT EXTRACT(MONTH FROM o.order_tms)  AS order_month,
       COUNT(DISTINCT o.order_id)       AS orders,
       SUM(oi.unit_price * oi.quantity) AS revenue
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE o.order_tms >= TRUNC(SYSDATE, 'YYYY')
  AND o.order_tms < LOCALTIMESTAMP
GROUP BY EXTRACT(MONTH FROM o.order_tms)
ORDER BY order_month
;


-- ---------------------------------------------------------------------- CO-SCHEMA-A-12 Packing slips in one round trip
-- The warehouse prints a packing slip for each shipment: the lines it carries and each line's product. Loading the
-- items and then each product lazily costs one query per line; load them together.
SELECT oi.order_id, oi.line_item_id, p.product_name, oi.quantity, sh.delivery_address
FROM shipments sh
         JOIN order_items oi ON oi.shipment_id = sh.shipment_id
         JOIN products p ON p.product_id = oi.product_id
WHERE sh.shipment_id = :shipmentId
ORDER BY oi.order_id, oi.line_item_id
;


-- --------------------------------------------------------------------- CO-SCHEMA-A-13 Store ranking and share of sales
-- The regional manager wants each store's revenue, its rank, its share of the chain's total, and a running total from
-- the best store down.
SELECT s.store_name,
       SUM(oi.unit_price * oi.quantity)                                          AS revenue,
       RANK() OVER (ORDER BY SUM(oi.unit_price * oi.quantity) DESC)              AS revenue_rank,
       ROUND(100 * RATIO_TO_REPORT(SUM(oi.unit_price * oi.quantity)) OVER (), 2) AS share_pct,
       SUM(SUM(oi.unit_price * oi.quantity))
           OVER (ORDER BY SUM(oi.unit_price * oi.quantity) DESC
               ROWS UNBOUNDED PRECEDING)                                         AS running_total
FROM stores s
         JOIN orders o ON o.store_id = s.store_id
         JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY s.store_id, s.store_name
ORDER BY revenue_rank
;

SELECT total, store_name, order_status, order_count, total_sales
FROM store_orders
ORDER BY store_name NULLS LAST, order_status NULLS FIRST
;


-- ------------------------------------------------------------- CO-SCHEMA-A-14 Brands and reviews from the product JSON
-- Merchandising wants every product of a brand with its colour, and the products whose average review score is 8 or
-- better. The brand, colour and reviews live in the JSON document in PRODUCTS.PRODUCT_DETAILS.
SELECT p.product_id,
       p.product_name,
       JSON_VALUE(p.product_details, '$.colour') AS colour
FROM products p
WHERE JSON_VALUE(p.product_details, '$.brand') = :brand
ORDER BY p.product_name
;

SELECT product_name, ROUND(AVG(rating), 2) AS avg_rating, COUNT(*) AS reviews
FROM product_reviews
GROUP BY product_name
HAVING AVG(rating) >= 8
ORDER BY avg_rating DESC, product_name
;
