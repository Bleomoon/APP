HOSTNAME = localhost
FILE = tests.txt

default: build

build:
	@echo building...
	javac -Xlint:all -Xdiags:verbose src/*.java

client:
	@echo launching client1, hostname = ${HOSTNAME}
	java src.Client "${HOSTNAME}"

clientF:
	@echo launching client1, hostname = ${HOSTNAME}, file = ${FILE}
	java src.Client "${HOSTNAME}" "${FILE}"

server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -rf src/*.class
