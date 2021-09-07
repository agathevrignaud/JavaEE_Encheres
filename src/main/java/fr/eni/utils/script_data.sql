INSERT INTO UTILISATEURS VALUES
('pseudo','Vrignaud', 'Agathe', 'email', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp'),
('pseudo','StAlme', 'Raphaël', 'email', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp'),
('pseudo','Launay', 'Ewen','email', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp');

INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur) VALUES
('admin','admin', 'admin', 'email', '0123456789', 'rue truc', '35000', 'Rennes', 'mdp', 1);

INSERT INTO CATEGORIES VALUES
('Informatique'),
('Ameublement'),
('Vêtements'),
('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS VALUES
('PC Gaming', 'Très Bon Etat Général', '07-09-2021', '10-09-2021', 150, 150, 2, 1),
('Veste en Jean', 'Manque un bouton à la poche droite', '06-09-2021', '11-09-2021', 80, 80, 3, 3)
;

