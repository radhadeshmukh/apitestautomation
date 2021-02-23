clean:
	mvn clean

build:
	mvn clean build

test-all:
	@echo "Tests are running against DEV by default"
	mvn clean test -fn

prod-test:
	mvn -Dtest.env=prod clean test -fn
