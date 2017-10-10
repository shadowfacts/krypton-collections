package net.shadowfacts.krypton.collections

import net.shadowfacts.krypton.Page
import net.shadowfacts.krypton.pipeline.stage.Stage

/**
 * @author shadowfacts
 */
class StageLoadCollections: Stage() {

	override val id = "loadCollections"

	override fun scan(page: Page) {
		val collectionId = page.metadata["collection"] as? String
		if (collectionId != null) {
			val collection = CollectionManager.get(collectionId)
			if (collection != null) {
				collection.pages += page
			} else {
				val collection = Collection(collectionId, mutableListOf(page))
				CollectionManager.put(collectionId, collection)
			}
		}
	}

	override fun apply(page: Page, input: String) = input

}