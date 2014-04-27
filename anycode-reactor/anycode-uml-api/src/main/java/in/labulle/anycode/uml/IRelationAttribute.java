package in.labulle.anycode.uml;

/**
 * UML relationship attribute. It is class attribute that is linked to another classifier of the model. One particularity of this UML API is that relationships
 * themselves are not part of the model, i.e. : there is no 'Relationship' class. This choice is made on purpose as it enables to describe any relationship and
 * it is easier for code generation, because a class has all its attributes, regardless they are simple or relational attributes.
 * 
 * @author Jose Carreno
 * 
 */
public interface IRelationAttribute extends IAttribute {
	/**
	 * attributes navigability. An attribute that is not navigable should not be explicitely part of the classifier.
	 * 
	 * @return true if attribute is navigable or if navigation is not specified, false otherwise.
	 */
	boolean isNavigable();

	/**
	 * Checks whether this relationship side is a composition.
	 * 
	 * @return true if this relation side is a composition
	 */
	boolean isComposition();

	/**
	 * Checks whether this relationship side is a aggregation.
	 * 
	 * @return true if this relationship side is a aggregation
	 */
	boolean isAggregation();

	/**
	 * Checks if attribute is 'many to one'. i.e. : this side is associated to zero or one element of the other side type and the other side is associated with
	 * several elements of this side type. One example : a [Person] has several [Car]s, a [Car] belongs to zero or one [Person], which is modelled with 'owner'
	 * attribute in [Car] class. Then owner relation attribute isManyToOne.
	 * 
	 * @return true if the relation attribute is many-to-one.
	 */
	boolean isManyToOne();

	/**
	 * Checks if attribute is 'many to one'. i.e. : this side is associated to zero or one element of the other side type and the other side is also associated
	 * with zero or one element of this side type. One example : a [User] has zero or one [UserAccount], a [UserAccount] belongs to one [User], which is
	 * modelled with 'user' attribute in [UserAccount] class. Then use relationship attribute isOneToOne.
	 * 
	 * @return true if the relationship attribute is one-to-one.
	 */
	boolean isOneToOne();

	/**
	 * Checks if attribute is 'many to many'. i.e. : this side is associated to several elements of the other side type and the other side is associated with
	 * several elements of this side type. One example : a [Student] attends [Course]s, a [Course] can be attended by several [Student]s, which is modelled with
	 * 'courses' attribute in [Student] class. Then courses relationship attribute isManyToMany.
	 * 
	 * @return true if the relationship attribute is many-to-many.
	 */
	boolean isManyToMany();

	/**
	 * Checks if attribute is 'one to many'. i.e. : this side is associated to several elements of the other side type and the other side is associated with
	 * zero or one element of this side type. One example : a [Person] has several [Car]s, a [Car] belongs to zero or one [Person], which is modelled with
	 * 'cars' attribute in [Person] class. Then cars relationship attribute isOneToMany.
	 * 
	 * @return true if the relationship attribute is one-to-many.
	 */
	boolean isOneToMany();

	/**
	 * Gets the other side relationship attribute. Together, both attributes enables to have the necessary information about the UML relation itself.
	 * 
	 * @return relationship attribute. Cannot be null.
	 */
	IRelationAttribute getOtherSide();

	/**
	 * Checks where this attribute is the owning side of this relationship. This concept of owning side is common in ORM mappings. The owning side is the side
	 * that ORM tool will use to persist changes. As a consequence, unidirectional relationship attribute will always be the owning side (as it will not be part
	 * of the other side class). Now here are some rules about owning side in bidirectional relationships :
	 * <ul>
	 * <li>A bidirectional relationship has both an owning side and an inverse side.</li>
	 * <li>A unidirectional relationship only has an owning side.</li>
	 * <li>ORM tool will (generally) only check the owning side of an association for changes.</li>
	 * </ul>
	 * 
	 * @return true if this attribute is the owning side.
	 */
	boolean isOwningSide();

	/**
	 * Checks if this 'relationship' is bidirectional. If it is not, it doesn't mean that this side is the owning side (it can be an unidirectional relationship
	 * owned by the other side).
	 * 
	 * @return true if relationship is bidirectional.
	 */
	boolean isBidirectionalRelation();
}
