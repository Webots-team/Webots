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
    link                varchar
);