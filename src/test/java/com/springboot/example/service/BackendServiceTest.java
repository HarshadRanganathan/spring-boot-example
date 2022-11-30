//package com.springboot.example.service;
//
//import com.springboot.example.properties.ExampleProperties;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.web.client.RestTemplate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//@ExtendWith(MockitoExtension.class)
//public class BackendServiceTest {
//
//    private BackendService backendService;
//
//    private MockRestServiceServer server;
//
//    @BeforeEach
//    public void setUp(@Mock ExampleProperties exampleProperties) {
//        final RestTemplate restTemplate = new RestTemplate();
//
//        server = MockRestServiceServer.createServer(restTemplate);
//        backendService = new BackendService(restTemplate, exampleProperties);
//
//        final ExampleProperties.Backend backend = mock(ExampleProperties.Backend.class);
//        when(exampleProperties.getBackend()).thenReturn(backend);
//        when(backend.getUrl()).thenReturn("https://www.googleapis.com/customsearch/v1");
//    }
//
//    @Test
//    void testBackendCall() {
//        this.server.expect(requestTo("https://www.googleapis.com/customsearch/v1"))
//                .andRespond(withSuccess("{\"kind\": \"customsearch\"}", MediaType.APPLICATION_JSON));
//        String response = backendService.backendCall().getBody();
//        assertThat(response).isEqualTo("{\"kind\": \"customsearch\"}");
//    }
//}
