CREATE TABLE IF NOT EXISTS services(
    id INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    service_type INTEGER NOT NULL,
    delivered BOOLEAN NOT NULL,
    arrival DATE NOT NULL,
    exit DATE NOT NULL,
    name NVARCHAR(12) NOT NULL,
    surname NVARCHAR(12) NOT NULL,
    model NVARCHAR(20) NOT NULL,
    registration NVARCHAR(20) NOT NULL,
    phone INTEGER NOT NULL,
    email NVARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS
