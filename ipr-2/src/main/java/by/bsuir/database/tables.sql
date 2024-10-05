-- [1] таблица для хранения адресов
create table if not exists addresses (
    id int primary key,   -- идентификатор адреса
    region varchar not null,             -- название области
    city varchar not null,               -- название города
    street varchar not null,             -- название улицы
    building varchar not null,           -- название дома
    zip varchar not null                 -- почтовый индекс
);

-- [2] таблица для хранения данных о ролях
create table if not exists roles (
    id int primary key,   -- идентификатор роли
    name varchar not null,               -- имя роли
    description varchar not null         -- описание роли
);

-- [3] таблица для хранения данных о пользователях
create table if not exists people (
    id int primary key,   -- идентификатор пользователя
    username varchar not null,           -- псевдоним
    password varchar not null,           -- пароль (хешированный)
    first_name varchar not null,         -- имя
    last_name varchar not null,          -- фамилия
    birth_date date not null,            -- дата рождения
    email varchar not null,              -- адрес электронной почты
    role_id int constraint fk_roles references roles(id)  -- ключ соответствующей колонки в таблице ролей
);

-- [4] таблица для хранения данных об отелях
create table if not exists hotels (
    id int primary key,    -- идентификатор отеля
    name varchar not null,                -- название отеля
    description varchar not null,         -- описание отеля
    adderess_id int constraint fk_addresses references addresses(id),  -- ключ соответствующей колонки в таблице адресов
    level varchar not null,               -- уровень заведения
    available_to_book boolean not null,   -- доступен для бронирования
    image_path varchar                    -- ссылка на изображение отеля
);

-- [5] таблица для хранения данных о комнатах
create table if not exists rooms (
    id int primary key,     -- идентификатор отеля
    name varchar not null,                 -- название комнаты
    capacity int not null,                 -- вместимость комнаты
    floor int not null,                    -- этаж
    basic_price decimal(10, 2) not null,   -- базовая стоимость за сутки аренды
    weekend_price decimal(10, 2) not null, -- стоимость в выходные и праздники
    image_path varchar,                    -- ссылка на изображение комнаты
    hotel_id int constraint fk_hotels references hotels(id)  -- ключ соответствующей колонки в таблице отелей
);

-- [1] заполнение таблицы адресов
insert into addresses (id, region, city, street, building, zip)
            values (0, 'Минская область','Солигорск', 'улица Ленина', '38', '223710'),
                   (1, 'Минская область','Солигорск', 'улица Ленина', '38', '223710'),
                   (2,'Минская область','Солигорск', 'улица Ленина', '38', '223710');

-- [2] заполнение таблицы ролей
insert into roles (id, name, description) values (0, 'ADMIN', 'Администратор'), (1,'USER', 'Пользователь');

-- [3] заполнение таблицы пользователей
insert into people (id, username, password, first_name, last_name, birth_date, email, role_id)
           values (0, 'user1', '', 'Вася', 'Пупкин', '01.01.2001', 'vasya@pup.kin', 1);

-- [4] заполнение таблицы отелей
insert into hotels (id, name, description, adderess_id, level, available_to_book, image_path)
           values (0, 'Алеся', 'Самый крутой отель', 0, 5, true, '');

-- [5] заполнение таблицы комнат
insert into rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id)
           values (0, '10', 2, 3, 150.00, 200.00, '', 0);

select * from rooms where basic_price>=10.00 and basic_price<=160 or weekend_price>=10.00 and weekend_price<=180;