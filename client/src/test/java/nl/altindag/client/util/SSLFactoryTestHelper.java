package nl.altindag.client.util;

import nl.altindag.sslcontext.SSLFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.mockito.Mockito;

public final class SSLFactoryTestHelper {

    public static SSLFactory createSSLFactory(boolean oneWayAuthenticationEnabled, boolean twoWayAuthenticationEnabled) {
        String keyStorePath = "keystores-for-unit-tests/identity.jks";
        String keyStorePassword = "secret";
        String trustStorePath = "keystores-for-unit-tests/truststore.jks";
        String trustStorePassword = "secret";

        SSLFactory.Builder sslFactoryBuilder = SSLFactory.builder();
        if (oneWayAuthenticationEnabled) {
            sslFactoryBuilder.withTrustStore(trustStorePath, trustStorePassword.toCharArray())
                    .withHostnameVerifier(new DefaultHostnameVerifier());
        }

        if (twoWayAuthenticationEnabled) {
            sslFactoryBuilder.withIdentity(keyStorePath, keyStorePassword.toCharArray())
                    .withTrustStore(trustStorePath, trustStorePassword.toCharArray())
                    .withHostnameVerifier(new DefaultHostnameVerifier());
        }
        return Mockito.spy(sslFactoryBuilder.build());
    }

}