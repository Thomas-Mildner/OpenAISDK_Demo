import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class OpenAIConnectorTest {

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "YOUR_API_KEY";

        String customServerUrl = "https://inference.ai.cnds.io/v1";

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .baseUrl(customServerUrl)
                .build();

        ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
                .model("artllama-chat-medium")
                .addDeveloperMessage("Make sure you mention Stainless!")
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.chat().completions().create(createParams).choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(System.out::println);
    }
}
