# Status Correlation Clustering 

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)

### Reference Project Report
Wahid, Dewan F., Status correlation clustering in social networks. Project Report. University of British Columbia. April 26, 2015. [[Report Download Link](https://github.com/dewanwahid/ip_status_cc/tree/main/report)]



## Project Description
The above report defines and formulates the status balance and Status Correlation Clustering (SCC) problem based on the status-network [4]. This repository presents an implementation of the Integer Programming (IP) formulation of the proposed SCC problem.  

## Basic Definitions
**Structural Balanced-Network:** A network (or graph) structural balance is a well-defined problem proposed and developed by Heider [1], Cartwright and Haray [2], and Davis [3]. 

According to this problem, a social group can achieve a balanced state based on the principle that **_“friend of my friend is my friend”_** whereas **_“enemy of my enemy is my enemy”_** and  **_“enemy of my enemy is my friend”_**. 

**Status-Network:** Leskovec et al. [4] proposed a new idea that a signed directed social network can be seen as a status-network of that society. For example, a positive edge from vertex A to vertex B means **_“B is friend of A”_**, but it may also mean **_“A thinks B has a higher status than herself”_**. Similarly, a negative edge form vertex A to vertex B means **_“B is an enemy of A”_**, but it may also mean, **_“A thinks B has lower status than herself”_**. 

**Status Correlation Clustering (SCC):** We use the idea of status theory to define status balance and then formulate a novel type of correlation clustering problem called  Status Correlation Clustering (SCC) problem based on status balance definition. This problem aims to minimize status-clustering error to product node clusters. We also formulate the integer programming formulation for the SCC problem.


You may read the [**full report**](https://github.com/dewanwahid/ip_status_cc/blob/main/report/Status%20correlation%20clustering%20in%20social%20networks.pdf) in here. 

## Quick Start
For a given status social network, status clusters can be identify by the following script: `statusCluster\StatusBalanceClusters.java`.

### Dependencies
Maven dependency of [jGrapht](https://jgrapht.org/javadoc/) to add this project:

```
<groupId>org.jgrapht</groupId>
<artifactId>jgrapht-core</artifactId>
<version>1.5.2</version
```

## References
[1]  F. Heider, “Attitudes and cognitive organization,” The Journal of psychology, vol. 21, no. 1, pp. 107–112, 1946.

[2]  D. Cartwright and F. Harary, “Structural balance: a generalization of heider’s
theory.” Psychological review, vol. 63, no. 5, p. 277, 1956.

[3]  J. A. Davis, “Clustering and structural balance in graphs,” Social networks. A
developing paradigm, pp. 27–34, 1977.

[4]  J. Leskovec, D. Huttenlocher, and J. Kleinberg, “Signed networks in social
media,” in Proceedings of the SIGCHI Conference on Human Factors in Computing
Systems. New York, NY, USA: ACM, 2010, pp. 1361–1370.


If you have any questions, feel free to email at dfwahid@gmail.com. 