HOSTNAME = localhost

default: build

build:
	@echo building...
	javac -Xlint:all -Xdiags:verbose */*.java

client:
	@echo launching client1, hostname = ${HOSTNAME}
	java src.Client "${HOSTNAME}" 1 2

test1:
	@echo launching MultiClientTest, hostname = ${HOSTNAME}
	java test.MultiClientTest "${HOSTNAME}"

server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -frv src/*.class
