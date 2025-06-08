#!/bin/zsh


mvn clean package

echo "Setting FULL_PATH"
FULL_PATH=$(pwd)/target/JavaMCP-1.0-SNAPSHOT.jar

echo "FULL_PATH: $FULL_PATH"
echo "Starting npx script"

echo "$FULL_PATH" | pbcopy
echo "Copied the FULL_PATH just paste with cmd+v or ctrl+v"
npx @modelcontextprotocol/inspector java -jar "$FULL_PATH"