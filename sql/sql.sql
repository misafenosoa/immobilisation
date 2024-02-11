create database immobilisation;

-- sprint 1 : acquisition
create table nature (
    natureid varchar(250) primary key,
    nature varchar(250),
    comptecode varchar(250)
);

create table typeamortissement(
    typeamortissementid varchar(250) primary key
);

create table typeamortissementregle(
    typeamortissementregle serial primary key,
    typeamortissementid varchar(250),
    debut integer ,
    fin integer,
    coefficient double precision
);

insert into typeamortissement values ('lineaire'),('degressif');

insert into typeamortissementregle values(default , 'degressif',2,4,1.25),(default , 'degressif',5,6,1.75),(default,'degressif',7,20 ,1.75);

create table bienacquis(
    bienacquisid text primary key,
    dateacquis timestamp default(now()),
    serie serial,
    villeacquis text,
    depot text,
    natureid varchar(250),
    description text,
    idtypeamortissement varchar(250),
    anneeamorti integer, 
    foreign key (idtypeamortissement) references typeamortissement,
    foreign key (natureid) references nature(natureid)
);

