-- nature
insert into nature values('ordinateur','ordinateur',2183);
insert into nature values('ordinateur','ordinateur',2183);
insert into nature values('chaise','chaise',2184);
insert into nature values('table','table',2185);
insert into nature values('imprimante','imprimante',2186);
insert into nature values('clavier','clavier',2187);

-- type d'amortissement
insert into typeamortissement values ('lineaire'),('degressif');

-- regle d'amortissement
insert into typeamortissementregle values(default , 'degressif',2,4,1.25),(default , 'degressif',5,6,1.75),(default,'degressif',7,20 ,1.75);

-- bien acquis
insert into bienacquis(bienacquisid, dateacquis, villeacquis, depot, natureid, description, idtypeamortissement, anneeamorti, achat,debututilisation ) 
values 
    ('1', '2021-06-14', 'tana', 'entrepot1', 'imprimante', 'à utiliser avec précaution, attention aux touches', 'lineaire', 5, 500),
    ('2', '2022-01-20', 'tana', 'entrepot1', 'ordinateur', 'performances élevées, idéal pour le travail multitâche', 'degressif', 6, 800),
    ('3', '2022-03-05', 'maj', 'entrepot3', 'chaise', 'confortable et ergonomique, adaptée pour de longues heures de travail', 'degressif', 7, 150),
    ('4', '2022-08-10', 'maj', 'entrepot3', 'table', 'solide et durable, parfait pour les réunions en salle de conférence', 'lineaire', 5, 300),
    ('5', '2022-05-02', 'tana', 'entrepot2', 'clavier', 'rétroéclairage LED, touches silencieuses pour une frappe confortable', 'lineaire', 5, 20000,'2020-05-7'),

    ('6','2022-11-28' , 'tana' ,'entrepot4' ,'ordinateur' ,'blabla' ,'lineaire' ,5 ,3500.00);