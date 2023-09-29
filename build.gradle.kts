plugins {
	java
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("jacoco")
}

group = "co.edu.unisabana"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2:2.2.220")
	implementation("mysql:mysql-connector-java:8.0.32")
	compileOnly("org.projectlombok:lombok:1.18.24")
	annotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco{
	toolVersion="0.8.9"
}
tasks.named<Test>("test") {
	finalizedBy("jacocoTestReport")
}

tasks.named<JacocoReport>("jacocoTestReport") {
	reports {
		csv.required.set(true)
	}
}

afterEvaluate {
	tasks.named<JacocoReport>("jacocoTestReport") {
		classDirectories.setFrom(classDirectories.files.map { dir ->
			fileTree(dir) {
				exclude(
					"co/edu/unisabana/lealtadcliente/controller/dto",
					"co/edu/unisabana/lealtadcliente/bd"
				)
			}
		})
	}
}




