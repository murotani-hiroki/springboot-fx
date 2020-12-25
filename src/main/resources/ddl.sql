CREATE DATABASE fxdb;

\c fxdb

CREATE TABLE IF NOT EXISTS trade (
    id serial,
    trading_date date,
    settlement_date date,
    currency_pair_id integer,
    trade_type integer,
    quantity integer,
    entry_price numeric(8,5),
    exit_price numeric(8,5),
    stop_loss integer,
    profit numeric(15,5),
    comment text,
    image_id integer,
    CONSTRAINT trade_pk PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS currency_pair (
    id integer,
    currency_pair varchar(7),
    CONSTRAINT currency_pair_pk PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS image (
    id serial,
    file_name varchar(50),
    CONSTRAINT image_pk PRIMARY KEY(id)
);


COPY currency_pair FROM stdin delimiters ',';
1,USD/JPY
2,EUR/JPY
3,GBP/JPY
4,AUD/JPY
5,NZD/JPY
6,CAD/JPY
7,EUD/USD
8,GBP/USD
9,AUD/USD
\.


COPY trade FROM stdin delimiters ',';
1,2020-12-17,2020-12-17,1,1,10000,105.10000,105.60000,10,5000.00000,,\N
2,2020-12-18,2020-12-18,2,2,10000,125.20000,125.10000,10,-1000.00000,,\N
3,2020-12-19,2020-12-19,4,1,10000,78.50000,78.70000,10,2000.00000,,\N
\.
