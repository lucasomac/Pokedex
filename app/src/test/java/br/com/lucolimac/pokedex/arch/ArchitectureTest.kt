package br.com.lucolimac.pokedex.arch

import com.tngtech.archunit.base.DescribedPredicate.alwaysTrue
import com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.Test

class ArchitectureTest {

    @Test
    fun `layer dependencies are respected`() {
        ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.lucolimac.pokedex").let { javaClasses ->
                layeredArchitecture().consideringOnlyDependenciesInLayers()
                    // Def
                    .layer("Presentation").definedBy("br.com.lucolimac.pokedex.ui..")
                    .layer("Domain").definedBy("br.com.lucolimac.pokedex.domain..")
                    .layer("Data").definedBy("br.com.lucolimac.pokedex.data..")
                    .layer("Framework").definedBy("br.com.lucolimac.pokedex.framework..")
                    // Accessed by
                    .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
                    .ignoreDependency(resideInAPackage("..framework.di.."), alwaysTrue())
                    .whereLayer("Domain").mayOnlyBeAccessedByLayers("Presentation", "Data")
                    .ignoreDependency(resideInAPackage("..framework.di.."), alwaysTrue())
                    .whereLayer("Data").mayOnlyBeAccessedByLayers("Framework")
                    .whereLayer("Framework").mayOnlyBeAccessedByLayers("Data")
                    .ignoreDependency(resideInAPackage("..ui.presentation.."), alwaysTrue())
                    // Access
                    .whereLayer("Presentation").mayOnlyAccessLayers("Domain")
                    .ignoreDependency(resideInAPackage("..ui.presentation.."), alwaysTrue())
                    .whereLayer("Domain").mayNotAccessAnyLayer() // Independence or death!!
                    .whereLayer("Data").mayOnlyAccessLayers("Domain", "Framework")
                    .whereLayer("Framework").mayOnlyAccessLayers("Data")
                    .ignoreDependency(resideInAPackage("..ui.presentation.viewmodel.."), alwaysTrue())
                    .ignoreDependency(resideInAPackage("..domain.repository.."), alwaysTrue())
                    .ignoreDependency(resideInAPackage("..domain.usecase.."), alwaysTrue())
                    .check(javaClasses)
            }
    }
}
