all:	GameClient.class GameServer.class \
		Game.class GameInterface.class

Game.class:	Game.java GameInterface.class
			@javac Notas.java

GameInterface.class:	GameInterface.java
						@javac GameInterface.java

GameClient.class:	GameClient.java
					@javac GameClient.java

GameServer.class:	GameServer.java
					@javac GameServer.java

run:	all
		@java GameServer &
		@sleep 1
		@java GameClient

clean:	
		@rm -f *.class *~

info:
		@echo "(c) Luiz P. F. Guerra (03 abr. 2021)"

