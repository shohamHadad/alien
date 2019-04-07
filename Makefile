# 207595620
# Shoham Hadad
compile: bin

	find src -name "*.java" > sources.txt	
	javac -cp  biuoop-1.4.jar:. -d bin @sources.txt

run:
	java -cp biuoop-1.4.jar:bin Ass7Game

bin:
	mkdir bin

jar:
	jar cfm space-invaders.jar Manifest.mf -C bin/ .