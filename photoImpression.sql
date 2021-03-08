DROP TABLE client;
DROP TABLE commande
DROP TABLE adresse;
DROP TABLE codePromo;
DROP TABLE produit

CREATE TABLE client (
	mail varchar2(80),
	nom varchar2(30),
	prenom varchar2(30),
	mdp varchar2(300),
	constraint PriC1 primary key (mail)
);

CREATE TABLE codePromo(
	mail varchar2(80),
	code varchar2(10),
	utilise boolean,
	constraint priCP1 primary key (mail,code),
	constraint frClientCP foreign key (mail) references client(mail) 
);

CREATE TABLE commande(
	idCommande number(3) ,
	mail varchar2(80),
	dateCommande DATE,
	estLivreChezClient boolean,
	prixTotal number(5),
	status varchar2(20),
	constraint priCO1 primary key (id),
	constraint frClientCom foreign key (mail) references client(mail)
);

CREATE TABLE adresse(
	numeroRue number(3),
	nomRue varchar2(80),
	ville varchar2(30),
	cp number(5),
	pays varchar2(30),
	constraint priA primary key //ici voir car la cle pre,d tous car une adresse doit etre totalement uniques 

);

CREATE TABLE produit(
	reference varchar(20),
	prix float(2),
	constraint PriP1 primary key (reference)
);

CREATE TABLE Fichier(
	chemin varchar2(30),
	mail varchar2(80),
	infoPVD varchar2(30),
	resolution integer,
	partage boolean,
	dateUpload Date,
	constraint PriF1 primary key (chemin),
	constraint frFichierClient foreign key (mail) references client(mail)	
);

