HOSTNAME = localhost
default: build

build:
	@echo building...
	javac -Xlint:all -Xdiags:verbose src/*.java

client:
	@echo launching client1, hostname = ${HOSTNAME}
	java src.Client "${HOSTNAME}"

server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -rf src/*.class
