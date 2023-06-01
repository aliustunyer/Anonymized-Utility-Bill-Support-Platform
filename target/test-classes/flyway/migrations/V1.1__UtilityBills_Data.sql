INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('John', 'Doe', 'john.doe@example.com', 'password1', '555-123-4567');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Jane', 'Doe', 'jane.doe@example.com', 'password2', '555-987-6543');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Emma', 'Brown', 'emma.brown@example.com', 'password3', '555-456-7890');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Oliver', 'Smith', 'oliver.smith@example.com', 'password4', '555-789-0123');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Sophia', 'Martinez', 'sophia.martinez@example.com', 'password5', '555-951-3574');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Mia', 'Garcia', 'mia.garcia@example.com', 'password6', '555-753-1592');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Harper', 'Lee', 'harper.lee@example.com', 'password7', '555-753-9512');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Ella', 'Hernandez', 'ella.hernandez@example.com', 'password8', '555-159-7532');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Avery', 'King', 'avery.king@example.com', 'password9', '555-951-7531');
INSERT INTO beneficiaries (first_name, last_name, email, password, cell_phone) VALUES ('Mila', 'Wright', 'mila.wright@example.com', 'password10', '555-123-9512');

INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Alice', 'Smith', 'alice.smith@example.com', 'password11', '555-321-7654');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Bob', 'Johnson', 'bob.johnson@example.com', 'password12', '555-654-9872');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Charlie', 'Williams', 'charlie.williams@example.com', 'password13', '555-987-6541');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('David', 'Jones', 'david.jones@example.com', 'password14', '555-123-7894');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Eva', 'Brown', 'eva.brown@example.com', 'password15', '555-456-7891');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Fiona', 'Davis', 'fiona.davis@example.com', 'password16', '555-789-0124');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('George', 'Miller', 'george.miller@example.com', 'password17', '555-258-3691');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Hannah', 'Wilson', 'hannah.wilson@example.com', 'password18', '555-963-2581');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Iris', 'Moore', 'iris.moore@example.com', 'password19', '555-741-9632');
INSERT INTO donors (first_name, last_name, email, password, cell_phone) VALUES ('Jack', 'Taylor', 'jack.taylor@example.com', 'password20', '555-369-2581');

INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (1, 'water', 50.00, '2023-05-01', 1);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (2, 'electricity', 75.00, '2023-05-10', 1);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (3, 'natural_gas', 30.00, '2023-05-15', 1);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (4, 'water', 45.00, '2023-05-20', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (5, 'electricity', 75.00, '2023-05-25', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (6, 'water', 55.00, '2023-06-01', 1);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (7, 'natural_gas', 40.00, '2023-06-10', 1);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (8, 'electricity', 90.00, '2023-06-15', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (9, 'water', 60.00, '2023-06-20', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (10, 'natural_gas', 35.00, '2023-06-25', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (5, 'natural_gas', 45.00, '2023-06-28', 0);
INSERT INTO utility_bills (beneficiary_id, utility_type, amount_due, due_date, is_paid) VALUES (6, 'electricity', 120.00, '2023-06-05', 0);

INSERT INTO payments (payment_date, donor_id, bill_id, beneficiary_id, payment_type) VALUES ('2023-04-30', 1, 1, 1, 'DIRECT');
INSERT INTO payments (payment_date, donor_id, bill_id, beneficiary_id, payment_type) VALUES ('2023-05-09', 2, 2, 2, 'DIRECT');
INSERT INTO payments (payment_date, donor_id, bill_id, beneficiary_id, payment_type) VALUES ('2023-05-14', 3, 3, 3, 'DIRECT');
INSERT INTO payments (payment_date, donor_id, bill_id, beneficiary_id, payment_type) VALUES ('2023-05-31', 4, 6, 6, 'DIRECT');
INSERT INTO payments (payment_date, donor_id, bill_id, beneficiary_id, payment_type) VALUES ('2023-06-09', 5, 7, 7, 'DIRECT');

INSERT INTO donations (donor_id, amount_donated, donation_date) VALUES
(1, 100.00, '2023-01-01'),
(2, 150.00, '2023-01-15'),
(3, 200.00, '2023-01-20'),
(4, 250.00, '2023-01-25'),
(5, 300.00, '2023-02-01'),
(6, 350.00, '2023-02-15'),
(7, 400.00, '2023-02-20'),
(8, 450.00, '2023-02-25'),
(9, 500.00, '2023-03-01'),
(10, 550.00, '2023-03-15');