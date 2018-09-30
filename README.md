# URL Shortener

## Problem Statement:
Write a link shortener, implementing the core functionality yourself. For any given link it should return another that redirects to the original link.

## Solution

NOTE: The solution that I have provided is best used as a simulation for the URL shortening process, and does not represent a real-world system.<br>
That being said, I have provided a section that explains how I would have implemented a more production ready URL Shortening System, provided<br>
I have access to the required resources.<br>


## Execution:
- The program is written using Java.
- To compile the code:
    - Navigate to the 'src 'folder and run the following command.:<br>
        `javac URLShortner.java`
    - To execute the program, run the following command:
        `java URLShortner`

## Approach:
    I have provided 2 solutions with 2 different approaches:
    - Approach 1: Random Key/String Approach
        - In this approach, the user specifies a domain(for the new URL) and a length(the length of the new key for the new URL). By Default these are set to "www.tiny.bit/" and 6 respectively.
        - I am using a <Key, URL> Map to store the shortened URL key associated with the each URL that was passed to a URLShortener instance. This Map is used as a replacement for a database.
        - I have an array of characters that stores all the possible characters - [a-z], [A-Z], [0-9]. I then run a loop for 1..length as provided by the user/ by default, and then pick random characters in each iteration to generate the key.
        - I then use a <URL, Key> Map to check if this randomly generated key already exists.
        - I then return the key appended to the domain, as the shorter URL.
        - To recover the longer URL, I use the <Key, URL> map to recover the original URL.
    
    - Approach 2: Hashing/ MD5 Approach
        - The data structures in this approach is the same as that of Approach 1.
        - The only change is that instead of using random characters form the character array, I generate a hash using the MD5 algorithm and then use length*8 bits of the hash to get the key.

## A Better System:
    
        
