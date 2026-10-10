# Java type mapping of the Oracle sample schemas

Every column of every table and view in `CO`, `HR` and `SH`, with the Oracle type the upstream DDL (`db-sample-schemas/*/*_create.sql`) declares for it, and the Java type of each attribute that maps it — the current one, and the one the definition calls for.

Generated from the DDL and the sources under `src/main/java`. The *fixed* type comes from the declared type alone, never from the sample data:

| Oracle type | Java type |
| --- | --- |
| `NUMBER` (no precision, no scale), and every computed number (`SUM`, `COUNT`, `AVG`, `ROUND`, arithmetic) | `BigDecimal`: up to 38 digits, fractions allowed |
| `NUMBER(p,s)`, `s > 0` | `BigDecimal` |
| `NUMBER(p)` / `NUMBER(p,0)` | the current integral type if it holds `p` digits; otherwise `Short` (p ≤ 4), `Integer` (p ≤ 9), `Long` (p ≤ 18) or `BigInteger` |
| `INTEGER` | `NUMBER(38,0)`, so `BigInteger` |
| `DATE` | `LocalDateTime`: an Oracle `DATE` carries a time of day |
| `TIMESTAMP` | `LocalDateTime` |
| `VARCHAR2`, `CHAR` | `String`, or an enum through `@Enumerated` / `@Convert` |
| `BLOB` | `byte[]`, or a type through `@Convert` |
| a foreign key mapped as a relationship | the referenced entity; its type follows that entity's id |

**Status**: ✓ the current type is the fixed one; **FIX** it is not; — the column is not mapped by that class (or by any class).

**DB min / DB max / Non-integral in DB** are observations, not definitions: what the installed sample data holds in each `NUMBER` column (`INTEGER` included) — its smallest and largest value, and how many of its non-null values have a fractional part, with the smallest and largest of those. They are filled for numeric columns only, read from `localhost:1521/freepdb1` on 2026-10-05, and play no part in the *Fixed* type.

A column can be mapped by several classes — the entity, an id class, an embeddable — and each mapping gets its own row.


## Summary: attributes to fix

47 attribute mappings differ from the type the definition calls for.

| Schema | Object | Column | Oracle type | Class | Attribute | Current | Fixed |
| --- | --- | --- | --- | --- | --- | --- | --- |
| CO | `ORDER_ITEMS` | `LINE_ITEM_ID` | `INTEGER` | [`OrderItemId`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItemId.java#L162) | `lineItemId` | `Long` | `BigInteger` |
| CO | `ORDER_ITEMS` | `QUANTITY` | `INTEGER` | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L439) | `quantity` | `Long` | `BigInteger` |
| CO | `INVENTORY` | `PRODUCT_INVENTORY` | `INTEGER` | [`Inventory`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Inventory.java#L359) | `productInventory` | `Long` | `BigInteger` |
| CO | `PRODUCT_REVIEWS` | `RATING` | `INTEGER` | [`ProductReview`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductReview.java#L253) | `rating` | `Integer` | `BigInteger` |
| CO | `PRODUCT_ORDERS` | `ORDER_COUNT` | `NUMBER` | [`ProductOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductOrder.java#L318) | `orderCount` | `Long` | `BigDecimal` |
| HR | `REGIONS` | `REGION_ID` | `NUMBER` | [`Region`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Region.java#L232) | `regionId` | `Long` | `BigDecimal` |
| SH | `COUNTRIES` | `COUNTRY_ID` | `NUMBER` | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L438) | `countryId` | `Long` | `BigDecimal` |
| SH | `COUNTRIES` | `COUNTRY_SUBREGION_ID` | `NUMBER` | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L459) | `countrySubregionId` | `Long` | `BigDecimal` |
| SH | `COUNTRIES` | `COUNTRY_REGION_ID` | `NUMBER` | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L468) | `countryRegionId` | `Long` | `BigDecimal` |
| SH | `COUNTRIES` | `COUNTRY_TOTAL_ID` | `NUMBER` | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L477) | `countryTotalId` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_ID` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1029) | `custId` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_CITY_ID` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1125) | `custCityId` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_STATE_PROVINCE_ID` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1145) | `custStateProvinceId` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_CREDIT_LIMIT` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1185) | `custCreditLimit` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_TOTAL_ID` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1215) | `custTotalId` | `Long` | `BigDecimal` |
| SH | `CUSTOMERS` | `CUST_SRC_ID` | `NUMBER` | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1223) | `custSrcId` | `Long` | `BigDecimal` |
| SH | `PROMOTIONS` | `PROMO_SUBCATEGORY_ID` | `NUMBER` | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L564) | `promoSubcategoryId` | `Long` | `BigDecimal` |
| SH | `PROMOTIONS` | `PROMO_CATEGORY_ID` | `NUMBER` | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L584) | `promoCategoryId` | `Long` | `BigDecimal` |
| SH | `PROMOTIONS` | `PROMO_TOTAL_ID` | `NUMBER` | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L634) | `promoTotalId` | `Long` | `BigDecimal` |
| SH | `PRODUCTS` | `PROD_SUBCATEGORY_ID` | `NUMBER` | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1050) | `prodSubcategoryId` | `Long` | `BigDecimal` |
| SH | `PRODUCTS` | `PROD_CATEGORY_ID` | `NUMBER` | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1082) | `prodCategoryId` | `Long` | `BigDecimal` |
| SH | `PRODUCTS` | `PROD_TOTAL_ID` | `NUMBER` | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1193) | `prodTotalId` | `Long` | `BigDecimal` |
| SH | `PRODUCTS` | `PROD_SRC_ID` | `NUMBER` | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1201) | `prodSrcId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `WEEK_ENDING_DAY_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1644) | `weekEndingDayId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `CALENDAR_MONTH_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1686) | `calendarMonthId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `FISCAL_MONTH_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1706) | `fiscalMonthId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_CAL_MONTH` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1715) | `daysInCalMonth` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_FIS_MONTH` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1724) | `daysInFisMonth` | `Long` | `BigDecimal` |
| SH | `TIMES` | `CALENDAR_QUARTER_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1784) | `calendarQuarterId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `FISCAL_QUARTER_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1804) | `fiscalQuarterId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_CAL_QUARTER` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1813) | `daysInCalQuarter` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_FIS_QUARTER` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1822) | `daysInFisQuarter` | `Long` | `BigDecimal` |
| SH | `TIMES` | `CALENDAR_YEAR_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1882) | `calendarYearId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `FISCAL_YEAR_ID` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1901) | `fiscalYearId` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_CAL_YEAR` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1910) | `daysInCalYear` | `Long` | `BigDecimal` |
| SH | `TIMES` | `DAYS_IN_FIS_YEAR` | `NUMBER` | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1919) | `daysInFisYear` | `Long` | `BigDecimal` |
| SH | `CHANNELS` | `CHANNEL_ID` | `NUMBER` | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L329) | `channelId` | `Long` | `BigDecimal` |
| SH | `CHANNELS` | `CHANNEL_CLASS_ID` | `NUMBER` | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L360) | `value` | `Long` | `BigDecimal` |
| SH | `CHANNELS` | `CHANNEL_TOTAL_ID` | `NUMBER` | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L380) | `channelTotalId` | `Long` | `BigDecimal` |
| SH | `SALES` | `CUST_ID` | `NUMBER` | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L270) | `custId` | `Long` | `BigDecimal` |
| SH | `COSTS` | `PROD_ID` | `NUMBER` | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L232) | `prodId` | `Integer` | `BigDecimal` |
| SH | `COSTS` | `PROMO_ID` | `NUMBER` | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L240) | `promoId` | `Integer` | `BigDecimal` |
| SH | `COSTS` | `CHANNEL_ID` | `NUMBER` | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L244) | `channelId` | `Long` | `BigDecimal` |
| SH | `SUPPLEMENTARY_DEMOGRAPHICS` | `CUST_ID` | `NUMBER` | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L687) | `custId` | `Long` | `BigDecimal` |
| SH | `SUPPLEMENTARY_DEMOGRAPHICS` | `YRS_RESIDENCE` | `NUMBER` | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L725) | `yrsResidence` | `Long` | `BigDecimal` |
| SH | `PROFITS` | `CUST_ID` | `NUMBER` | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L569) | `custId` | `Long` | `BigDecimal` |
| SH | `PROFITS` | `CUST_ID` | `NUMBER` | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L271) | `custId` | `Long` | `BigDecimal` |

## CO (Customer Orders)


