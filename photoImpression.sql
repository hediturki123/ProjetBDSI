DROP TABLE IF EXISTS LesClients;
DROP TABLE IF EXISTS LesCommandes;
DROP TABLE IF EXISTS LesAdresses;
DROP TABLE IF EXISTS LesCodePromos;
DROP TABLE IF EXISTS LesProduits;
DROP TABLE IF EXISTS LesImpressions;
DROP TABLE IF EXISTS LesFichiers;
DROP TABLE IF EXISTS LesArticles;
DROP TABLE IF EXISTS LesPhotos;
DROP TABLE IF EXISTS LesPhotoTirees;
DROP TABLE IF EXISTS LesPhotoAlbums;
DROP TABLE IF EXISTS LesPhotosParPages;
DROP TABLE IF EXISTS LesPages;

CREATE TABLE LesClients (
	mail varchar2(80),
	nom varchar2(30) not null,
	prenom varchar2(30) not null,
	mdp varchar2(80) not null ,
	numeroRue number(3) not null,
	nomRue varchar2(80) not null,
	ville varchar2(30)not null,
	cp number(5) not null,
	pays varchar2(30) not null,
	constraint PriC1 primary key (mail)
);

CREATE TABLE LesCodesPromos(
	mail varchar2(80),
	code varchar2(10),
	estUtilise boolean,
	constraint priCP1 primary key (code),--generé random
	constraint frClientCP foreign key (mail) references LesClients(mail)
);

CREATE TABLE LesCommandes(
	idCommande number(3) AUTO_INCREMENT not null,
	mail varchar2(80) not null,
	dateCommande DATE not null,
	estLivreChezClient boolean not null,
	prixTotal number(5) not null,
	status varchar2(20) not null,
	code varchar(10) not null,
	constraint priCO1 primary key (id),
	constraint frClientCom foreign key (mail) references LesClients(mail),
	constraint frCodeProm foreign key (code) references LesCodePromos(code),
	constraint ckStat check status in ('enCours','preteEnvoi','envoyee')

);

CREATE TABLE LesAdresses(
	mailClient varchar(80) not null,
	numeroRue number(4) not null,
	nomRue varchar2(80) not null,
	ville varchar2(30) not null,
	cp number(5) not null,
	pays varchar2(30) not null,
	constraint priA primary key (numeroRue,nomRue,ville,cp,pays),
	constraint frClientAdresse foreign key (mailClient) references LesClients(mail)
);

CREATE TABLE LesProduits(
	reference varchar(20),
	prix float(3),
	stock number(3),
	constraint PriP1 primary key (reference)
);

CREATE TABLE LesFichiers(
	chemin varchar2(80),
	mailProprio varchar2(80),
	infoPVD varchar2(30),
	resolution integer,
	estPartage boolean,
	dateUpload Date,
	constraint PriF1 primary key (chemin),
	constraint frFichierClient foreign key (mailProprio) references LesClients(mail)	
);

CREATE TABLE LesImpressions(
	idImpression number(3),
	type varchar2(30),
	titre varchar2(30) null,--ne doit pas être nul si c'est un album
	constraint PriIM1 primary key (chemin),
	constraint ckType check type in ('tirage','cadre','album','calendrier')

);

CREATE TABLE LesPages(
	idPage number(3) AUTO_INCREMENT,
	idImpression number(3),
	miseEnForme varchar2(20),
	constraint priPa1 primary key (idPage),
	constraint frImprPage1 foreign key (idImpression) references LesImpressions(idImpression)
);

CREATE TABLE LesPhotos(
	idPhoto number(3) AUTO_INCREMENT,
	chemin varchar2(80) not null,
	constraint priPho1 primary key (idPhoto),
	constraint frPhoFichier foreign key (chemin) references LesFichiers(chemin)
);


CREATE TABLE LesPhotosParPages(
	idPhoto number(3),
	idPage number(3),
	constraint priPhotoParPage primary key (idPhoto,idPage),
	constraint frPPPLesPages foreign key (idPage) references LesPages(idPage),
	constraint frPPPLesPhotos foreign key (idPhoto) references LesPhotos(idPhoto)
);

CREATE TABLE LesArticles(
	idCommande number(3),
	idImpression number(3),
	quantite number(3),
	constraint priArticle1 primary key (idCommande,idImpression),
	constraint frImprArticle foreign key (idImpression) references LesImpressions(idImpression),
	constraint frImprCommande foreign key (idCommande) references LesCommandes(idCommande)

);
CREATE TABLE LesPhotoTirees(
	idPhoto number(3),
	nbPhotoTirees number(3),
	constraint priPT1 primary key (idPhoto),
	constraint frPtPhoto foreign key (idPhoto) references LesPhotos(idPhoto),
	constraint ckNbTirees check nbPhotoTirees >=1 
);

CREATE TABLE LesPhotoAlbums(
	idPhoto number(3),
	titre varchar (80),
	constraint priPPA1 primary key (idPhoto),
	constraint frPPALesPhoto foreign key (idPhoto) references LesPhotos(idPhoto)
);

