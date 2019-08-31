create table appointment(
    id serial primary key,
    doctor_id integer,
    date date,
    hour integer
);

alter table appointment add constraint uq_appointment unique (doctor_id, date, hour);