INSERT INTO FITNESS_CENTAR(naziv_centra, adresa, broj_telefona_centra, email_centra, obrisan) VALUES ('MAX GYM', 'Mihajla Pavicevica 5', '035112233', 'maxgym.jag@gmail.com', '0');
INSERT INTO FITNESS_CENTAR(naziv_centra, adresa, broj_telefona_centra, email_centra, obrisan) VALUES ('SUPER GYM', 'Marsala Tita 25', '035332211', 'supergym.jag@gmail.com', '0');
INSERT INTO FITNESS_CENTAR(naziv_centra, adresa, broj_telefona_centra, email_centra, obrisan) VALUES ('MEGA GYM', 'Partizanska 29', '021123456', 'megagym.ns@gmail.com', '1');

INSERT INTO ADMINISTRATOR(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('Djokeen', 'Djordje', 'Rasic', '1234', 'djokeen@gmail.com', '2000-07-20', '0648202942', '1');

INSERT INTO CLAN(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('dencica_', 'Aleksandra', 'Dencic', '1234', 'adencic@gmail.com', '2000-12-12 12:00:00', '0690011223', '1');
INSERT INTO CLAN(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('bojan31', 'Bojan', 'Radojicic', '1234', 'bradojicic@gmail.com', '2000-11-11', '0611223344', '1');
INSERT INTO CLAN(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('izet', 'Izet', 'Fazlinovic', '1234', 'ifazlinovic@gmail.com', '2000-06-06 12:00:00', '0691111111', '1');
INSERT INTO CLAN(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('faruk', 'Faruk', 'Fazlinovic', '1234', 'ffazlinovic@gmail.com', '2000-05-05', '0692222222', '1');
INSERT INTO CLAN(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan) VALUES ('damir', 'Damir', 'Fazlinovic', '1234', 'dfazlinovic@gmail.com', '2000-05-05', '0692222322', '1');


INSERT INTO TRENER(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('isi', 'Isidora', 'Tulumbic', '1234', 'itulumbic@gmail.com', '2000-10-10', '0644332211', '1', '0', '3.5', 1);
INSERT INTO TRENER(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('aloja', 'Andrea', 'Lojpur', '1234', 'alojpur@gmail.com', '2000-09-09', '0644442211', '1', '0', '4.0', 1);
INSERT INTO TRENER(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('aleksa', 'Aleksa', 'Rasic', '1234', 'arasic@gmail.com', '2000-08-08', '0644112211', '0', '0', '0.0', 1);
INSERT INTO TRENER(username, ime, prezime, lozinka, email, datum_rodjenja, telefon, aktivan, obrisan, prosecna_ocena, fitness_centar_id) VALUES ('milica', 'Milica', 'Rasic', 'moze', 'mrasic@gmail.com', '2000-07-07', '0644222211', '0', '0', '0.0', 1);

INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id, u_upotrebi) VALUES ('10', 'MG1', 1, 1);
INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id, u_upotrebi) VALUES ('15', 'MG2', 1, 1);
INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id, u_upotrebi) VALUES ('11', 'SG1', 2, 1);
INSERT INTO SALA(kapacitet, oznaka_sale, fitness_centar_id, u_upotrebi) VALUES ('1', 'MG3', 1, 1);

INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('NAZIV1', 'OPIS1', 'TIP1'); 
INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('NAZIV2', 'OPIS2', 'TIP2');
INSERT INTO TRENING(naziv_treninga, opis, tip) VALUES ('NAZIV3', 'OPIS3', 'TIP3');

INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-11-04 10:30:00', '2021-11-04 12:30:00', '120', '550', 0, 1, 1, 1); 
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-11-05 12:15:00', '2021-11-05 13:15:00', '60', '750', 0, 2, 2, 2);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-11-06 18:15:00', '2021-11-06 19:20:00', '65', '1050', 0, 3, 2, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-11-07 20:30:00', '2021-11-07 21:15:00', '45', '500', 0, 1, 1, 4); 
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-05-01 10:45:00', '2021-05-01 12:15:00', '90', '700', 0, 2, 2, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-05-02 13:15:00', '2021-05-02 13:55:00', '40', '500', 0, 3, 1, 1);
INSERT INTO TERMIN(pocetak_termina, kraj_termina, trajanje_termina, cena_termina, otkazan, trening_id, trener_id, sala_id) VALUES ('2021-11-11 13:05:00', '2021-11-11 13:55:00', '50', '300', 0, 3, 1, 1);


INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('4.0', 6, 1);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('3.0', 6, 1);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('3.0', 5, 3);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('5.0', 5, 4);
INSERT INTO OCENA(ocena, termin_id, clan_id) VALUES ('4.0', 5, 5);

INSERT INTO ATTENDED(termin_id, clan_id) VALUES (5, 1);
INSERT INTO ATTENDED(termin_id, clan_id) VALUES (5, 2);
INSERT INTO ATTENDED(termin_id, clan_id) VALUES (6, 1);
INSERT INTO ATTENDED(termin_id, clan_id) VALUES (6, 2);

INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (1, 1);
INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (3, 1);
INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (4, 2);
INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (7, 3);
INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (7, 4);
INSERT INTO UNATTENDED(termin_id, clan_id) VALUES (7, 5);
