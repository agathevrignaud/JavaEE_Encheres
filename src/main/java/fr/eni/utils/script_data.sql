INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe) VALUES
('avrignaud','Vrignaud', 'Agathe', 'testA@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes', 'Testing123!'),
('rstalme','StAlme', 'Raphaël', 'testR@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes', 'Testing123!'),
('elaunay','Launay', 'Ewen','testE@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes', 'Testing123!');

INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur) VALUES
('admin','admin', 'admin', 'admin@email.com', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp', 1);

INSERT INTO CATEGORIES VALUES
('Informatique'),
('Ameublement'),
('Vêtements'),
('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS VALUES
('PC Gaming', 'Très Bon Etat Général', '07-09-2021', '10-09-2021', 150, 150, 'E', 1, 1),
('Veste en Jean', 'Manque un bouton à la poche droite', '06-09-2021', '11-09-2021', 80, 80, 'E', 3, 3)
;

INSERT INTO ENCHERES VALUES
 (2,1,'2021-09-10 10:30:50',220),
 (3,1,'2021-09-10 11:45:22',250);
