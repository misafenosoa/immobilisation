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



create table bienacquis(
    bienacquisid text primary key,
    dateacquis date default(now()),
    serie serial,
    villeacquis text,
    depot text,
    natureid varchar(250),
    description text,
    idtypeamortissement varchar(250),
    anneeamorti integer, 
    achat double precision ,
    etatgeneral integer default(10),
    foreign key (idtypeamortissement) references typeamortissement,
    foreign key (natureid) references nature(natureid)
);

create table bienconfigurationpardefaut(
    bienconfigurationpardefaut serial primary key ,
    natureidmere text ,
    naturefille text ,
    quantite double precision default(1),
    foreign key (natureidmere) references nature(natureid),
    foreign key (naturefille) references nature(natureid)
);

create table historisationconfiguration(
    historisationconfigurationid serial primary key,
    bienacquisid text ,
    naturefille text ,
    datehistorisationdebut date default(now()),
    datehistorisationfin date ,
    quantite double precision default (1),
    foreign key (bienacquisid) references bienacquis(bienacquisid),
    foreign key (naturefille) references nature(natureid)
);