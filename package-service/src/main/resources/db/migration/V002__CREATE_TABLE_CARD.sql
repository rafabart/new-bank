    create table card (
       id  bigserial not null,
        card_number varchar(19),
        create_at timestamp,
        updated_at timestamp,
        primary key (id)
    );

    alter table card
       add constraint UK_by1nk98m2hq5onhl68bo09sc1 unique (card_number);