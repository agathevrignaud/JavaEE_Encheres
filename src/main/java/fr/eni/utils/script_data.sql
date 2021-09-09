INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe)
VALUES ('avrignaud', 'Vrignaud', 'Agathe', 'testA@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes',
        'Testing123!'),
       ('rstalme', 'StAlme', 'Raphaël', 'testR@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes', 'Testing123!'),
       ('elaunay', 'Launay', 'Ewen', 'testE@gmail.com', '0123456789', 'rue truc', '35000', 'Rennes', 'Testing123!');

INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur)
VALUES ('admin', 'admin', 'admin', 'email', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp', 1);

INSERT INTO CATEGORIES
VALUES ('Informatique'),
       ('Ameublement'),
       ('Vêtements'),
       ('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS
VALUES ('PC Gaming', 'Très Bon Etat Général', '07-09-2021', '10-09-2021', 150, 150, 2, 1),
       ('Veste en Jean', 'Manque un bouton à la poche droite', '06-09-2021', '11-09-2021', 80, 80, 3, 3)
;

