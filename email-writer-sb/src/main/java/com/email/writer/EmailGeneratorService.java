package com.email.writer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class EmailGeneratorService {
    private final WebClient webClient;
    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt = buildPrompt(emailRequest);

        Map<String, Object> requestBody = Map.of("contents", new Object[] {
                Map.of("parts", new Object[] {
                        Map.of("text", prompt)
                })
        });

        String response=webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //Extract response and return
        return extractResponseContent(response);
    }


    public String refineEmailReply(String generatedReply, String action) {

        String instruction;

        switch (action.toUpperCase()) {

            case "GRAMMAR":
                instruction = "Correct grammar, spelling and punctuation " +
                        "without changing the original meaning or tone. " +
                        "Return only the corrected email.";
                break;

            case "SHORTER":
                instruction = "Make this email shorter and more concise " +
                        "while preserving all important information. " +
                        "Return only the revised email.";
                break;

            default:
                throw new IllegalArgumentException("Invalid refinement action");
        }

        String prompt = instruction +
                "\n\nEmail to refine:\n" + generatedReply;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text").asText();
        }catch(Exception e){
            return "Error processing request: "+e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append(
                "Generate a professional email ready for the following email content as a reply to the email.Please don't generate a subject line ");
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        if (emailRequest.getLength() != null && !emailRequest.getLength().isEmpty()) {
            prompt.append("Give a ").append(emailRequest.getLength()).append(" length.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
