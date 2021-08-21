create table project (id bigint not null, code varchar(255) not null, name varchar(255) not null, project_manager_id bigint not null, primary key (id));
create table task (id bigint not null, deadline date not null, description varchar(255) not null, progress decimal(19,2) not null, status varchar(255) not null, assignee_id bigint, project_id bigint not null, primary key (id));
create table user (id bigint not null, name varchar(255) not null, surname varchar(255) not null, email varchar(255) not null, username varchar(255) not null, password varchar(255) not null, role varchar(255) not null, primary key (id));

alter table task add constraint fk_task__assignee foreign key (assignee_id) references user;
alter table task add constraint fk_task__project foreign key (project_id) references project;
alter table project add constraint fk_project__project_manager foreign key (project_manager_id) references user;

insert into user (id, name, surname, email, username, password, role) values
                                         (1, 'Rafal', 'Wiktorski', 'r.wiktorski@manhattan.com', 'r.wiktorski', '$2a$10$kyH096o91Gax3NNch3j2r.SwxrLBXPuX3X258w7fDFo9x9NF.Z7Fq', 'ADMINISTRATOR'),
                                         (2, 'Robert', 'Oppenheimer', 'r.oppenheimer@manhattan.com', 'r.oppenheimer', '$2a$10$zDBCDR189.3iMp4sttUqe.cjM.K3PkF/vnbljhPvtc.5dX5rXJIny', 'PROJECT_MANAGER'),
                                         (3, 'Hans', 'Bethe', 'h.bethe@manhattan.com', 'h.bethe', '$2a$10$E70U3DkjCa.8ldvWcmcM9.LwWKRC7XITPQjlJtaNRhV3a44VCaEuu', 'DEVELOPER');

insert into project (id, code, name, project_manager_id) values
(1, 'MHTN', 'Manhattan Project', 2),
(2, 'X', 'Project X', 2);

insert into task (id, project_id, description, progress, status, deadline) values
(1, 1, 'Construct atomic bomb', 25.6, 'IN_PROGRESS', '1945-01-01'),
(2, 1, 'Test 1', 0, 'NEW', '1945-08-06'),
(3, 1, 'Test 2', 0, 'NEW', '1945-08-09');