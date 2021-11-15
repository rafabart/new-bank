    create table card_benefit (
       cards_id bigserial not null,
        benefits_id bigserial not null
    );

    alter table card_benefit
       add constraint FK3weayboxgksjafwy4dpfwmyax
       foreign key (benefits_id)
       references benefit;

    alter table card_benefit
       add constraint FKc5sc4pn9vg72v7ssri94msk1b
       foreign key (cards_id)
       references card;