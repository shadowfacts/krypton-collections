package net.shadowfacts.krypton.collections.pipeline.stage

import net.shadowfacts.krypton.Page
import net.shadowfacts.krypton.collections.CollectionManager
import net.shadowfacts.krypton.collections.config.enableDefaultCollections
import net.shadowfacts.krypton.pipeline.stage.Stage

/**
 * @author shadowfacts
 */
class StageLoadCollections: Stage() {

	override val id = "loadCollections"

	override fun scan(page: Page) {
		val collectionId = page.metadata["collection"] as? String
		if (collectionId != null) {
			CollectionManager.getOrCreate(id).pages += page
		} else if (page.krypton.config.enableDefaultCollections) {
			val default = CollectionManager.getDefault(page.source.parentFile, page.krypton.config.source)
			if (default != null) {
				default.pages += page
			}
		}
	}

	override fun apply(page: Page, input: String) = input

}