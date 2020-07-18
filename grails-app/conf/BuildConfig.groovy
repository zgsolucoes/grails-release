Map<String, String> ENV = System.getenv()
String mvnRepoHost = ENV['MVN_REPO_HOST']
String mvnRepoUser = ENV['MVN_REPO_USER']
String mvnRepoPassword = ENV['MVN_REPO_PASSWORD']

grails.project.work.dir = 'target'
grails.project.docs.output.dir = "docs"
grails.project.source.level = 1.6

grails.project.dependency.resolution = {

    inherits "global"
    log "warn"

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenRepo "https://repo1.maven.org/maven2/"
        mavenRepo ENV['MVN_REPO_REPOSITORIES_URL_LIBS']
        mavenRepo ENV['MVN_REPO_REPOSITORIES_GRAILS_PLUGINS']
    }

    credentials {
        realm = ENV['MVN_REPO_REALM']
        host = mvnRepoHost
        username = mvnRepoUser
        password = mvnRepoPassword
    }

    plugins {
        compile ":rest-client-builder:1.0.3"
    }

    dependencies {
        compile "org.apache.ivy:ivy:2.2.0"
        compile("org.apache.maven:maven-ant-tasks:2.1.3") {
            excludes "commons-logging", "xml-apis", "groovy"
        }
        test("org.gmock:gmock:0.8.0") {
            export = false
        }
    }
}

grails.project.repos.releases.url = ENV['MVN_REPO_REPOSITORIES_URL_PLUGINS_RELEASE']
grails.project.repos.releases.username = mvnRepoUser
grails.project.repos.releases.password = mvnRepoPassword

grails.project.repos.snapshots.url = ENV['MVN_REPO_REPOSITORIES_URL_PLUGINS_SNAPSHOT']
grails.project.repos.snapshots.username = mvnRepoUser
grails.project.repos.snapshots.password = mvnRepoPassword

grails.project.repos.default = 'snapshots'
