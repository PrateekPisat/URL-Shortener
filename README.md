# URL Shortener

## Problem Statement:
Write a link shortener, implementing the core functionality yourself. For any given link it should return another that redirects to the original link.

NOTE: The solution that I have provided is best serves as a simulation for the URL shortening process, and does not represent a real-world system.<br>

## Execution:
- The program is written using Java.
- To compile the code:
    - Navigate to the 'src 'folder and run the following command.:<br>
        `javac URLShortener.java`<br>
        OR<br>
        `javac URLShortener2.java`
    - To execute the program, run the following command:<br>
        `java URLShortener`<br>
        OR<br>
        `java URLShortener2`

## Usage:
- API:
    - I have provided 2 API methods:
        1. String shortURL(String longURL):
            Which returns the shortened URL/ Link
        2. String longURL(String shortURL):
            This Returns the long or original URL associated with this shorter version.
- shortURL:
    - To get the short-URL, you first need to create an instance of the class URLShortener/URLShortener2.
    - Then using this instance call shortURL(longURL), passing the long URL as an argument.
    - Ex.<br>
      ```java
      URLShortener u = new URLShortener();
      String shorter = u.shortURL("www.google.com/asdasd/dasdasd/adsasd");
       ```
- longURL:
    - To get the long-URL, you first need to create an instance of the class URLShortener/URLShortener2.
    - Then using this instance call longURL(shortURL), passing the short URL as an argument.
    - Ex.<br>
      ```java
      URLShortener u = new URLShortener();
      String shorter = u.shortURL("www.google.com/asdasd/dasdasd/adsasd");
      String longer = u.longURL(shorter);
      ```
- I have included some test cases in the main method that can also used as examples for further understanding. 

## Approaches:
I have provided 2 solutions with 2 different approaches:<br>
- Approach 1: Random Key/String Approach
    - In this approach, the user specifies a domain(for the new URL) and a length(the length of the new key for the new URL). 
      By Default these are set to "www.tiny.bit/" and 6 respectively.
    - I am using a <Key, URL> Map to store the shortened URL key associated with the each URL that was passed to a URLShortener 
          instance. This Map is used as a replacement for a database.
    - I have an array of characters that stores all the possible characters - [a-z], [A-Z], [0-9]. 
          I then run a loop for 1..length as provided by the user/ by default, and then pick random characters in each iteration to 
          generate the key.
    - I then use a <URL, Key> Map to check if this randomly generated key already exists.
    - I then return the key appended to the domain, as the shorter URL.
    - To recover the longer URL, I use the <Key, URL> map to recover the original URL.   
    
- Approach 2: Hashing/ MD5 Approach
    - The data structures, classes, etc. used in this approach are the same as that of Approach 1.
    - The only change is that instead of using random characters form the character array, I generate a hash using the MD5 algorithm 
          using the given URL as the message digest
          and then use 1..length characters of the hash to get the key.

## Problems:
The program that I have built suffers from the following problems:
1. The Solution is not persistent, since all the data regarding the URLs is stored in a Map. If the program crashes at any point of time all the shorter URL generated will be lost and all the shorter URL currently in use will be invalidated. 
A solution to is problem is using a database a storage solution. This will allow persistent data storage and deal with the problem of data loss on crashes.
