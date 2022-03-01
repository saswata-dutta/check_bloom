# Validate BloomFilter False Positives

- Generate 10 mil random strings containing A-Z and 0-9
- Put first 50k into a BloomFilter, with fpp = 0.0000001
- BloomSize on disk is 200kb
- On testing the 10 mil strings from above, found 3 extra matches (in 2 sec)
