drop table if EXISTS SALES;

create table SALES (
    id INTEGER(10) NOT NULL AUTO_INCREMENT,
    country VARCHAR(128) NOT NULL,
    item_type VARCHAR(128) NOT NULL,
    sales_channel VARCHAR(128) NOT NULL,
    orderDate DATETIME NOT NULL,
    unitSold INTEGER(10) NOT NULL,
    unitPrice INTEGER(10) NOT NULL,
    unitCost INTEGER(10) NOT NULL,
    PRIMARY KEY (id)
);