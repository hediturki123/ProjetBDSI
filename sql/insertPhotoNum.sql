INSERT INTO LesClients VALUES (
    'alexis@gmail.com', 'YVON', 'Alexis', 'motdepasse',
    11, 'rue Marinho', 'Grenoble', 38100, 'France', 1
);

INSERT INTO LesClients VALUES (
    'nico@gmail.com', 'MARINHO', 'Nicolas', 'motdepasse',
    11, 'rue Yvon', 'Grenoble', 38100, 'France', 1
);

INSERT INTO LesClients VALUES (
    'pierre@gmail.com', 'BRUYERE', 'Pierre', 'motdepasse',
    11, 'rue Turki', 'Grenoble', 38100, 'France', 1
);

INSERT INTO LesClients VALUES (
    'hedi@gmail.com', 'TURKI', 'Hedi', 'motdepasse',
    11, 'rue Bruyere', 'Grenoble', 38100, 'France', 1
);

INSERT INTO LesAdressesDeLivraison VALUES ('alexis@gmail.com', 12, 'rue Marinho', 'Grenoble', 38100, 'France');
INSERT INTO LesAdressesDeLivraison VALUES ('nico@gmail.com', 12, 'rue Yvon', 'Grenoble', 38100, 'France');
INSERT INTO LesAdressesDeLivraison VALUES ('pierre@gmail.com', 12, 'rue Turki', 'Grenoble', 38100, 'France');
INSERT INTO LesAdressesDeLivraison VALUES ('hedi@gmail.com', 12, 'rue Bruyere', 'Grenoble', 38100, 'France');

INSERT INTO LesCommandes VALUES (1, 'alexis@gmail.com', '02-FEB-21', 1, 'preteEnvoi', null, 12, 'rue Marinho', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (2, 'alexis@gmail.com', '21-MAR-21', 1, 'enCours', null, 12, 'rue Marinho', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (3, 'alexis@gmail.com', '25-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Marinho', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (4, 'alexis@gmail.com', '23-MAR-21', 1, 'envoyee', null, 12, 'rue Marinho', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (5, 'alexis@gmail.com', '01-JAN-21', 1, 'envoyee', null, 12, 'rue Marinho', 'Grenoble', 38100, 'France');

INSERT INTO LesCommandes VALUES (6, 'nico@gmail.com', '30-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Yvon', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (7, 'nico@gmail.com', '31-MAR-21', 1, 'enCours', null, 12, 'rue Yvon', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (8, 'nico@gmail.com', '01-MAR-21', 1, 'enCours', null, 12, 'rue Yvon', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (9, 'nico@gmail.com', '02-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Yvon', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (10, 'nico@gmail.com', '03-MAR-21', 1, 'enCours', null, 12, 'rue Yvon', 'Grenoble', 38100, 'France');

INSERT INTO LesCommandes VALUES (11, 'pierre@gmail.com', '30-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Turki', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (12, 'pierre@gmail.com', '31-MAR-21', 1, 'enCours', null, 12, 'rue Turki', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (13, 'pierre@gmail.com', '01-MAR-21', 1, 'enCours', null, 12, 'rue Turki', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (14, 'pierre@gmail.com', '02-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Turki', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (15, 'pierre@gmail.com', '03-MAR-21', 1, 'enCours', null, 12, 'rue Turki', 'Grenoble', 38100, 'France');

INSERT INTO LesCommandes VALUES (16, 'hedi@gmail.com', '30-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Bruyere', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (17, 'hedi@gmail.com', '31-MAR-21', 1, 'enCours', null, 12, 'rue Bruyere', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (18, 'hedi@gmail.com', '01-MAR-21', 1, 'enCours', null, 12, 'rue Bruyere', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (19, 'hedi@gmail.com', '02-MAR-21', 1, 'preteEnvoi', null, 12, 'rue Bruyere', 'Grenoble', 38100, 'France');
INSERT INTO LesCommandes VALUES (20, 'hedi@gmail.com', '03-MAR-21', 1, 'enCours', null, 12, 'rue Bruyere', 'Grenoble', 38100, 'France');

INSERT INTO LesProduits VALUES ('CARTA7BRIL', 10.5, 10);
INSERT INTO LesProduits VALUES ('100X200M', 12, 5);
INSERT INTO LesProduits VALUES ('TABA4MAT', 25, 8);
INSERT INTO LesProduits VALUES ('50X50TTT', 18, 1);
INSERT INTO LesProduits VALUES ('CALA4BRIL', 13, 2);
INSERT INTO LesProduits VALUES ('TABA4BRIL', 5, 20);

INSERT INTO LesImpressions VALUES (1, 'alexis@gmail.com', 'CARTA7BRIL', 'tirage', 'MonTirage');
INSERT INTO LesImpressions VALUES (2, 'alexis@gmail.com', '100X200M', 'cadre', 'MonCadre');
INSERT INTO LesImpressions VALUES (3, 'alexis@gmail.com', 'TABA4MAT', 'album', 'MonAlbum');
INSERT INTO LesImpressions VALUES (4, 'alexis@gmail.com', '50X50TTT', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (5, 'alexis@gmail.com', 'CALA4BRIL', 'tirage', 'MonTirage');

INSERT INTO LesImpressions VALUES (6, 'nico@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (7, 'nico@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (8, 'nico@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (9, 'nico@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (10, 'nico@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');

INSERT INTO LesImpressions VALUES (11, 'pierre@gmail.com', 'CARTA7BRIL', 'tirage', 'MonTirage');
INSERT INTO LesImpressions VALUES (12, 'pierre@gmail.com', '100X200M', 'cadre', 'MonCadre');
INSERT INTO LesImpressions VALUES (13, 'pierre@gmail.com', 'TABA4MAT', 'album', 'MonAlbum');
INSERT INTO LesImpressions VALUES (14, 'pierre@gmail.com', '50X50TTT', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (15, 'pierre@gmail.com', 'CALA4BRIL', 'tirage', 'MonTirage');

INSERT INTO LesImpressions VALUES (16, 'hedi@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (17, 'hedi@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (18, 'hedi@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (19, 'hedi@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');
INSERT INTO LesImpressions VALUES (20, 'hedi@gmail.com', 'TABA4BRIL', 'calendrier', 'MonCalendrier');

INSERT INTO LesArticles VALUES (1, 1, 1);
INSERT INTO LesArticles VALUES (2, 2, 3);
INSERT INTO LesArticles VALUES (3, 3, 5);
INSERT INTO LesArticles VALUES (4, 4, 1);
INSERT INTO LesArticles VALUES (5, 5, 2);
INSERT INTO LesArticles VALUES (6, 6, 1);
INSERT INTO LesArticles VALUES (7, 7, 1);
INSERT INTO LesArticles VALUES (8, 8, 1);
INSERT INTO LesArticles VALUES (9, 9, 1);
INSERT INTO LesArticles VALUES (10, 10, 1);
INSERT INTO LesArticles VALUES (11, 11, 1);
INSERT INTO LesArticles VALUES (12, 12, 3);
INSERT INTO LesArticles VALUES (13, 13, 5);
INSERT INTO LesArticles VALUES (14, 14, 1);
INSERT INTO LesArticles VALUES (15, 15, 2);
INSERT INTO LesArticles VALUES (16, 16, 1);
INSERT INTO LesArticles VALUES (17, 17, 1);
INSERT INTO LesArticles VALUES (18, 18, 1);
INSERT INTO LesArticles VALUES (19, 19, 1);
INSERT INTO LesArticles VALUES (20, 20, 1);
