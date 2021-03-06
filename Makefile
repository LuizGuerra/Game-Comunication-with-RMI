all:	GameClient.class GameServer.class \
		Game.class GameInterface.class \
		Player.class PlayerInterface.class

Game.class:	Game.java GameInterface.class
			@javac GameInterface.java

GameInterface.class:	GameInterface.java
						@javac GameInterface.java

Player.class:	Player.java PlayerInterface.class
				@javac Player.java

PlayerInterface.class:	PlayerInterface.java
						@javac PlayerInterface.java

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