### `CUSTOMERS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CUSTOMER_ID` | `INTEGER` | co_create.sql:64 | `1` | `399` | none | [`Customer`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Customer.java#L339) | `customerId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `EMAIL_ADDRESS` | `VARCHAR2(255 CHAR)` | co_create.sql:65 | | | [`Customer`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Customer.java#L353) | `emailAddress` | `String` | `String` | ✓ |  |
| `FULL_NAME` | `VARCHAR2(255 CHAR)` | co_create.sql:66 | | | [`Customer`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Customer.java#L364) | `fullName` | `String` | `String` | ✓ |  |

### `STORES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `STORE_ID` | `INTEGER` | co_create.sql:77 | `1` | `23` | none | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L855) | `storeId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `STORE_NAME` | `VARCHAR2(255 CHAR)` | co_create.sql:78 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L867) | `storeName` | `String` | `String` | ✓ |  |
| `WEB_ADDRESS` | `VARCHAR2(100 CHAR)` | co_create.sql:79 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L878) | `webAddress` | `String` | `String` | ✓ |  |
| `PHYSICAL_ADDRESS` | `VARCHAR2(512 CHAR)` | co_create.sql:80 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L889) | `physicalAddress` | `String` | `String` | ✓ |  |
| `LATITUDE` | `NUMBER(9,6)` | co_create.sql:81 | `-34.61016` | `52.5161` | 22 of 22 (`-34.61016` … `52.5161`) | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L904) | `latitude` | `BigDecimal` | `BigDecimal` | ✓ | scale 6 |
| `LONGITUDE` | `NUMBER(9,6)` | co_create.sql:82 | `-122.33221` | `151.143826` | 22 of 22 (`-122.33221` … `151.143826`) | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L919) | `longitude` | `BigDecimal` | `BigDecimal` | ✓ | scale 6 |
| `LOGO` | `BLOB` | co_create.sql:83 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L922) | `logo` (@Lob) | `byte[]` | `byte[]` | ✓ |  |
| `LOGO` | `BLOB` | co_create.sql:83 | | | [`StoreLogo → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L219) | `bytes` (@AttributeOverride) | `byte[]` | `byte[]` | ✓ |  |
| `LOGO_MIME_TYPE` | `VARCHAR2(512 CHAR)` | co_create.sql:84 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L936) | `logoMimeType` | `String` | `String` | ✓ |  |
| `LOGO_MIME_TYPE` | `VARCHAR2(512 CHAR)` | co_create.sql:84 | | | [`StoreLogo → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L222) | `mimeType` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `LOGO_FILENAME` | `VARCHAR2(512 CHAR)` | co_create.sql:85 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L947) | `logoFilename` | `String` | `String` | ✓ |  |
| `LOGO_FILENAME` | `VARCHAR2(512 CHAR)` | co_create.sql:85 | | | [`StoreLogo → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L225) | `filename` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `LOGO_CHARSET` | `VARCHAR2(512 CHAR)` | co_create.sql:86 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L958) | `logoCharset` | `String` | `String` | ✓ |  |
| `LOGO_CHARSET` | `VARCHAR2(512 CHAR)` | co_create.sql:86 | | | [`StoreLogo → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L228) | `charset` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `LOGO_LAST_UPDATED` | `DATE` | co_create.sql:87 | | | [`Store`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Store.java#L963) | `logoLastUpdated` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `LOGO_LAST_UPDATED` | `DATE` | co_create.sql:87 | | | [`StoreLogo → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L231) | `lastUpdated` (@AttributeOverride) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |

### `PRODUCTS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PRODUCT_ID` | `INTEGER` | co_create.sql:98 | `1` | `46` | none | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L624) | `productId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | co_create.sql:99 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L635) | `productName` | `String` | `String` | ✓ |  |
| `UNIT_PRICE` | `NUMBER(10,2)` | co_create.sql:100 | `5.65` | `49.12` | 44 of 46 (`5.65` … `49.12`) | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L648) | `unitPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `PRODUCT_DETAILS` | `BLOB` | co_create.sql:101 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L652) | `productDetails` (@Lob) | `byte[]` | `byte[]` | ✓ |  |
| `PRODUCT_IMAGE` | `BLOB` | co_create.sql:102 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L659) | `productImage` (@Lob) | `byte[]` | `byte[]` | ✓ |  |
| `PRODUCT_IMAGE` | `BLOB` | co_create.sql:102 | | | [`ProductImage → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L219) | `bytes` (@AttributeOverride) | `byte[]` | `byte[]` | ✓ |  |
| `IMAGE_MIME_TYPE` | `VARCHAR2(512 CHAR)` | co_create.sql:103 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L672) | `imageMimeType` | `String` | `String` | ✓ |  |
| `IMAGE_MIME_TYPE` | `VARCHAR2(512 CHAR)` | co_create.sql:103 | | | [`ProductImage → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L222) | `mimeType` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `IMAGE_FILENAME` | `VARCHAR2(512 CHAR)` | co_create.sql:104 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L683) | `imageFilename` | `String` | `String` | ✓ |  |
| `IMAGE_FILENAME` | `VARCHAR2(512 CHAR)` | co_create.sql:104 | | | [`ProductImage → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L225) | `filename` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `IMAGE_CHARSET` | `VARCHAR2(512 CHAR)` | co_create.sql:105 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L694) | `imageCharset` | `String` | `String` | ✓ |  |
| `IMAGE_CHARSET` | `VARCHAR2(512 CHAR)` | co_create.sql:105 | | | [`ProductImage → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L228) | `charset` (@AttributeOverride) | `String` | `String` | ✓ |  |
| `IMAGE_LAST_UPDATED` | `DATE` | co_create.sql:106 | | | [`Product`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Product.java#L699) | `imageLastUpdated` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `IMAGE_LAST_UPDATED` | `DATE` | co_create.sql:106 | | | [`ProductImage → _Binary`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/_Binary.java#L231) | `lastUpdated` (@AttributeOverride) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |

### `ORDERS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ORDER_ID` | `INTEGER` | co_create.sql:116 | `1` | `1950` | none | [`Order`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Order.java#L640) | `orderId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `ORDER_TMS` | `TIMESTAMP` | co_create.sql:117 | | | [`Order`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Order.java#L647) | `orderTms` | `LocalDateTime` | `LocalDateTime` | ✓ |  |
| `CUSTOMER_ID` | `INTEGER` | co_create.sql:118 | `1` | `392` | none | [`Order`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Order.java#L656) | `customer` (FK, @ManyToOne) | `Customer` | `Customer` | ✓ | follows the referenced id |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | co_create.sql:119 | | | [`Order`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Order.java#L665) | `orderStatus` (@Enumerated) | `OrderStatus` | `OrderStatus` | ✓ |  |
| `STORE_ID` | `INTEGER` | co_create.sql:120 | `1` | `23` | none | [`Order`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Order.java#L674) | `store` (FK, @ManyToOne) | `Store` | `Store` | ✓ | follows the referenced id |

### `SHIPMENTS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `SHIPMENT_ID` | `INTEGER` | co_create.sql:130 | `1` | `2026` | none | [`Shipment`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Shipment.java#L493) | `shipmentId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `STORE_ID` | `INTEGER` | co_create.sql:131 | `1` | `1` | none | [`Shipment`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Shipment.java#L502) | `store` (FK, @ManyToOne) | `Store` | `Store` | ✓ | follows the referenced id |
| `CUSTOMER_ID` | `INTEGER` | co_create.sql:132 | `1` | `392` | none | [`Shipment`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Shipment.java#L511) | `customer` (FK, @ManyToOne) | `Customer` | `Customer` | ✓ | follows the referenced id |
| `DELIVERY_ADDRESS` | `VARCHAR2(512 CHAR)` | co_create.sql:133 | | | [`Shipment`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Shipment.java#L524) | `deliveryAddress` | `String` | `String` | ✓ |  |
| `SHIPMENT_STATUS` | `VARCHAR2(100 CHAR)` | co_create.sql:134 | | | [`Shipment`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Shipment.java#L535) | `shipmentStatus` (@Convert) | `ShipmentStatus` | `ShipmentStatus` | ✓ |  |

### `ORDER_ITEMS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ORDER_ID` | `INTEGER` | co_create.sql:144 | `1` | `1950` | none | [`OrderItemId`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItemId.java#L155) | `orderId` | `Long` | `Long` | ✓ | Annex A: FK → `ORDERS.ORDER_ID` |
| `ORDER_ID` | `INTEGER` | co_create.sql:144 | `1` | `1950` | none | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L404) | `order` (FK, @ManyToOne, @MapsId) | `Order` | `Order` | ✓ | follows the referenced id |
| `LINE_ITEM_ID` | `INTEGER` | co_create.sql:145 | `1` | `3` | none | [`OrderItemId`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItemId.java#L162) | `lineItemId` | `Long` | `BigInteger` | **FIX** | 38 digits |
| `PRODUCT_ID` | `INTEGER` | co_create.sql:146 | `2` | `46` | none | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L418) | `product` (FK, @ManyToOne) | `Product` | `Product` | ✓ | follows the referenced id |
| `UNIT_PRICE` | `NUMBER(10,2)` | co_create.sql:147 | `5.65` | `49.12` | 3747 of 3914 (`5.65` … `49.12`) | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L433) | `unitPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `QUANTITY` | `INTEGER` | co_create.sql:148 | `1` | `5` | none | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L439) | `quantity` | `Long` | `BigInteger` | **FIX** | 38 digits |
| `SHIPMENT_ID` | `INTEGER` | co_create.sql:149 | `1` | `2020` | none | [`OrderItem`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/OrderItem.java#L452) | `shipment` (FK, @ManyToOne) | `Shipment` | `Shipment` | ✓ | follows the referenced id |

### `INVENTORY` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `INVENTORY_ID` | `INTEGER` | co_create.sql:159 | `1` | `566` | none | [`Inventory`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Inventory.java#L337) | `inventoryId` (@Id) | `Long` | `Long` | ✓ | Annex A: identity |
| `STORE_ID` | `INTEGER` | co_create.sql:160 | `1` | `23` | none | [`Inventory`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Inventory.java#L345) | `store` (FK, @ManyToOne) | `Store` | `Store` | ✓ | follows the referenced id |
| `PRODUCT_ID` | `INTEGER` | co_create.sql:161 | `1` | `46` | none | [`Inventory`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Inventory.java#L352) | `product` (FK, @ManyToOne) | `Product` | `Product` | ✓ | follows the referenced id |
| `PRODUCT_INVENTORY` | `INTEGER` | co_create.sql:162 | `0` | `25` | none | [`Inventory`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/Inventory.java#L359) | `productInventory` | `Long` | `BigInteger` | **FIX** | 38 digits |

### `CUSTOMER_ORDER_PRODUCTS` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ORDER_ID` | `INTEGER` | `ORDERS.ORDER_ID` (co_create.sql:116) | `1` | `1950` | none | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L412) | `orderId` (@Id) | `Long` | `Long` | ✓ | Annex A: passes `ORDERS.ORDER_ID` through |
| `ORDER_TMS` | `TIMESTAMP` | `ORDERS.ORDER_TMS` (co_create.sql:117) | | | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L417) | `orderTms` | `LocalDateTime` | `LocalDateTime` | ✓ |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | `ORDERS.ORDER_STATUS` (co_create.sql:119) | | | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L427) | `orderStatus` | `String` | `String` | ✓ |  |
| `CUSTOMER_ID` | `INTEGER` | `CUSTOMERS.CUSTOMER_ID` (co_create.sql:64) | `1` | `392` | none | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L432) | `customerId` | `Long` | `Long` | ✓ | Annex A: passes `CUSTOMERS.CUSTOMER_ID` through |
| `EMAIL_ADDRESS` | `VARCHAR2(255 CHAR)` | `CUSTOMERS.EMAIL_ADDRESS` (co_create.sql:65) | | | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L442) | `emailAddress` | `String` | `String` | ✓ |  |
| `FULL_NAME` | `VARCHAR2(255 CHAR)` | `CUSTOMERS.FULL_NAME` (co_create.sql:66) | | | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L452) | `fullName` | `String` | `String` | ✓ |  |
| `ORDER_TOTAL` | `NUMBER` | `SUM(oi.quantity * oi.unit_price)` (co_create.sql:173) | `8.66` | `529.4` | 1909 of 1950 (`8.66` … `529.4`) | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L456) | `orderTotal` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |
| `ITEMS` | `VARCHAR2(4000)` | `LISTAGG(p.product_name, ', ' ON OVERFLOW TRUNCATE ...)` (co_create.sql:173) | | | [`CustomerOrderProduct`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/CustomerOrderProduct.java#L465) | `items` | `String` | `String` | ✓ |  |

### `STORE_ORDERS` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `TOTAL` | `VARCHAR2(12)` | `CASE grouping_id(...) WHEN ... THEN 'STORE TOTAL'/'STATUS TOTAL'/'GRAND TOTAL' END` (co_create.sql:195) | | | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L415) | `total` | `String` | `String` | ✓ |  |
| `STORE_NAME` | `VARCHAR2(255 CHAR)` | `STORES.STORE_NAME` (co_create.sql:78) | | | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L424) | `storeName` | `String` | `String` | ✓ |  |
| `ADDRESS` | `VARCHAR2(512 CHAR)` | `COALESCE(s.web_address, s.physical_address)` (co_create.sql:195) | | | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L433) | `address` | `String` | `String` | ✓ |  |
| `LATITUDE` | `NUMBER(9,6)` | `STORES.LATITUDE` (co_create.sql:81) | `-34.61016` | `52.5161` | 52 of 52 (`-34.61016` … `52.5161`) | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L437) | `latitude` | `BigDecimal` | `BigDecimal` | ✓ | scale 6 |
| `LONGITUDE` | `NUMBER(9,6)` | `STORES.LONGITUDE` (co_create.sql:82) | `-122.33221` | `151.143826` | 52 of 52 (`-122.33221` … `151.143826`) | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L441) | `longitude` | `BigDecimal` | `BigDecimal` | ✓ | scale 6 |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | `ORDERS.ORDER_STATUS` (co_create.sql:119) | | | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L450) | `orderStatus` | `String` | `String` | ✓ |  |
| `ORDER_COUNT` | `NUMBER` | `COUNT(DISTINCT o.order_id)` (co_create.sql:195) | `1` | `1950` | none | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L454) | `orderCount` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |
| `TOTAL_SALES` | `NUMBER` | `SUM(oi.quantity * oi.unit_price)` (co_create.sql:195) | `145.17` | `308598.53` | 60 of 60 (`145.17` … `308598.53`) | [`StoreOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/StoreOrder.java#L458) | `totalSales` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |

### `PRODUCT_REVIEWS` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | `PRODUCTS.PRODUCT_NAME` (co_create.sql:99) | | | [`ProductReview`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductReview.java#L249) | `productName` | `String` | `String` | ✓ |  |
| `RATING` | `INTEGER` | `JSON_TABLE ... rating INTEGER PATH '$.rating'` (co_create.sql:223) | `1` | `10` | none | [`ProductReview`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductReview.java#L253) | `rating` | `Integer` | `BigInteger` | **FIX** | 38 digits |
| `AVG_RATING` | `NUMBER` | `ROUND(AVG(r.rating) OVER (PARTITION BY product_name), 2)` (co_create.sql:223) | `2.67` | `10` | 226 of 261 (`2.67` … `8.5`) | [`ProductReview`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductReview.java#L257) | `avgRating` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |
| `REVIEW` | `VARCHAR2(4000)` | `JSON_TABLE ... review VARCHAR2(4000) PATH '$.review'` (co_create.sql:223) | | | [`ProductReview`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductReview.java#L266) | `review` | `String` | `String` | ✓ |  |

### `PRODUCT_ORDERS` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PRODUCT_NAME` | `VARCHAR2(255 CHAR)` | `PRODUCTS.PRODUCT_NAME` (co_create.sql:99) | | | [`ProductOrderId`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductOrderId.java#L172) | `productName` | `String` | `String` | ✓ |  |
| `ORDER_STATUS` | `VARCHAR2(10 CHAR)` | `ORDERS.ORDER_STATUS` (co_create.sql:119) | | | [`ProductOrderId`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductOrderId.java#L181) | `orderStatus` | `String` | `String` | ✓ |  |
| `TOTAL_SALES` | `NUMBER` | `SUM(oi.quantity * oi.unit_price)` (co_create.sql:247) | `16.95` | `15520.64` | 103 of 109 (`16.95` … `15520.64`) | [`ProductOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductOrder.java#L314) | `totalSales` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |
| `ORDER_COUNT` | `NUMBER` | `COUNT(*)` (co_create.sql:247) | `1` | `146` | none | [`ProductOrder`](oracle-sample-schemas-persistence-co/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/co/ProductOrder.java#L318) | `orderCount` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |

## HR (Human Resources)


### `REGIONS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `REGION_ID` | `NUMBER` | hr_create.sql:64 | `10` | `50` | none | [`Region`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Region.java#L232) | `regionId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `REGION_NAME` | `VARCHAR2(25)` | hr_create.sql:66 | | | [`Region`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Region.java#L244) | `regionName` | `String` | `String` | ✓ |  |

### `COUNTRIES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `COUNTRY_ID` | `CHAR(2)` | hr_create.sql:85 | | | [`Country`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Country.java#L291) | `countryId` (@Id) | `String` | `String` | ✓ |  |
| `COUNTRY_NAME` | `VARCHAR2(60)` | hr_create.sql:87 | | | [`Country`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Country.java#L299) | `countryName` | `String` | `String` | ✓ |  |
| `REGION_ID` | `NUMBER` | hr_create.sql:88 | `10` | `50` | none | [`Country`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Country.java#L309) | `region` (FK, @ManyToOne) | `Region` | `Region` | ✓ | follows the referenced id |

### `LOCATIONS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `LOCATION_ID` | `NUMBER(4)` | hr_create.sql:107 | `1000` | `3200` | none | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L509) | `locationId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `STREET_ADDRESS` | `VARCHAR2(40)` | hr_create.sql:108 | | | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L521) | `streetAddress` | `String` | `String` | ✓ |  |
| `POSTAL_CODE` | `VARCHAR2(12)` | hr_create.sql:109 | | | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L532) | `postalCode` | `String` | `String` | ✓ |  |
| `CITY` | `VARCHAR2(30)` | hr_create.sql:110 | | | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L544) | `city` | `String` | `String` | ✓ |  |
| `STATE_PROVINCE` | `VARCHAR2(25)` | hr_create.sql:112 | | | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L555) | `stateProvince` | `String` | `String` | ✓ |  |
| `COUNTRY_ID` | `CHAR(2)` | hr_create.sql:113 | | | [`Location`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Location.java#L566) | `country` (FK, @ManyToOne) | `Country` | `Country` | ✓ | follows the referenced id |

### `DEPARTMENTS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `DEPARTMENT_ID` | `NUMBER(4)` | hr_create.sql:144 | `10` | `270` | none | [`Department`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Department.java#L413) | `departmentId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `DEPARTMENT_NAME` | `VARCHAR2(30)` | hr_create.sql:145 | | | [`Department`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Department.java#L422) | `departmentName` | `String` | `String` | ✓ |  |
| `MANAGER_ID` | `NUMBER(6)` | hr_create.sql:147 | `100` | `205` | none | [`Department`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Department.java#L433) | `manager` (FK, @ManyToOne) | `Employee` | `Employee` | ✓ | follows the referenced id |
| `LOCATION_ID` | `NUMBER(4)` | hr_create.sql:148 | `1400` | `2700` | none | [`Department`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Department.java#L444) | `location` (FK, @ManyToOne) | `Location` | `Location` | ✓ | follows the referenced id |

### `JOBS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `JOB_ID` | `VARCHAR2(10)` | hr_create.sql:179 | | | [`Job`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Job.java#L566) | `jobId` (@Id) | `String` | `String` | ✓ |  |
| `JOB_TITLE` | `VARCHAR2(35)` | hr_create.sql:180 | | | [`Job`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Job.java#L578) | `jobTitle` | `String` | `String` | ✓ |  |
| `MIN_SALARY` | `NUMBER(6)` | hr_create.sql:182 | `2008` | `20080` | none | [`Job`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Job.java#L591) | `minSalary` | `Integer` | `Integer` | ✓ |  |
| `MAX_SALARY` | `NUMBER(6)` | hr_create.sql:183 | `5000` | `40000` | none | [`Job`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Job.java#L604) | `maxSalary` | `Integer` | `Integer` | ✓ |  |

### `EMPLOYEES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | hr_create.sql:202 | `100` | `206` | none | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L956) | `employeeId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `FIRST_NAME` | `VARCHAR2(20)` | hr_create.sql:203 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L970) | `firstName` | `String` | `String` | ✓ |  |
| `LAST_NAME` | `VARCHAR2(25)` | hr_create.sql:204 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L984) | `lastName` | `String` | `String` | ✓ |  |
| `EMAIL` | `VARCHAR2(25)` | hr_create.sql:206 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L998) | `email` | `String` | `String` | ✓ |  |
| `PHONE_NUMBER` | `VARCHAR2(20)` | hr_create.sql:208 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1009) | `phoneNumber` | `String` | `String` | ✓ |  |
| `HIRE_DATE` | `DATE` | hr_create.sql:209 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1016) | `hireDate` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `JOB_ID` | `VARCHAR2(10)` | hr_create.sql:211 | | | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1033) | `job` (FK, @ManyToOne) | `Job` | `Job` | ✓ | follows the referenced id |
| `SALARY` | `NUMBER(8,2)` | hr_create.sql:213 | `2100` | `24000` | none | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1043) | `salary` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `COMMISSION_PCT` | `NUMBER(2,2)` | hr_create.sql:214 | `0.1` | `0.4` | 35 of 35 (`0.1` … `0.4`) | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1051) | `commissionPct` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `MANAGER_ID` | `NUMBER(6)` | hr_create.sql:215 | `100` | `205` | none | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1067) | `manager` (FK, @ManyToOne) | `Employee` | `Employee` | ✓ | follows the referenced id |
| `DEPARTMENT_ID` | `NUMBER(4)` | hr_create.sql:216 | `10` | `110` | none | [`Employee`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/Employee.java#L1083) | `department` (FK, @ManyToOne) | `Department` | `Department` | ✓ | follows the referenced id |

### `JOB_HISTORY` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | hr_create.sql:266 | `101` | `201` | none | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L497) | `employeeId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `EMPLOYEE_ID` | `NUMBER(6)` | hr_create.sql:266 | `101` | `201` | none | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L509) | `employee` (FK, @ManyToOne) | `Employee` | `Employee` | ✓ | follows the referenced id |
| `EMPLOYEE_ID` | `NUMBER(6)` | hr_create.sql:266 | `101` | `201` | none | [`JobHistoryId`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistoryId.java#L161) | `employeeId` | `Integer` | `Integer` | ✓ |  |
| `START_DATE` | `DATE` | hr_create.sql:268 | | | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L521) | `startDate` (@Id) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `START_DATE` | `DATE` | hr_create.sql:268 | | | [`JobHistoryId`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistoryId.java#L172) | `startDate` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `END_DATE` | `DATE` | hr_create.sql:270 | | | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L530) | `endDate` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `JOB_ID` | `VARCHAR2(10)` | hr_create.sql:272 | | | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L546) | `job` (FK, @ManyToOne) | `Job` | `Job` | ✓ | follows the referenced id |
| `DEPARTMENT_ID` | `NUMBER(4)` | hr_create.sql:274 | `20` | `110` | none | [`JobHistory`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/JobHistory.java#L558) | `department` (FK, @ManyToOne) | `Department` | `Department` | ✓ | follows the referenced id |

### `EMP_DETAILS_VIEW` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | `EMPLOYEES.EMPLOYEE_ID` (hr_create.sql:202) | `100` | `206` | none | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L761) | `employeeId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `JOB_ID` | `VARCHAR2(10)` | `EMPLOYEES.JOB_ID` (hr_create.sql:211) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L768) | `jobId` | `String` | `String` | ✓ |  |
| `MANAGER_ID` | `NUMBER(6)` | `EMPLOYEES.MANAGER_ID` (hr_create.sql:215) | `100` | `205` | none | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L773) | `managerId` | `Integer` | `Integer` | ✓ |  |
| `DEPARTMENT_ID` | `NUMBER(4)` | `EMPLOYEES.DEPARTMENT_ID` (hr_create.sql:216) | `10` | `110` | none | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L778) | `departmentId` | `Integer` | `Integer` | ✓ |  |
| `LOCATION_ID` | `NUMBER(4)` | `DEPARTMENTS.LOCATION_ID` (hr_create.sql:148) | `1400` | `2700` | none | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L782) | `locationId` | `Integer` | `Integer` | ✓ |  |
| `COUNTRY_ID` | `CHAR(2)` | `LOCATIONS.COUNTRY_ID` (hr_create.sql:113) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L788) | `countryId` | `String` | `String` | ✓ |  |
| `FIRST_NAME` | `VARCHAR2(20)` | `EMPLOYEES.FIRST_NAME` (hr_create.sql:203) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L793) | `firstName` | `String` | `String` | ✓ |  |
| `LAST_NAME` | `VARCHAR2(25)` | `EMPLOYEES.LAST_NAME` (hr_create.sql:204) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L799) | `lastName` | `String` | `String` | ✓ |  |
| `SALARY` | `NUMBER(8,2)` | `EMPLOYEES.SALARY` (hr_create.sql:213) | `2100` | `24000` | none | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L804) | `salary` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `COMMISSION_PCT` | `NUMBER(2,2)` | `EMPLOYEES.COMMISSION_PCT` (hr_create.sql:214) | `0.1` | `0.4` | 34 of 34 (`0.1` … `0.4`) | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L809) | `commissionPct` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `DEPARTMENT_NAME` | `VARCHAR2(30)` | `DEPARTMENTS.DEPARTMENT_NAME` (hr_create.sql:145) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L816) | `departmentName` | `String` | `String` | ✓ |  |
| `JOB_TITLE` | `VARCHAR2(35)` | `JOBS.JOB_TITLE` (hr_create.sql:180) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L823) | `jobTitle` | `String` | `String` | ✓ |  |
| `CITY` | `VARCHAR2(30)` | `LOCATIONS.CITY` (hr_create.sql:110) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L830) | `city` | `String` | `String` | ✓ |  |
| `STATE_PROVINCE` | `VARCHAR2(25)` | `LOCATIONS.STATE_PROVINCE` (hr_create.sql:112) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L836) | `stateProvince` | `String` | `String` | ✓ |  |
| `COUNTRY_NAME` | `VARCHAR2(60)` | `COUNTRIES.COUNTRY_NAME` (hr_create.sql:87) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L842) | `countryName` | `String` | `String` | ✓ |  |
| `REGION_NAME` | `VARCHAR2(25)` | `REGIONS.REGION_NAME` (hr_create.sql:66) | | | [`EmpDetailsView`](oracle-sample-schemas-persistence-hr/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/hr/EmpDetailsView.java#L848) | `regionName` | `String` | `String` | ✓ |  |

## SH (Sales History)


### `COUNTRIES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `COUNTRY_ID` | `NUMBER` | sh_create.sql:64 | `52769` | `52803` | none | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L438) | `countryId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `COUNTRY_ISO_CODE` | `CHAR(2)` | sh_create.sql:65 | | | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L445) | `countryIsoCode` | `String` | `String` | ✓ |  |
| `COUNTRY_NAME` | `VARCHAR2(40)` | sh_create.sql:66 | | | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L450) | `countryName` | `String` | `String` | ✓ |  |
| `COUNTRY_SUBREGION` | `VARCHAR2(30)` | sh_create.sql:67 | | | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L455) | `countrySubregion` | `String` | `String` | ✓ |  |
| `COUNTRY_SUBREGION_ID` | `NUMBER` | sh_create.sql:68 | `52792` | `52799` | none | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L459) | `countrySubregionId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `COUNTRY_REGION` | `VARCHAR2(20)` | sh_create.sql:69 | | | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L464) | `countryRegion` | `String` | `String` | ✓ |  |
| `COUNTRY_REGION_ID` | `NUMBER` | sh_create.sql:70 | `52798` | `52805` | none | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L468) | `countryRegionId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `COUNTRY_TOTAL` | `VARCHAR2(11)` | sh_create.sql:71 | | | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L473) | `countryTotal` | `String` | `String` | ✓ |  |
| `COUNTRY_TOTAL_ID` | `NUMBER` | sh_create.sql:72 | `52806` | `52806` | none | [`Country`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Country.java#L477) | `countryTotalId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |

### `CUSTOMERS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CUST_ID` | `NUMBER` | sh_create.sql:84 | `1` | `104500` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1029) | `custId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_FIRST_NAME` | `VARCHAR2(20)` | sh_create.sql:85 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1040) | `custFirstName` | `String` | `String` | ✓ |  |
| `CUST_LAST_NAME` | `VARCHAR2(40)` | sh_create.sql:86 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1051) | `custLastName` | `String` | `String` | ✓ |  |
| `CUST_GENDER` | `CHAR(1)` | sh_create.sql:87 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1062) | `custGender` | `String` | `String` | ✓ |  |
| `CUST_YEAR_OF_BIRTH` | `NUMBER(4)` | sh_create.sql:88 | `1924` | `2001` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1073) | `custYearOfBirth` | `Integer` | `Integer` | ✓ |  |
| `CUST_MARITAL_STATUS` | `VARCHAR2(20)` | sh_create.sql:89 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1083) | `custMaritalStatus` | `String` | `String` | ✓ |  |
| `CUST_STREET_ADDRESS` | `VARCHAR2(40)` | sh_create.sql:90 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1094) | `custStreetAddress` | `String` | `String` | ✓ |  |
| `CUST_POSTAL_CODE` | `VARCHAR2(10)` | sh_create.sql:91 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1105) | `custPostalCode` | `String` | `String` | ✓ |  |
| `CUST_CITY` | `VARCHAR2(30)` | sh_create.sql:92 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1116) | `custCity` | `String` | `String` | ✓ |  |
| `CUST_CITY_ID` | `NUMBER` | sh_create.sql:93 | `51040` | `52531` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1125) | `custCityId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_STATE_PROVINCE` | `VARCHAR2(40)` | sh_create.sql:94 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1136) | `custStateProvince` | `String` | `String` | ✓ |  |
| `CUST_STATE_PROVINCE_ID` | `NUMBER` | sh_create.sql:95 | `52533` | `52771` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1145) | `custStateProvinceId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `COUNTRY_ID` | `NUMBER` | sh_create.sql:96 | `52769` | `52791` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1156) | `country` (FK, @ManyToOne) | `Country` | `Country` | ✓ | follows the referenced id |
| `CUST_MAIN_PHONE_NUMBER` | `VARCHAR2(25)` | sh_create.sql:97 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1167) | `custMainPhoneNumber` | `String` | `String` | ✓ |  |
| `CUST_INCOME_LEVEL` | `VARCHAR2(30)` | sh_create.sql:98 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1177) | `custIncomeLevel` | `String` | `String` | ✓ |  |
| `CUST_CREDIT_LIMIT` | `NUMBER` | sh_create.sql:99 | `1500` | `15000` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1185) | `custCreditLimit` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_EMAIL` | `VARCHAR2(50)` | sh_create.sql:100 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1195) | `custEmail` | `String` | `String` | ✓ |  |
| `CUST_TOTAL` | `VARCHAR2(14)` | sh_create.sql:101 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1206) | `custTotal` | `String` | `String` | ✓ |  |
| `CUST_TOTAL_ID` | `NUMBER` | sh_create.sql:102 | `52772` | `52772` | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1215) | `custTotalId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_SRC_ID` | `NUMBER` | sh_create.sql:103 | all NULL | all NULL | none | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1223) | `custSrcId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_EFF_FROM` | `DATE` | sh_create.sql:104 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1231) | `custEffFrom` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `CUST_EFF_TO` | `DATE` | sh_create.sql:105 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1239) | `custEffTo` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `CUST_VALID` | `VARCHAR2(1)` | sh_create.sql:106 | | | [`Customer`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Customer.java#L1249) | `custValid` | `String` | `String` | ✓ |  |

### `PROMOTIONS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PROMO_ID` | `NUMBER(6)` | sh_create.sql:120 | `33` | `999` | none | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L533) | `promoId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `PROMO_NAME` | `VARCHAR2(30)` | sh_create.sql:121 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L544) | `promoName` | `String` | `String` | ✓ |  |
| `PROMO_SUBCATEGORY` | `VARCHAR2(30)` | sh_create.sql:122 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L555) | `promoSubcategory` | `String` | `String` | ✓ |  |
| `PROMO_SUBCATEGORY_ID` | `NUMBER` | sh_create.sql:123 | `11` | `32` | none | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L564) | `promoSubcategoryId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROMO_CATEGORY` | `VARCHAR2(30)` | sh_create.sql:124 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L575) | `promoCategory` | `String` | `String` | ✓ |  |
| `PROMO_CATEGORY_ID` | `NUMBER` | sh_create.sql:125 | `2` | `10` | none | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L584) | `promoCategoryId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROMO_COST` | `NUMBER(10,2)` | sh_create.sql:126 | `0` | `100000` | none | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L596) | `promoCost` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `PROMO_BEGIN_DATE` | `DATE` | sh_create.sql:127 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L605) | `promoBeginDate` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROMO_END_DATE` | `DATE` | sh_create.sql:128 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L614) | `promoEndDate` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROMO_TOTAL` | `VARCHAR2(15)` | sh_create.sql:129 | | | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L625) | `promoTotal` | `String` | `String` | ✓ |  |
| `PROMO_TOTAL_ID` | `NUMBER` | sh_create.sql:130 | `1` | `1` | none | [`Promotion`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Promotion.java#L634) | `promoTotalId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |

### `PRODUCTS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER(6)` | sh_create.sql:142 | `13` | `148` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1006) | `prodId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `PROD_NAME` | `VARCHAR2(50)` | sh_create.sql:143 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1018) | `prodName` | `String` | `String` | ✓ |  |
| `PROD_DESC` | `VARCHAR2(4000)` | sh_create.sql:144 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1029) | `prodDesc` | `String` | `String` | ✓ |  |
| `PROD_SUBCATEGORY` | `VARCHAR2(50)` | sh_create.sql:145 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1041) | `prodSubcategory` | `String` | `String` | ✓ |  |
| `PROD_SUBCATEGORY_ID` | `NUMBER` | sh_create.sql:146 | `2011` | `2056` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1050) | `prodSubcategoryId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROD_SUBCATEGORY_DESC` | `VARCHAR2(2000)` | sh_create.sql:147 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1061) | `prodSubcategoryDesc` | `String` | `String` | ✓ |  |
| `PROD_CATEGORY` | `VARCHAR2(50)` | sh_create.sql:148 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1073) | `prodCategory` | `String` | `String` | ✓ |  |
| `PROD_CATEGORY_ID` | `NUMBER` | sh_create.sql:149 | `201` | `205` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1082) | `prodCategoryId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROD_CATEGORY_DESC` | `VARCHAR2(2000)` | sh_create.sql:150 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1093) | `prodCategoryDesc` | `String` | `String` | ✓ |  |
| `PROD_WEIGHT_CLASS` | `NUMBER(3)` | sh_create.sql:151 | `1` | `4` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1105) | `prodWeightClass` | `Integer` | `Integer` | ✓ |  |
| `PROD_UNIT_OF_MEASURE` | `VARCHAR2(20)` | sh_create.sql:152 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1115) | `prodUnitOfMeasure` | `String` | `String` | ✓ |  |
| `PROD_PACK_SIZE` | `VARCHAR2(30)` | sh_create.sql:153 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1126) | `prodPackSize` | `String` | `String` | ✓ |  |
| `SUPPLIER_ID` | `NUMBER(6)` | sh_create.sql:154 | `1` | `1` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1136) | `supplierId` | `Integer` | `Integer` | ✓ |  |
| `PROD_STATUS` | `VARCHAR2(20)` | sh_create.sql:155 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1147) | `prodStatus` | `String` | `String` | ✓ |  |
| `PROD_LIST_PRICE` | `NUMBER(8,2)` | sh_create.sql:156 | `6.99` | `1299.99` | 72 of 72 (`6.99` … `1299.99`) | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1160) | `prodListPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `PROD_MIN_PRICE` | `NUMBER(8,2)` | sh_create.sql:157 | `6.99` | `1299.99` | 72 of 72 (`6.99` … `1299.99`) | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1173) | `prodMinPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `PROD_TOTAL` | `VARCHAR2(13)` | sh_create.sql:158 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1184) | `prodTotal` | `String` | `String` | ✓ |  |
| `PROD_TOTAL_ID` | `NUMBER` | sh_create.sql:159 | `1` | `1` | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1193) | `prodTotalId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROD_SRC_ID` | `NUMBER` | sh_create.sql:160 | all NULL | all NULL | none | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1201) | `prodSrcId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROD_EFF_FROM` | `DATE` | sh_create.sql:161 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1209) | `prodEffFrom` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROD_EFF_TO` | `DATE` | sh_create.sql:162 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1217) | `prodEffTo` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROD_VALID` | `VARCHAR2(1)` | sh_create.sql:163 | | | [`Product`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Product.java#L1227) | `prodValid` | `String` | `String` | ✓ |  |

### `TIMES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `TIME_ID` | `DATE` | sh_create.sql:175 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1570) | `timeId` (@Id) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `DAY_NAME` | `VARCHAR2(9)` | sh_create.sql:176 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1582) | `dayName` | `String` | `String` | ✓ |  |
| `DAY_NUMBER_IN_WEEK` | `NUMBER(1)` | sh_create.sql:177 | `1` | `7` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1593) | `dayNumberInWeek` | `Integer` | `Integer` | ✓ |  |
| `DAY_NUMBER_IN_MONTH` | `NUMBER(2)` | sh_create.sql:178 | `1` | `31` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1604) | `dayNumberInMonth` | `Integer` | `Integer` | ✓ |  |
| `CALENDAR_WEEK_NUMBER` | `NUMBER(2)` | sh_create.sql:179 | `1` | `53` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1615) | `calendarWeekNumber` | `Integer` | `Integer` | ✓ |  |
| `FISCAL_WEEK_NUMBER` | `NUMBER(2)` | sh_create.sql:180 | `1` | `53` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1626) | `fiscalWeekNumber` | `Integer` | `Integer` | ✓ |  |
| `WEEK_ENDING_DAY` | `DATE` | sh_create.sql:181 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1635) | `weekEndingDay` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `WEEK_ENDING_DAY_ID` | `NUMBER` | sh_create.sql:182 | `1462` | `2257` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1644) | `weekEndingDayId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CALENDAR_MONTH_NUMBER` | `NUMBER(2)` | sh_create.sql:183 | `1` | `12` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1655) | `calendarMonthNumber` | `Integer` | `Integer` | ✓ |  |
| `FISCAL_MONTH_NUMBER` | `NUMBER(2)` | sh_create.sql:184 | `1` | `12` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1666) | `fiscalMonthNumber` | `Integer` | `Integer` | ✓ |  |
| `CALENDAR_MONTH_DESC` | `VARCHAR2(8)` | sh_create.sql:185 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1677) | `calendarMonthDesc` | `String` | `String` | ✓ |  |
| `CALENDAR_MONTH_ID` | `NUMBER` | sh_create.sql:186 | `1672` | `2223` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1686) | `calendarMonthId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `FISCAL_MONTH_DESC` | `VARCHAR2(8)` | sh_create.sql:187 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1697) | `fiscalMonthDesc` | `String` | `String` | ✓ |  |
| `FISCAL_MONTH_ID` | `NUMBER` | sh_create.sql:188 | `1720` | `2258` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1706) | `fiscalMonthId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_CAL_MONTH` | `NUMBER` | sh_create.sql:189 | `28` | `31` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1715) | `daysInCalMonth` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_FIS_MONTH` | `NUMBER` | sh_create.sql:190 | `25` | `35` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1724) | `daysInFisMonth` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `END_OF_CAL_MONTH` | `DATE` | sh_create.sql:191 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1733) | `endOfCalMonth` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `END_OF_FIS_MONTH` | `DATE` | sh_create.sql:192 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1742) | `endOfFisMonth` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `CALENDAR_MONTH_NAME` | `VARCHAR2(9)` | sh_create.sql:193 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1753) | `calendarMonthName` | `String` | `String` | ✓ |  |
| `FISCAL_MONTH_NAME` | `VARCHAR2(9)` | sh_create.sql:194 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1764) | `fiscalMonthName` | `String` | `String` | ✓ |  |
| `CALENDAR_QUARTER_DESC` | `CHAR(7)` | sh_create.sql:195 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1775) | `calendarQuarterDesc` | `String` | `String` | ✓ |  |
| `CALENDAR_QUARTER_ID` | `NUMBER` | sh_create.sql:196 | `1769` | `2150` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1784) | `calendarQuarterId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `FISCAL_QUARTER_DESC` | `CHAR(7)` | sh_create.sql:197 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1795) | `fiscalQuarterDesc` | `String` | `String` | ✓ |  |
| `FISCAL_QUARTER_ID` | `NUMBER` | sh_create.sql:198 | `1785` | `2260` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1804) | `fiscalQuarterId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_CAL_QUARTER` | `NUMBER` | sh_create.sql:199 | `90` | `92` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1813) | `daysInCalQuarter` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_FIS_QUARTER` | `NUMBER` | sh_create.sql:200 | `1` | `98` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1822) | `daysInFisQuarter` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `END_OF_CAL_QUARTER` | `DATE` | sh_create.sql:201 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1831) | `endOfCalQuarter` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `END_OF_FIS_QUARTER` | `DATE` | sh_create.sql:202 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1840) | `endOfFisQuarter` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `CALENDAR_QUARTER_NUMBER` | `NUMBER(1)` | sh_create.sql:203 | `1` | `4` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1851) | `calendarQuarterNumber` | `Integer` | `Integer` | ✓ |  |
| `FISCAL_QUARTER_NUMBER` | `NUMBER(1)` | sh_create.sql:204 | `1` | `4` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1862) | `fiscalQuarterNumber` | `Integer` | `Integer` | ✓ |  |
| `CALENDAR_YEAR` | `NUMBER(4)` | sh_create.sql:205 | `2019` | `2023` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1873) | `calendarYear` | `Integer` | `Integer` | ✓ |  |
| `CALENDAR_YEAR_ID` | `NUMBER` | sh_create.sql:206 | `1802` | `1813` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1882) | `calendarYearId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `FISCAL_YEAR` | `NUMBER(4)` | sh_create.sql:207 | `2019` | `2024` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1892) | `fiscalYear` | `Integer` | `Integer` | ✓ |  |
| `FISCAL_YEAR_ID` | `NUMBER` | sh_create.sql:208 | `1806` | `2259` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1901) | `fiscalYearId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_CAL_YEAR` | `NUMBER` | sh_create.sql:209 | `365` | `366` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1910) | `daysInCalYear` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `DAYS_IN_FIS_YEAR` | `NUMBER` | sh_create.sql:210 | `361` | `371` | none | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1919) | `daysInFisYear` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `END_OF_CAL_YEAR` | `DATE` | sh_create.sql:211 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1928) | `endOfCalYear` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `END_OF_FIS_YEAR` | `DATE` | sh_create.sql:212 | | | [`Time`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Time.java#L1937) | `endOfFisYear` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |

### `CHANNELS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CHANNEL_ID` | `NUMBER` | sh_create.sql:224 | `2` | `9` | none | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L329) | `channelId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CHANNEL_DESC` | `VARCHAR2(20)` | sh_create.sql:225 | | | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L340) | `channelDesc` | `String` | `String` | ✓ |  |
| `CHANNEL_CLASS` | `VARCHAR2(20)` | sh_create.sql:226 | | | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L351) | `key` | `String` | `String` | ✓ |  |
| `CHANNEL_CLASS_ID` | `NUMBER` | sh_create.sql:227 | `12` | `14` | none | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L360) | `value` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CHANNEL_TOTAL` | `VARCHAR2(13)` | sh_create.sql:228 | | | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L371) | `channelTotal` | `String` | `String` | ✓ |  |
| `CHANNEL_TOTAL_ID` | `NUMBER` | sh_create.sql:229 | `1` | `1` | none | [`Channel`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Channel.java#L380) | `channelTotalId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |

### `SALES` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER(6)` | sh_create.sql:241 | `13` | `148` | none | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L529) | `product` (FK, @ManyToOne) | `Product` | `Product` | ✓ | follows the referenced id |
| `PROD_ID` | `NUMBER(6)` | sh_create.sql:241 | `13` | `148` | none | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L266) | `prodId` | `Integer` | `Integer` | ✓ |  |
| `CUST_ID` | `NUMBER` | sh_create.sql:242 | `2` | `101000` | none | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L541) | `customer` (FK, @ManyToOne) | `Customer` | `Customer` | ✓ | follows the referenced id |
| `CUST_ID` | `NUMBER` | sh_create.sql:242 | `2` | `101000` | none | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L270) | `custId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `TIME_ID` | `DATE` | sh_create.sql:243 | | | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L553) | `time` (FK, @ManyToOne) | `Time` | `Time` | ✓ | follows the referenced id |
| `TIME_ID` | `DATE` | sh_create.sql:243 | | | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L274) | `timeId` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `CHANNEL_ID` | `NUMBER(1)` | sh_create.sql:244 | `2` | `9` | none | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L565) | `channel` (FK, @ManyToOne) | `Channel` | `Channel` | ✓ | follows the referenced id |
| `CHANNEL_ID` | `NUMBER(1)` | sh_create.sql:244 | `2` | `9` | none | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L280) | `channelId` | `Long` | `Long` | ✓ |  |
| `PROMO_ID` | `NUMBER(6)` | sh_create.sql:245 | `33` | `999` | none | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L577) | `promotion` (FK, @ManyToOne) | `Promotion` | `Promotion` | ✓ | follows the referenced id |
| `PROMO_ID` | `NUMBER(6)` | sh_create.sql:245 | `33` | `999` | none | [`SaleId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SaleId.java#L286) | `promoId` | `Integer` | `Integer` | ✓ |  |
| `QUANTITY_SOLD` | `NUMBER(3)` | sh_create.sql:246 | `1` | `1` | none | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L584) | `quantitySold` | `Integer` | `Integer` | ✓ |  |
| `AMOUNT_SOLD` | `NUMBER(10,2)` | sh_create.sql:247 | `6.4` | `1782.72` | 909840 of 918843 (`6.4` … `1782.72`) | [`Sale`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Sale.java#L595) | `amountSold` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |

### `COSTS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `PROD_ID` | `NUMBER` | sh_create.sql:304 | `13` | `148` | none | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L446) | `product` (FK, @ManyToOne) | `Product` | `Product` | ✓ | follows the referenced id |
| `PROD_ID` | `NUMBER` | sh_create.sql:304 | `13` | `148` | none | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L232) | `prodId` | `Integer` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `TIME_ID` | `DATE` | sh_create.sql:305 | | | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L458) | `time` (FK, @ManyToOne) | `Time` | `Time` | ✓ | follows the referenced id |
| `TIME_ID` | `DATE` | sh_create.sql:305 | | | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L236) | `timeId` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROMO_ID` | `NUMBER` | sh_create.sql:306 | `350` | `999` | none | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L470) | `promotion` (FK, @ManyToOne) | `Promotion` | `Promotion` | ✓ | follows the referenced id |
| `PROMO_ID` | `NUMBER` | sh_create.sql:306 | `350` | `999` | none | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L240) | `promoId` | `Integer` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CHANNEL_ID` | `NUMBER` | sh_create.sql:307 | `2` | `4` | none | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L482) | `channel` (FK, @ManyToOne) | `Channel` | `Channel` | ✓ | follows the referenced id |
| `CHANNEL_ID` | `NUMBER` | sh_create.sql:307 | `2` | `4` | none | [`CostId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CostId.java#L244) | `channelId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `UNIT_COST` | `NUMBER(10,2)` | sh_create.sql:308 | `-23.74` | `1342.06` | 81019 of 82112 (`-23.74` … `1342.06`) | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L493) | `unitCost` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `UNIT_PRICE` | `NUMBER(10,2)` | sh_create.sql:309 | `6.4` | `1782.72` | 81233 of 82112 (`6.4` … `1782.72`) | [`Cost`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Cost.java#L504) | `unitPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |

### `SUPPLEMENTARY_DEMOGRAPHICS` (table)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CUST_ID` | `NUMBER` | sh_create.sql:374 | `100001` | `104500` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L687) | `custId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `EDUCATION` | `VARCHAR2(21)` | sh_create.sql:375 | | | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L697) | `education` | `String` | `String` | ✓ |  |
| `OCCUPATION` | `VARCHAR2(21)` | sh_create.sql:376 | | | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L707) | `occupation` | `String` | `String` | ✓ |  |
| `HOUSEHOLD_SIZE` | `VARCHAR2(21)` | sh_create.sql:377 | | | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L717) | `householdSize` | `String` | `String` | ✓ |  |
| `YRS_RESIDENCE` | `NUMBER` | sh_create.sql:378 | `0` | `14` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L725) | `yrsResidence` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `AFFINITY_CARD` | `NUMBER(10)` | sh_create.sql:379 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L735) | `affinityCard` | `Long` | `Long` | ✓ |  |
| `CRICKET` | `NUMBER(10)` | sh_create.sql:380 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L744) | `cricket` | `Long` | `Long` | ✓ |  |
| `BASEBALL` | `NUMBER(10)` | sh_create.sql:381 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L753) | `baseball` | `Long` | `Long` | ✓ |  |
| `TENNIS` | `NUMBER(10)` | sh_create.sql:382 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L762) | `tennis` | `Long` | `Long` | ✓ |  |
| `SOCCER` | `NUMBER(10)` | sh_create.sql:383 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L771) | `soccer` | `Long` | `Long` | ✓ |  |
| `GOLF` | `NUMBER(10)` | sh_create.sql:384 | `1` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L780) | `golf` | `Long` | `Long` | ✓ |  |
| `UNKNOWN` | `NUMBER(10)` | sh_create.sql:385 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L791) | `unknown` | `Long` | `Long` | ✓ |  |
| `MISC` | `NUMBER(10)` | sh_create.sql:386 | `0` | `1` | none | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L800) | `misc` | `Long` | `Long` | ✓ |  |
| `COMMENTS` | `VARCHAR2(4000)` | sh_create.sql:387 | | | [`SupplementaryDemographics`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/SupplementaryDemographics.java#L810) | `comments` | `String` | `String` | ✓ |  |

### `PROFITS` (view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CHANNEL_ID` | `NUMBER(1)` | `SALES.CHANNEL_ID` (sh_create.sql:244) | `2` | `4` | none | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L560) | `channelId` (@Id) | `Long` | `Long` | ✓ |  |
| `CHANNEL_ID` | `NUMBER(1)` | `SALES.CHANNEL_ID` (sh_create.sql:244) | `2` | `4` | none | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L267) | `channelId` | `Long` | `Long` | ✓ |  |
| `CUST_ID` | `NUMBER` | `SALES.CUST_ID` (sh_create.sql:242) | `2` | `50840` | none | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L569) | `custId` (@Id) | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `CUST_ID` | `NUMBER` | `SALES.CUST_ID` (sh_create.sql:242) | `2` | `50840` | none | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L271) | `custId` | `Long` | `BigDecimal` | **FIX** | unconstrained NUMBER: up to 38 digits, any scale |
| `PROD_ID` | `NUMBER(6)` | `SALES.PROD_ID` (sh_create.sql:241) | `13` | `148` | none | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L579) | `prodId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `PROD_ID` | `NUMBER(6)` | `SALES.PROD_ID` (sh_create.sql:241) | `13` | `148` | none | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L277) | `prodId` | `Integer` | `Integer` | ✓ |  |
| `PROMO_ID` | `NUMBER(6)` | `SALES.PROMO_ID` (sh_create.sql:245) | `350` | `999` | none | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L589) | `promoId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `PROMO_ID` | `NUMBER(6)` | `SALES.PROMO_ID` (sh_create.sql:245) | `350` | `999` | none | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L283) | `promoId` | `Integer` | `Integer` | ✓ |  |
| `TIME_ID` | `DATE` | `SALES.TIME_ID` (sh_create.sql:243) | | | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L598) | `timeId` (@Id) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `TIME_ID` | `DATE` | `SALES.TIME_ID` (sh_create.sql:243) | | | [`ProfitId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/ProfitId.java#L287) | `timeId` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `UNIT_COST` | `NUMBER(10,2)` | `COSTS.UNIT_COST` (sh_create.sql:308) | `-23.74` | `1342.06` | 901951 of 916039 (`-23.74` … `1342.06`) | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L609) | `unitCost` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `UNIT_PRICE` | `NUMBER(10,2)` | `COSTS.UNIT_PRICE` (sh_create.sql:309) | `6.4` | `1782.72` | 907020 of 916039 (`6.4` … `1782.72`) | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L620) | `unitPrice` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `AMOUNT_SOLD` | `NUMBER(10,2)` | `SALES.AMOUNT_SOLD` (sh_create.sql:247) | `6.4` | `1782.72` | 907036 of 916039 (`6.4` … `1782.72`) | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L631) | `amountSold` | `BigDecimal` | `BigDecimal` | ✓ | scale 2 |
| `QUANTITY_SOLD` | `NUMBER(3)` | `SALES.QUANTITY_SOLD` (sh_create.sql:246) | `1` | `1` | none | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L638) | `quantitySold` | `Integer` | `Integer` | ✓ |  |
| `TOTAL_COST` | `NUMBER` | `c.unit_cost * s.quantity_sold` (sh_create.sql:400) | `-23.74` | `1342.06` | 901951 of 916039 (`-23.74` … `1342.06`) | [`Profit`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/Profit.java#L642) | `totalCost` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |

### `CAL_MONTH_SALES_MV` (materialized view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `CALENDAR_MONTH_DESC` | `VARCHAR2(8)` | `TIMES.CALENDAR_MONTH_DESC` (sh_create.sql:185) | | | [`CalMonthSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CalMonthSalesMv.java#L184) | `calendarMonthDesc` (@Id) | `String` | `String` | ✓ |  |
| `DOLLARS` | `NUMBER` | `SUM(s.amount_sold)` (sh_create.sql:425) | `1573272.66` | `2547042.11` | 47 of 48 (`1573272.66` … `2547042.11`) | [`CalMonthSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CalMonthSalesMv.java#L188) | `dollars` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |

### `FWEEK_PSCAT_SALES_MV` (materialized view)

| Column | Oracle type | Source | DB min | DB max | Non-integral in DB | Class | Attribute | Current | Fixed | Status | Note |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `WEEK_ENDING_DAY` | `DATE` | `TIMES.WEEK_ENDING_DAY` (sh_create.sql:181) | | | [`FweekPscatSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMv.java#L348) | `weekEndingDay` (@Id) | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `WEEK_ENDING_DAY` | `DATE` | `TIMES.WEEK_ENDING_DAY` (sh_create.sql:181) | | | [`FweekPscatSalesMvId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMvId.java#L242) | `weekEndingDay` | `LocalDateTime` | `LocalDateTime` | ✓ | DATE carries a time of day |
| `PROD_SUBCATEGORY` | `VARCHAR2(50)` | `PRODUCTS.PROD_SUBCATEGORY` (sh_create.sql:145) | | | [`FweekPscatSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMv.java#L362) | `prodSubcategory` (@Id) | `String` | `String` | ✓ |  |
| `PROD_SUBCATEGORY` | `VARCHAR2(50)` | `PRODUCTS.PROD_SUBCATEGORY` (sh_create.sql:145) | | | [`FweekPscatSalesMvId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMvId.java#L251) | `prodSubcategory` | `String` | `String` | ✓ |  |
| `DOLLARS` | `NUMBER` | `SUM(s.amount_sold)` (sh_create.sql:436) | `7.34` | `150049.08` | 10380 of 10512 (`7.34` … `150049.08`) | [`FweekPscatSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMv.java#L386) | `dollars` | `BigDecimal` | `BigDecimal` | ✓ | unconstrained NUMBER: up to 38 digits, any scale |
| `CHANNEL_ID` | `NUMBER(1)` | `SALES.CHANNEL_ID` (sh_create.sql:244) | `2` | `9` | none | [`FweekPscatSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMv.java#L372) | `channelId` (@Id) | `Long` | `Long` | ✓ |  |
| `CHANNEL_ID` | `NUMBER(1)` | `SALES.CHANNEL_ID` (sh_create.sql:244) | `2` | `9` | none | [`FweekPscatSalesMvId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMvId.java#L260) | `channelId` | `Long` | `Long` | ✓ |  |
| `PROMO_ID` | `NUMBER(6)` | `SALES.PROMO_ID` (sh_create.sql:245) | `33` | `999` | none | [`FweekPscatSalesMv`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMv.java#L382) | `promoId` (@Id) | `Integer` | `Integer` | ✓ |  |
| `PROMO_ID` | `NUMBER(6)` | `SALES.PROMO_ID` (sh_create.sql:245) | `33` | `999` | none | [`FweekPscatSalesMvId`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/FweekPscatSalesMvId.java#L269) | `promoId` | `Integer` | `Integer` | ✓ |  |

### Attributes with no table of their own

Declared by an embeddable that declares no `@Column` and that no entity embeds yet; an embedding entity defines their columns with `@AttributeOverride`s.

- [`CountrySection.name`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CountrySection.java#L158) `String`
- [`CountrySection.id`](oracle-sample-schemas-persistence-sh/src/main/java/com/github/jinahya/oracle/sample/schemas/persistence/sh/CountrySection.java#L163) `Long`

## Annex A: identity-backed columns

The rules above take a column's type at its word. One kind of column is narrower than its type says: a column whose values come from an identity. Its values start at `START WITH` and step by `INCREMENT BY`, so the count of values it can reach is bounded by how many times the identity can fire, not by the 38 digits of `INTEGER`. A foreign key to such a column holds only values that exist in it, and a view column that passes it through holds the same values. These columns are fixed to `Long` instead of `BigInteger`.

### Why `Long` holds them

None of the identities below declares an option, so each takes Oracle's defaults: `START WITH 1`, `INCREMENT BY 1`, `MAXVALUE 9999999999999999999999999999`. Values therefore run 1, 2, 3, … and reach `Long.MAX_VALUE` (9 223 372 036 854 775 807) only after 9.2 × 10¹⁸ generations — about 292 000 years at a million rows a second.

This is a bound on how far the identity can count, not one the DDL declares, and it has two limits:

- **The declared `MAXVALUE` is 28 digits**, wider than `Long`. Nothing in the definition stops the identity there; only the arithmetic above does. Declaring `MAXVALUE 9223372036854775807` would make the bound part of the definition.
- **`GENERATED BY DEFAULT ON NULL` accepts an explicit value.** An `INSERT` that supplies its own id bypasses the identity and may store any `INTEGER`. `GENERATED ALWAYS` would rule that out.

Under the rules alone, every column below is `BigInteger`.

| Schema | Column | Oracle type | Identity | Start | Increment | Max value | DDL |
| --- | --- | --- | --- | --- | --- | --- | --- |
| CO | `CUSTOMERS.CUSTOMER_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:64 |
| CO | `STORES.STORE_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:77 |
| CO | `PRODUCTS.PRODUCT_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:98 |
| CO | `ORDERS.ORDER_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:116 |
| CO | `SHIPMENTS.SHIPMENT_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:130 |
| CO | `INVENTORY.INVENTORY_ID` | `INTEGER` | `GENERATED BY DEFAULT ON NULL AS IDENTITY` | `1` (default) | `1` (default) | `9999999999999999999999999999` (default) | co_create.sql:159 |

And the columns that carry those values:

| Schema | Column | Why |
| --- | --- | --- |
| CO | `ORDERS.CUSTOMER_ID` | FK → `CUSTOMERS.CUSTOMER_ID` |
| CO | `ORDERS.STORE_ID` | FK → `STORES.STORE_ID` |
| CO | `SHIPMENTS.STORE_ID` | FK → `STORES.STORE_ID` |
| CO | `SHIPMENTS.CUSTOMER_ID` | FK → `CUSTOMERS.CUSTOMER_ID` |
| CO | `ORDER_ITEMS.ORDER_ID` | FK → `ORDERS.ORDER_ID` |
| CO | `ORDER_ITEMS.SHIPMENT_ID` | FK → `SHIPMENTS.SHIPMENT_ID` |
| CO | `ORDER_ITEMS.PRODUCT_ID` | FK → `PRODUCTS.PRODUCT_ID` |
| CO | `INVENTORY.STORE_ID` | FK → `STORES.STORE_ID` |
| CO | `INVENTORY.PRODUCT_ID` | FK → `PRODUCTS.PRODUCT_ID` |
| CO | `CUSTOMER_ORDER_PRODUCTS.ORDER_ID` | passes `ORDERS.ORDER_ID` through |
| CO | `CUSTOMER_ORDER_PRODUCTS.CUSTOMER_ID` | passes `CUSTOMERS.CUSTOMER_ID` through |

### Sequences

`HR` generates its ids from stand-alone sequences, not identities. They are listed for completeness only: a sequence is not tied to a column, and every column it feeds is a `NUMBER(4)` or `NUMBER(6)` that its current type already holds, so nothing is excluded on their account.

| Schema | Sequence | Start | Increment | Max value | DDL |
| --- | --- | --- | --- | --- | --- |
| HR | `LOCATIONS_SEQ` | `3300` | `100` | `9900` | hr_create.sql:130 |
| HR | `DEPARTMENTS_SEQ` | `280` | `10` | `9990` | hr_create.sql:165 |
| HR | `EMPLOYEES_SEQ` | `207` | `1` | none (default) | hr_create.sql:252 |
