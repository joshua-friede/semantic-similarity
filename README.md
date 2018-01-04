# semantic-similarity
Final project for CS2230

This is a simple command line program written in java

-f "path/to/file.txt" specify path text file

-v "w" returns the semantic descriptor vector for word w

-t "w,n" returns the top n similar words to word w

-m "euc" specify an alternative method of calculating word vector similarity such as euclidian distance
         valid options: cosine, euc, eucnorm
         
-k "n,m" return n k-means clusters with m k-means iterations
