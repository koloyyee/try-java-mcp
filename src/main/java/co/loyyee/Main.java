package co.loyyee;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.loyyee.milk.MilkRecords;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger("Main");

    private static final String schema = """
				{
				  "type" : "object",
				  "id" : "urn:jsonschema:Operation",
				  "properties" : {
				    "operation" : {
				      "type" : "string"
				    }
				  }
				}
				""";

    public static void main(String[] args) {

        var transport = new StdioServerTransportProvider(new ObjectMapper());

        var jOnesyncTool = jOneSyncTools();
        var milkSyncTool = milkSyncTools();

        var mcpServer = McpServer.sync(transport)
                .serverInfo("javaone-mcp-server", "0.0.1")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .tools(true)
                        .logging()
                        .build())
                .tools(jOnesyncTool, milkSyncTool)
                .build();
    }

    static McpServerFeatures.SyncToolSpecification jOneSyncTools() {
        var tool = new McpSchema.Tool("get_presentation", "Get presentations of JavaOne", schema);
        var syncTool = new McpServerFeatures.SyncToolSpecification(
                tool,
                (exchange, arguments) -> {
                    var presentations = new PresentationTools().getPresentations();
                    List<McpSchema.Content> contents = new ArrayList<>();
                    for (Presentation presentation : presentations) {
                        contents.add(new McpSchema.TextContent(presentation.toString()));
                    }
                    return new McpSchema.CallToolResult(contents, false);
                }
        );
        return syncTool;
    }

    static McpServerFeatures.SyncToolSpecification milkSyncTools() {
        var tool = new McpSchema.Tool("get_milk_records", "Canada milk testings", schema);
        var syncTool = new McpServerFeatures.SyncToolSpecification(
                tool,
                (exchange, arguments) -> {

                    var records = new MilkRecords().findAll();

                    List<McpSchema.Content> contents = new ArrayList<>();
                    for (var milk : records) {
                        contents.add(new McpSchema.TextContent(milk.toString()));
                    }
                    return new McpSchema.CallToolResult(contents, false);
                });
        return syncTool;
    }
}
