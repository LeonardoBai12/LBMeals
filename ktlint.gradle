apply plugin: "org.jlleitschuh.gradle.ktlint"

ktlint {
    verbose = true
    ignoreFailures = false

    reporters {
        reporter "plain"
        reporter "checkstyle"
    }

    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
