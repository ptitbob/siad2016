package fr.univ.tours.siad.jaxb.model.person;

import java.util.Objects;

/**
 * Membre du bureau
 */
public class BoardMember extends Adherent {

    /**
     * Poste dans le bureau de l'association
     */
    private BoardMemberType boardMemberType;

    public BoardMember() {
    }

    public BoardMember(Long id, String name, String surname, String membershipDate, BoardMemberType boardMemberType, Address address) {
        super(id, name, surname, membershipDate, address);
        this.boardMemberType = boardMemberType;
    }

    public BoardMemberType getBoardMemberType() {
        return boardMemberType;
    }

    public void setBoardMemberType(BoardMemberType boardMemberType) {
        this.boardMemberType = boardMemberType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardMember)) return false;
        if (!super.equals(o)) return false;
        BoardMember that = (BoardMember) o;
        return Objects.equals(getBoardMemberType(), that.getBoardMemberType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBoardMemberType());
    }

    @Override
    public String toString() {
        return "BoardMember{" +
                "boardMemberType=" + boardMemberType +
                "} " + super.toString();
    }
}
