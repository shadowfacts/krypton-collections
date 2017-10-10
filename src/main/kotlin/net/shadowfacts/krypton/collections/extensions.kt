package net.shadowfacts.krypton.collections

import net.shadowfacts.krypton.Page

/**
 * @author shadowfacts
 */
val Page.collection: Collection
	get() {
		val id = metadata["collection"] as? String ?: throw RuntimeException("Attempted to get collection for page $this without specified collection")
		return CollectionManager.get(id) ?: throw RuntimeException("No collection for id $id")
	}