package fr.univ.tours.siad.jaxb.model.person;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum BoardMemberType {
    @XmlEnumValue("président")
    PRESIDENT,
    @XmlEnumValue("trésorier")
    TREASURER,
    @XmlEnumValue("secretaire")
    SECRETARY;
}
