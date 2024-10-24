create table roles
(
    id          integer not null
        primary key,
    name        varchar not null,
    description varchar not null
);

alter table roles
    owner to postgres;

INSERT INTO public.roles (id, name, description) VALUES (0, 'ADMIN', 'Администратор');
INSERT INTO public.roles (id, name, description) VALUES (1, 'USER', 'Пользователь');
