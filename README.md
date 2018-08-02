# semantic-similarity
Final project for CS2230

A Java command-line tool used to analyze the semantic usage of words in literature to understand their meanings and group similar words via k-means clustering


'''
-f "path/to/file.txt" specify path text file

-v "w" returns the semantic descriptor vector for word w

-t "w,n" returns the top n similar words to word w

-m "euc" specify an alternative method of calculating word vector similarity such as euclidian distance
         valid options: cosine, euc, eucnorm
         
-k "n,m" return n k-means clusters with m k-means iterations
'''


uses org.apache.commons.cli v1.4 for the command line interface
uses opennlp.tools v1.8.3 for word stemming
