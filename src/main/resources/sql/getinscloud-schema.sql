DROP DATABASE IF EXISTS getinscloud;
CREATE DATABASE getinscloud;
-- CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci;
USE getinsvn;

create table image
(
  id  INT PRIMARY KEY AUTO_INCREMENT,
  url VARCHAR(4000)
);

CREATE TABLE contact
(
  id         INT PRIMARY KEY AUTO_INCREMENT,
  name       NVARCHAR(4000),
  birth_date DATE,
  phone      CHAR(11),
  email      VARCHAR(4000)
);

CREATE TABLE user
(
  id        INT PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) UNIQUE NOT NULL,
  password  VARCHAR(4000)       NOT NULL,
  email     VARCHAR(255) UNIQUE NOT NULL,
  avatar    VARCHAR(4000),
  enabled   BIT DEFAULT 1
);

CREATE TABLE user_detail
(
  user_id    INT PRIMARY KEY,
  full_name  NVARCHAR(4000),
  birth_date DATE DEFAULT '2000-1-1',
  gender     BIT  DEFAULT 1,
  address    NVARCHAR(4000),
  phone      CHAR(11),
  job        NVARCHAR(4000),
  CONSTRAINT FK_userDetail_user FOREIGN KEY (user_id)
    REFERENCES user (id)
    ON DELETE CASCADE
);

CREATE TABLE role
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) UNIQUE NOT NULL,
  description NVARCHAR(4000),
  enabled     BIT DEFAULT 1
);

CREATE TABLE user_role
(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT FK_userRole_user FOREIGN KEY (user_id)
    REFERENCES user (id) ON DELETE CASCADE,
  CONSTRAINT FK_userRole_role FOREIGN KEY (role_id)
    REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE app_function
(
  id        VARCHAR(50) PRIMARY KEY,
  name      NVARCHAR(4000),
  url       VARCHAR(4000),
  icon      VARCHAR(4000),
  parent_id VARCHAR(50),
  CONSTRAINT FK_function_function FOREIGN KEY (parent_id)
    REFERENCES app_function (id) ON DELETE CASCADE
);

CREATE TABLE role_function
(
  role_id     INT         NOT NULL,
  function_id VARCHAR(50) NOT NULL,
  can_view    BIT DEFAULT 1,
  can_create  BIT DEFAULT 1,
  can_update  BIT DEFAULT 1,
  can_delete  BIT DEFAULT 1,
  PRIMARY KEY (role_id, function_id),
  CONSTRAINT FK_roleFunction_role FOREIGN KEY (role_id)
    REFERENCES role (id) ON DELETE CASCADE,
  CONSTRAINT FK_roleFunction_function FOREIGN KEY (function_id)
    REFERENCES app_function (id) ON DELETE CASCADE
);

CREATE TABLE post_type
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  name        NVARCHAR(4000) NOT NULL,
  description NVARCHAR(4000)
);

CREATE TABLE post
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  author_id   INT,
  type_id     INT,
  subject     MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI NOT NULL,
  content     MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI NOT NULL,
  attach      VARCHAR(4000),
  create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  enabled     BIT       DEFAULT 1,
  CONSTRAINT FK_post_user FOREIGN KEY (author_id)
    REFERENCES user (id),
  CONSTRAINT FK_post_postType FOREIGN KEY (type_id)
    REFERENCES post_type (id)
);

CREATE TABLE course_type
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  name        NVARCHAR(255) UNIQUE NOT NULL,
  description NVARCHAR(4000)
);

CREATE TABLE course
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  author_id   INT,
  type_id     INT,
  name        NVARCHAR(4000)                                                 NOT NULL,
  description MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI NOT NULL,
  content     MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI NOT NULL,
  video_url   VARCHAR(4000),
  price       DECIMAL(15, 3) DEFAULT 0,
  rating      DECIMAL(3, 1)  DEFAULT 0,
  requirement MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI,
  duration    INT            DEFAULT 0,
  enabled     BIT            DEFAULT 1,
  CONSTRAINT FK_course_courseType FOREIGN KEY (type_id)
    REFERENCES course_type (id),
  CONSTRAINT FK_course_user FOREIGN KEY (author_id)
    REFERENCES user (id)
);

CREATE TABLE class
(
  id             INT PRIMARY KEY AUTO_INCREMENT,
  course_id      INT,
  teacher_id     INT,
  name           NVARCHAR(4000) NOT NULL,
  student_number TINYINT DEFAULT 0,
  description    MEDIUMTEXT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_VIETNAMESE_CI,
  begin_date     DATE,
  end_date       DATE,
  enabled        BIT     DEFAULT 1,
  CONSTRAINT FK_class_course FOREIGN KEY (course_id)
    REFERENCES course (id)
    ON DELETE CASCADE,
  CONSTRAINT FK_class_user FOREIGN KEY (teacher_id)
    REFERENCES user (id)
    ON DELETE CASCADE
);

CREATE TABLE orders
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT NOT NULL,
  total_money DECIMAL(15, 2) DEFAULT 0,
  create_date TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  enabled     BIT            DEFAULT 0,
  CONSTRAINT FK_order_user FOREIGN KEY (customer_id)
    REFERENCES user (id)
    ON DELETE CASCADE
);

CREATE TABLE order_detail
(
  order_id INT NOT NULL,
  class_id INT NOT NULL,
  discount DECIMAL(3, 1) DEFAULT 0,
  PRIMARY KEY (order_id, class_id),
  CONSTRAINT FK_orderDetail_order FOREIGN KEY (order_id)
    REFERENCES orders (id)
    ON DELETE CASCADE,
  CONSTRAINT FK_orderDetail_class FOREIGN KEY (class_id)
    REFERENCES class (id)
    ON DELETE CASCADE
);

CREATE TABLE persistent_logins
(
  username  VARCHAR(50),
  series    VARCHAR(250),
  token     VARCHAR(250),
  last_used DATETIME
);

CREATE TABLE password_reset_token
(
  user_id     INT PRIMARY KEY,
  token       VARCHAR(250) NOT NULL,
  expiry_date DATETIME     NOT NULL,
  CONSTRAINT FK_password_user FOREIGN KEY (user_id)
    REFERENCES user (id)
);

