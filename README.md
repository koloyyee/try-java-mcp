# Java MCP server

## Pick Your Branch!

- plain-java: using MCP maven package directly
- spring-mcp: using Spring AI MCP package running as a Spring Application

## Setup

### For Unix user here

you can use the `build.sh` by `chmod +x build.sh` then `./build.sh`
It will automatically copy the full path of the jar to your clipboard,
just paste it in your Claude desktop app config file.

### For Windows

you can copy the full path and paste it.

In your Claude Config file add the following

```json

  "mcpServers": {
		...,
    "java-mcp": {
      "command": "java",
      "args": [
        "-jar",
        "<full path to your jar.>"
      ]
    }
}
```

### Running the server

**Skip it, if you are use build.sh**

or:
`npx @modelcontextprotocol/inspector java -jar "<full path of the jar file>"`

### Using it

Open your claude desktop, under `Search & Tools` right under the input select `java-mcp` then toggle the `get_milk_records` and `get_presentations`
