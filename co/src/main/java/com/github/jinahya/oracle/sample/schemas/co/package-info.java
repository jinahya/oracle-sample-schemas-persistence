/// Defines entities for mapping {@code co} schema.
///
/// * CUSTOMERS
/// * INVENTORY
///   * Refers PRODUCTS
///   * Refers STORES
/// * ORDER_ITEMS
///   * Refers ORDERS
///   * Refers PRODUCTS
///   * Refers SHIPMENTS
/// * ORDERS
///   * Refers CUSTOMERS
///   * Refers STORES
/// * PRODUCTS
/// * SHIPMENTS
///   * Refers CUSTOMERS
///   * Refers STORES
/// * STORES
///
/// author: <NAME> &lt;onacit_at_gmail.com&gt;
package com.github.jinahya.oracle.sample.schemas.co;