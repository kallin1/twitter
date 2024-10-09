import java.util.*
import java.util.Locale

plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(19)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	//implementation ("org.projectlombok:lombok")
	compileOnly ("org.projectlombok:lombok")
	annotationProcessor ("org.projectlombok:lombok")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly ("mysql:mysql-connector-java:8.0.33");
	testImplementation ("org.junit.jupiter:junit-jupiter:5.8.1") // 최신 버전으로 수정
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val reactfrontendDir = "$projectDir/src/main/frontend"

sourceSets {
	main {
		resources {
			srcDirs("$projectDir/src/main/resources")
		}
	}
}

tasks.register("installReact", Exec::class) {
	workingDir = file(reactfrontendDir)
	inputs.dir(reactfrontendDir)
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "audit", "fix")
		commandLine("npm.cmd", "install")
	} else {
		commandLine("npm", "audit", "fix")
		commandLine("npm", "install")
	}
}

tasks.register("buildReact", Exec::class) {
	dependsOn("installReact")
	workingDir = file(reactfrontendDir)
	inputs.dir(reactfrontendDir)
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "run-script", "build")
	} else {
		commandLine("npm", "run-script", "build")
	}
}

tasks.register<Copy>("copyReactBuildFiles") {
	dependsOn("buildReact")
	from("$reactfrontendDir/build")
	into("$projectDir/src/main/resources/static")
	// 추가적인 설정이 필요하면 여기서 할 수 있습니다.
}

tasks.named<Copy>("processResources") {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE // 또는 DuplicatesStrategy.EXCLUDE
}

val frontendDir = "$projectDir/src/main/frontend"

sourceSets {
	main {
		resources {
			srcDir("$projectDir/src/main/resources")
		}
	}
}

val frontendDir = "$projectDir/src/main/frontend"

sourceSets {
	main {
		resources {
			srcDirs("$projectDir/src/main/resources")
		}
	}
}

tasks.register<Copy>("copyReactBuildFiles") {
	dependsOn("buildReact")
	from("$reactfrontendDir/build")
	into("$projectDir/src/main/resources/static")
}

tasks.register<Exec>("installReact") {
	workingDir = file(reactfrontendDir)
	inputs.dir(reactfrontendDir)
	group = BasePlugin.BUILD_GROUP

	if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "audit", "fix")
		commandLine("npm.cmd", "install")
	} else {
		commandLine("npm", "audit", "fix")
		commandLine("npm", "install")
	}
}

tasks.register<Exec>("buildReact") {
	dependsOn("installReact")
	workingDir = file(reactfrontendDir)
	inputs.dir(reactfrontendDir)
	group = BasePlugin.BUILD_GROUP

	if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "run-script", "build")
	} else {
		commandLine("npm", "run-script", "build")
	}
}

tasks.named("processResources") {
	dependsOn("copyReactBuildFiles")
}
