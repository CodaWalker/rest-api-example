create sequence hibernate_sequence start 1 increment 1;

create table calendar (
    id varchar(36) not null,
    last_interval_date date,
    event VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK' not null,
    first_interval_date date,
    employee_id varchar(36),
    primary key (id)
);
create table department (
    id varchar(36) not null,
    first_working_date timestamp,
    last_working_date timestamp,
    name varchar(255),
    primary key (id)
);
create table employee (
    id varchar(36) not null,
    first_name varchar(255),
    first_working_date timestamp,
    last_name varchar(255),
    last_working_date timestamp,
    presence_at_work boolean,
    department_id varchar(36),
    position_id varchar(36),
    primary key (id)
);
create table position (
    id varchar(36) not null,
    name varchar(255),
    primary key (id)
);
alter table calendar
    add constraint FKslt8252kcie1jrit02ltj7txk foreign key (employee_id) references employee;
alter table employee
    add constraint FKbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department;
alter table employee
    add constraint FKbc8rdko9o9n1ri9bpdyxv3x7i foreign key (position_id) references position;