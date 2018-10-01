// Approach 2: MD5 Hash
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class URLShortener2
{
    // I am using a Map as a replacement to a database.
    // This is a <Key, URL> Map.
    static HashMap<String, String> makeshiftdatabase;
    // This a <URL, Key> map to return the shortned URL, if it already exists,
    // in constatnt time.
    static HashMap<String, String> urlCheck;
    // The domain for the new, short url. Default = "www.tiny.bit/"
    String domain;
    // The length of the new URL. i.e. The number of characters after "/"
    // Example: www.tiny.bit/1sr12; lenght = 5.
    int length;
    
    // Default Constructor. Initializes variable to their default values.
    protected URLShortener2()
    {
        makeshiftdatabase = new HashMap<String, String>();
        urlCheck = new HashMap<String, String>();
        domain = "www.tiny.bit/";
        length = 6;
    }
    
    // Paramertized constructor. Initializes variables to the given values.
    protected URLShortener2(String domain, int length)
    {
        makeshiftdatabase = new HashMap<String, String>();
        urlCheck = new HashMap<String, String>();
        this.length = length;
        this.domain = domain;
        // If the provided domain has no trailing "/", we need to add one.
        if(this.domain.charAt(this.domain.length()-1) != '/')
            this.domain+="/";
    }
    
    // API Methods:
    
    // shortURL: String -> String
    // Input: A long URL which is to be shortened.
    // Output: The shortened version, seven character long, of the original URL
    // Example: new URLShortner.shortURL("www.google.com/assd/sdasd/index.html")
    //          -> "www.tiny.bit/1234567"
    public String shortURL(String URL)
    {
        // TODO: Validate URL
        URL = sanitizeURL(URL);
        if(urlCheck.containsKey(URL))
            return domain + urlCheck.get(URL).toString();
        else
        {
            String key = generateKey(URL);
            return domain + key;
        }
    }
    
    // longURL: String -> String
    // Input: A short version of a URL
    // Output: A long version of the URL/ The true url.
    // Example: new URLShortner.LongURL("www.tiny.bit/1234567") ->
    //          "www.google.com/assd/sdasd/index.html"
    public String longURL(String shortURL)
    {
        String key = shortURL.substring(domain.length(), shortURL.length());
        if(makeshiftdatabase.containsKey(key))
            return makeshiftdatabase.get(key);
        System.out.println("Invalid URL");
        return null;
    }
    
    // Helper Methods:
    
    // generateKey: String -> String
    // Input: A long URL, that is yet to be converted and stored in the 
    //        Map/ Database.
    // Output: A random key(String of characters) that will be appended to the 
    //         specified domain to get the short URL.
    //         Also stores the <Key, URL> pair into the (make-shift)DB and 
    //         the <URL, Key> into urlCheck map.
    // Example: new URLShotner().generateKey(
    //          "www.google.com/assd/sdasd/index.html" ) -> "1234567"
    private String generateKey(String unknownURL)
    {
        boolean flag = false;
        String key = null;
        while(!flag)
        {
            key = getKey(unknownURL);
            if(!makeshiftdatabase.containsKey(key))
                flag = true;
        }
        makeshiftdatabase.put(key, unknownURL);
        urlCheck.put(unknownURL, key);
        return key;
    }
    
    // getKey: String -> String
    // Input: A long form URL, that is unknow to the map/db.
    // Output: A random key(String of characters) that will be appended to the 
    //         specified domain to get the short URL.
    // Example: new URLShotner().generateKey(
    //          "www.google.com/assd/sdasd/index.html" ) -> "1234567"
    private String getKey(String input)
    {
        String key = "";
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            }
            if(hashtext.length()<this.length)
                return hashtext;
            else 
                return hashtext.subSequence(0, length).toString(); 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }
    }
    
    // sanitizeURL: String -> String
    // Input: The given URL
    // Output: A more generic version of the URL
    // Example: new URLShortner().sanitizeURL("www.google.com/")
    //              -> www.google.com
    //          new URLShortner().sanitizeURL("https://www.google.com")
    //              -> www.google.com
    String sanitizeURL(String url) 
    {
	if (url.substring(0, 7).equals("http://"))
		url = url.substring(7);
	if (url.substring(0, 8).equals("https://"))
		url = url.substring(8);
	if (url.charAt(url.length() - 1) == '/')
		url = url.substring(0, url.length() - 1);
	return url;
    }
    
    // main method to test
    public static void main(String ar[]) throws Exception
    {
        URLShortener2 u = new URLShortener2();
        String shortURL = u.shortURL("www.google.com/");
        System.out.println(shortURL);
        System.out.println(u.shortURL("www.google.com"));
        System.out.println(u.shortURL("https://www.google.com/"));
        String longURL = u.longURL(shortURL);
        System.out.println(longURL);
    }
}
