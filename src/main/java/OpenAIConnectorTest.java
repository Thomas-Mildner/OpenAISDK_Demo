import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class OpenAIConnectorTest {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://inference.ai.cnds.io/v1";
    private static final String MODEL_NAME = "artllama-chat-medium"; // custom model name for this openai instance - do not change

    private static OpenAIClient createClient() {
        return OpenAIOkHttpClient.builder()
                .apiKey(API_KEY)
                .baseUrl(BASE_URL)
                .build();
    }

    public static void main(String[] args) {
        String result = executeAiRequest("Tell me a story about building the best SDK!");
        System.out.println(result);
    }

    public static String executeAiRequest(String prompt) {
        OpenAIClient client = createClient();

        ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
                .model(MODEL_NAME)
                .addUserMessage(prompt)
                .build();

        StringBuilder responseBuilder = new StringBuilder();
        client.chat().completions().create(createParams).choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(responseBuilder::append);

        return responseBuilder.toString();
    }
}
