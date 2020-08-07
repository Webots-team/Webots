DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
  id                    bigserial, 
  username		VARCHAR(50) ,
  password              VARCHAR(80),
  phone                 VARCHAR(15)  UNIQUE,
  first_name            VARCHAR(50),
  last_name             VARCHAR(50),
  email                 VARCHAR(50) UNIQUE,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),  ('ROLE_ADMIN');

INSERT INTO users (username,  password, phone, first_name, last_name, email)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','11111111','Admin','Admin','admin@gmail.com');



INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2);

DROP TABLE IF EXISTS profile;
CREATE TABLE profile
(
    id bigserial PRIMARY KEY,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    patronymic varchar(50) not null,
    birthdate date not null,
    gender varchar(1) not null DEFAULT 'X',
    phone varchar(12),
    position varchar(50),
    VKlink varchar(50),
    FBookLink varchar(50),
    FOREIGN KEY (id)
    REFERENCES users (id)

);

INSERT INTO profile (firstname, lastname, patronymic, birthdate, gender, phone, position, VKlink, FBookLink)
VALUES
('James','Bond','007','2001-09-28','M','11111111','Admin','','');

DROP TABLE IF EXISTS users_images;
CREATE TABLE users_images (id bigserial PRIMARY KEY, user_id bigint, 
path varchar(255), FOREIGN KEY (user_id) REFERENCES profile(id));

INSERT INTO users_images (user_id, path) VALUES
(1, 'img_1.jpg');



DROP TABLE IF EXISTS vkuserinfo;
CREATE TABLE vkuserinfo
(
    id bigserial PRIMARY KEY,
    VKuid varchar(50),
    firstname varchar(50),
    lastname varchar(50),
    birthdate varchar(50),
    city varchar(50)
  
);

