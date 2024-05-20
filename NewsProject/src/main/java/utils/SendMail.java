package utils;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import entity.User;
import utils.DBUtil;
import entityManager.UserDB;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

public class SendMail {
	public boolean SendVerifyMail(User user, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		EntityManager entityManager = DBUtil.getEntityManager();
		UserDB userDb = new UserDB(entityManager);
		
		boolean test=false;
		String toEmail=user.getEmail();
		long ttlMillis = Duration.ofMinutes(15).toMillis();
	    String link = generateJwt(String.valueOf(user.getUserId()), ttlMillis);
	    System.out.println("Check resetToken: "+link);
	    
        user.setVerifylink(link);
        userDb.updateUser(user);
        
        String url = "Your confirmation link: "+ getBaseUrl(request) +"/NewsProject"+ "/verify?link=" + link;
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
      //Complete the setApiKey with this key:  xkeysib-...-rK0LRLRe0OSBHsPE
        apiKey.setApiKey("...-b8a86b8944bf0d089f963b597d85457f5eea39cb458b060d7404eda77ab9cc5a-...");
        
        String username=request.getParameter("username");
        String userEmail= request.getParameter("email");
      try {
                    TransactionalEmailsApi api = new TransactionalEmailsApi();
                    SendSmtpEmailSender sender = new SendSmtpEmailSender();
                    sender.setEmail("testnixomg123@gmail.com");
                    sender.setName("nixOMG");
                    List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
                    SendSmtpEmailTo to = new SendSmtpEmailTo();
                    to.setEmail(toEmail);
                    to.setName(username);
                    toList.add(to);
                    
                    
                    
                    Properties headers = new Properties();
                    headers.setProperty("Some-Custom-Name", "unique-id-1234");
                    Properties params = new Properties();
                    params.setProperty("parameter", "My param value");
                    params.setProperty("username", username);
                    params.setProperty("userEmail", userEmail);
                    params.setProperty("url", url);
                    SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
                    sendSmtpEmail.setSender(sender);
                    sendSmtpEmail.setTo(toList);
                    sendSmtpEmail.setTextContent(username);
                    sendSmtpEmail.setTextContent(userEmail);    
                    sendSmtpEmail.setTextContent(url);   
                    sendSmtpEmail.setSubject("Confirmation Link");
                    sendSmtpEmail.setHeaders(headers);
                    sendSmtpEmail.setParams(params);
                    sendSmtpEmail.setTemplateId(2L);
                    CreateSmtpEmail res = api.sendTransacEmail(sendSmtpEmail);
                    System.out.println(res.toString());
                    test=true;
                } 
      catch (Exception e) {
                    System.out.println("Exception occurred:- " + e.getMessage());
      }          
		return test;
	}

	public boolean SendResetPasswordMail(User user, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
	    boolean test=false;
	    String toEmail=user.getEmail();
	    // Generate a JWT that contains the user's ID and expires after 15 minutes.
	    long ttlMillis = Duration.ofMinutes(15).toMillis();
	    String resetToken = generateJwt(String.valueOf(user.getUserId()), ttlMillis);
	    String url = "Your password reset link: "+ getBaseUrl(request) +"/NewsProject"+ "/resetPass?token=" + resetToken;
	    System.out.println("Check resetToken: "+resetToken);
	    
	    EntityManager entityManager = DBUtil.getEntityManager();
		UserDB userDb = new UserDB(entityManager);
		
		user.setVerifylink(resetToken);
        userDb.updateUser(user);
		
	    
	    ApiClient defaultClient = Configuration.getDefaultApiClient();
	    // Configure API key authorization: api-key
	    ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
	    apiKey.setApiKey("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");
	    
	    try {
	        TransactionalEmailsApi api = new TransactionalEmailsApi();
	        SendSmtpEmailSender sender = new SendSmtpEmailSender();
	        sender.setEmail("testnixomg123@gmail.com");
	        sender.setName("nixOMG");
	        List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
	        SendSmtpEmailTo to = new SendSmtpEmailTo();
	        to.setEmail(toEmail);
	        toList.add(to);
	        
	        Properties headers = new Properties();
	        headers.setProperty("Some-Custom-Name", "unique-id-1234");
	        Properties params = new Properties();
	        params.setProperty("parameter", "My param value");
	        params.setProperty("userEmail", toEmail);
	        params.setProperty("url", url);
	        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
	        sendSmtpEmail.setSender(sender);
	        sendSmtpEmail.setTo(toList);
	        sendSmtpEmail.setTextContent(toEmail);    
	        sendSmtpEmail.setTextContent(url);   
	        sendSmtpEmail.setSubject("Password Reset Link");
	        sendSmtpEmail.setHeaders(headers);
	        sendSmtpEmail.setParams(params);
	        sendSmtpEmail.setTemplateId(2L);
	        CreateSmtpEmail res = api.sendTransacEmail(sendSmtpEmail);
	        System.out.println(res.toString());
	        test=true;
	    } 
	    catch (Exception e) {
	        System.out.println("Exception occurred:- " + e.getMessage());
	    }          
	    return test;
	}

	
	private String getBaseUrl(HttpServletRequest request) {
	    String scheme = request.getScheme();             // http
	    String serverName = request.getServerName();     // hostname.com
	    int serverPort = request.getServerPort();        // 80

	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
	        url.append(":").append(serverPort);
	    }

	    return url.toString();
	}
	
	private static final byte[] SECRET_KEY = "notnixXCyEGipCYqax3M2oORE2R8RbpPY2PjXcbkBVGa0I+t8".getBytes();

    public String generateJwt(String readerId, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String jws = Jwts.builder()
            .setIssuer("TenshiProject")
            .setIssuedAt(now)
            .setSubject(readerId)
            .setExpiration(new Date(nowMillis + ttlMillis))
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY), SignatureAlgorithm.HS256)
            .compact();
        System.out.println(jws);
        return jws;
        
    }

    public String verifyJwt(String jws) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY))
                .build()
                .parseClaimsJws(jws);

            return claims.getBody().getSubject();
        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
            return null;
        }
    }
}
