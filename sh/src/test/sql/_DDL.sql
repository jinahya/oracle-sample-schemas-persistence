create table COUNTRIES
(
    COUNTRY_ID           NUMBER       not null
        constraint COUNTRIES_PK
            primary key,
    COUNTRY_ISO_CODE     CHAR(2)      not null,
    COUNTRY_NAME         VARCHAR2(40) not null,
    COUNTRY_SUBREGION    VARCHAR2(30) not null,
    COUNTRY_SUBREGION_ID NUMBER       not null,
    COUNTRY_REGION       VARCHAR2(20) not null,
    COUNTRY_REGION_ID    NUMBER       not null,
    COUNTRY_TOTAL        VARCHAR2(11) not null,
    COUNTRY_TOTAL_ID     NUMBER       not null
)
/

comment on table COUNTRIES is 'country dimension table (snowflake)'
/

comment on column COUNTRIES.COUNTRY_ID is 'primary key'
/

comment on column COUNTRIES.COUNTRY_NAME is 'country name'
/

comment on column COUNTRIES.COUNTRY_SUBREGION is 'e.g. Western Europe, to allow hierarchies'
/

comment on column COUNTRIES.COUNTRY_REGION is 'e.g. Europe, Asia'
/

create table CUSTOMERS
(
    CUST_ID                NUMBER       not null
        constraint CUSTOMERS_PK
            primary key,
    CUST_FIRST_NAME        VARCHAR2(20) not null,
    CUST_LAST_NAME         VARCHAR2(40) not null,
    CUST_GENDER            CHAR         not null,
    CUST_YEAR_OF_BIRTH     NUMBER(4)    not null,
    CUST_MARITAL_STATUS    VARCHAR2(20),
    CUST_STREET_ADDRESS    VARCHAR2(40) not null,
    CUST_POSTAL_CODE       VARCHAR2(10) not null,
    CUST_CITY              VARCHAR2(30) not null,
    CUST_CITY_ID           NUMBER       not null,
    CUST_STATE_PROVINCE    VARCHAR2(40) not null,
    CUST_STATE_PROVINCE_ID NUMBER       not null,
    COUNTRY_ID             NUMBER       not null
        constraint CUSTOMERS_COUNTRY_FK
            references COUNTRIES,
    CUST_MAIN_PHONE_NUMBER VARCHAR2(25) not null,
    CUST_INCOME_LEVEL      VARCHAR2(30),
    CUST_CREDIT_LIMIT      NUMBER,
    CUST_EMAIL             VARCHAR2(50),
    CUST_TOTAL             VARCHAR2(14) not null,
    CUST_TOTAL_ID          NUMBER       not null,
    CUST_SRC_ID            NUMBER,
    CUST_EFF_FROM          DATE,
    CUST_EFF_TO            DATE,
    CUST_VALID             VARCHAR2(1)
)
/

comment on table CUSTOMERS is 'dimension table'
/

comment on column CUSTOMERS.CUST_ID is 'primary key'
/

comment on column CUSTOMERS.CUST_FIRST_NAME is 'first name of the customer'
/

comment on column CUSTOMERS.CUST_LAST_NAME is 'last name of the customer'
/

comment on column CUSTOMERS.CUST_GENDER is 'gender; low cardinality attribute'
/

comment on column CUSTOMERS.CUST_YEAR_OF_BIRTH is 'customer year of birth'
/

comment on column CUSTOMERS.CUST_MARITAL_STATUS is 'customer marital status; low cardinality attribute'
/

comment on column CUSTOMERS.CUST_STREET_ADDRESS is 'customer street address'
/

comment on column CUSTOMERS.CUST_POSTAL_CODE is 'postal code of the customer'
/

comment on column CUSTOMERS.CUST_CITY is 'city where the customer lives'
/

comment on column CUSTOMERS.CUST_STATE_PROVINCE is 'customer geography: state or province'
/

comment on column CUSTOMERS.COUNTRY_ID is 'foreign key to the countries table (snowflake)'
/

comment on column CUSTOMERS.CUST_MAIN_PHONE_NUMBER is 'customer main phone number'
/

comment on column CUSTOMERS.CUST_INCOME_LEVEL is 'customer income level'
/

comment on column CUSTOMERS.CUST_CREDIT_LIMIT is 'customer credit limit'
/

