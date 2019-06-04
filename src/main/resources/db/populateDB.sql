DELETE FROM USER_ROLES;
DELETE FROM DISHES;
DELETE FROM USERS;
DELETE FROM MENUS;
DELETE FROM RESTAURANT;
DELETE FROM VOTES;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('Jon', 'Jon@gmail.com', '{noop}JonDoe12'),
  ('Peter', 'Peter@gmail.com', '{noop}Peter12345'),
  ('Admin', 'admin@gmail.com', '{noop}admin'),
  ('New_user', 'New_user@gmail.com', '{noop}New_user');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100003);

INSERT INTO DISHES ( name, calories, price)
VALUES ('Гамбургер', 650, 60.75),
       ('Крабовый салат', 435, 100.50),
       ('Паэлья', 795.5, 100.50),
       ('Гохан', 420, 100.50),
       ('Картошка по-селянски', 600, 100.50),
       ('Рис со шпинатом', 388, 100.50),
       ('Крылышки BBQ', 422, 100.50),
       ('Креветки в темпуре', 524, 100.50),
       ('Мисо суп', 345, 100.50),
       ('Удон суп', 480, 100.50);

INSERT INTO MENUS (name)
VALUES ('Летнее меню'),
       ('Меню столовой'),
       ('Меню Киевское'),
       ('Салаты'),
       ('Суши'),
       ('Пицца'),
       ('Напитки');

INSERT INTO RESTAURANT (MENU_ID, NAME, ADDRESS)
VALUES (100016,'School canteen','Florida, st.Paola street 17'),
       (100017,'Kievsky restaurant','Kiev, Horiva street 1'),
       (100018,'Salateria','Kiev, Shevchenka street 5'),
       (100019,'Evrasia','Kiev, Bolshoi Val street 22'),
       (100020,'Solo Pizza','Kiev, Petra Sagaidachnogo street 17');

INSERT INTO VOTES (VOTE_DATE,USER_ID,RESTAURANT_ID)
VALUES ('2018-09-29 10:00:00',100000,100022),
       ('2018-09-29 11:00:00',100001,100022),
       ('2018-09-29 09:00:00',100002,100023);

INSERT INTO MENUS_DISHES (DISH_ID, MENU_ID)
VALUES (100009,100015),
       (100007,100015),
       (100013,100019),
       (100014,100019);