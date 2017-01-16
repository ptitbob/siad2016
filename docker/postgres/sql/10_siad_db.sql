CREATE TABLE "city" (
    "id" bigint NOT NULL,
    "elevation" double precision,
    "inseeid" character varying(5),
    "name" character varying(100),
    "citystatus_id" bigint,
    "district_id" bigint,
    "region_region_id" bigint
);


CREATE SEQUENCE "city_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE "citystatus" (
    "id" bigint NOT NULL,
    "label" character varying(100)
);


CREATE SEQUENCE "citystatus_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--

CREATE TABLE "district" (
    "id" bigint NOT NULL,
    "cheflieuid" character varying(5),
    "inseeid" character varying(5),
    "name" character varying(100),
    "uppername" character varying(100),
    "region_region_id" bigint
);


CREATE SEQUENCE "district_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE "person" (
    "id" bigint NOT NULL,
    "login" character varying(255),
    "birth" "date",
    "firstname" character varying(100),
    "reference" character varying(7),
    "surname" character varying(100),
    "address_fk" BIGINT
);


CREATE SEQUENCE "person_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE "region" (
    "region_id" bigint NOT NULL,
    "cheflieuid" character varying(5),
    "inseeid" character varying(2),
    "name" character varying(100),
    "uppername" character varying(100)
);


CREATE SEQUENCE "region_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE "sequence" (
    "seq_name" character varying(50) NOT NULL,
    "seq_count" numeric(38,0)
);


CREATE TABLE "zipcode" (
    "id" bigint NOT NULL,
    "zipcode" character varying(6),
    "city_id" bigint
);


CREATE SEQUENCE "zipcode_sequence"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE address
(
    address_id BIGINT NOT NULL,
    floor INTEGER,
    line1 VARCHAR(100),
    line2 VARCHAR(100),
    city_fk BIGINT,
    zipcode_fk BIGINT,
    CONSTRAINT fk_address_city_fk FOREIGN KEY (city_fk) REFERENCES city (id),
    CONSTRAINT fk_address_zipcode_fk FOREIGN KEY (zipcode_fk) REFERENCES zipcode (id)
);

ALTER TABLE ONLY "address"
    ADD CONSTRAINT "PK_address_id" PRIMARY KEY ("address_id");

CREATE SEQUENCE "address_sequence"
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

commit;
