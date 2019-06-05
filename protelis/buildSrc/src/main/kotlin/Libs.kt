import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions` */
object Libs {
    /**
     * http://logback.qos.ch */
    const val logback_classic: String = "ch.qos.logback:logback-classic:" + Versions.logback_classic

    const val com_github_spotbugs_gradle_plugin: String =
            "com.github.spotbugs:com.github.spotbugs.gradle.plugin:" +
            Versions.com_github_spotbugs_gradle_plugin

    /**
     * https://spotbugs.github.io/ */
    const val spotbugs_annotations: String = "com.github.spotbugs:spotbugs-annotations:" +
            Versions.spotbugs_annotations

    /**
     * https://github.com/google/guava */
    const val guava: String = "com.google.guava:guava:" + Versions.guava

    const val com_gradle_build_scan_gradle_plugin: String =
            "com.gradle.build-scan:com.gradle.build-scan.gradle.plugin:" +
            Versions.com_gradle_build_scan_gradle_plugin

    const val com_jfrog_bintray_gradle_plugin: String =
            "com.jfrog.bintray:com.jfrog.bintray.gradle.plugin:" +
            Versions.com_jfrog_bintray_gradle_plugin

    /**
     * https://github.com/pinterest/ktlint */
    const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint

    /**
     * http://commons.apache.org/proper/commons-codec/ */
    const val commons_codec: String = "commons-codec:commons-codec:" + Versions.commons_codec

    /**
     * http://commons.apache.org/proper/commons-io/ */
    const val commons_io: String = "commons-io:commons-io:" + Versions.commons_io

    const val de_fayard_buildsrcversions_gradle_plugin: String =
            "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    /**
     * http://ruedigermoeller.github.io/fast-serialization/ */
    const val fst: String = "de.ruedigermoeller:fst:" + Versions.fst

    /**
     * https://github.com/AlchemistSimulator/alchemist-interfaces */
    const val alchemist_interfaces: String = "it.unibo.alchemist:alchemist-interfaces:" +
            Versions.it_unibo_alchemist

    /**
     * https://github.com/AlchemistSimulator/alchemist-loading */
    const val alchemist_loading: String = "it.unibo.alchemist:alchemist-loading:" +
            Versions.it_unibo_alchemist

    /**
     * http://junit.org */
    const val junit: String = "junit:junit:" + Versions.junit

    /**
     * http://trove4j.sf.net */
    const val trove4j: String = "net.sf.trove4j:trove4j:" + Versions.trove4j

    /**
     * http://sourceforge.net/projects/streamsupport/ */
    const val streamsupport: String = "net.sourceforge.streamsupport:streamsupport:" +
            Versions.streamsupport

    /**
     * http://commons.apache.org/proper/commons-lang/ */
    const val commons_lang3: String = "org.apache.commons:commons-lang3:" + Versions.commons_lang3

    /**
     * http://commons.apache.org/proper/commons-math/ */
    const val commons_math3: String = "org.apache.commons:commons-math3:" + Versions.commons_math3

    const val org_danilopianini_git_sensitive_semantic_versioning_gradle_plugin: String =
            "org.danilopianini.git-sensitive-semantic-versioning:org.danilopianini.git-sensitive-semantic-versioning.gradle.plugin:" +
            Versions.org_danilopianini_git_sensitive_semantic_versioning_gradle_plugin

    const val org_danilopianini_publish_on_central_gradle_plugin: String =
            "org.danilopianini.publish-on-central:org.danilopianini.publish-on-central.gradle.plugin:" +
            Versions.org_danilopianini_publish_on_central_gradle_plugin

    /**
     * http://www.eclipse.org/emf */
    const val org_eclipse_emf_ecore: String = "org.eclipse.emf:org.eclipse.emf.ecore:" +
            Versions.org_eclipse_emf_ecore

    /**
     * http://www.jboss.org/apiviz/ */
    const val apiviz: String = "org.jboss.apiviz:apiviz:" + Versions.apiviz

    /**
     * https://kotlinlang.org/ */
    const val kotlin_scripting_compiler_embeddable: String =
            "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" +
            Versions.kotlin_scripting_compiler_embeddable

    /**
     * https://kotlinlang.org/ */
    const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin_stdlib

    const val org_jlleitschuh_gradle_ktlint_gradle_plugin: String =
            "org.jlleitschuh.gradle.ktlint:org.jlleitschuh.gradle.ktlint.gradle.plugin:" +
            Versions.org_jlleitschuh_gradle_ktlint_gradle_plugin

    const val org_protelis_protelisdoc_gradle_plugin: String =
            "org.protelis.protelisdoc:org.protelis.protelisdoc.gradle.plugin:" +
            Versions.org_protelis_protelisdoc_gradle_plugin

    /**
     * https://github.com/Protelis/protelis-interpreter */
    const val protelis_interpreter: String = "org.protelis:protelis-interpreter:" +
            Versions.protelis_interpreter

    /**
     * http://protelis.org */
    const val protelis_parser: String = "org.protelis:protelis.parser:" + Versions.protelis_parser

    /**
     * http://www.slf4j.org */
    const val slf4j_api: String = "org.slf4j:slf4j-api:" + Versions.slf4j_api

    /**
     * https://github.com/spring-projects/spring-framework */
    const val spring_core: String = "org.springframework:spring-core:" + Versions.spring_core
}