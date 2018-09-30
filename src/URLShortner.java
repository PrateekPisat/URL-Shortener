
import java.util.*;

public class URLShortner
{
    // I am using a Map as a replacement to a database.
    // I've explaind more about this approach in the README.
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
    // To randomly select characters from the following character array.
    Random r;
    // An array of all possible characters
    char[] charMap = {
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
        's','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J',
        'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1',
        '2','3','4','5','6','7','8','9' 
    };
    
    // Default Constructor. Initializes variable to their default values.
    protected URLShortner()
    {
        makeshiftdatabase = new HashMap();
        urlCheck = new HashMap();
        r = new Random();
        domain = "www.tiny.bit/";
        length = 7;
    }
    
    // Paramertized constructor. Initializes variables to the given values.
    protected URLShortner(String domain, int length)
    {
        makeshiftdatabase = new HashMap();
        urlCheck = new HashMap();
        r = new Random();
        // If the provided domain has no trailing "/", we need to add one.
        if(domain.charAt(domain.length())-1 != '/')
            domain+="/";
        this.domain = domain;
        this.length = length;
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
            key = getKey();
            if(!makeshiftdatabase.containsKey(key))
                flag = true;
        }
        makeshiftdatabase.put(key, unknownURL);
        urlCheck.put(unknownURL, key);
        return key;
    }
    
    // getKey: void -> String
    // Input: A long form URL, that is unknow to the map/db.
    // Output: A random key(String of characters) that will be appended to the 
    //         specified domain to get the short URL.
    // Example: new URLShotner().generateKey(
    //          "www.google.com/assd/sdasd/index.html" ) -> "1234567"
    private String getKey()
    {
        String key = "";
        for(int i=0;i<length;i++)
        {
            key += Character.toString(charMap[r.nextInt(charMap.length)]);
        }
        return key;
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
        URLShortner u = new URLShortner();
        String shortURL = u.shortURL("www.google.com/asd");
        System.out.println(shortURL);
        System.out.println(u.shortURL("www.google.com/dsd"));
        System.out.println(u.shortURL("https://www.google.com/as"));
        String longURL = u.longURL(shortURL);
        System.out.println(longURL);
    }
}