create table appointment (
    id serial primary key,
    version integer,
    doctor_id integer,
    date date
);

create  table appointment_hours (
    appointment_id integer,
    hours integer
);
