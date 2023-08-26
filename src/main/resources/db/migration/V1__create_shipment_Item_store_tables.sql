-- Create the STORE table
CREATE TABLE STORE (
                       id SERIAL PRIMARY KEY,
                       store_code VARCHAR(255) NOT NULL UNIQUE,
                       location VARCHAR(255) NOT NULL
);

-- Create the SHIPMENT table
CREATE TABLE SHIPMENT (
                          id SERIAL PRIMARY KEY,
                          shipment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(255),
                          order_code VARCHAR(255) NOT NULL,
                          shipping_location VARCHAR(255) NOT NULL,
                          store_id INTEGER,
                          customer_email VARCHAR(100) NOT NULL CHECK (customer_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
                          FOREIGN KEY (store_id) REFERENCES STORE(id)
);

-- Create the SHIPMENT_ITEM table
CREATE TABLE SHIPMENT_ITEM (
                               id SERIAL PRIMARY KEY,
                               shipment_id INTEGER,
                               store_id INTEGER,
                               product_code VARCHAR(255) NOT NULL,
                               quantity INTEGER CHECK (quantity > 0),
                               FOREIGN KEY (shipment_id) REFERENCES SHIPMENT(id),
                               FOREIGN KEY (store_id) REFERENCES STORE(id)
);