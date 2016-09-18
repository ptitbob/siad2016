package fr.univ.tours.siad.jaxb.model.club;

import java.util.Objects;

/**
 * Association culturelle
 */
public class Cultural extends Association {

    /**
     * Type d'association culturelle (musique, peinture)
     */
    private String associationTarget;

    public Cultural() {
    }

    public Cultural(Long id, String name, String associationTarget) {
        super(id, name);
        this.associationTarget = associationTarget;
    }

    public String getAssociationTarget() {
        return associationTarget;
    }

    public void setAssociationTarget(String associationTarget) {
        this.associationTarget = associationTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cultural)) return false;
        if (!super.equals(o)) return false;
        Cultural cultural = (Cultural) o;
        return Objects.equals(getAssociationTarget(), cultural.getAssociationTarget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAssociationTarget());
    }

    @Override
    public String toString() {
        return "Cultural{" +
                "associationTarget='" + associationTarget + '\'' +
                "} " + super.toString();
    }
}
