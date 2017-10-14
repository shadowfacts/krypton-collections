package net.shadowfacts.krypton.collections.pipeline

import net.shadowfacts.krypton.Page
import net.shadowfacts.krypton.collections.CollectionManager
import net.shadowfacts.krypton.collections.config.enableDefaultCollections
import net.shadowfacts.krypton.pipeline.Pipeline
import net.shadowfacts.krypton.pipeline.selector.PipelineSelectorFilename
import net.shadowfacts.krypton.pipeline.stage.Stage
import org.yaml.snakeyaml.Yaml

/**
 * @author shadowfacts
 */
object PipelineLoadDefaultCollections: Pipeline(PipelineSelectorFilename("_collection.yml"), mutableListOf(StageLoadDefaultCollections))

private object StageLoadDefaultCollections: Stage() {

	override val id = "defaultCollections"

	override fun scan(page: Page) {
		if (page.krypton.config.enableDefaultCollections) {
			val yaml = Yaml().load(page.input) as Map<String, Any>
			if ("collection" in yaml) {
				CollectionManager.putDefault(page.source.parentFile, yaml["collection"] as String)
			}
		}
	}

	override fun apply(page: Page, input: String) = input
}