comment on column CUSTOMERS.CUST_EMAIL is 'customer email id'
/

create bitmap index CUSTOMERS_GENDER_BIX
    on CUSTOMERS (CUST_GENDER)
/

create bitmap index CUSTOMERS_MARITAL_BIX
    on CUSTOMERS (CUST_MARITAL_STATUS)
/

create bitmap index CUSTOMERS_YOB_BIX
    on CUSTOMERS (CUST_YEAR_OF_BIRTH)
/

create table PROMOTIONS
(
    PROMO_ID             NUMBER(6)     not null
        constraint PROMO_PK
            primary key,
    PROMO_NAME           VARCHAR2(30)  not null,
    PROMO_SUBCATEGORY    VARCHAR2(30)  not null,
    PROMO_SUBCATEGORY_ID NUMBER        not null,
    PROMO_CATEGORY       VARCHAR2(30)  not null,
    PROMO_CATEGORY_ID    NUMBER        not null,
    PROMO_COST           NUMBER(10, 2) not null,
    PROMO_BEGIN_DATE     DATE          not null,
    PROMO_END_DATE       DATE          not null,
    PROMO_TOTAL          VARCHAR2(15)  not null,
    PROMO_TOTAL_ID       NUMBER        not null
)
/

comment on table PROMOTIONS is 'dimension table without a PK-FK relationship with the facts table, to show outer join functionality'
/

comment on column PROMOTIONS.PROMO_ID is 'primary key column'
/

comment on column PROMOTIONS.PROMO_NAME is 'promotion description'
/

comment on column PROMOTIONS.PROMO_SUBCATEGORY is 'enables to investigate promotion hierarchies'
/

comment on column PROMOTIONS.PROMO_CATEGORY is 'promotion category'
/

comment on column PROMOTIONS.PROMO_COST is 'promotion cost, to do promotion effect calculations'
/

comment on column PROMOTIONS.PROMO_BEGIN_DATE is 'promotion begin day'
/

comment on column PROMOTIONS.PROMO_END_DATE is 'promotion end day'
/

create table PRODUCTS
(
    PROD_ID               NUMBER(6)      not null
        constraint PRODUCTS_PK
            primary key,
    PROD_NAME             VARCHAR2(50)   not null,
    PROD_DESC             VARCHAR2(4000) not null,
    PROD_SUBCATEGORY      VARCHAR2(50)   not null,
    PROD_SUBCATEGORY_ID   NUMBER         not null,
    PROD_SUBCATEGORY_DESC VARCHAR2(2000) not null,
    PROD_CATEGORY         VARCHAR2(50)   not null,
    PROD_CATEGORY_ID      NUMBER         not null,
    PROD_CATEGORY_DESC    VARCHAR2(2000) not null,
    PROD_WEIGHT_CLASS     NUMBER(3)      not null,
    PROD_UNIT_OF_MEASURE  VARCHAR2(20),
    PROD_PACK_SIZE        VARCHAR2(30)   not null,
    SUPPLIER_ID           NUMBER(6)      not null,
    PROD_STATUS           VARCHAR2(20)   not null,
    PROD_LIST_PRICE       NUMBER(8, 2)   not null,
    PROD_MIN_PRICE        NUMBER(8, 2)   not null,
    PROD_TOTAL            VARCHAR2(13)   not null,
    PROD_TOTAL_ID         NUMBER         not null,
    PROD_SRC_ID           NUMBER,
    PROD_EFF_FROM         DATE,
    PROD_EFF_TO           DATE,
    PROD_VALID            VARCHAR2(1)
)
/

comment on table PRODUCTS is 'dimension table'
/

comment on column PRODUCTS.PROD_ID is 'primary key'
/

comment on column PRODUCTS.PROD_NAME is 'product name'
/

comment on column PRODUCTS.PROD_DESC is 'product description'
/

comment on column PRODUCTS.PROD_SUBCATEGORY is 'product subcategory'
/

comment on column PRODUCTS.PROD_SUBCATEGORY_DESC is 'product subcategory description'
/

comment on column PRODUCTS.PROD_CATEGORY is 'product category'
/

comment on column PRODUCTS.PROD_CATEGORY_DESC is 'product category description'
/

