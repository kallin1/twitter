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
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok:1.18.24")
	annotationProcessor("org.projectlombok:lombok:1.18.24")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("mysql:mysql-connector-java:8.0.33")
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	implementation("java.xml.bind:jaxb-api")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// frontendDir 변수를 선언하여 리액트 프론트엔드 디렉터리 경로 지정
val frontendDir = "$projectDir/src/main/frontend"

// sourceSets에서 리소스 디렉터리 설정
sourceSets {
	main {
		resources {
			srcDirs("$projectDir/src/main/resources")
		}
	}
}

// 리액트 빌드 파일을 복사하는 작업 정의
tasks.register<Copy>("copyReactBuildFiles") {
	dependsOn("buildReact")  // buildReact 작업이 끝난 후 실행
	from("$frontendDir/build")  // 리액트 빌드 결과 디렉터리
	into("$projectDir/src/main/resources/static")  // 복사 대상 디렉터리
}

// 리액트 종속성 설치 작업 정의
tasks.register<Exec>("installReact") {
	workingDir = file(frontendDir)  // 작업 디렉터리 설정
	inputs.dir(frontendDir)  // 입력 디렉터리 설정
	group = BasePlugin.BUILD_GROUP  // 그룹 설정

	// OS에 따라 다른 명령어 실행
	if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "audit", "fix")
		commandLine("npm.cmd", "install")
	} else {
		commandLine("npm", "audit", "fix")
		commandLine("npm", "install")
	}
}

// 리액트 빌드를 수행하는 작업 정의
tasks.register<Exec>("buildReact") {
	dependsOn("installReact")  // installReact 작업이 끝난 후 실행
	workingDir = file(frontendDir)  // 작업 디렉터리 설정
	inputs.dir(frontendDir)  // 입력 디렉터리 설정
	group = BasePlugin.BUILD_GROUP  // 그룹 설정

	// OS에 따라 다른 명령어 실행
	if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
		commandLine("npm.cmd", "run-script", "build")
	} else {
		commandLine("npm", "run-script", "build")
	}
}

// processResources 작업에 copyReactBuildFiles 작업을 의존성으로 추가
tasks.named("processResources") {
	dependsOn("copyReactBuildFiles")  // processResources 전에 copyReactBuildFiles 작업 실행
}
