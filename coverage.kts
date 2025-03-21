import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

plugins {
    jacoco
}

val excludes = listOf(
    "**/databinding",
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    "**/ui",
    "**/data/model",
    "**/domain/entity",
    "**/generated",
    "**/*$ViewInjector*.*",
    "**/*$ViewBinder*.*",
    "**/Lambda$*.class",
    "**/Lambda.class",
    "**/*Lambda.class",
    "**/*Lambda*.class",
    "**/*_MembersInjector.class",
    "**/Dagger*Component*.*",
    "**/*Module_*Factory.class",
    "**/di/module/*",
    "**/*_Factory*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*Hilt*.*",
    "**/*MapperImpl*.*",
    "**/*$ViewInjector*.*",
    "**/*$ViewBinder*.*",
    "**/BuildConfig.*",
    "**/*Component*.*",
    "**/*BR*.*",
    "**/Manifest*.*",
    "**/*$Lambda$*.*",
    "**/*Companion*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*Hilt*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*"
)
// configure Jacoco
configure<JacocoPluginExtension> {
    toolVersion = "0.8.12"
}

//Configure after Evaluate to ensure the all the variants are configured
project.afterEvaluate {
    val androidExtension = extensions.getByName("android")
    if (androidExtension.javaClass.methods.any { it.name == "getApplicationVariants" }) {
        val applicationVariants = androidExtension.javaClass.getMethod("getApplicationVariants")
            .invoke(androidExtension) as? Collection<*>
        applicationVariants?.forEach { variant ->
            if (variant != null) {
                createVariantCoverage(variant)
            }
        }
    } else if (androidExtension.javaClass.methods.any { it.name == "getLibraryVariants" }) {
        val libraryVariants = androidExtension.javaClass.getMethod("getLibraryVariants")
            .invoke(androidExtension) as? Collection<*>
        libraryVariants?.forEach { variant ->
            if (variant != null) {
                createVariantCoverage(variant)
            }
        }
    }
}

fun createVariantCoverage(variant: Any) {
    val capitalizedVariantName =
        variant.javaClass.getMethod("getName").invoke(variant).toString().capitalize()
    val variantName = variant.javaClass.getMethod("getName").invoke(variant).toString()
    val testTaskName = "test${capitalizedVariantName}UnitTest"
    val variantJavaCompileProvider =
        variant.javaClass.getMethod("getJavaCompileProvider").invoke(variant)
    val destinationDir = variantJavaCompileProvider.javaClass.getMethod("getDestinationDir")
        .invoke(variantJavaCompileProvider) as File

    val testTask = tasks.findByName(testTaskName) as? Test
    testTask?.configure {
        extensions.configure<JacocoTaskExtension> {
            // Ensure the report generate in the desired file
            destinationFile = File(buildDir, "jacoco/${testTaskName}.exec")
            classDirectories.setFrom(files(destinationDir.path))
        }
    }

    // Add unit test coverage tasks
    tasks.register<JacocoReport>("${testTaskName}Coverage") {
        group = "Reporting"
        description = "Generate Jacoco coverage reports for the ${capitalizedVariantName} build."
        dependsOn(testTaskName)
        reports {
            html.required.set(true)
        }
        val javaClasses = fileTree(destinationDir).apply {
            exclude(excludes)
        }
        val kotlinClasses = fileTree("${buildDir}/tmp/kotlin-classes/${variantName}").apply {
            exclude(excludes)
        }

        classDirectories.setFrom(files(javaClasses, kotlinClasses))
        sourceDirectories.setFrom(
            files(
                "$projectDir/src/main/java",
                "$projectDir/src/${variantName}/java",
                "$projectDir/src/main/kotlin",
                "$projectDir/src/${variantName}/kotlin"
            )
        )

        executionData.setFrom(files("${buildDir}/outputs/unit_test_code_coverage/${variantName}UnitTest/${testTaskName}.exec"))

        doLast {
            val m =
                File("$buildDir/reports/jacoco/${testTaskName}Coverage/html/index.html").readText()
                    .let { it.toRegex().find(it, 0) }
            if (m != null) {
                val percent = m.groups[1]?.value
                println("Test coverage: ${percent}")
                println("Coverage report path -> file:\\\\${reports.html.outputLocation}")
            }
        }
    }

    // Add unit test coverage verification tasks
    tasks.register<JacocoCoverageVerification>("${testTaskName}CoverageVerification") {
        group = "Reporting"
        description = "Verifies Jacoco coverage for the ${capitalizedVariantName} build."
        dependsOn("${testTaskName}Coverage")
        violationRules {
            rule {
                limit {
                    minimum = 0.0
                }
            }
            rule {
                element = "BUNDLE"
                limit {
                    counter = "LINE"
                    value = "COVEREDRATIO"
                    minimum = 0.15
                }
            }
        }
        val javaClasses = fileTree(destinationDir).apply {
            exclude(excludes)
        }
        val kotlinClasses = fileTree("${buildDir}/tmp/kotlin-classes/${variantName}").apply {
            exclude(excludes)
        }

        classDirectories.setFrom(files(javaClasses, kotlinClasses))
        sourceDirectories.setFrom(
            files(
                "$projectDir/src/main/java",
                "$projectDir/src/${variantName}/java",
                "$projectDir/src/main/kotlin",
                "$projectDir/src/${variantName}/kotlin"
            )
        )
        executionData.setFrom(files("${buildDir}/outputs/unit_test_code_coverage/${variantName}UnitTest/${testTaskName}.exec"))
    }
}