IGN's Code-Foo 2013
=============
Hi, 
in this file i'll explain my solutions to the back-end challenges 

Back-end
--------

Q1
===
Create a 2-5 minute video introducing yourself and showing your passion for IGN and the Code-Foo program.



Q2
===
How many gamers are in the San Francisco Bay Area? Describe each step in your thought process.
 

Q3
===
Write a program to find the given words from the included word search. Both word search and words can be found at [word-search.txt](https://github.com/ign/code-foo-2013/blob/master/word-search.txt)


Assumptions
------------
	the word can be matched in any direction of the 8 basic (S,SE,E,NE,N,NW,W,SW)
	the chars of the matched word must be on the same line (can't match a zigzag word)


Algorithm
----------
	1. find the location of the first char in the giver word
	2. try to match the word in all direction (using dx[], dy[])
	3. on match return a new match containg the starting location, end location and the direction of match 
	
	i used a pre calculated list to save the occurrences of each letter in the grid (insert 2 consecutive integers y,x)


Q4
===
Write a program that searches a family tree for members that match name and/or generation. How does this algorithm scale?

Assumptions
-----------
	there is a very large number of queries on a single tree
	the family tree is given in parent array notation & list of names

Algorithm  
----------
	n: number of family members

	precalcualtion : build a child list (adjacency list) of the tree
				     build a TreeMap<name, node_no> for all family members

	find by name   : query the nameMap by name to get a list of nodes that have the same name 
					 O(log(different names)) =  O(log(n))

	find by level  : make a BFS on the created child list 
					 O(V+E) = O(n+n) = O(n)

Scaling the algorithm
---------------------
	this algo works as long as the memory can fit the child list, and the nameMap, yet we can reduce the space complexity by no creating the nameMap. In this case the find by name will be O(n) and is done by looping over all values of nameList.

Optimization
------------
	we can precalculate the nodes at each level by calling the findByLevel() log(n) times and saving the answer each time.


Q5
===
Write a sorting algorithm that can sort _n_ number of high scores (`float` Score, `string` Name) by score. What algorithm did you use? How does this algorithm scale? Can you reduce time and space complexity?

Assumptions 
-----------
	assuming the score have a normal distributions then the random quick sort in this case will be O(nlog(n))

Algorithm
---------
	using randomized quick sort 


Complexity
----------
	time complexity: 
	quick sort have expected O(nlog(n))  (average case) and worst case O(n^2) (not the case of out problem).
	in most cases in practice quicksort achieves better results than other algorithms eg. merge sort (make a good use cache)
	also provided an iterative version.

	space complexity:
	no extra storage is needed, quicksort sorts in place

	if the given data is not normally distributed then it's better to use merge sort (stable comparison based algorithm) extra O(n) space and O(nlog(n)) running  time

	Algorithm can be scaled widely


Q6
====
Given two three-letter words, write a program that will determine the amount of "moves" it takes to change one word to the other. A "move" is considered changing a single letter of the given word while still keeping it a valid three-letter word. 

Algorithm
---------
	build a graph G(V,E) where V exists for every word in the dictionary. Edge e exists between any 2 vertices if the difference between them is 1.
	use a BFS to traverse the graph searching for the target word.

Complexity
----------
	BFS O(V+E)

Optimization
------------
	we don't actually need to build a graph. We can loop over the dictionary inside the BFS and still get the same time complexity. 
	Building the graph takes a lot of space.

============================================================================================================

Bonus Question
--------------
Create a game similar to Space Invaders. There are no language restrictions. 
not attempted .. please check my repos for other games 
