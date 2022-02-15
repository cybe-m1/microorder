CREATE TABLE IF NOT EXISTS creneau (
  id_creneau INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  description VARCHAR(100),
  date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS position (
  id_position INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  description VARCHAR(100),
  longitude double,
  latitude double
);

CREATE TABLE IF NOT EXISTS truck (
  id_truck INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  description VARCHAR(100),
  id_creneau INT NULL,
  FOREIGN KEY (id_creneau) REFERENCES creneau (id_creneau),
  id_position INT NULL,
  FOREIGN KEY (id_position) REFERENCES position (id_position)
);