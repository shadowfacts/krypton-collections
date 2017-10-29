package net.shadowfacts.krypton.collections

/**
 * @author shadowfacts
 */
object CollectionManager {

	private val collections = mutableMapOf<String, Collection>()

	fun get(id: String): Collection? {
		return collections[id]
	}

	fun getOrCreate(id: String): Collection {
		if (id !in collections) {
			put(id, Collection(id, mutableListOf()))
		}
		return get(id)!!
	}

	fun put(id: String, collection: Collection) {
		collections[id] = collection
	}

}