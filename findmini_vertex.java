import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class findmini_vertex {
    public static void main (String[] args) throws GraphException {
        int[] n = new int[]{10};
        int[] k = new int[]{5};
        boolean exsit = false;
        cover_vertex covermethod = new cover_vertex(n[0], 600, 600);
        while(!exsit){
        for(int i = 0; i<k.length;i++) {
//            cover_vertex covermethod = new cover_vertex(n[i], 600, 600)
            System.out.println("the current k is "+ k[i]);

            //return if the graph has vertex cover <= k using brute force.
            long startTime_brute = System.nanoTime();
            System.out.println(covermethod.bruteforce(k[i]));
            long elapsedTime_brute = System.nanoTime() - startTime_brute;
            System.out.println("the runing time for bruteforce is  " + elapsedTime_brute);
            exsit = covermethod.bruteforce(k[i]);

            //return the bruteforce solution.
            if (exsit) {
                System.out.print("the bruteforce solution  is ");
                Iterator<Integer> iter3 = covermethod.bruteforcesolution().iterator();
                while (iter3.hasNext()) {
                    System.out.print(iter3.next() + " ");

                }

                System.out.println();
            }

            //return if the graph has vertex cover less than k.
            long startTime_recursion = System.nanoTime();
            System.out.println(covermethod.recursion(k[i]));
            long elapsedTime_recursion = System.nanoTime() - startTime_recursion;
            System.out.println("the runing time for recursion is  " + elapsedTime_recursion);
            //return the bruteforce solution.
            if (exsit) {
                System.out.print("the recursion solution is ");
                Iterator<Integer> iter2 = covermethod.recursionsolution().iterator();
                while (iter2.hasNext()) {
                    System.out.print(iter2.next() + " ");

                }
                System.out.println();
            }

            // return the approximation solutions.
            long startTime_appximation = System.nanoTime();
            Set<GraphNode> a = covermethod.approxmethod();
            long elapsedTime_appximation = System.nanoTime() - startTime_appximation;
            System.out.println("the runing time for appximation method is  " + elapsedTime_appximation);
            Iterator<GraphNode> iter = a.iterator();
            System.out.print("the approximation solution is ");
            while (iter.hasNext()) {
                System.out.print(iter.next().getName() + " ");
            }
            System.out.println();
            k[i] = k[i] +1;

        }}




//
//
//

        }







//
//
//
        }











