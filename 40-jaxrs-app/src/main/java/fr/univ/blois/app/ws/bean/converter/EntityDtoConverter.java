package fr.univ.blois.app.ws.bean.converter;

/**
 * ionterface (contrat) pour les conversion entité / dto (objet de transfert)
 * @param <EntityClass>
 * @param <DtoClass>
 */
public interface EntityDtoConverter<EntityClass, DtoClass> {

  /**
   * Renvoi une entité en retour du passage du dto
   * @param dto dto
   * @return entité
   */
  EntityClass getEntity(DtoClass dto);

  /**
   * Renvoi un dto (DataObjectTransfert) en fonction d'une entité
   * @param entity entité
   * @return dto
   */
  DtoClass getDto(EntityClass entity);
}
