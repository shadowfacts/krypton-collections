package net.shadowfacts.krypton.collections

import net.shadowfacts.krypton.Krypton
import net.shadowfacts.krypton.collections.pipeline.PipelineLoadDefaultCollections
import net.shadowfacts.krypton.collections.pipeline.stage.StageLoadCollections
import net.shadowfacts.krypton.config.Configuration
import net.shadowfacts.krypton.pipeline.selector.PipelineSelectorExtension
import net.shadowfacts.krypton.ekt.StageRenderEKT
import net.shadowfacts.krypton.pipeline.stage.finalstage.FinalStageOutput
import net.shadowfacts.krypton.util.dependencies.Dependencies
import java.io.File

/**
 * @author shadowfacts
 */
fun main(args: Array<String>) {
	val krypton = Krypton(Configuration {
		source = File("source")
		output = File("output")
	})
	krypton.createPipeline {
		selector = PipelineSelectorExtension("html")
		addStage(StageLoadCollections(), Dependencies {
		})
		addStage(StageRenderEKT(null, null, mapOf()), Dependencies {
			after += "loadCollections"
		})
		final = FinalStageOutput()
	}
	krypton.addPipeline(PipelineLoadDefaultCollections)
	krypton.generate()
}