package fr.univ.tours.siad.jaxb;

import fr.univ.tours.siad.jaxb.model.club.Sport;
import fr.univ.tours.siad.jaxb.model.club.SportType;
import fr.univ.tours.siad.jaxb.model.person.*;
import fr.univ.tours.siad.jaxb.util.ValidateProcess;
import org.junit.Test;

import java.util.logging.Logger;

public class td2 extends ValidateProcess {

    private static final Logger LOGGER = Logger.getLogger("td1");

    /**
     * TD 2
     * Pour valider la serialisation d'une association, vous devrez respecter l'exemple fourni en commentaire à la fin de ce fichier source
     * Les nom doivent être francisé
     * - Association : le nom de l'élément doit être association_sportive
     *      - id : identifiant en attribut
     *      - sport : en attribut
     *      - sportType : en attribut et leur valeur doit être un entier (respectivement 10 et 100)
     *      - teamCount : en attribut (nombre_equipe)
     * - La liste d'adherent doit être nommée "membres"
     *      - pour les adherent membre du bureau : chaque element de cette classe doit être nommé membre_bureau
     *          - le type de membre du bureau doit être en français (respectivement président, trésorier, secretaire) et doit être placé en attribut
     *      - Pour les adhérents simple, le nom doit être adherent
     *      - Pour les donnateur, le nom doit être donnateur
     *
     * Aidez vous de l'exemple ;)
     *
     */
    @Test
    public void validateAssociationSportive() {
        Sport sport = new Sport(42L, "Vivas Basket", "basketball", SportType.TEAM, 10);
        sport.setAddress(new Address(3L, "Veigné", "37250", "Rue du stade"));
        sport.addAdherent(new BoardMember(10L, "machin", "truc", "1999-12-31", BoardMemberType.PRESIDENT, new Address(1L, "Tours", "37000", "Ligne 1.1", "Ligne 1.2")));
        sport.addAdherent(new BoardMember(11L, "bidule", "truc", "2005-02-12", BoardMemberType.TREASURER, new Address(2L, "Veigné", "37250", "Ligne 2.1", "Ligne 2.2")));
        sport.addAdherent(new Adherent(101L, "Player", "One", "2005-02-12", new Address(2001L, "Veigné", "37250", "Ligne 2001.1", "Ligne 2001.2")));
        sport.addAdherent(new Adherent(102L, "Player", "Two", "2004-01-16", new Address(2002L, "Veigné", "37250", "Ligne 2002.1", "Ligne 2002.2")));
        sport.addAdherent(new Adherent(103L, "Player", "Three", "2008-05-01", new Address(2003L, "Veigné", "37250", "Ligne 2003.1", "Ligne 2003.2")));
        sport.addAdherent(new Adherent(104L, "Tony", "Parker", "2013-09-20", new Address(2004L, "Veigné", "37250", "Ligne 2004.1", "Ligne 2004.2")));
        sport.addAdherent(new Adherent(105L, "Rudy", "Gobert", "2013-09-20", new Address(2005L, "Veigné", "37250", "Ligne 2005.1", "Ligne 2005.2")));
        sport.addDonor(new Donor(2L, "Anthony Jr", "Stark", 10000000, DonorType.PP, new Address(255L, "Tours", "37000", "Ligne 2.1", "Ligne 2.2")));
        validateXML(sport, "td2-association");
    }

}

/*
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<association_sportive sport="basketball" sport_type="100" nombre_equipe="10" identifiant="42">
    <nom>Vivas Basket</nom>
    <membres>
        <membre_bureau poste="président" identifiant="10">
            <Prenom>machin</Prenom>
            <Nom>truc</Nom>
            <addresse_principale identifiant="1">
                <Ligne>Ligne 1.1</Ligne>
                <Ligne>Ligne 1.2</Ligne>
                <code_postal>37000</code_postal>
                <ville>Tours</ville>
            </addresse_principale>
            <membershipDate>1999-12-31</membershipDate>
        </membre_bureau>
        <membre_bureau poste="trésorier" identifiant="11">
            <Prenom>bidule</Prenom>
            <Nom>truc</Nom>
            <addresse_principale identifiant="2">
                <Ligne>Ligne 2.1</Ligne>
                <Ligne>Ligne 2.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2005-02-12</membershipDate>
        </membre_bureau>
        <adherent identifiant="101">
            <Prenom>Player</Prenom>
            <Nom>One</Nom>
            <addresse_principale identifiant="2001">
                <Ligne>Ligne 2001.1</Ligne>
                <Ligne>Ligne 2001.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2005-02-12</membershipDate>
        </adherent>
        <adherent identifiant="102">
            <Prenom>Player</Prenom>
            <Nom>Two</Nom>
            <addresse_principale identifiant="2002">
                <Ligne>Ligne 2002.1</Ligne>
                <Ligne>Ligne 2002.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2004-01-16</membershipDate>
        </adherent>
        <adherent identifiant="103">
            <Prenom>Player</Prenom>
            <Nom>Three</Nom>
            <addresse_principale identifiant="2003">
                <Ligne>Ligne 2003.1</Ligne>
                <Ligne>Ligne 2003.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2008-05-01</membershipDate>
        </adherent>
        <adherent identifiant="104">
            <Prenom>Tony</Prenom>
            <Nom>Parker</Nom>
            <addresse_principale identifiant="2004">
                <Ligne>Ligne 2004.1</Ligne>
                <Ligne>Ligne 2004.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2013-09-20</membershipDate>
        </adherent>
        <adherent identifiant="105">
            <Prenom>Rudy</Prenom>
            <Nom>Gobert</Nom>
            <addresse_principale identifiant="2005">
                <Ligne>Ligne 2005.1</Ligne>
                <Ligne>Ligne 2005.2</Ligne>
                <code_postal>37250</code_postal>
                <ville>Veigné</ville>
            </addresse_principale>
            <membershipDate>2013-09-20</membershipDate>
        </adherent>
        <donnateur type="PP" identifiant="2">
            <Prenom>Anthony Jr</Prenom>
            <Nom>Stark</Nom>
            <addresse_principale identifiant="255">
                <Ligne>Ligne 2.1</Ligne>
                <Ligne>Ligne 2.2</Ligne>
                <code_postal>37000</code_postal>
                <ville>Tours</ville>
            </addresse_principale>
            <donnation>10000000</donnation>
        </donnateur>
    </membres>
    <adherentCount>7</adherentCount>
    <address identifiant="3">
        <Ligne>Rue du stade</Ligne>
        <code_postal>37250</code_postal>
        <ville>Veigné</ville>
    </address>
</association_sportive>

 */