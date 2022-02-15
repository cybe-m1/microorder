--INSERT TABLE COMPETENCES
insert into competence (nom, descriptif) values('Java', 'Compétence en java');
insert into competence (nom, descriptif) values('POO', 'Compétence en Programmation Orientée Objet');
insert into competence (nom, descriptif) values('C', 'Compétence en C');

--INSERT TABLE OFFREEMPLOI
insert into offre_emploi (actif, date_dispo, descriptif, nom) values (true, '2021-12-12', 'Cherche développeur en alternance', 'Dev alternance');
insert into offre_emploi (actif, date_dispo, descriptif, nom) values (true, '2021-12-12', 'Cherche photographe pour mission courte de suivi de personnalités', 'Photographe');

-- ASSOCIATION COMPETENCE A UN CANDIDAT
insert into competence_candidat (niveau_expertise, candidat_can_id, competence_com_id) values (3, 1, 3);

-- ASSOCIATION COMPETENCE A UNE OFFREEMPLOI
insert into competence_offreemploi (obligatoire, competence2_com_id, offre_emploi2_off_id) values(true, 1, 1)


