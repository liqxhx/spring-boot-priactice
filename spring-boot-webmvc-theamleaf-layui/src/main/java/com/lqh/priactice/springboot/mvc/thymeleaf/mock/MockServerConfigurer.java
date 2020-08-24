package com.lqh.priactice.springboot.mvc.thymeleaf.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * <p> 类描述: MockServerConfigurer
 *
 *
 *
 *
 * @author liqinghui
 * @version TODO
 * @date 2020/05/26 23:36
 * @since 2020/05/26 23:36
 */
public class MockServerConfigurer {
    /**
    * main
    */
    public static void main(String[] args) throws IOException {
//        start();
        init();
    }

    private static void init() throws IOException {
        WireMockConfiguration wireMockConfiguration = new WireMockConfiguration().port(8062).disableRequestJournal();

        WireMockServer wireMockServer = new WireMockServer(wireMockConfiguration);
        wireMockServer.start();

        wireMockServer.resetMappings();


        mock(wireMockServer, "/mock/users", "users");

    }

    private static void mock(WireMockServer wireMockServer, String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/".concat(file).concat(".json"));

        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");

        wireMockServer.stubFor(WireMock.get(urlPathEqualTo(url))
                .willReturn(aResponse()
                        .withBody(String.format(content, UUID.randomUUID().toString().replace("-", ""), System.currentTimeMillis()))
                        .withStatus(200)));
    }

    /**
     * java -jar wiremock-jre8-standalone-2.26.3.jar --port 8062
     * @throws IOException
     */
    private static void start() throws IOException {
        configureFor(8062);
        removeAllMappings();

        // curl -X post http://localhost:8062/hdh/bind

        mock("/hdh/bind", "bind");
        mock("/hdh/unbind", "unbind");
        mock("/hdh/transferbind", "transferbind");
        mock("/hdh/delaybind", "delaybind");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/".concat(file).concat(".json"));

        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(post(urlPathEqualTo(url)).willReturn(aResponse()
                .withBody(String.format(content, UUID.randomUUID().toString().replace("-", ""), System.currentTimeMillis()))
                .withStatus(200)));
    }
}