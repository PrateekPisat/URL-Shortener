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

## Approach:
I have provided 2 solutions with 2 different approaches:
    1. Approach 1: Random Key/String Approach
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
    
    2. Approach 2: Hashing/ MD5 Approach
        - The data structures, classes, etc. used in this approach are the same as that of Approach 1.
        - The only change is that instead of using random characters form the character array, I generate a hash using the MD5 algorithm 
          using the given URL as the message digest
          and then use 1..length characters of the hash to get the key.
