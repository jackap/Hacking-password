# How complex is to hack a password? 

I made this simple program during the course of _Network Security_ in Aalto University. 
It is not meant to be used in real life scenarios but it gives you an idea of how easy is to _brute force_
weak passwords. 

## How do we define a weak password? 
1. Length. A small password is weak because it requires less effort and
computing power to be brute-forced.
2. Complexity. A password that contains only alphabetical characters is easier to decrypt because
there are less combination of possible values.
3. Dictionary. Words that are in the dictionary are very easy to spot. Look at the file `words_dict.txt`.
If you find your password there, it means that you have a problem.
