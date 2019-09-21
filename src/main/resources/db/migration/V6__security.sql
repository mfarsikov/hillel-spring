create table clinic_user (
    id serial primary key,
    username varchar(255),
    password varchar(255),
    authorities text
)