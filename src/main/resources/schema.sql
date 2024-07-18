create table category(
id INT PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
description varchar(255)
);

create table product(
id INT PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
description varchar(255),
price DOUBLE,
categoryId INT,
FOREIGN KEY (categoryId) REFERENCES category(id)
);