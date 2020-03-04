create sequence hibernate_sequence start 1 increment 1;

create table DEPARTMENT (
    id varchar(36) not null,
    first_working_date timestamp,
    last_working_date timestamp,
    name varchar(255),
    primary key (id)
);
create table EMPLOYEES (
    id varchar(36) not null,
    event VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK' not null,
    first_name varchar(255),
    first_working_date timestamp,
    last_name varchar(255),
    last_working_date timestamp,
    department_id varchar(36),
    primary key (id)
);
alter table EMPLOYEES
    add constraint FK8if1byloc650qvkaxabyjveap foreign key (department_id) references DEPARTMENT