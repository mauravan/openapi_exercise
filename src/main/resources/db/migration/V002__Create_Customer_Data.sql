DELETE FROM CUSTOMER;

INSERT INTO CUSTOMER (id, address_city, address_country, address_postal_code,
                      address_state_province, address_street, credit_card_expiration_month,
                      credit_card_expiration_year, credit_card_number, credit_card_type,
                      email, first_name, last_name, username)
VALUES (0, 'Bern', 'Schweiz', '1234', 'Bern', 'Poststrasse',
        4, 2022, 19456189489, 'VISA',
        'max@muster.ch', 'Max', 'Muster', 'max.muster');

INSERT INTO CUSTOMER (id, address_city, address_country, address_postal_code,
                      address_state_province, address_street, credit_card_expiration_month,
                      credit_card_expiration_year, credit_card_number, credit_card_type,
                      email, first_name, last_name, username)
VALUES (1, 'Zürich', 'Schweiz', '5678', 'Zürich', 'Hönggstrasse',
        5, 2023, 19456189489, 'MASTER_CARD',
        'max@muster.ch', 'Maximilian', 'Muster', 'maximilian.muster');


INSERT INTO CUSTOMER (id, address_city, address_country, address_postal_code,
                      address_state_province, address_street, credit_card_expiration_month,
                      credit_card_expiration_year, credit_card_number, credit_card_type,
                      email, first_name, last_name, username)
VALUES (2, 'Lausanne', 'Schweiz', '4567', 'Lausanne', 'rue d`apricots',
        6, 2024, 19456189478, 'VISA',
        'jean@livre.ch', 'Jean', 'Livre', 'jean.livre');