plugins {
    java
    application
}

group = "pl.nith.wikia.testassignment.homework"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.selenium.bom))
    implementation(libs.selenium.java)
    implementation(libs.selenium.support)
    implementation(libs.selenium.driver.firefox)
    implementation(libs.selenium.driver.chrome)
    testImplementation(libs.testng)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.test {
    useTestNG {
        suites("src/test/resources/wikia_homework_page_test_suite.xml")
    }

    systemProperty("webdriver.chrome.driver", System.getenv("webdriver_chrome_driver"))
}
