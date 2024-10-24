create table rooms
(
    id            integer        not null
        primary key,
    name          varchar        not null,
    capacity      integer        not null,
    floor         integer        not null,
    basic_price   numeric(10, 2) not null,
    weekend_price numeric(10, 2) not null,
    image_path    varchar,
    hotel_id      integer
        constraint fk_hotels
            references hotels,
    is_available  boolean
);

alter table rooms
    owner to postgres;

INSERT INTO public.rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) VALUES (4, '10', 2, 5, 150.00, 200.00, null, 1, true);
INSERT INTO public.rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) VALUES (0, '11', 2, 3, 150.00, 200.00, null, 1, true);
INSERT INTO public.rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) VALUES (2, '11', 2, 3, 150.00, 200.00, null, 0, true);
INSERT INTO public.rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) VALUES (1, '10', 2, 2, 150.00, 200.00, null, 0, false);
INSERT INTO public.rooms (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) VALUES (3, '10', 2, 4, 150.00, 200.00, null, 1, false);
