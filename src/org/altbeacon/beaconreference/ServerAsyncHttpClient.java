package org.altbeacon.beaconreference;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

import com.loopj.android.http.AsyncHttpClient;

import android.content.Context;

public class ServerAsyncHttpClient extends AsyncHttpClient {

	public ServerAsyncHttpClient(Context paramContext) {
		setSSLSocketFactory(SslSocketFactory(paramContext));

	}

	private SSLSocketFactory SslSocketFactory(Context paramContext) {
		SSLSocketFactory localSSLSocketFactory = null;
		try {
			KeyStore localKeyStore = KeyStore.getInstance("BKS");
			InputStream localInputStream = paramContext.getResources()
					.openRawResource(R.raw.quizserver);
			try {
				localKeyStore.load(localInputStream, "qportal14".toCharArray());
				localInputStream.close();
				localSSLSocketFactory = new MySSLSocketFactory(localKeyStore);

			} finally {
				localInputStream.close();
			}
		} catch (Exception localException) {
			throw new AssertionError(localException);
		}

		return localSSLSocketFactory;
	}

	public class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}

	}

}

