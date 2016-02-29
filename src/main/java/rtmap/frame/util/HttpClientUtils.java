package rtmap.frame.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于HttpClinet4.+请求服务
 * HttpClient支持HTTP/1.1这个版本定义的所有Http方法：GET,HEAD,POST,PUT,DELETE,TRACE和OPTIONS
 * 
 * @author liqingshan
 */
public class HttpClientUtils {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String scheme = "https";
	private String host;
	private String actionPath;
	private int port;

	/**
	 * 设置协议名
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * 设置主机名
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 设置请求资源的路径
	 */
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	/**
	 * 设置主机端口
	 */
	public void setPort(int port) {
		this.port = port;
	}

	public void ssl(String keyFullPath, String pwd) throws URISyntaxException {
		CloseableHttpClient httpclient = null;

		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File(keyFullPath));

			try {
				trustStore.load(instream, pwd.toCharArray());
			} catch (CertificateException e) {
				logger.error(e.toString());
				e.printStackTrace();
			} finally {
				instream.close();
			}

			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port).build();
			HttpGet httpget = new HttpGet(uri);
			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					EntityUtils.toString(entity);
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (KeyManagementException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (KeyStoreException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error(e.toString());
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private List<NameValuePair> convertPara(Map<String, String> paras) {
		if (paras == null)
			return null;

		List<NameValuePair> rst = new ArrayList<>();
		Set set = paras.entrySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();

			rst.add(new NameValuePair() {
				@Override
				public String getValue() {
					return (String) entry.getValue();
				}

				@Override
				public String getName() {
					return (String) entry.getKey();
				}
			});
		}

		return rst;
	}

	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public String postForm(Map<String, String> paras) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port).build();
			HttpPost httppost = new HttpPost(uri);
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(convertPara(paras), "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public String post(Map<String, String> paras) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port).build();
			HttpPost httppost = new HttpPost(uri);
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(convertPara(paras), "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 发送 get请求
	 */
	public String get(Map<String, String> paras) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			URI uri;
			if (paras == null || paras.size() == 0) {
				uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port).build();
			} else {
				uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port)
						.addParameters(convertPara(paras)).build();
			}

			HttpGet httpget = new HttpGet(uri);
			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					return EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 上传文件
	 * 
	 * @throws URISyntaxException
	 */
	public void upload(String fullPath) throws URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPath(actionPath).setPort(port).build();
			HttpPost httppost = new HttpPost(uri);
			FileEntity fileEntity = new FileEntity(new File(fullPath));
			fileEntity.setChunked(true);
			httppost.setEntity(fileEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					resEntity.getContentLength();
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
