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
	java Test.MultiClientTest "${HOSTNAME}"

test2:
	@echo launching MultiClientTest2, hostname = ${HOSTNAME}
	java Test.MultiClientTest2 "${HOSTNAME}"
	
test3:
	@echo launching MultiClientTest3, hostname = ${HOSTNAME}
	java Test.MultiClientTest3 "${HOSTNAME}"

server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -frv src/*.class
	rm -frv test/*.class
