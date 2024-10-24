create table people
(
    id         integer not null
        primary key,
    username   varchar not null,
    password   varchar not null,
    first_name varchar not null,
    last_name  varchar not null,
    birth_date date    not null,
    email      varchar not null,
    role_id    integer
        constraint fk_roles
            references roles
);

alter table people
    owner to postgres;

INSERT INTO public.people (id, username, password, first_name, last_name, birth_date, email, role_id) VALUES (1, 'pupa', 'daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5', 'VASYA', 'PUPKIN', '2020-01-01', 'leen_kezy@mail.ru', 1);
INSERT INTO public.people (id, username, password, first_name, last_name, birth_date, email, role_id) VALUES (0, 'shufladka', 'daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5', 'ALEH', 'KLIAZOVICH', '2002-01-18', 'olegolegolegoleg88@gmail.com', 0);
