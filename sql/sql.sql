create database immobilisation;

-- sprint 1 : acquisition
create table nature (
    natureid varchar(250) primary key,
    nature varchar(250),
    comptecode varchar(250)
);

insert into nature values('ordinateur','ordinateur',2183);
insert into nature values('ordinateur','ordinateur',2183);
insert into nature values('chaise','chaise',2184);
insert into nature values('table','table',2185);
insert into nature values('imprimante','imprimante',2186);
insert into nature values('clavier','clavier',2187);


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
    dateacquis date default(now()),
    serie serial,
    villeacquis text,
    depot text,
    natureid varchar(250),
    description text,
    idtypeamortissement varchar(250),
    anneeamorti integer, 
    achat double precision ,
    foreign key (idtypeamortissement) references typeamortissement,
    foreign key (natureid) references nature(natureid)
);

insert into bienacquis(bienacquisid,dateacquis,villeacquis,depot,natureid,description,idtypeamortissement,anneeamorti, achat) values (1,'2021-06-14','tana', 'entrepot1','imprimante',' a utiliser avec precaution, attention aux touches', 'lineaire',5,500);
insert into bienacquis(bienacquisid, dateacquis, villeacquis, depot, natureid, description, idtypeamortissement, anneeamorti, achat) 
values 
    ('1', '2021-06-14', 'tana', 'entrepot1', 'imprimante', 'à utiliser avec précaution, attention aux touches', 'lineaire', 5, 500),
    ('2', '2022-01-20', 'tana', 'entrepot1', 'ordinateur', 'performances élevées, idéal pour le travail multitâche', 'degressif', 6, 800),
    ('3', '2022-03-05', 'maj', 'entrepot3', 'chaise', 'confortable et ergonomique, adaptée pour de longues heures de travail', 'degressif', 7, 150),
    ('4', '2022-08-10', 'maj', 'entrepot3', 'table', 'solide et durable, parfait pour les réunions en salle de conférence', 'lineaire', 5, 300),
    ('5', '2022-11-28', 'tana', 'entrepot2', 'clavier', 'rétroéclairage LED, touches silencieuses pour une frappe confortable', 'lineaire', 5, 100);

    ('6','2022-11-28' , 'tana' ,'entrepot4' ,'ordinateur' ,'blabla' ,'lineaire' ,5 ,3500.00);
-- 1 : mi creer view mi generer anneemisesy 1 a anneeamorti

-- 2 : 




________________________________________________________________________________
Année | Base | Taux dégressif | Taux linéaire | Annuité | Valeur nette comptable|
________________________________________________________________________________  
 
