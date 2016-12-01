package fr.univ.tours.siad.jaxb.model.club;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type de sport
 */
@XmlType
@XmlEnum(Integer.class)
public enum SportType {
    @XmlEnumValue("10")
    INDIVIDUAL,
    @XmlEnumValue("100")
    TEAM;
}
