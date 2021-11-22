HOSTNAME = localhost

default: build

build:
	@echo building...
	javac -Xlint:all -Xdiags:verbose src/*.java
	javac -Xlint:all -Xdiags:verbose Test/*.java
	
test1:
	@echo launching MultiClientTest, hostname = ${HOSTNAME}
	java Test.MultiClientTest "${HOSTNAME}"

test2:
	@echo launching MultiClientTest2, hostname = ${HOSTNAME}
	java Test.MultiClientTest2 "${HOSTNAME}"
	
test3:
	@echo launching MultiClientTest3, hostname = ${HOSTNAME}
	java Test.MultiClientTest3 "${HOSTNAME}"

test4:
	@echo launching MultiClientTest4, hostname = ${HOSTNAME}
	java Test.MultiClientTest4 "${HOSTNAME}"

test5:
	@echo launching MultiClientTest5, hostname = ${HOSTNAME}
	java Test.MultiClientTest5 "${HOSTNAME}"

test6:
	@echo launching TestAttente, hostname = ${HOSTNAME}
	java Test.TestAttente "${HOSTNAME}"

server:
	@echo launching server...
	java src.Server

clean:
	@echo cleaning class files
	rm -frv src/*.class
	rm -frv Test/*.class
