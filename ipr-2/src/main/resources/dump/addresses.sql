create table addresses
(
    id       integer not null
        primary key,
    region   varchar not null,
    city     varchar not null,
    street   varchar not null,
    building varchar not null,
    zip      varchar not null
);

alter table addresses
    owner to postgres;

INSERT INTO public.addresses (id, region, city, street, building, zip) VALUES (0, 'Минская область', 'Солигорск', 'улица Ленина', '38', '223707');
INSERT INTO public.addresses (id, region, city, street, building, zip) VALUES (1, 'Минская область', 'Солигорск', 'улица Козлова', '33', '223707');
