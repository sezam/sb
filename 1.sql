CREATE TABLE products
(
  id integer,
  product_name character varying(256),
  price integer
)

CREATE TABLE props
(
  id integer,
  product_id integer,
  prop_name character varying(256)
)

INSERT INTO products (id, product_name, price) VALUES (1, 'Продукт 1', 100);
INSERT INTO products (id, product_name, price) VALUES (2, 'Продукт 2', 200);
INSERT INTO products (id, product_name, price) VALUES (3, 'Продукт 3', 300);
INSERT INTO products (id, product_name, price) VALUES (4, 'Продукт 4', 300);

INSERT INTO props (id, product_id, prop_name) VALUES (1, 1, 'Свойство продукта 1');
INSERT INTO props (id, product_id, prop_name) VALUES (2, 2, 'Свойство продукта 2');
INSERT INTO props (id, product_id, prop_name) VALUES (3, 3, 'Свойство 1 продукта 3');
INSERT INTO props (id, product_id, prop_name) VALUES (4, 3, 'Свойство 2 продукта 3');


-- Продукты и свойства, у каждого продукта может быть 0, 1 или несколько свойств

-- 1. Вывести полный перечень всех продуктов и их свойств. Если у продукта нет свойств, то в колонке с именем свойства должно быть пусто
select * from products LEFT JOIN props on products.id=props.product_id

-- 2. Вывести продукт(ы) с максимальной ценой
SELECT * FROM products WHERE price in (SELECT MAX(price)  FROM products)

-- 3. Вывести продкут(ы), у которых больше одгого свойства
SELECT products.*, COUNT(*) c FROM products, props WHERE products.id=props.product_id GROUP BY product_id HAVING c > 1