CREATE SEQUENCE CUSTOMER_SEQ
    START WITH 10000
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE CUSTOMER
(
    ID                           BIGINT NOT NULL,
    USERNAME                     VARCHAR NOT NULL UNIQUE,
    FIRST_NAME                   VARCHAR NOT NULL,
    LAST_NAME                    VARCHAR NOT NULL,
    EMAIL                        VARCHAR NOT NULL,
    ADDRESS_STREET               VARCHAR NOT NULL,
    ADDRESS_CITY                 VARCHAR NOT NULL,
    ADDRESS_STATE_PROVINCE       VARCHAR,
    ADDRESS_POSTAL_CODE          VARCHAR NOT NULL,
    ADDRESS_COUNTRY              VARCHAR NOT NULL,
    CREDIT_CARD_TYPE             VARCHAR NOT NULL,
    CREDIT_CARD_NUMBER           VARCHAR NOT NULL,
    CREDIT_CARD_EXPIRATION_MONTH INTEGER NOT NULL,
    CREDIT_CARD_EXPIRATION_YEAR  INTEGER NOT NULL,
    PRIMARY KEY (ID)
);
