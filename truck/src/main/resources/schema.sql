CREATE TABLE IF NOT EXISTS truck (
  id_truck INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  description VARCHAR(100),
  id_creneau INT NULL,
  id_position INT NULL
);