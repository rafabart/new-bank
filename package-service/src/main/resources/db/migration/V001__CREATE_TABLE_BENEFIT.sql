    create table benefit (
       id  bigserial not null,
        active boolean,
        create_at timestamp,
        description varchar(255),
        price numeric(19, 2),
        tax_cycle int4,
        title varchar(100),
        updated_at timestamp,
        primary key (id)
    );