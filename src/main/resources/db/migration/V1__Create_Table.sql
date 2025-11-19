create table IF NOT EXISTS advancedcrud (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	author_name VARCHAR(80) NOT NULL,
	title VARCHAR(120) NOT NULL,
	launch_date DATE NOT NULL,
	price DOUBLE NOT NULL
);