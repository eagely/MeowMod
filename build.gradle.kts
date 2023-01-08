import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.7.10"
}

group = "me.eagely.meowmod"
version = "0.5"

// Toolchains:
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

// Minecraft configuration:
loom {
    log4jConfigs.from(file("log4j2.xml"))
    launchConfigs {
        "client" {
            // If you don't want mixins, remove these lines
            //property("mixin.debug", "true")
            //property("asmhelper.verbose", "true")
            //arg("--tweakClass", "org.spongepowered.asm.launch.MixinTweaker")
            //arg("--mixin", "mixins.meowmod.json")
        }
    }
    forge {
        pack200Provider.set(dev.architectury.pack200.java.Pack200Adapter())
        // If you don't want mixins, remove this lines
        //mixinConfig("mixins.meowmod.json")
    }
    // If you don't want mixins, remove these lines
    //mixin {
    //    defaultRefmapName.set("mixins.meowmod.refmap.json")
    //}
}

sourceSets.main {
    //java.srcDir(file("$projectDir/src/main/kotlin"))
    output.setResourcesDir(file("$buildDir/classes/java/main"))
}

val packageLib by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}
// Dependencies:

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/maven/")
    maven("https://repo.sk1er.club/repository/maven-public")
    // If you don't want to log in with your real minecraft account, remove this line
    // maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")

}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    shadowImpl(kotlin("stdlib-jdk8"))

    // If you don't want mixins, remove these lines
    //shadowImpl("org.spongepowered:mixin:0.7.11-SNAPSHOT") {
    //    isTransitive = false
    //}
    annotationProcessor("org.spongepowered:mixin:0.8.4-SNAPSHOT")

    // If you don't want to log in with your real minecraft account, remove this line
    // runtimeOnly("me.djtheredstoner:DevAuth-forge-legacy:1.1.0")

    shadowImpl("gg.essential:loader-launchwrapper:1.1.3")
    implementation("gg.essential:essential-1.8.9-forge:3662")
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.apache.httpcomponents:httpcore:4.4.15")
}

// Tasks:

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType(Jar::class) {
    archiveBaseName.set("meowmod")
    manifest.attributes.run {
        this["FMLCorePluginContainsFMLMod"] = "true"
        this["ForceLoadAsMod"] = "true"

        // If you don't want mixins, remove these lines
        //this["TweakClass"] = "org.spongepowered.asm.launch.MixinTweaker"
        //this["MixinConfigs"] = "mixins.meowmod.json"
    }
}


val remapJar by tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
    archiveClassifier.set("all")
    from(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks.shadowJar {
    archiveClassifier.set("all-dev")
    configurations = listOf(shadowImpl)
    doLast {
        configurations.forEach {
            println("Config: ${it.files}")
        }
    }

    // If you want to include other dependencies and shadow them, you can relocate them in here
    fun relocate(name: String) = relocate(name, "com.meowmod.deps.$name")
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }

tasks.assemble.get().dependsOn(tasks.remapJar)
