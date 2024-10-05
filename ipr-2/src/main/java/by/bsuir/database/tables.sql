-- [1] таблица для хранения адресов
create table if not exists addresses (
    id int auto_increment primary key,   -- идентификатор адреса
    city varchar not null,               -- название города
    street varchar not null,             -- название улицы
    building varchar not null,           -- название дома
    zip varchar not null                 -- почтовый индекс
);

-- [2] таблица для хранения данных о ролях
create table if not exists roles (
    id int auto_increment primary key,   -- идентификатор роли
    name varchar not null                -- имя роли
);

-- [3] таблица для хранения данных о пользователях
create table if not exists people (
    id int auto_increment primary key,   -- идентификатор пользователя
    username varchar not null,           -- псевдоним
    password varchar not null,           -- пароль (хешированный)
    first_name varchar not null,         -- имя
    last_name varchar not null,          -- фамилия
    birthday date not null,              -- день рождения
    email varchar not null,              -- адрес электронной почты
    role_id int constraint fk_roles references roles(id)  -- ключ соответствующей колонки в таблице ролей
);

-- [4] таблица для хранения данных об отелях
create table if not exists hotels (
    id int auto_increment primary key,    -- идентификатор отеля
    name varchar not null,                -- название отеля
    description varchar not null,         -- описание отеля
    adderess_id int constraint fk_addresses references addresses(id),  -- ключ соответствующей колонки в таблице адресов
    level varchar not null,               -- уровень заведения
    available_to_book boolean not null,   -- доступен для бронирования
    image_path varchar                    -- ссылка на изображение отеля
);

-- [5] таблица для хранения данных о комнатах
create table if not exists rooms (
    id int auto_increment primary key,     -- идентификатор отеля
    name varchar not null,                 -- название комнаты
    capacity int not null,                 -- вместимость комнаты
    floor int not null,                    -- этаж
    basic_price decimal(10, 2) not null,   -- базовая стоимость за сутки аренды
    weekend_price decimal(10, 2) not null, -- стоимость в выходные и праздники
    image_path varchar,                    -- ссылка на изображение комнаты
    hotel_id int constraint fk_hotels references hotels(id)  -- ключ соответствующей колонки в таблице отелей
);

-- [1] заполнение таблицы адресов
insert into addresses (city, street, building, zip) values ('Солигорск', 'улица Ленина', '38', '223710'),
                                                           ('Солигорск', 'улица Ленина', '38', '223710'),
                                                           ('Солигорск', 'улица Ленина', '38', '223710'),
                                                           ('Солигорск', 'улица Ленина', '38', '223710'),
                                                           ('Солигорск', 'улица Ленина', '38', '223710');

-- [2] заполнение таблицы ролей
insert into roles (name) values ('ADMIN'), ('USER');

-- [3] заполнение таблицы пользователей
insert into people (username, password, first_name, last_name, birthday, email, role_id)
           values ('user1', '', 'Вася', 'Пупкин', '01.01.2001', 'vasya@pup.kin', 1);

-- [4] заполнение таблицы отелей
insert into hotels (name, description, adderess_id, level, available_to_book, image_path)
           values ('Алеся', 'Самый крутой отель', 0, 5, true, '');

-- [5] заполнение таблицы комнат
insert into rooms (name, capacity, floor, basic_price, weekend_price, image_path, hotel_id)
           values ('10', 2, 3, 150.00, 200.00, '', 0);
