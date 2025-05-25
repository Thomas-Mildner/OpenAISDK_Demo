import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class OpenAIConnectorTest {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://inference.ai.cnds.io/v1";
    private static final String MODEL_NAME = "artllama-chat-medium"; // custom model name for this openai instance - do
                                                                     // not change

    private static OpenAIClient createClient() {
        return OpenAIOkHttpClient.builder()
                .apiKey(API_KEY)
                .baseUrl(BASE_URL)
                .build();
    }

    public static void main(String[] args) {
        System.out.println("Starting OpenAI Connector Test and sending request...");
        String result = executeAiRequest(
                "Simuliere ein Gespräch zwischen einem Software-Entwickler und einem besonders sarkastischen Code-Compiler, der sich über die Codequalität beschwert.");

        // Clear the loading line
        System.out.print("\r" + " ".repeat(30) + "\r");
        System.out.println("\n--- AI Response ---\n");
        System.out.println(result);
    }

    public static String executeAiRequest(String prompt) {
        Thread loadingThread = startLoadingAnimation();

        OpenAIClient client = createClient();

        ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
                .model(MODEL_NAME)
                .addUserMessage(prompt)
                .build();

        StringBuilder responseBuilder = new StringBuilder();
        client.chat().completions().create(createParams).choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(responseBuilder::append);

        stopLoadingAnimation(loadingThread);

        return responseBuilder.toString();
    }

    // nice loading animation while waiting for the response
    private static volatile boolean loading = true;

    private static Thread startLoadingAnimation() {
        Thread thread = new Thread(() -> {
            String[] symbols = { "|", "/", "-", "\\" };
            int i = 0;
            while (loading) {
                System.out.print("\rWaiting for AI Response " + symbols[i % symbols.length]);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                i++;
            }
        });
        thread.start();
        return thread;
    }

    private static void stopLoadingAnimation(Thread thread) {
        loading = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
