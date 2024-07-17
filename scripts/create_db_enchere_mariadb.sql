CREATE TABLE CATEGORIES (
                            no_categorie   INT AUTO_INCREMENT NOT NULL,
                            libelle        VARCHAR(30) NOT NULL,
                            PRIMARY KEY (no_categorie)
);

CREATE TABLE ENCHERES (
                          id_enchere       INT AUTO_INCREMENT NOT NULL,
                          no_utilisateur   INT NOT NULL,
                          no_article       INT NOT NULL,
                          date_enchere     DATETIME NOT NULL,
                          montant_enchere  INT NOT NULL,
                          PRIMARY KEY (id_enchere)
);

CREATE TABLE RETRAITS (
                          no_article         INT NOT NULL,
                          rue                VARCHAR(30) NOT NULL,
                          code_postal        VARCHAR(15) NOT NULL,
                          ville              VARCHAR(30) NOT NULL,
                          PRIMARY KEY (no_article)
);

CREATE TABLE UTILISATEURS (
                              no_utilisateur   INT AUTO_INCREMENT NOT NULL,
                              pseudo           VARCHAR(30) NOT NULL,
                              nom              VARCHAR(30) NOT NULL,
                              prenom           VARCHAR(30) NOT NULL,
                              email            VARCHAR(50) NOT NULL, -- Augmenté la taille de l'email
                              telephone        VARCHAR(15),
                              rue              VARCHAR(30) NOT NULL,
                              code_postal      VARCHAR(10) NOT NULL,
                              ville            VARCHAR(30) NOT NULL,
                              mot_de_passe     VARCHAR(255) NOT NULL, -- Augmenté la taille du mot de passe
                              credit           INT NOT NULL,
                              administrateur   BOOLEAN NOT NULL,
                              PRIMARY KEY (no_utilisateur),
                              UNIQUE (email, pseudo) -- Ajout d'une contrainte unique sur l'email
);

CREATE TABLE ARTICLES_VENDUS (
                                 no_article                    INT AUTO_INCREMENT NOT NULL,
                                 nom_article                   VARCHAR(30) NOT NULL,
                                 description                   VARCHAR(300) NOT NULL,
                                 date_debut_encheres           DATE NOT NULL,
                                 date_fin_encheres             DATE NOT NULL,
                                 prix_initial                  INT,
                                 prix_vente                    INT,
                                 no_utilisateur                INT NOT NULL,
                                 no_categorie                  INT NOT NULL,
                                 PRIMARY KEY (no_article)
);

CREATE TABLE ROLES (
                       email        VARCHAR(50) NOT NULL,
                       role         VARCHAR(30) NOT NULL,
                       PRIMARY KEY (email, role),
                       FOREIGN KEY (email) REFERENCES UTILISATEURS (email) -- Ajout de la contrainte de clé étrangère
);

CREATE TABLE image(
    id_image int auto_increment not null,
    no_article int not null,
    image varchar(255) not null,

    PRIMARY KEY (id_image),
    FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
);

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateurs_fk FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY (no_categorie) REFERENCES CATEGORIES (no_categorie)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;
        