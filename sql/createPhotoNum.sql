CREATE TABLE LesClients (
	mail varchar2(80),
	nom varchar2(30) not null,
	prenom varchar2(30) not null,
	mdp varchar2(80) not null,
	numeroRue number(3) not null,
	nomRue varchar2(80) not null,
	ville varchar2(30) not null,
	cp number(5) not null,
	pays varchar2(30) not null,
	constraint PriC1 primary key (mail)
);

CREATE TABLE LesCodesPromo (
	code varchar2(10), -- Attention si de manière procédurale.
	mail varchar2(80),
	estUtilise number(1),
	constraint priCP1 primary key (code),-- Genéré de manière procédurale.
	constraint frClientCP foreign key (mail) references LesClients(mail)
);

CREATE TABLE LesCommandes (
	idCommande number(3),
	mail varchar2(80) not null,
	dateCommande date not null,
	estLivreChezClient number(1) not null,
	status varchar2(20) not null,
	codePromo varchar2(10) null,
	constraint priCO1 primary key (idCommande),
	constraint frClientCom foreign key (mail) references LesClients(mail),
	constraint frCodeProm foreign key (codePromo) references LesCodesPromo(code),
	constraint ckStat check (status in ('enCours','preteEnvoi','envoyee'))
);

CREATE TABLE LesAdressesDeLivraison (
	mailClient varchar(80) not null,
	numeroRue number(4) not null,
	nomRue varchar2(80) not null,
	ville varchar2(30) not null,
	cp number(5) not null,
	pays varchar2(30) not null,
	constraint priA1 primary key (numeroRue,nomRue,ville,cp,pays),
	constraint frClientAdresse foreign key (mailClient) references LesClients(mail)
);

CREATE TABLE LesProduits (
	reference varchar(20),
	prix float(3),
	stock number(3),
	constraint PriP1 primary key (reference)
);

CREATE TABLE LesFichiersImage (
	chemin varchar2(80),
	mailProprio varchar2(80),
	infoPVD varchar2(30),
	resolution integer,
	estPartage number(1),
	dateUpload Date,
	constraint PriF1 primary key (chemin),
	constraint frFichierClient foreign key (mailProprio) references LesClients(mail)
);

CREATE TABLE LesImpressions (
	idImpression number(3),
	mailClient varchar2(80),
	reference varchar2(20) not null,
	type varchar2(30) not null,
	titre varchar2(30) null,-- Ne doit pas être nul si c'est un album.
	constraint priImpr1 primary key (idImpression),
	constraint frRefImpr1 foreign key (reference) references LesProduits(reference),
	constraint frClient foreign key (mailClient) references LesClients(mail),
	constraint ckType check (type in ('tirage','cadre','album','calendrier'))
);

CREATE TABLE LesPages (
	idPage number(3),-- AUTO_INCREMENT
	idImpression number(3),
	miseEnForme varchar2(20),
	constraint priPa1 primary key (idPage),
	constraint frImprPage1 foreign key (idImpression) references LesImpressions(idImpression)
);

CREATE TABLE LesPhotos (
	idPhoto number(3) ,--AUTO_INCREMENT
	chemin varchar2(80) not null,
	mailClient varchar2(80) not null,
	constraint priPho1 primary key (idPhoto),
	constraint frPhoFichier foreign key (chemin) references LesFichiersImage(chemin)
	constraint frMailClient foreign key (mailClient) references LesClients(mail)
);


CREATE TABLE LesPhotosParPages (
	idPhoto number(3),
	idPage number(3),
	constraint priPhotoParPage primary key (idPhoto,idPage),
	constraint frPPPLesPages foreign key (idPage) references LesPages(idPage),
	constraint frPPPLesPhotos foreign key (idPhoto) references LesPhotos(idPhoto)
);

CREATE TABLE LesArticles (
	idCommande number(3),
	idImpression number(3),
	quantite number(3),
	constraint priArticle1 primary key (idCommande,idImpression),
	constraint frImprArticle foreign key (idImpression) references LesImpressions(idImpression),
	constraint frImprCommande foreign key (idCommande) references LesCommandes(idCommande),
	constraint ckQuantite check (quantite>=0)
);

CREATE TABLE LesPhotosTirees (
	idPhoto number(3),
	nbPhotoTirees number(3),
	constraint priPT1 primary key (idPhoto),
	constraint frPtPhoto foreign key (idPhoto) references LesPhotos(idPhoto),
	constraint ckNbTirees check (nbPhotoTirees >=1)
);

CREATE TABLE LesPhotosAlbum (
	idPhoto number(3),
	texteDescriptif varchar2(80),
	constraint priPPA1 primary key (idPhoto),
	constraint frPPALesPhoto foreign key (idPhoto) references LesPhotos(idPhoto)
);

CREATE VIEW LesCommandesPrix AS
	SELECT idCommande, sum(prix * quantite * CASE WHEN codePromo IS NULL THEN 1 ELSE 0.95 END) AS prixTotal
	FROM LesCommandes
	NATURAL JOIN LesArticles
	NATURAL JOIN LesImpressions
	NATURAL JOIN LesProduits
	GROUP BY idCommande
;