create table orders
(
    id         integer not null
        primary key,
    person_id  integer
        constraint fk_people
            references people,
    room_id    integer
        constraint fk_rooms
            references rooms,
    created_at date    not null,
    updated_at date,
    closed_at  date,
    status     varchar not null
);

alter table orders
    owner to postgres;

INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (3, 0, 4, '2024-10-16', '2024-10-16', '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (5, 0, 0, '2024-10-16', null, '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (4, 0, 1, '2024-10-16', null, '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (6, 0, 3, '2024-10-16', null, '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (7, 0, 2, '2024-10-16', null, '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (8, 0, 2, '2024-10-16', '2024-10-16', '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (9, 1, 3, '2024-10-16', '2024-10-17', '2024-10-17', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (10, 0, 1, '2024-10-18', null, null, 'CREATED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (11, 0, 3, '2024-10-18', null, null, 'CREATED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (0, 0, 0, '2024-10-16', null, '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (1, 0, 0, '2024-10-16', '2024-10-16', '2024-10-16', 'CLOSED');
INSERT INTO public.orders (id, person_id, room_id, created_at, updated_at, closed_at, status) VALUES (2, 0, 3, '2024-10-16', null, '2024-10-16', 'CLOSED');
