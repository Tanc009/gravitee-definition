/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.definition.jackson.datatype.api.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.gravitee.definition.model.HttpClientSslOptions;

import java.io.IOException;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class HttpClientSslOptionsDeserializer extends AbstractStdScalarDeserializer<HttpClientSslOptions> {

    public HttpClientSslOptionsDeserializer(Class<HttpClientSslOptions> vc) {
        super(vc);
    }

    @Override
    public HttpClientSslOptions deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        HttpClientSslOptions httpClientSslOptions = new HttpClientSslOptions();

        JsonNode enabledNode = node.get("enabled");
        if (enabledNode != null) {
            boolean enabled = enabledNode.asBoolean(false);
            httpClientSslOptions.setEnabled(enabled);
        }

        JsonNode trustAllNode = node.get("trustAll");
        if (trustAllNode != null) {
            boolean trustAll = trustAllNode.asBoolean(false);
            httpClientSslOptions.setTrustAll(trustAll);
        }

        JsonNode hostnameVerifierNode = node.get("hostnameVerifier");
        if (hostnameVerifierNode != null) {
            boolean hostnameVerifier = hostnameVerifierNode.asBoolean(false);
            httpClientSslOptions.setHostnameVerifier(hostnameVerifier);
        }

        String sPem = readStringValue(node, "pem");
        if (sPem != null) {
            httpClientSslOptions.setPem(sPem);
        }

        return httpClientSslOptions;
    }
}