create sequence hibernate_sequence start 1 increment 1;

create table calendar (
    id varchar(36) not null,
    last_working_date timestamp,
    event VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK' not null,
    first_working_date timestamp,
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
create table employees (
    id varchar(36) not null,
    event VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK' not null,
    first_name varchar(255), first_working_date timestamp,
    last_name varchar(255), last_working_date timestamp,
    department_id varchar(36), primary key (id)
);
alter table calendar
    add constraint FKnmah044irgqhhna8e5gtak0ri foreign key (employee_id) references employees;
alter table employees
    add constraint FK8if1byloc650qvkaxabyjveap foreign key (department_id) references department;