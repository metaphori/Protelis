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

    const val me_tatarka_retrolambda_gradle_plugin: String =
            "me.tatarka.retrolambda:me.tatarka.retrolambda.gradle.plugin:" +
            Versions.me_tatarka_retrolambda_gradle_plugin

    /**
     * http://trove4j.sf.net */
    const val trove4j: String = "net.sf.trove4j:trove4j:" + Versions.trove4j

    /**
     * https://pmd.github.io/ */
    const val pmd_core: String = "net.sourceforge.pmd:pmd-core:" + Versions.net_sourceforge_pmd

    /**
     * https://pmd.github.io/ */
    const val pmd_java: String = "net.sourceforge.pmd:pmd-java:" + Versions.net_sourceforge_pmd

    /**
     * https://pmd.github.io/ */
    const val pmd_jsp: String = "net.sourceforge.pmd:pmd-jsp:" + Versions.net_sourceforge_pmd

    /**
     * https://pmd.github.io/ */
    const val pmd_plsql: String = "net.sourceforge.pmd:pmd-plsql:" + Versions.net_sourceforge_pmd

    /**
     * https://pmd.github.io/ */
    const val pmd_vm: String = "net.sourceforge.pmd:pmd-vm:" + Versions.net_sourceforge_pmd

    /**
     * https://pmd.github.io/ */
    const val pmd_xml: String = "net.sourceforge.pmd:pmd-xml:" + Versions.net_sourceforge_pmd

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

    const val org_danilopianini_build_commons_gradle_plugin: String =
            "org.danilopianini.build-commons:org.danilopianini.build-commons.gradle.plugin:" +
            Versions.org_danilopianini_build_commons_gradle_plugin

    /**
     * http://www.eclipse.org/emf */
    const val org_eclipse_emf_ecore: String = "org.eclipse.emf:org.eclipse.emf.ecore:" +
            Versions.org_eclipse_emf_ecore

    /**
     * https://www.eclipse.org/Xtext/ */
    const val org_eclipse_xtext_common_types: String =
            "org.eclipse.xtext:org.eclipse.xtext.common.types:" +
            Versions.org_eclipse_xtext_common_types

    /**
     * http://www.jboss.org/apiviz/ */
    const val apiviz: String = "org.jboss.apiviz:apiviz:" + Versions.apiviz

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
