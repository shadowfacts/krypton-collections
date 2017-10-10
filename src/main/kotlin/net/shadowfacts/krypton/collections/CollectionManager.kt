package net.shadowfacts.krypton.collections

/**
 * @author shadowfacts
 */
object CollectionManager {

	private val collections = mutableMapOf<String, Collection>()

	fun get(id: String): Collection? {
		return collections[id]
	}

	fun put(id: String, collection: Collection) {
		collections[id] = collection
	}

}