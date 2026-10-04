# The SH schema

## Schema

Every table and view of the schema, as the installed sample schema
([db-sample-schemas](https://github.com/oracle-samples/db-sample-schemas), the `db-sample-schemas` submodule) defines
it. Generated from the data dictionary of a database installed from it.

- **Check**: `NOT NULL`, `PK`, `UNIQUE`, `FK → TABLE.COLUMN`, single-column `CHECK` constraints, and `IDENTITY`
  columns. A `*` marks a column in a constraint over more than one column; those are listed under the table.
- **Description**: the column's comment, as the schema defines it.
- **Notes**: observations about the installed data.

### CAL_MONTH_SALES_MV

Materialized view.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CALENDAR_MONTH_DESC` | `VARCHAR2(8)` | NOT NULL |  |  |
| `DOLLARS` | `NUMBER` |  |  |  |

### CHANNELS

Table. small dimension table

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CHANNEL_ID` | `NUMBER` | NOT NULL, PK | primary key column |  |
| `CHANNEL_DESC` | `VARCHAR2(20)` | NOT NULL | e.g. telesales, internet, catalog |  |
| `CHANNEL_CLASS` | `VARCHAR2(20)` | NOT NULL | e.g. direct, indirect |  |
| `CHANNEL_CLASS_ID` | `NUMBER` | NOT NULL |  |  |
| `CHANNEL_TOTAL` | `VARCHAR2(13)` | NOT NULL |  |  |
| `CHANNEL_TOTAL_ID` | `NUMBER` | NOT NULL |  |  |

### COSTS

Table.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER` | NOT NULL, FK → PRODUCTS.PROD_ID |  |  |
| `TIME_ID` | `DATE` | NOT NULL, FK → TIMES.TIME_ID |  |  |
| `PROMO_ID` | `NUMBER` | NOT NULL, FK → PROMOTIONS.PROMO_ID |  |  |
| `CHANNEL_ID` | `NUMBER` | NOT NULL, FK → CHANNELS.CHANNEL_ID |  |  |
| `UNIT_COST` | `NUMBER(10,2)` | NOT NULL |  |  |
| `UNIT_PRICE` | `NUMBER(10,2)` | NOT NULL |  |  |

### COUNTRIES

Table. country dimension table (snowflake)

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `COUNTRY_ID` | `NUMBER` | NOT NULL, PK | primary key |  |
| `COUNTRY_ISO_CODE` | `CHAR(2)` | NOT NULL |  |  |
| `COUNTRY_NAME` | `VARCHAR2(40)` | NOT NULL | country name |  |
| `COUNTRY_SUBREGION` | `VARCHAR2(30)` | NOT NULL | e.g. Western Europe, to allow hierarchies |  |
| `COUNTRY_SUBREGION_ID` | `NUMBER` | NOT NULL |  |  |
| `COUNTRY_REGION` | `VARCHAR2(20)` | NOT NULL | e.g. Europe, Asia |  |
| `COUNTRY_REGION_ID` | `NUMBER` | NOT NULL |  |  |
| `COUNTRY_TOTAL` | `VARCHAR2(11)` | NOT NULL |  |  |
| `COUNTRY_TOTAL_ID` | `NUMBER` | NOT NULL |  |  |

### CUSTOMERS

Table. dimension table

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CUST_ID` | `NUMBER` | NOT NULL, PK | primary key |  |
| `CUST_FIRST_NAME` | `VARCHAR2(20)` | NOT NULL | first name of the customer |  |
| `CUST_LAST_NAME` | `VARCHAR2(40)` | NOT NULL | last name of the customer |  |
| `CUST_GENDER` | `CHAR(1)` | NOT NULL | gender; low cardinality attribute |  |
| `CUST_YEAR_OF_BIRTH` | `NUMBER(4)` | NOT NULL | customer year of birth |  |
| `CUST_MARITAL_STATUS` | `VARCHAR2(20)` |  | customer marital status; low cardinality attribute |  |
| `CUST_STREET_ADDRESS` | `VARCHAR2(40)` | NOT NULL | customer street address |  |
| `CUST_POSTAL_CODE` | `VARCHAR2(10)` | NOT NULL | postal code of the customer |  |
| `CUST_CITY` | `VARCHAR2(30)` | NOT NULL | city where the customer lives |  |
| `CUST_CITY_ID` | `NUMBER` | NOT NULL |  |  |
| `CUST_STATE_PROVINCE` | `VARCHAR2(40)` | NOT NULL | customer geography: state or province |  |
| `CUST_STATE_PROVINCE_ID` | `NUMBER` | NOT NULL |  |  |
| `COUNTRY_ID` | `NUMBER` | NOT NULL, FK → COUNTRIES.COUNTRY_ID | foreign key to the countries table (snowflake) |  |
| `CUST_MAIN_PHONE_NUMBER` | `VARCHAR2(25)` | NOT NULL | customer main phone number |  |
| `CUST_INCOME_LEVEL` | `VARCHAR2(30)` |  | customer income level |  |
| `CUST_CREDIT_LIMIT` | `NUMBER` |  | customer credit limit |  |
| `CUST_EMAIL` | `VARCHAR2(50)` |  | customer email id |  |
| `CUST_TOTAL` | `VARCHAR2(14)` | NOT NULL |  |  |
| `CUST_TOTAL_ID` | `NUMBER` | NOT NULL |  |  |
| `CUST_SRC_ID` | `NUMBER` |  |  |  |
| `CUST_EFF_FROM` | `DATE` |  |  |  |
| `CUST_EFF_TO` | `DATE` |  |  |  |
| `CUST_VALID` | `VARCHAR2(1)` |  |  |  |

### FWEEK_PSCAT_SALES_MV

Materialized view.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `WEEK_ENDING_DAY` | `DATE` | NOT NULL |  |  |
| `PROD_SUBCATEGORY` | `VARCHAR2(50)` | NOT NULL |  |  |
| `DOLLARS` | `NUMBER` |  |  |  |
| `CHANNEL_ID` | `NUMBER(1)` | NOT NULL |  |  |
| `PROMO_ID` | `NUMBER(6)` | NOT NULL |  |  |

### PRODUCTS

Table. dimension table

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER(6)` | NOT NULL, PK | primary key |  |
| `PROD_NAME` | `VARCHAR2(50)` | NOT NULL | product name |  |
| `PROD_DESC` | `VARCHAR2(4000)` | NOT NULL | product description |  |
| `PROD_SUBCATEGORY` | `VARCHAR2(50)` | NOT NULL | product subcategory |  |
| `PROD_SUBCATEGORY_ID` | `NUMBER` | NOT NULL |  |  |
| `PROD_SUBCATEGORY_DESC` | `VARCHAR2(2000)` | NOT NULL | product subcategory description |  |
| `PROD_CATEGORY` | `VARCHAR2(50)` | NOT NULL | product category |  |
| `PROD_CATEGORY_ID` | `NUMBER` | NOT NULL |  |  |
| `PROD_CATEGORY_DESC` | `VARCHAR2(2000)` | NOT NULL | product category description |  |
| `PROD_WEIGHT_CLASS` | `NUMBER(3)` | NOT NULL | product weight class |  |
| `PROD_UNIT_OF_MEASURE` | `VARCHAR2(20)` |  | product unit of measure |  |
| `PROD_PACK_SIZE` | `VARCHAR2(30)` | NOT NULL | product package size |  |
| `SUPPLIER_ID` | `NUMBER(6)` | NOT NULL | this column |  |
| `PROD_STATUS` | `VARCHAR2(20)` | NOT NULL | product status |  |
| `PROD_LIST_PRICE` | `NUMBER(8,2)` | NOT NULL | product list price |  |
| `PROD_MIN_PRICE` | `NUMBER(8,2)` | NOT NULL | product minimum price |  |
| `PROD_TOTAL` | `VARCHAR2(13)` | NOT NULL |  |  |
| `PROD_TOTAL_ID` | `NUMBER` | NOT NULL |  |  |
| `PROD_SRC_ID` | `NUMBER` |  |  |  |
| `PROD_EFF_FROM` | `DATE` |  |  |  |
| `PROD_EFF_TO` | `DATE` |  |  |  |
| `PROD_VALID` | `VARCHAR2(1)` |  |  |  |

### PROFITS

View.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CHANNEL_ID` | `NUMBER(1)` | NOT NULL |  |  |
| `CUST_ID` | `NUMBER` | NOT NULL |  |  |
| `PROD_ID` | `NUMBER(6)` | NOT NULL |  |  |
| `PROMO_ID` | `NUMBER(6)` | NOT NULL |  |  |
| `TIME_ID` | `DATE` | NOT NULL |  |  |
| `UNIT_COST` | `NUMBER(10,2)` | NOT NULL |  |  |
| `UNIT_PRICE` | `NUMBER(10,2)` | NOT NULL |  |  |
| `AMOUNT_SOLD` | `NUMBER(10,2)` | NOT NULL |  |  |
| `QUANTITY_SOLD` | `NUMBER(3)` | NOT NULL |  |  |
| `TOTAL_COST` | `NUMBER` |  |  |  |

### PROMOTIONS

Table. dimension table without a PK-FK relationship with the facts table, to show outer join functionality

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PROMO_ID` | `NUMBER(6)` | NOT NULL, PK | primary key column |  |
| `PROMO_NAME` | `VARCHAR2(30)` | NOT NULL | promotion description |  |
| `PROMO_SUBCATEGORY` | `VARCHAR2(30)` | NOT NULL | enables to investigate promotion hierarchies |  |
| `PROMO_SUBCATEGORY_ID` | `NUMBER` | NOT NULL |  |  |
| `PROMO_CATEGORY` | `VARCHAR2(30)` | NOT NULL | promotion category |  |
| `PROMO_CATEGORY_ID` | `NUMBER` | NOT NULL |  |  |
| `PROMO_COST` | `NUMBER(10,2)` | NOT NULL | promotion cost, to do promotion effect calculations |  |
| `PROMO_BEGIN_DATE` | `DATE` | NOT NULL | promotion begin day |  |
| `PROMO_END_DATE` | `DATE` | NOT NULL | promotion end day |  |
| `PROMO_TOTAL` | `VARCHAR2(15)` | NOT NULL |  |  |
| `PROMO_TOTAL_ID` | `NUMBER` | NOT NULL |  |  |

### SALES

Table. facts table, without a primary key; all rows are uniquely identified by the combination of all foreign keys

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER(6)` | NOT NULL, FK → PRODUCTS.PROD_ID | FK to the products dimension table |  |
| `CUST_ID` | `NUMBER` | NOT NULL, FK → CUSTOMERS.CUST_ID | FK to the customers dimension table |  |
| `TIME_ID` | `DATE` | NOT NULL, FK → TIMES.TIME_ID | FK to the times dimension table |  |
| `CHANNEL_ID` | `NUMBER(1)` | NOT NULL, FK → CHANNELS.CHANNEL_ID | FK to the channels dimension table |  |
| `PROMO_ID` | `NUMBER(6)` | NOT NULL, FK → PROMOTIONS.PROMO_ID | promotion identifier, without FK constraint (intentionally) to show outer join optimization | The comment says there is no foreign key, but `SALES_PROMO_FK` references `PROMOTIONS`. |
| `QUANTITY_SOLD` | `NUMBER(3)` | NOT NULL | product quantity sold with the transaction |  |
| `AMOUNT_SOLD` | `NUMBER(10,2)` | NOT NULL | invoiced amount to the customer |  |

### SUPPLEMENTARY_DEMOGRAPHICS

Table.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `CUST_ID` | `NUMBER` | NOT NULL, PK |  |  |
| `EDUCATION` | `VARCHAR2(21)` |  |  |  |
| `OCCUPATION` | `VARCHAR2(21)` |  |  |  |
| `HOUSEHOLD_SIZE` | `VARCHAR2(21)` |  |  |  |
| `YRS_RESIDENCE` | `NUMBER` |  |  |  |
| `AFFINITY_CARD` | `NUMBER(10)` |  |  |  |
| `CRICKET` | `NUMBER(10)` |  |  |  |
| `BASEBALL` | `NUMBER(10)` |  |  |  |
| `TENNIS` | `NUMBER(10)` |  |  |  |
| `SOCCER` | `NUMBER(10)` |  |  |  |
| `GOLF` | `NUMBER(10)` |  |  |  |
| `UNKNOWN` | `NUMBER(10)` |  |  |  |
| `MISC` | `NUMBER(10)` |  |  |  |
| `COMMENTS` | `VARCHAR2(4000)` |  |  |  |

### TIMES

Table. Time dimension table to support multiple hierarchies and materialized views

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `TIME_ID` | `DATE` | NOT NULL, PK | primary key; day date, finest granularity, CORRECT ORDER |  |
| `DAY_NAME` | `VARCHAR2(9)` | NOT NULL | Monday to Sunday, repeating |  |
| `DAY_NUMBER_IN_WEEK` | `NUMBER(1)` | NOT NULL | 1 to 7, repeating |  |
| `DAY_NUMBER_IN_MONTH` | `NUMBER(2)` | NOT NULL | 1 to 31, repeating |  |
| `CALENDAR_WEEK_NUMBER` | `NUMBER(2)` | NOT NULL | 1 to 53, repeating |  |
| `FISCAL_WEEK_NUMBER` | `NUMBER(2)` | NOT NULL | 1 to 53, repeating |  |
| `WEEK_ENDING_DAY` | `DATE` | NOT NULL | date of last day in week, CORRECT ORDER |  |
| `WEEK_ENDING_DAY_ID` | `NUMBER` | NOT NULL |  |  |
| `CALENDAR_MONTH_NUMBER` | `NUMBER(2)` | NOT NULL | 1 to 12, repeating |  |
| `FISCAL_MONTH_NUMBER` | `NUMBER(2)` | NOT NULL | 1 to 12, repeating |  |
| `CALENDAR_MONTH_DESC` | `VARCHAR2(8)` | NOT NULL | e.g. 1998-01, CORRECT ORDER |  |
| `CALENDAR_MONTH_ID` | `NUMBER` | NOT NULL |  |  |
| `FISCAL_MONTH_DESC` | `VARCHAR2(8)` | NOT NULL | e.g. 1998-01, CORRECT ORDER |  |
| `FISCAL_MONTH_ID` | `NUMBER` | NOT NULL |  |  |
| `DAYS_IN_CAL_MONTH` | `NUMBER` | NOT NULL | e.g. 28,31, repeating |  |
| `DAYS_IN_FIS_MONTH` | `NUMBER` | NOT NULL | e.g. 25,32, repeating |  |
| `END_OF_CAL_MONTH` | `DATE` | NOT NULL | last day of calendar month |  |
| `END_OF_FIS_MONTH` | `DATE` | NOT NULL | last day of fiscal month |  |
| `CALENDAR_MONTH_NAME` | `VARCHAR2(9)` | NOT NULL | January to December, repeating |  |
| `FISCAL_MONTH_NAME` | `VARCHAR2(9)` | NOT NULL | January to December, repeating |  |
| `CALENDAR_QUARTER_DESC` | `CHAR(7)` | NOT NULL | e.g. 1998-Q1, CORRECT ORDER |  |
| `CALENDAR_QUARTER_ID` | `NUMBER` | NOT NULL |  |  |
| `FISCAL_QUARTER_DESC` | `CHAR(7)` | NOT NULL | e.g. 1999-Q3, CORRECT ORDER |  |
| `FISCAL_QUARTER_ID` | `NUMBER` | NOT NULL |  |  |
| `DAYS_IN_CAL_QUARTER` | `NUMBER` | NOT NULL | e.g. 88,90, repeating |  |
| `DAYS_IN_FIS_QUARTER` | `NUMBER` | NOT NULL | e.g. 88,90, repeating |  |
| `END_OF_CAL_QUARTER` | `DATE` | NOT NULL | last day of calendar quarter |  |
| `END_OF_FIS_QUARTER` | `DATE` | NOT NULL | last day of fiscal quarter |  |
| `CALENDAR_QUARTER_NUMBER` | `NUMBER(1)` | NOT NULL | 1 to 4, repeating |  |
| `FISCAL_QUARTER_NUMBER` | `NUMBER(1)` | NOT NULL | 1 to 4, repeating |  |
| `CALENDAR_YEAR` | `NUMBER(4)` | NOT NULL | e.g. 1999, CORRECT ORDER |  |
| `CALENDAR_YEAR_ID` | `NUMBER` | NOT NULL |  |  |
| `FISCAL_YEAR` | `NUMBER(4)` | NOT NULL | e.g. 1999, CORRECT ORDER |  |
| `FISCAL_YEAR_ID` | `NUMBER` | NOT NULL |  |  |
| `DAYS_IN_CAL_YEAR` | `NUMBER` | NOT NULL | 365,366 repeating |  |
| `DAYS_IN_FIS_YEAR` | `NUMBER` | NOT NULL | e.g. 355,364, repeating |  |
| `END_OF_CAL_YEAR` | `DATE` | NOT NULL | last day of cal year |  |
| `END_OF_FIS_YEAR` | `DATE` | NOT NULL | last day of fiscal year |  |
