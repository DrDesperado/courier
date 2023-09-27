CREATE TABLE IF NOT EXISTS couriers
(
    courier_id bigint not null unique,
    working_hours character varying(255)[] NOT NULL,
    regions integer[] NOT NULL,
    courier_type smallint NOT NULL,
    CONSTRAINT couriers_pkey PRIMARY KEY (courier_id)
);


CREATE TABLE IF NOT EXISTS orders
(
    order_id bigint not null unique ,
    completed_time timestamp(6) without time zone,
    cost integer NOT NULL,
    delivery_hours character varying(255)[]  NOT NULL,
    regions integer NOT NULL,
    weight real NOT NULL,
    courier_id bigint,
    waiting_time time without time zone,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT couriers_pkey FOREIGN KEY (courier_id)
        REFERENCES couriers (courier_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);