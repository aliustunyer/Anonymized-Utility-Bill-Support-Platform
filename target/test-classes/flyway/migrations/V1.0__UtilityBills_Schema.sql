
DROP TABLE IF exists donor_beneficiary;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS utility_bills;
DROP TABLE IF EXISTS donors;
DROP TABLE IF EXISTS beneficiaries;


CREATE TABLE beneficiaries (
  beneficiary_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  cell_phone VARCHAR(20) NOT NULL
);

CREATE TABLE donors (
  donor_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  cell_phone VARCHAR(20) NOT NULL
);

CREATE TABLE utility_bills (
  bill_id INT AUTO_INCREMENT PRIMARY KEY,
  beneficiary_id INT NOT NULL,
  utility_type ENUM('water', 'electricity', 'natural_gas') NOT NULL,
  amount_due DECIMAL(10, 2) NOT NULL,
  due_date DATE NOT NULL,
  is_paid BOOLEAN NOT NULL DEFAULT 0,
  FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(beneficiary_id) ON DELETE CASCADE
);

CREATE TABLE payments (
  payment_id INT AUTO_INCREMENT PRIMARY KEY,
  payment_date DATE NOT NULL,
  donor_id INT NOT NULL,
  bill_id INT NOT NULL,
  beneficiary_id INT NOT NULL,
  FOREIGN KEY (donor_id) REFERENCES donors(donor_id) ON DELETE CASCADE,
  FOREIGN KEY (bill_id) REFERENCES utility_bills(bill_id) ON DELETE CASCADE,
  FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(beneficiary_id) ON DELETE CASCADE
);

CREATE TABLE donor_beneficiary (
  donor_id INT NOT NULL,
  beneficiary_id INT NOT NULL,
  PRIMARY KEY (donor_id, beneficiary_id),
  FOREIGN KEY (donor_id) REFERENCES donors(donor_id) ON DELETE CASCADE,
  FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(beneficiary_id) ON DELETE CASCADE
);