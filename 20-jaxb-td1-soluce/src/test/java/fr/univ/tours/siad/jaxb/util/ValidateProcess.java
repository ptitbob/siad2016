package fr.univ.tours.siad.jaxb.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ValidateProcess {

    private static final Logger LOGGER = Logger.getLogger("Validation");

    protected <T> void validateXML(T t, String testName) {
        Path path = Paths.get("xml", testName + ".xml");
        String testContent;
        try {
            testContent = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            testContent = "[Impossible a lire";
        }
        LOGGER.log(Level.INFO, "\n\tObjet a valider : " + t
                        + "\n\tLocalisation fichier de test : " + path.toAbsolutePath()
                //+ "\n\tContenu :\n" + testContent
        );
        validateXML(t, path, testContent);
    }

    protected <T> void validateXML(T t, Path testName, String testContent) {
        StringWriter studentResultStringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.encoding", "UTF-8");
            marshaller.setProperty("jaxb.formatted.output", true);
            marshaller.marshal(t, studentResultStringWriter);
        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, "\n\tIl y a une erreur de serialisation XML, VOUS DEVEZ annoter vos classes pour permettre cette serialisation"
                    + "\n\t\tErreur : " + e.getMessage());
            fail("Erreur de serialisation du à un manque d'annotation JAXB");
        }
        ValidateObjectEquals(t, testName);
        validateObjectAsXMLEquals(testContent, studentResultStringWriter);
    }

    private void validateObjectAsXMLEquals(String testContent, StringWriter studentResultStringWriter) {
        LOGGER.log(Level.INFO, "Test des objets le contenu XML de la sérialisation"
                        + "\n\tAttendu : \n" + testContent
                        + "\n\tObtenu : \n" + studentResultStringWriter.toString()
                        + "\n"
        );
        assertEquals(testContent.trim(), studentResultStringWriter.toString().trim());
        LOGGER.log(Level.INFO, "Les objets sérialisés (XML) sont égaux, la définition du squelette XML est bonne");
    }

    @SuppressWarnings("unchecked")
    private <T> void ValidateObjectEquals(T t, Path testName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T fromContent = (T) unmarshaller.unmarshal(testName.toFile());
            LOGGER.log(Level.INFO, "Test des objets via la deserialisation"
                            + "\n\tAttendu :\t" + fromContent
                            + "\n\tObtenu :\t" + t
            );
            assertEquals("Les objets doivent être identique", fromContent, t);
            LOGGER.log(Level.INFO, "Les objets sont égaux, la gestion de la désérialisation est bonne");
        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, "\n\tErreur de dérialisation, Vérifiez vos annotation\n\tErreur : " + e.getMessage());
            fail("Erreur lors de la déserialisation : Vérifiez vos annotations");
        }
    }

}
