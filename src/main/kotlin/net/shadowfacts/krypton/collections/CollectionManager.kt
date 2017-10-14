package net.shadowfacts.krypton.collections

import java.io.File

/**
 * @author shadowfacts
 */
object CollectionManager {

	private val collections = mutableMapOf<String, Collection>()

	private val defaultCollections = mutableMapOf<File, Collection>()

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

	fun putDefault(dir: File, collection: String) {
		defaultCollections[dir] = getOrCreate(collection)
	}

	fun getDefault(dir: File, rootDir: File): Collection? {
		return if (dir == rootDir) {
			null
		} else if (dir in defaultCollections) {
			defaultCollections[dir]
		} else {
			getDefault(dir.parentFile, rootDir)
		}
	}

}