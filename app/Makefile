	.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app
	
lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

build-run: build run

.PHONY: build
