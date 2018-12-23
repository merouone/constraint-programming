# constraint-programming
constraint programming using choco 
Imagine we do not have an image probably we lost it or we compressed and we want our AI system to restore it just by using its signature .
Here some here comes programming by constraints in which we give our initial conditions and domain of our variables and let the solver to figure out what is the solution 
In this example we input to out program a signature in form of arrays
Each array represents a particular signature , one for Rows one For Columns and one for Diagonals 

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
