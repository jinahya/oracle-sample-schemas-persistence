# The CO schema

## Schema

Every table and view of the schema, as the installed sample schema
([db-sample-schemas](https://github.com/oracle-samples/db-sample-schemas), the `db-sample-schemas` submodule) defines
it. Generated from the data dictionary of a database installed from it.

- **Check**: `NOT NULL`, `PK`, `UNIQUE`, `FK → TABLE.COLUMN`, single-column `CHECK` constraints, and `IDENTITY`
  columns. A `*` marks a column in a constraint over more than one column; those are listed under the table.
- **Description**: the column's comment, as the schema defines it.
- **Notes**: observations about the installed data.

### CUSTOMERS

Table. Details of the people placing orders

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CUSTOMER_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `EMAIL_ADDRESS` | `VARCHAR2(255 CHAR)` | NOT NULL, UNIQUE | The email address the person uses to access the account |  |
| `FULL_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What this customer is called |  |

### CUSTOMER_ORDER_PRODUCTS

View. A summary of who placed each order and what they bought

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `ORDER_ID` | `NUMBER(*,0)` | NOT NULL | The primary key of the order |  |
| `ORDER_TMS` | `TIMESTAMP(6)` | NOT NULL | The date and time the order was placed |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | NOT NULL | The current state of this order |  |
| `CUSTOMER_ID` | `NUMBER(*,0)` | NOT NULL | The primary key of the customer |  |
| `EMAIL_ADDRESS` | `VARCHAR2(255 CHAR)` | NOT NULL | The email address the person uses to access the account |  |
| `FULL_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What this customer is called |  |
| `ORDER_TOTAL` | `NUMBER` |  | The total amount the customer paid for the order |  |
| `ITEMS` | `VARCHAR2(4000 CHAR)` |  | A comma-separated list naming the products in this order |  |

### INVENTORY

Table. Details of the quantity of stock available for products at each location

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `INVENTORY_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `STORE_ID` | `NUMBER(*,0)` | NOT NULL, UNIQUE*, FK → STORES.STORE_ID | Which location the goods are located at |  |
| `PRODUCT_ID` | `NUMBER(*,0)` | NOT NULL, UNIQUE*, FK → PRODUCTS.PRODUCT_ID | Which item this stock is for |  |
| `PRODUCT_INVENTORY` | `NUMBER(*,0)` | NOT NULL | The current quantity in stock |  |

\* Constraints over more than one column:

- `INVENTORY_STORE_PRODUCT_U`: UNIQUE (STORE_ID, PRODUCT_ID)

### ORDERS

Table. Details of who made purchases where

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `ORDER_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `ORDER_TMS` | `TIMESTAMP(6)` | NOT NULL | When the order was placed |  |
| `CUSTOMER_ID` | `NUMBER(*,0)` | NOT NULL, FK → CUSTOMERS.CUSTOMER_ID | Who placed this order |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | NOT NULL, CHECK (order_status in ( 'CANCELLED','COMPLETE','OPEN','PAID','REFUNDED','SHIPPED')) | What state the order is in. Valid values are: OPEN - the order is in progress PAID - money has been received from the customer for this order SHIPPED - the products have been dispatched to the customer COMPLETE - the customer has received the order CANCELLED - the customer has stopped the order REFUNDED - there has been an issue with the order and the money has been returned to the customer |  |
| `STORE_ID` | `NUMBER(*,0)` | NOT NULL, FK → STORES.STORE_ID | Where this order was placed |  |

### ORDER_ITEMS

Table. Details of which products the customer has purchased in an order

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `ORDER_ID` | `NUMBER(*,0)` | NOT NULL, PK*, UNIQUE*, FK → ORDERS.ORDER_ID | The order these products belong to |  |
| `LINE_ITEM_ID` | `NUMBER(*,0)` | NOT NULL, PK* | An incrementing number, starting at one for each order |  |
| `PRODUCT_ID` | `NUMBER(*,0)` | NOT NULL, UNIQUE*, FK → PRODUCTS.PRODUCT_ID | Which item was purchased |  |
| `UNIT_PRICE` | `NUMBER(10,2)` | NOT NULL | How much the customer paid for one item of the product |  |
| `QUANTITY` | `NUMBER(*,0)` | NOT NULL | How many items of this product the customer purchased |  |
| `SHIPMENT_ID` | `NUMBER(*,0)` | FK → SHIPMENTS.SHIPMENT_ID | Where this product will be delivered |  |

\* Constraints over more than one column:

- `ORDER_ITEMS_PK`: PRIMARY KEY (ORDER_ID, LINE_ITEM_ID)
- `ORDER_ITEMS_PRODUCT_U`: UNIQUE (PRODUCT_ID, ORDER_ID)

### PRODUCTS

Table. Details of goods that customers can purchase

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PRODUCT_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What a product is called |  |
| `UNIT_PRICE` | `NUMBER(10,2)` |  | The monetary value of one item of this product |  |
| `PRODUCT_DETAILS` | `BLOB` | CHECK (product_details is json) | Further details of the product stored in JSON format |  |
| `PRODUCT_IMAGE` | `BLOB` |  | A picture of the product |  |
| `IMAGE_MIME_TYPE` | `VARCHAR2(512 CHAR)` |  | The mime-type of the product image |  |
| `IMAGE_FILENAME` | `VARCHAR2(512 CHAR)` |  | The name of the file loaded in the image column |  |
| `IMAGE_CHARSET` | `VARCHAR2(512 CHAR)` |  | The character set used to encode the image |  |
| `IMAGE_LAST_UPDATED` | `DATE` |  | The date the image was last changed |  |

### PRODUCT_ORDERS

View. A summary of the state of the orders placed for each product

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What this product is called |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | NOT NULL | The current state of these order |  |
| `TOTAL_SALES` | `NUMBER` |  | The total value of orders placed |  |
| `ORDER_COUNT` | `NUMBER` |  | The total number of orders placed |  |

### PRODUCT_REVIEWS

View. A relational view of the reviews stored in the JSON for each product

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What this product is called |  |
| `RATING` | `NUMBER` |  | The review score the customer has placed. Range is 1-10 |  |
| `AVG_RATING` | `NUMBER` |  | The mean of the review scores for this product |  |
| `REVIEW` | `VARCHAR2(4000)` |  | The text of the review |  |

### SHIPMENTS

Table. Details of where ordered goods will be delivered

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `SHIPMENT_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `STORE_ID` | `NUMBER(*,0)` | NOT NULL, FK → STORES.STORE_ID | Which location the goods will be transported from |  |
| `CUSTOMER_ID` | `NUMBER(*,0)` | NOT NULL, FK → CUSTOMERS.CUSTOMER_ID | Who this shipment is for |  |
| `DELIVERY_ADDRESS` | `VARCHAR2(512 CHAR)` | NOT NULL | Where the goods will be transported to |  |
| `SHIPMENT_STATUS` | `VARCHAR2(100 CHAR)` | NOT NULL, CHECK (shipment_status in ( 'CREATED', 'SHIPPED', 'IN-TRANSIT', 'DELIVERED')) | The current status of the shipment. Valid values are: CREATED - the shipment is ready for order assignment SHIPPED - the goods have been dispatched IN-TRANSIT - the goods are en-route to their destination DELIVERED - the good have arrived at their destination | Holds `CREATED`, `SHIPPED`, `IN-TRANSIT` (with a hyphen) and `DELIVERED`. |

### STORES

Table. Physical and virtual locations where people can purchase products

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `STORE_ID` | `NUMBER(*,0)` | NOT NULL, PK, IDENTITY | Auto-incrementing primary key |  |
| `STORE_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL, UNIQUE | What the store is called |  |
| `WEB_ADDRESS` | `VARCHAR2(100 CHAR)` | CHECK* | The URL of a virtual store |  |
| `PHYSICAL_ADDRESS` | `VARCHAR2(512 CHAR)` | CHECK* | The postal address of this location |  |
| `LATITUDE` | `NUMBER(9,6)` |  | The north-south position of a physical store |  |
| `LONGITUDE` | `NUMBER(9,6)` |  | The east-west position of a physical store |  |
| `LOGO` | `BLOB` |  | An image used by this store |  |
| `LOGO_MIME_TYPE` | `VARCHAR2(512 CHAR)` |  | The mime-type of the store logo |  |
| `LOGO_FILENAME` | `VARCHAR2(512 CHAR)` |  | The name of the file loaded in the image column |  |
| `LOGO_CHARSET` | `VARCHAR2(512 CHAR)` |  | The character set used to encode the image |  |
| `LOGO_LAST_UPDATED` | `DATE` |  | The date the image was last changed |  |

\* Constraints over more than one column:

- `STORE_AT_LEAST_ONE_ADDRESS_C`: CHECK (web_address IS NOT NULL or physical_address IS NOT NULL)

### STORE_ORDERS

View. A summary of what was purchased at each location, including summaries each store, order status and overall total

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `TOTAL` | `VARCHAR2(12)` |  | Indicates what type of total is displayed, including Store, Status, or Grand Totals |  |
| `STORE_NAME` | `VARCHAR2(255 CHAR)` | NOT NULL | What the store is called |  |
| `ADDRESS` | `VARCHAR2(512 CHAR)` |  | The physical or virtual location of this store |  |
| `LATITUDE` | `NUMBER(9,6)` |  | The north-south position of a physical store |  |
| `LONGITUDE` | `NUMBER(9,6)` |  | The east-west position of a physical store |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | NOT NULL | The current state of this order |  |
| `ORDER_COUNT` | `NUMBER` |  | The total number of orders placed |  |
| `TOTAL_SALES` | `NUMBER` |  | The total value of orders placed |  |
