    create table customer (
       id uuid not null,
        cnpj varchar(255),
        cpf varchar(255),
        create_at timestamp,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        updated_at timestamp,
        primary key (id)
    );

    alter table customer 
       add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);
