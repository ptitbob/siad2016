package fr.univ.tours.siad.jaxb;

import fr.univ.tours.siad.jaxb.model.person.Address;
import fr.univ.tours.siad.jaxb.model.person.Donor;
import fr.univ.tours.siad.jaxb.model.person.DonorType;
import fr.univ.tours.siad.jaxb.util.ValidateProcess;
import org.junit.Test;

import java.util.logging.Logger;

public class td1 extends ValidateProcess {

    private static final Logger LOGGER = Logger.getLogger("td1");

    /**
     * TD 1.1
     * Validation des annotation portée sur la classe Address
     * - La classe doit être sérialisable
     *      - le nom de la sérialisation doit être adresse
     * - La sérialisation doit se baser sur les champs
     * - Les nom doivent être en Français
     *      - id -> identifiant
     *      - lineList -> Ligne
     *      - zipCode -> code_postal
     *      - town -> ville
     * - L'identifiant doit être en attribut
     */
    @Test
    public void validateAddress() {
        LOGGER.info("TD 1.1 --> Test de l'adresse");
        validateXML(new Address(1L, "Tours", "37000", "Ligne 1", "Ligne 2"), "td1.1-Address");
    }

    /**
     * TD 1.2
     * Validation des annotations portée sur la classe Donor (et pas seulement ;) )
     * - La classe doit être sérialisable
     *      - le nom doit être donnateur
     * - La sérialisation doit se baser sur les champs
     * - les nom doivent être en français
     *      - donationAmount -> donnation
     *      - donorType ->
     *          - Doit être un attribut
     *      - id -> identifiant
     *          - Doit être un attribut
     *      - name -> Prenom
     *      - surname -> Nom
     *      - phoneNumberList -> telephones
     *      - address - addresse_principale
     */
    @Test
    public void validateDonor() {
        LOGGER.info("TD 1.2 --> Test des donnateur");
        validateXML(
                new Donor(1L, null, "Entreprise 1", 1000, DonorType.PM, new Address(1L, "Tours", "37000", "Ligne 1.1", "Ligne 1.2"))
                , "td1.2-DonnorPM"
        );
        validateXML(
                new Donor(2L, "Anthony Jr", "Stark", 10000000, DonorType.PP, new Address(2L, "Tours", "37000", "Ligne 2.1", "Ligne 2.2"))
                , "td1.2-DonnorPP"
        );
        validateXML(new Address(1L, "Tours", "37000", "Ligne 1", "Ligne 2"), "td1.1-Address");
    }

}
