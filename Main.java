package tp4;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;



public class Main {

    public static void main(String[]args)
    {

        // Signature
        int dataRow[] = new int[]{ 2, 2 ,2, 6, 4 ,2, 2 ,2};
        int dataCol[] = new int[]{ 1, 1, 6, 3, 3, 6 ,1 ,1};
        int dataDiagUp[] = new int[]{ 0, 0, 0, 2, 4, 1, 2, 4, 3, 2, 1, 2, 1, 0, 0};
        int dataDiagDown[] = new int[]{ 0, 0, 1, 2, 1, 2, 3, 4, 2, 1, 4, 2, 0, 0, 0};


        // definition du modele
        Model model = new Model("-- Mission 4 --");
        // definition de N, image de taille NxN
        final int N = dataRow.length;
        //
        IntVar[][]  tab =  new IntVar[N][N];
        // domaine de chaque cellule
        for(int i=0; i< N ;i++)
        {
            for(int j = 0; j< N ; j++)
            {
                tab[i][j] = model.intVar(0,1); //variable de domaine pouvant prendre comme valeur 0 ou 1, 0: case blanche, 1: case noire
            }
        }
        // contraintes

        // Contraintes sur les lignes et les colonnes
        for(int i = 0; i <N ;i++)
        {
            IntVar tmpRows[] = new IntVar[N];
            IntVar tmpCols[] = new IntVar[N];
            for(int j = 0; j< N ; j++)
            {
                tmpRows[j] = tab[i][j];
                tmpCols[j] = tab[j][i];
            }

            model.sum(tmpRows,"=" ,dataRow[i]).post(); // Le résultat de la somme sur chaque ligne doit correspondre à la signature de la ligne en question
            model.sum(tmpCols,"=", dataCol[i]).post(); // Le résultat de la somme sur chaque colonne doit correspondre à la signature de la colonne en question
        }

        /**
         *   Partie supérieure de diagolales montantes
         *  |1|2|3|4|5|6|7|8|
         *  |1|2|3|4|5|6|7| |
         *  |1|2|3|4|5|6| | |
         *  |1|2|3|4|5| | | |
         *  |1|2|3|4| | | | |
         *  |1|2|3| | | | | |
         *  |1|2| | | | | | |
         *  |1| | | | | | | | // Partie inférieure
         */

        // Diagonales montantes
        for(int i = 0; i < N ;i++)
        {

            IntVar tmpDiagUp[] = new IntVar[i+1];       // Partie supérieure de diagolales montantes
            IntVar tmpDiagDown[] = new IntVar[i+1];     // Partie inférieure de diagolales montantes

            for(int j = 0; j <= i ; j++)
            {
                tmpDiagUp[j] = tab[i - j][j];   // Partie supérieure
                if( i < N - 2)  // Verifier si c'est la diagonale orthogonale 1
                    tmpDiagDown[j] = tab[N - j - 1][ N - i + j - 1];    // Partie inférieure
            }
            model.sum(tmpDiagUp, "=" , dataDiagUp[i]).post();
            if( i < N - 2)      // Deuxieme partie de la contrainte
                model.sum(tmpDiagDown, "=" , dataDiagUp[dataDiagUp.length - i - 1]).post();
        }
        // Diagonales descendantes
        for(int i = 0; i < N ;i++)
        {   //
            IntVar tmpDiagUp[] = new IntVar[i+1];     // Partie supérieure de diagolales descendantes
            IntVar tmpDiagDown[] = new IntVar[i+1];   // // Partie inférieure de diagolales descendantes
            //
            for(int j = 0; j <= i ; j++)
            {
                tmpDiagUp[j] = tab[i - j][N - j - 1];   // Partie supérieure
                if( i < N - 1 )  // Verifier si c'est la diagonale orthogonale 2
                {
                    tmpDiagDown[j] = tab[N - i + j - 1][j];    // Partie inférieure
                }
            }
            //
            model.sum(tmpDiagUp, "=" , dataDiagDown[dataDiagDown.length - i - 1]).post();
            if( i < N - 1)      // Deuxieme partie de la contrainte
                model.sum(tmpDiagDown, "=" , dataDiagDown[i]).post();

        }
        //
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }

    }
}
