# Cryptocurrency
A simplified implementation of a cryptocurrency.

Classes in brief:

Transaction
Represents a transaction that has a unique id.

Node
Represents a user in our simulated cryptocurrency network. Each node has:
a list of transactions that it either made or had received/ heard of through network announcements.
a cache of blocks it created or had received/ heard of through network announcements.
a private key (generated using key pair generator) 
a public key (generated using key pair generator) that a node can be identified with, however we left the nodes identified by unique labels so that it is easier for eye inspection when testing.
its version of the blockchain.

Graph
Represents the network and contains the bulk of our work and the most important methods used.
The graph has
an adjacency list that maps each node to its direct neighbors. The graph connection is hard coded in the main method and will be further explained in the ‘running the code’ section.
a transaction quota which represents the number of transactions needed to create a block.







Main methods used:

makeTransaction(Node n)
First, a user creates a new transaction and signs it using his private key using the RSA algorithm. The transaction is added to the user’s list of transactions, then the user announces the transaction to a random number of its neighbors. Finally, the method propagate transaction is called for each node in the set of neighbors that received the transaction announcement.

propagateTransaction(Node n, Transaction t)
Similar to the last step in the previous method, a user n announces the transaction t to a random number of its neighbors who in turn add the transaction to their list of transactions. Then, the method propagateTransaction is recursively called with the set of random neighbors.
However, a condition call check is applied to prevent a node from announcing the same transaction more than once.

createBlock(Node n)
First, we check that node n has a certain number (transaction quota) of valid transactions.
Next, the previous hash is set to the hash of the last block in the blockchain this new block is to be added to or it is set to an empty string in the case of a genesis block. Then, the block is created, added to the block cache of the node and announced using the method propagateBlock.

propagateBlock(Node n, Block b)
Operates in exact manner as propagateTransaction except with blocks.
Although the node cache may not contain a certain block due to random announcements, its blockchain will be the same as all the other nodes which is the last valid blockchain.

Block
A block has:
a list of n transaction (n depending on the transaction quota)
a hash value which is calculated by applying SHA-256 algorithm to the list of transactions, the hash of the preceding block (if exists) and a nonce value that is unique to this one block and is incremented until the result hash value starts with two 0’s.
a hash value of the preceding block 

Blockchain

The blockchain is essentially a linked list of blocks.
    
Running the code:
To test run our code:
Simply run the main class. We made sure to add enough print statements to showcase everything that is happening with the ‘network’.
Sample run:


Parameters to play with:
the graph connection. As mentioned earlier, the network nodes are hard coded into the main method, nonetheless the number of nodes and how they are connected can be easily changed from the main method.
the transactions made can also be manipulated from the main method.
the transaction quota can be set from the Graph class.



Credits:
Graph:
https://www2.cs.duke.edu/courses/cps100e/fall10/class/11_Bacon/code/Graph.html

RSA Encryption:
https://gist.github.com/dmydlarz/32c58f537bb7e0ab9ebf