comment on column PRODUCTS.PROD_WEIGHT_CLASS is 'product weight class'
/

comment on column PRODUCTS.PROD_UNIT_OF_MEASURE is 'product unit of measure'
/

comment on column PRODUCTS.PROD_PACK_SIZE is 'product package size'
/

comment on column PRODUCTS.SUPPLIER_ID is 'this column'
/

comment on column PRODUCTS.PROD_STATUS is 'product status'
/

comment on column PRODUCTS.PROD_LIST_PRICE is 'product list price'
/

comment on column PRODUCTS.PROD_MIN_PRICE is 'product minimum price'
/

create bitmap index PRODUCTS_PROD_STATUS_BIX
    on PRODUCTS (PROD_STATUS)
/

create index PRODUCTS_PROD_SUBCAT_IX
    on PRODUCTS (PROD_SUBCATEGORY)
/

create index PRODUCTS_PROD_CAT_IX
    on PRODUCTS (PROD_CATEGORY)
/

create table TIMES
(
    TIME_ID                 DATE        not null
        constraint TIMES_PK
            primary key,
    DAY_NAME                VARCHAR2(9) not null,
    DAY_NUMBER_IN_WEEK      NUMBER(1)   not null,
    DAY_NUMBER_IN_MONTH     NUMBER(2)   not null,
    CALENDAR_WEEK_NUMBER    NUMBER(2)   not null,
    FISCAL_WEEK_NUMBER      NUMBER(2)   not null,
    WEEK_ENDING_DAY         DATE        not null,
    WEEK_ENDING_DAY_ID      NUMBER      not null,
    CALENDAR_MONTH_NUMBER   NUMBER(2)   not null,
    FISCAL_MONTH_NUMBER     NUMBER(2)   not null,
    CALENDAR_MONTH_DESC     VARCHAR2(8) not null,
    CALENDAR_MONTH_ID       NUMBER      not null,
    FISCAL_MONTH_DESC       VARCHAR2(8) not null,
    FISCAL_MONTH_ID         NUMBER      not null,
    DAYS_IN_CAL_MONTH       NUMBER      not null,
    DAYS_IN_FIS_MONTH       NUMBER      not null,
    END_OF_CAL_MONTH        DATE        not null,
    END_OF_FIS_MONTH        DATE        not null,
    CALENDAR_MONTH_NAME     VARCHAR2(9) not null,
    FISCAL_MONTH_NAME       VARCHAR2(9) not null,
    CALENDAR_QUARTER_DESC   CHAR(7)     not null,
    CALENDAR_QUARTER_ID     NUMBER      not null,
    FISCAL_QUARTER_DESC     CHAR(7)     not null,
    FISCAL_QUARTER_ID       NUMBER      not null,
    DAYS_IN_CAL_QUARTER     NUMBER      not null,
    DAYS_IN_FIS_QUARTER     NUMBER      not null,
    END_OF_CAL_QUARTER      DATE        not null,
    END_OF_FIS_QUARTER      DATE        not null,
    CALENDAR_QUARTER_NUMBER NUMBER(1)   not null,
    FISCAL_QUARTER_NUMBER   NUMBER(1)   not null,
    CALENDAR_YEAR           NUMBER(4)   not null,
    CALENDAR_YEAR_ID        NUMBER      not null,
    FISCAL_YEAR             NUMBER(4)   not null,
    FISCAL_YEAR_ID          NUMBER      not null,
    DAYS_IN_CAL_YEAR        NUMBER      not null,
    DAYS_IN_FIS_YEAR        NUMBER      not null,
    END_OF_CAL_YEAR         DATE        not null,
    END_OF_FIS_YEAR         DATE        not null
)
/

comment on table TIMES is 'Time dimension table to support multiple hierarchies and materialized views'
/

comment on column TIMES.TIME_ID is 'primary key; day date, finest granularity, CORRECT ORDER'
/

comment on column TIMES.DAY_NAME is 'Monday to Sunday, repeating'
/

comment on column TIMES.DAY_NUMBER_IN_WEEK is '1 to 7, repeating'
/

comment on column TIMES.DAY_NUMBER_IN_MONTH is '1 to 31, repeating'
/

comment on column TIMES.CALENDAR_WEEK_NUMBER is '1 to 53, repeating'
/

