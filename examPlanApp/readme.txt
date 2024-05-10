************************************
	Import and install 
************************************

1- under maria db create the database  db_planexam  
2- import the project and execute mvn intsall and mvn update
3- Start the application and check that the tables are created correctly
4- Enter the following data into your database:

INSERT INTO `role` (`idRole`, `nomRole`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_PROF'),
(3, 'ROLE_CADRE_ADMIN'),
(4, 'ROLE_STUDENT');


INSERT INTO `personne` (`idPersonne`, `cin`, `email`, `nom`, `nomArabe`, `photo`, `prenom`, `prenomArabe`, `telephone`) VALUES
(1, 'R1111', 'boudaa@aaa.fr', 'boudaa', 'aaaAr', '', 'aaa', 'tarikAr', '0600000'),
(2, 'R1121', 'boudaa1@aaa.fr', 'boudaa1', 'aaaAr1', '', 'aaa1', 'tarikAr1', '06100000'),
(3, 'R1123', 'boudaa3@aaa.fr', 'boudaa3', 'aaaAr3', '', 'aaa3', 'tarikAr3', '06300000'),
(4, 'R1124', 'boudaa4@aaa.fr', 'boudaa4', 'aaaAr4', '', 'aaa4', 'tarikAr4', '04400000');


INSERT INTO `cadreadministrateur` (`grade`, `idCardreAdmin`) VALUES
('ING', 1);
INSERT INTO `enseignant` (`specialite`, `idEnseighant`) VALUES
('math', 2);

INSERT INTO `etudiant` (`cne`, `dateNaissance`, `idEtudiant`) VALUES
('aaaa', NULL, 3);


INSERT INTO `compte` (`idCompte`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `enabled`, `login`, `password`, `idUtilisateur`, `idRole`) VALUES
(1, b'1', b'1', b'1', b'1', 'admin', '{bcrypt}$2a$10$vsjRwUSUi2B/jlw4ATXEN.B2IIcVY8ourImFZnWcW1tLz3G6mLfpe', 1, 1);


INSERT INTO `compte` (`idCompte`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `enabled`, `login`, `password`, `idUtilisateur`, `idRole`) VALUES
(2, b'1', b'1', b'1', b'1', 'student', '{bcrypt}$2a$10$vsjRwUSUi2B/jlw4ATXEN.B2IIcVY8ourImFZnWcW1tLz3G6mLfpe', 4, 4);



INSERT INTO `compte` (`idCompte`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `enabled`, `login`, `password`, `idUtilisateur`, `idRole`) VALUES
(3, b'1', b'1', b'1', b'1', 'cadre_admin', '{bcrypt}$2a$10$vsjRwUSUi2B/jlw4ATXEN.B2IIcVY8ourImFZnWcW1tLz3G6mLfpe', 3, 3);


INSERT INTO `compte` (`idCompte`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `enabled`, `login`, `password`, `idUtilisateur`, `idRole`) VALUES
(4, b'1', b'1', b'1', b'1', 'prof', '{bcrypt}$2a$10$vsjRwUSUi2B/jlw4ATXEN.B2IIcVY8ourImFZnWcW1tLz3G6mLfpe', 2, 2);




7- restart the application then connect to http://localhost:8080/ with the following accounts:
Compte admin :
**********
login:  admin pass : admin

Compte Student :
**********
login: student pass : admin

Compte cadre admin :
**********
login: cadre_admin pass : admin

Compte prof :
**********
login: prof pass : admin
