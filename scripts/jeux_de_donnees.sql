insert into UTILISATEURS (pseudo, nom, prenom, email,telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
values ('antoine1', 'Guillo', 'Antoine', 'antoine.guillo@gmail.com', '0123456789', '1 rue de la rue', '35000', 'Rennes', '{bcrypt}$2b$12$VYqvDmv3Q1vLQgzTr6UgCeKsd2j3mSHHOiwE8PbKQbb/Zy3FqgR4u', 500, TRUE ),
       ('Batou1', 'Kara', 'Baptiste', 'baptiste.kara@gmail.com', '0123456789', '1 rue de la rue', '35000', 'Rennes', '{bcrypt}$2b$12$VYqvDmv3Q1vLQgzTr6UgCeKsd2j3mSHHOiwE8PbKQbb/Zy3FqgR4u', 500, TRUE );

INSERT INTO ROLES (email, role)
VALUES ('antoine.guillo@gmail.com', 'admin'),
       ('baptiste.kara@gmail.com', 'admin');

INSERT INTO CATEGORIES (libelle)
VALUES ('Mobilier'),
       ('Vêtements'),
       ('Electronique'),
       ('Livres et Jeux'),
       ('Divers');

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES ('enchereur1', 'Dupont', 'Jean', 'jean.dupont@exemple.com', '0123456789', '1 rue des Fleurs', '75000', 'Paris', '{bcrypt}$2b$12$VYqvDmv3Q1vLQgzTr6UgCeKsd2j3mSHHOiwE8PbKQbb/Zy3FqgR4u', 100, FALSE),
       -- mot de passe original: 'password'
       ('vendeur1', 'Martin', 'Léa', 'lea.martin@exemple.com', '0987654321', '5 avenue Victor Hugo', '35000', 'Rennes', '{bcrypt}$2b$12$6GQiH1HICZh3A5sFEJ6l8O5mGHJSDkfHJfL.q2dCkOFG8Sy3Rz8L6', 50, TRUE),
       -- mot de passe original: 'motdepasse'
       ('acheteur2', 'Lefebvre', 'Paul', 'paul.lefebvre@exemple.com', '0654321987', '2 place de la République', '69000', 'Lyon', '{bcrypt}$2b$12$eWi4J9Fty5Gh7/.7rhEAsOu8TcH1BHeaHG0ZdMN9O.KFwaZ/jYTGm', 200, FALSE);
       -- mot de passe original: 'password'

INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)
VALUES ('Canapé en cuir', 'Magnifique canapé en cuir marron, très confortable.', '2024-07-08', '2024-07-22', 100, 2, 1),
       ('Robe de soirée', 'Robe de soirée noire, jamais portée, taille 38.', '2024-07-07', '2024-07-15', 50, 1, 2),
       ('Smartphone récent', 'Smartphone dernière génération, état neuf.', '2024-07-05', '2024-07-12', 300, 3, 3);

INSERT INTO ROLES (email, role)
VALUES ('lea.martin@exemple.com', 'admin');


INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (1, 1, '2024-07-08', 120),
       (3, 1, '2024-07-09', 150),
       (2, 2, '2024-07-08', 60);

INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES (1, '10 boulevard Haussmann', '75009', 'Paris'),
       (2, '3 rue des Lilas', '35000', 'Rennes');