comment on column TIMES.FISCAL_WEEK_NUMBER is '1 to 53, repeating'
/

comment on column TIMES.WEEK_ENDING_DAY is 'date of last day in week, CORRECT ORDER'
/

comment on column TIMES.CALENDAR_MONTH_NUMBER is '1 to 12, repeating'
/

comment on column TIMES.FISCAL_MONTH_NUMBER is '1 to 12, repeating'
/

comment on column TIMES.CALENDAR_MONTH_DESC is 'e.g. 1998-01, CORRECT ORDER'
/

comment on column TIMES.FISCAL_MONTH_DESC is 'e.g. 1998-01, CORRECT ORDER'
/

comment on column TIMES.DAYS_IN_CAL_MONTH is 'e.g. 28,31, repeating'
/

comment on column TIMES.DAYS_IN_FIS_MONTH is 'e.g. 25,32, repeating'
/

comment on column TIMES.END_OF_CAL_MONTH is 'last day of calendar month'
/

comment on column TIMES.END_OF_FIS_MONTH is 'last day of fiscal month'
/

comment on column TIMES.CALENDAR_MONTH_NAME is 'January to December, repeating'
/

comment on column TIMES.FISCAL_MONTH_NAME is 'January to December, repeating'
/

comment on column TIMES.CALENDAR_QUARTER_DESC is 'e.g. 1998-Q1, CORRECT ORDER'
/

comment on column TIMES.FISCAL_QUARTER_DESC is 'e.g. 1999-Q3, CORRECT ORDER'
/

comment on column TIMES.DAYS_IN_CAL_QUARTER is 'e.g. 88,90, repeating'
/

comment on column TIMES.DAYS_IN_FIS_QUARTER is 'e.g. 88,90, repeating'
/

comment on column TIMES.END_OF_CAL_QUARTER is 'last day of calendar quarter'
/

comment on column TIMES.END_OF_FIS_QUARTER is 'last day of fiscal quarter'
/

comment on column TIMES.CALENDAR_QUARTER_NUMBER is '1 to 4, repeating'
/

comment on column TIMES.FISCAL_QUARTER_NUMBER is '1 to 4, repeating'
/

comment on column TIMES.CALENDAR_YEAR is 'e.g. 1999, CORRECT ORDER'
/

comment on column TIMES.FISCAL_YEAR is 'e.g. 1999, CORRECT ORDER'
/

comment on column TIMES.DAYS_IN_CAL_YEAR is '365,366 repeating'
/

comment on column TIMES.DAYS_IN_FIS_YEAR is 'e.g. 355,364, repeating'
/

comment on column TIMES.END_OF_CAL_YEAR is 'last day of cal year'
/

comment on column TIMES.END_OF_FIS_YEAR is 'last day of fiscal year'
/

create table CHANNELS
(
    CHANNEL_ID       NUMBER       not null
        constraint CHANNELS_PK
            primary key,
    CHANNEL_DESC     VARCHAR2(20) not null,
    CHANNEL_CLASS    VARCHAR2(20) not null,
    CHANNEL_CLASS_ID NUMBER       not null,
    CHANNEL_TOTAL    VARCHAR2(13) not null,
    CHANNEL_TOTAL_ID NUMBER       not null
)
/

comment on table CHANNELS is 'small dimension table'
/

comment on column CHANNELS.CHANNEL_ID is 'primary key column'
/

comment on column CHANNELS.CHANNEL_DESC is 'e.g. telesales, internet, catalog'
/

comment on column CHANNELS.CHANNEL_CLASS is 'e.g. direct, indirect'
/

create table SALES
(
    PROD_ID       NUMBER(6)     not null
        constraint SALES_PRODUCT_FK
            references PRODUCTS,
    CUST_ID       NUMBER        not null
        constraint SALES_CUSTOMER_FK
            references CUSTOMERS,
    TIME_ID       DATE          not null
        constraint SALES_TIME_FK
            references TIMES,
    CHANNEL_ID    NUMBER(1)     not null
        constraint SALES_CHANNEL_FK
            references CHANNELS,
    PROMO_ID      NUMBER(6)     not null
        constraint SALES_PROMO_FK
            references PROMOTIONS,
    QUANTITY_SOLD NUMBER(3)     not null,
    AMOUNT_SOLD   NUMBER(10, 2) not null
)
/

