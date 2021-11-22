HOSTNAME = localhost

default: build

build:
	@echo building...
	javac -Xlint:all -Xdiags:verbose src/*.java

client:
	@echo launching client1, hostname = ${HOSTNAME}
	java src.Client "${HOSTNAME}" 1 1500


server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -frv src/*.class
