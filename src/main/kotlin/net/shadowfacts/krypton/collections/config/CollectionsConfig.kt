package net.shadowfacts.krypton.collections.config

import net.shadowfacts.krypton.config.Configuration
import net.shadowfacts.krypton.config.config

/**
 * @author shadowfacts
 */
var Configuration.enableDefaultCollections: Boolean by config(java.lang.Boolean::parseBoolean, fallback = { true })