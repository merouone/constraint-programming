# constraint-programming
Imagine we do not have an image probably we lost it or we compressed it and we want that our AI system be able restore it just by having only its signature .
Here comes programming by constraints in which we give our initial conditions and the domain of our variables and let the solver to figure out what could be  the solution .
In this example we input to our program a signature in form of 4 arrays
Each array represents a particular signature , one for Rows one For Columns and one for Diagonals 
afterward out program combines all the contraints using sum operation .
For example in our signature below, we say that ```dataRow[0] = 2``` which means that the summation of all our first rows in this picture must be equal to 2, likewise  ```dataCol[0] = 1 ``` all the first columns of the image must be equal to 1 for the diagonals too .

![](signature.png)


for this example i used this signature 
```
 int dataRow[] = new int[]{ 2, 2 ,2, 6, 4 ,2, 2 ,2};
 int dataCol[] = new int[]{ 1, 1, 6, 3, 3, 6 ,1 ,1};
 int dataDiagUp[] = new int[]{ 0, 0, 0, 2, 4, 1, 2, 4, 3, 2, 1, 2, 1, 0, 0};
 int dataDiagDown[] = new int[]{ 0, 0, 1, 2, 1, 2, 3, 4, 2, 1, 4, 2, 0, 0, 0};

```
to make it work please add to your maven file the following code

```
<dependency>
   <groupId>org.choco-solver</groupId>
   <artifactId>choco-solver</artifactId>
   <version>4.10.0</version>
</dependency>

```