comment on table SALES is 'facts table, without a primary key; all rows are uniquely identified by the combination of all foreign keys'
/

comment on column SALES.PROD_ID is 'FK to the products dimension table'
/

comment on column SALES.CUST_ID is 'FK to the customers dimension table'
/

comment on column SALES.TIME_ID is 'FK to the times dimension table'
/

comment on column SALES.CHANNEL_ID is 'FK to the channels dimension table'
/

comment on column SALES.PROMO_ID is 'promotion identifier, without FK constraint (intentionally) to show outer join optimization'
/

comment on column SALES.QUANTITY_SOLD is 'product quantity sold with the transaction'
/

comment on column SALES.AMOUNT_SOLD is 'invoiced amount to the customer'
/

create bitmap index SALES_PROD_BIX
    on SALES (PROD_ID)
/

create bitmap index SALES_CUST_BIX
    on SALES (CUST_ID)
/

create bitmap index SALES_TIME_BIX
    on SALES (TIME_ID)
/

create bitmap index SALES_CHANNEL_BIX
    on SALES (CHANNEL_ID)
/

create bitmap index SALES_PROMO_BIX
    on SALES (PROMO_ID)
/

create table COSTS
(
    PROD_ID    NUMBER        not null
        constraint COSTS_PRODUCT_FK
            references PRODUCTS,
    TIME_ID    DATE          not null
        constraint COSTS_TIME_FK
            references TIMES,
    PROMO_ID   NUMBER        not null
        constraint COSTS_PROMO_FK
            references PROMOTIONS,
    CHANNEL_ID NUMBER        not null
        constraint COSTS_CHANNEL_FK
            references CHANNELS,
    UNIT_COST  NUMBER(10, 2) not null,
    UNIT_PRICE NUMBER(10, 2) not null
)
/

create bitmap index COSTS_PROD_BIX
    on COSTS (PROD_ID)
/

create bitmap index COSTS_TIME_BIX
    on COSTS (TIME_ID)
/

create table SUPPLEMENTARY_DEMOGRAPHICS
(
    CUST_ID        NUMBER not null
        constraint SUPP_DEMO_PK
            primary key,
    EDUCATION      VARCHAR2(21),
    OCCUPATION     VARCHAR2(21),
    HOUSEHOLD_SIZE VARCHAR2(21),
    YRS_RESIDENCE  NUMBER,
    AFFINITY_CARD  NUMBER(10),
    CRICKET        NUMBER(10),
    BASEBALL       NUMBER(10),
    TENNIS         NUMBER(10),
    SOCCER         NUMBER(10),
    GOLF           NUMBER(10),
    UNKNOWN        NUMBER(10),
    MISC           NUMBER(10),
    COMMENTS       VARCHAR2(4000)
)
/

create index SUP_TEXT_IDX
    on SUPPLEMENTARY_DEMOGRAPHICS (COMMENTS)
    indextype is CTXSYS.CONTEXT
/

create materialized view CAL_MONTH_SALES_MV
    refresh force on demand
as
SELECT t.calendar_month_desc,
       SUM(s.amount_sold) AS dollars
FROM sh.sales s,
     sh.times t
WHERE s.time_id = t.time_id
GROUP BY t.calendar_month_desc
/

create materialized view FWEEK_PSCAT_SALES_MV
    refresh force on demand
as
SELECT t.week_ending_day,
       p.prod_subcategory,
       SUM(s.amount_sold) AS dollars,
       s.channel_id,
       s.promo_id
FROM sh.sales s,
     sh.times t,
     sh.products p
WHERE s.time_id = t.time_id
  AND s.prod_id = p.prod_id
GROUP BY t.week_ending_day,
         p.prod_subcategory,
         s.channel_id,
         s.promo_id
/

create view PROFITS as
SELECT s.channel_id,
       s.cust_id,
       s.prod_id,
       s.promo_id,
       s.time_id,
       c.unit_cost,
       c.unit_price,
       s.amount_sold,
       s.quantity_sold,
       c.unit_cost * s.quantity_sold TOTAL_COST
FROM costs c,
     sales s
WHERE c.prod_id = s.prod_id
  AND c.time_id = s.time_id
  AND c.channel_id = s.channel_id
  AND c.promo_id = s.promo_id
/
