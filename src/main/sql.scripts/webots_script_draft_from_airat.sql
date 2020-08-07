--скрипт для создания таблиц
create table webots.roles
(
    id   serial      not null
        constraint roles_pkey
            primary key,
    name varchar(50) not null
);

alter table webots.roles
    owner to postgres;

create table webots.users_roles
(
    user_id integer not null,
    role_id integer not null
        constraint users_roles_role_id_fkey
            references webots.roles
            on update cascade on delete cascade,
    constraint users_roles_pkey
        primary key (user_id, role_id)
);

alter table webots.users_roles
    owner to postgres;
--эта таблица profile. Она была в старом коде, не уверен, что нужна
-- create table webots.z
-- (
--     id         bigserial                                 not null
--         constraint profile_pkey
--             primary key,
--     firstname  varchar(50)                               not null,
--     lastname   varchar(50)                               not null,
--     patronymic varchar(50)                               not null,
--     birthdate  date                                      not null,
--     gender     varchar(1) default 'X'::character varying not null,
--     phone      varchar(12),
--     position   varchar(50),
--     vklink     varchar(50),
--     fbooklink  varchar(50)
-- );
--
-- alter table webots.z
--     owner to postgres;

create table webots.users_images
(
    id      bigserial not null
        constraint users_images_pkey
            primary key,
    user_id bigint
        constraint users_images_user_id_fkey
            references webots.z,
    path    varchar(255)
);

alter table webots.users_images
    owner to postgres;

create table webots.vkuserinfo
(
    id        bigserial not null
        constraint vkuserinfo_pkey
            primary key,
    vkuid     varchar(50),
    firstname varchar(50),
    lastname  varchar(50),
    birthdate varchar(50),
    city      varchar(50)
);

alter table webots.vkuserinfo
    owner to postgres;

create table webots.users
(
    id              serial       not null
        constraint users_pkey
            primary key,
    email           varchar(32)  not null
        constraint users_email_key
            unique,
    password        varchar(255) not null,
    fullprivileges  boolean default false,
    profile         integer,
    enabled         boolean default false,
    confirmed_email boolean default false,
    authorized      boolean default false,
    using2fa        boolean default false,
    created         timestamp,
    first_name      varchar,
    last_name       varchar,
    username        varchar,
    phone           varchar,
    tokens_id       integer,
    profile_id      integer
);

alter table webots.users
    owner to postgres;

create table webots.tokens
(
    id      integer not null
        constraint tokens_pk
            primary key,
    token   varchar,
    user_id integer
);

alter table webots.tokens
    owner to postgres;

create unique index tokens_token_uindex
    on webots.tokens (token);

create table webots.social_links
(
    profile_id          integer,
    social_network_id   integer,
    id                  serial not null
        constraint social_links_pk
            primary key,
    created_by_id       serial,
    created_date        serial,
    modified_by_id      serial,
    last_modified_by_id serial,
    last_modified_date  serial,
    -- Only integer types can be auto increment
    link                varchar default nextval('webots.social_links_links_seq'::regclass)
);

alter table webots.social_links
    owner to postgres;

create table webots.locations
(
    id                  serial not null
        constraint locations_pk
            primary key,
    profile_id          integer,
    created_by_id       integer,
    created_date        integer,
    last_modified_by_id integer,
    last_modified_date  integer,
    city                varchar,
    country             varchar,
    post_code           varchar,
    street              varchar
);

alter table webots.locations
    owner to postgres;

create table webots.social_networks
(
    id                  integer not null
        constraint social_networks_pk
            primary key,
    created_by_id       integer,
    last_modified_by_id integer,
    created_date        integer,
    last_modified_date  integer,
    icon                varchar,
    name                varchar,
    url                 varchar
);

alter table webots.social_networks
    owner to postgres;

create table webots.profiles
(
    id                  serial  not null
        constraint profiles_pk
            primary key,
    firstname           varchar,
    lastname            varchar,
    patronymic          varchar,
    gender              varchar,
    phone               varchar,
    utc                 varchar,
    location_id         integer,
    created_by_id       integer,
    created_date        varchar,
    last_modified_by_id integer,
    last_modified_date  varchar,
    birthdate           date,
    photo               bytea,
    email               varchar not null,
    user_id             integer
);

alter table webots.profiles
    owner to postgres;

create unique index profiles_id_uindex
    on webots.profiles (id);

