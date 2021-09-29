create sequence hibernate_sequence start 1 increment 1;

    create table country (
       country_id int8 not null,
        country_name varchar(255) not null,
        primary key (country_id)
    );

    create table country_currency (
       country_id int8 not null,
        currency_id int8 not null,
        primary key (country_id, currency_id)
    );

    create table currency (
       currency_id int8 not null,
        currency_code varchar(255) not null,
        primary key (currency_id)
    );

    create table quotation (
       quotation_id int8 not null,
        date date,
        rate numeric(8, 4),
        currency_id int8,
        primary key (quotation_id)
    );

    alter table country 
       add constraint UK_qrkn270121aljmucrdbnmd35p unique (country_name);

    alter table currency 
       add constraint UK_7n17ygajjchsso2n0lyxrsyif unique (currency_code);

    alter table country_currency 
       add constraint FKdk4atlhgo1xeki0lgs136drnb 
       foreign key (currency_id) 
       references currency;

    alter table country_currency 
       add constraint FK3sgwqmtr49xyq7hplrg2r4kqf 
       foreign key (country_id) 
       references country;

    alter table quotation 
       add constraint FKriqr781i8uaks8kw4hipfejmu 
       foreign key (currency_id) 
       references currency;
