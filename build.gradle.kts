plugins {
    id("dev.kikugie.loom-back-compat")
    id("dev.deftu.gradle.bloom") version "0.2.0"
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

val modid = property("mod.id") as String
val modname = property("mod.name") as String
val modversion = property("mod.version") as String
val mcversion = property("minecraft_version") as String
val versionrange = property("minecraft_version_range")
val loaderversion = property("loader_version")

base {
    archivesName.set("$modid-$modversion+$mcversion")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
    maven("https://repo.polyfrost.org/releases")
    maven("https://repo.polyfrost.org/snapshots")
    maven("https://maven.parchmentmc.org") {
        content { includeGroupAndSubgroups("org.parchmentmc") }
    }
    maven("https://maven.gegy.dev/releases") {
        content { includeGroup("dev.lambdaurora") }
    }
    maven("https://maven.deftu.dev/releases") {
        content { includeGroup("dev.deftu") }
    }
    maven("https://maven.fabricmc.net/releases") {
        content { includeGroup("net.fabricmc") }
    }
    //maven("https://maven.terraformersmc.com/releases") {
    maven("https://maven.gnomecraft.net/releases/") {
        content { includeGroup("com.terraformersmc") }
    }
    maven("https://central.sonatype.com/repository/maven-snapshots/") {
        content { includeGroup("net.kyori") }
    }
}

loom {
    runConfigs.all {
        ideConfigGenerated(stonecutter.current.isActive)
        runDir = "../../run" // This sets the run folder for all mc versions to the same folder. Remove this line if you want individual run folders.
    }

    runConfigs.remove(runConfigs["server"]) // Removes server run configs
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")

    val hasOfficialMappings = findProperty("has_official_mappings")?.toString()?.toBoolean() ?: true
    if (hasOfficialMappings) {
        @Suppress("UnstableApiUsage")
        mappings(loom.layered {
            officialMojangMappings()
            optionalProp("${property("parchment_version")}") {
                parchment("org.parchmentmc.data:parchment-${property("minecraft_version")}:$it@zip")
            }
            optionalProp("${property("yalmm_version")}") {
                mappings("dev.lambdaurora:yalmm-mojbackward:${property("minecraft_version")}+build.$it")
            }
        })
    }
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("org.polyfrost.oneconfig:${property("minecraft_version")}-fabric:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:commands:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:config:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:config-impl:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:events:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:internal:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:ui:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:utils:1.0.0-beta.6")
    modImplementation("org.polyfrost.oneconfig:hud:1.0.0-beta.6")
}

bloom {
    replacement("@MOD_ID@", modid)
    replacement("@MOD_NAME@", modname)
    replacement("@MOD_VERSION@", modversion)
}

tasks.processResources {
    val props = mapOf(
        "mod_id" to modid,
        "mod_name" to modname,
        "mod_version" to modversion,
        "minecraft_version_range" to versionrange,
        "loader_version" to loaderversion
    )

    inputs.properties(props)

    filesMatching("fabric.mod.json") {
        expand(props)
    }
}

val javaVersionInt = (findProperty("java_version")?.toString() ?: "21").toInt()

tasks.withType<JavaCompile>().configureEach {
    options.release.set(javaVersionInt)
}

java {
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersionInt))
    }
}

tasks.jar {
    inputs.property("archivesName", base.archivesName)

    from("LICENSE") {
        rename { "${it}_${inputs.properties["archivesName"]}" }
    }
}

fun <T> optionalProp(property: String, block: (String) -> T?): T? =
    findProperty(property)?.toString()?.takeUnless { it.isBlank() }?.let(block)

val modrinthMinecraftVersionOverride = mapOf(
    "26.1" to listOf("26.1", "26.1.1", "26.1.2")
)

val modrinthId = listOf("oneconfig.publish.modrinth", "publish.modrinth").firstNotNullOfOrNull { findProperty(it) }?.toString()?.takeIf { it.isNotBlank() }
val modrinthToken = listOf("oneconfig.publish.modrinth.token", "publish.modrinth.token", "modrinth.token").firstNotNullOfOrNull { findProperty(it) }?.toString()?.takeIf { it.isNotBlank() }
val minecraftVersion = modrinthMinecraftVersionOverride[mcversion] ?: listOf(mcversion)
val publishJarTaskName = if ("remapJar" in tasks.names) "remapJar" else "jar"
val changelogs = rootProject.file("CHANGELOG.md").takeIf { it.exists() }?.readText() ?: "No changelog provided."

publishMods {
    file = tasks.named<AbstractArchiveTask>(publishJarTaskName).flatMap { it.archiveFile }

    displayName = modversion
    version = "v$modversion"
    changelog = changelogs
    type = STABLE

    modLoaders.add("fabric")

    dryRun = modrinthId == null || modrinthToken == null

    if (modrinthId != null) {
        modrinth {
            projectId = modrinthId
            accessToken = modrinthToken.orEmpty()

            minecraftVersions.addAll(minecraftVersion)

            requires("oneconfig")
            requires("fabric-language-kotlin")
        }
    }
}
