create table hotels
(
    id                integer not null
        primary key,
    name              varchar not null,
    description       varchar not null,
    address_id        integer
        constraint fk_addresses
            references addresses,
    level             varchar not null,
    available_to_book boolean not null,
    image_path        varchar
);

alter table hotels
    owner to postgres;

INSERT INTO public.hotels (id, name, description, address_id, level, available_to_book, image_path) VALUES (1, 'Новое Полесье', 'Трехзвёздочная гостиница Солигорска «Новое Полесье» (СГУПП «ЖКХ «Комплекс») предоставляет для гостей и жителей города комфортабельные и уютные номера.', 1, '3', true, 'https://i.imgur.com/vS2ykXm.png');
INSERT INTO public.hotels (id, name, description, address_id, level, available_to_book, image_path) VALUES (0, 'Алеся', 'Гостиница располагается в центре города, рядом с гостиницей находятся кинотеатр, Дворец культуры, банк, почта, магазины, городской парк, кафе и рестораны, водохранилище.', 0, '4', true, 'https://i.imgur.com/6TVtfcp.png');
