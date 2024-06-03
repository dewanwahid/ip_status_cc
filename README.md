# Status Correlation Clustering 
Define and formulate the status balance and status correlation clustering problem.


## Description
Network (or graph) structural balance is a well-defined problem proposed and developed by Heider[1], Cartwright and Haray[2], and Davis[3]. 
According to this problem, a social group can achieve a balanced state based on the principle that “friend of my
friend is my friend”_ whereas _“enemy of my enemy is my enemy”_ and _“enemy of my enemy is my friend”_. 

Leskovec et al.[4] proposed a new idea that a signed directed social network can be seen as a
status-network of that society. For example, a positive edge from vertex A to vertex B means _“B is friend of A”_, but it may also mean _“A
thinks B has a higher status than herself”_. Similarly, a negative edge form vertex A to vertex
B means _“B is an enemy of A”_, but it may also mean, _“A thinks B has lower status than
herself”_. 

We use the idea of status theory to define status balance and then formulate a novel type of correlation clustering problem called 
Status Correlation Clustering (SCC) problem based on status balance definition. We also formulate the integer programming formulation for the SCC problem.


You may read the [**full report**](https://github.com/dewanwahid/ip_status_cc/blob/main/report/Status%20correlation%20clustering%20in%20social%20networks.pdf) in here. 


## Dependencies
- [jGrapht](https://jgrapht.org/javadoc/)

### References
[1]  F. Heider, “Attitudes and cognitive organization,” The Journal of psychology, vol. 21,
no. 1, pp. 107–112, 1946.

[2]  D. Cartwright and F. Harary, “Structural balance: a generalization of heider’s
theory.” Psychological review, vol. 63, no. 5, p. 277, 1956.

[3]  J. A. Davis, “Clustering and structural balance in graphs,” Social networks. A
developing paradigm, pp. 27–34, 1977.

[4]  J. Leskovec, D. Huttenlocher, and J. Kleinberg, “Signed networks in social
media,” in Proceedings of the SIGCHI Conference on Human Factors in Computing
Systems. New York, NY, USA: ACM, 2010, pp. 1361–1370.
