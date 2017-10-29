package net.shadowfacts.krypton.collections

import net.shadowfacts.krypton.Krypton
import net.shadowfacts.krypton.collections.pipeline.stage.StageLoadCollections
import net.shadowfacts.krypton.pipeline.selector.PipelineSelectorExtension
import net.shadowfacts.krypton.ekt.pipeline.stage.StageRenderEKT
import net.shadowfacts.krypton.pipeline.stage.finalstage.FinalStageOutput
import net.shadowfacts.krypton.util.dependencies.Dependencies
import java.io.File

/**
 * @author shadowfacts
 */
fun main(args: Array<String>) {
	val krypton = Krypton {
		source = File("source")
		output = File("output")
	}
	krypton.createPipeline {
		selector = PipelineSelectorExtension("html")
		addStage(StageLoadCollections(), Dependencies {
		})
		addStage(StageRenderEKT(mapOf()), Dependencies {
			after += "loadCollections"
		})
		final = FinalStageOutput()
	}
	krypton.generate()
}