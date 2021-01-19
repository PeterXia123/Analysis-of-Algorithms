
import java.util.*;
import java.util.Random;

/* Program for testing the Graph methods. */
public class cover_vertex {
    private Graph graph;
    private int nodes_num;
    private Set<Integer> bruteforce_solution;
    private Set<Integer> recursion_solution;

   // create randomly genereated graph
    public cover_vertex(int n,int width,int height) throws GraphException {
        nodes_num = n;
        graph = new Graph(n,width,height);
        bruteforce_solution = null;
        recursion_solution = null;

    }

  // return the graph.
    public Graph getGraph() { return graph;}




// use the brute force to find the solution.
    public boolean bruteforce(int k) {
        List<Set<Integer>> ll;
        ll = new ArrayList<>();
        ll = helper_bruteforce(nodes_num,k,0, new HashSet<>(), ll);

        for (int i = 0; i < ll.size(); i++) {
            Set<Integer> perm = ll.get(i);
            Iterator<GraphEdge> iter = graph.returnedges().iterator();
            boolean a = true;
            while (iter.hasNext()) {
                GraphEdge iteredge = iter.next();
                GraphNode first = iteredge.firstEndpoint();
                GraphNode second = iteredge.secondEndpoint();
                if (!(perm.contains(first.getName()) | perm.contains(second.getName()))) {
                    a = false;
                    break;
                }
            }
            if (a) {
                bruteforce_solution = perm;
                return true;
            }
        }
        return false;

    }




// the helper function to find the permutation of nodes.
    private List<Set<Integer>> helper_bruteforce(int n, int k, int startN, Set<Integer> set, List<Set<Integer>> ll) {
        if (k == set.size()) {
            ll.add(new HashSet<>(set));
            return ll;
        }
        for (int i = startN; i < n; i++) {
            set.add(i);
            ll = helper_bruteforce(n, k, i + 1, set, ll);
            set.remove(i);
        }

        return ll;
    }

    public Set<Integer> bruteforcesolution(){
        return bruteforce_solution;
    }



// the recursion method
    public boolean recursion(int k) throws GraphException {
        if (graph.returnedges().size() == 0) return true;
        if (graph.returnedges().size() > k * nodes_num) return false;
        Set<Integer> leftnodeset = new HashSet<Integer>();
        Set<Integer> rightnodeset = new HashSet<Integer>();
        List<GraphEdge> leftedge = new ArrayList(graph.returnedges());
        List<GraphEdge> rightedge = new ArrayList(graph.returnedges());

        GraphNode leftnode = leftedge.get(0).firstEndpoint();
        Iterator<GraphEdge> iter = graph.incidentEdges(leftnode);
        while (iter.hasNext()) {
                GraphEdge nextedge = iter.next();
                if (leftedge.contains(nextedge)) {
                    leftedge.remove(nextedge);
                }
            }
        leftnodeset.add(leftnode.getName());
        boolean a = recursion_helper(k - 1, leftedge,leftnodeset);

        GraphNode rightnode = rightedge.get(0).secondEndpoint();
        Iterator<GraphEdge> iter2 = graph.incidentEdges(rightnode);
        while (iter2.hasNext()) {
                GraphEdge nextedge = iter2.next();
                if (rightedge.contains(nextedge)) {
                    rightedge.remove(nextedge);
                }
            }
        rightnodeset.add(rightnode.getName());
        boolean b = recursion_helper(k - 1, rightedge,rightnodeset);
        if(a ||b) return true;
        else return false;

        }





//
    private boolean recursion_helper(int k,List<GraphEdge> edge,Set<Integer>nodeset )throws GraphException {
            if (edge.size() == 0) {
                recursion_solution = nodeset;
                return true;
            }
            if (edge.size() > k * nodes_num) return false;

            List<GraphEdge> leftedge = new ArrayList(edge);
            List<GraphEdge> rightedge = new ArrayList(edge);
            Set<Integer> leftnodeset = new HashSet<>(nodeset);
            Set<Integer> rightnodeset = new HashSet<>(nodeset);

            GraphNode leftnode = leftedge.get(0).firstEndpoint();
            Iterator<GraphEdge> iter = graph.incidentEdges(leftnode);

            while (iter.hasNext()) {
                GraphEdge nextedge = iter.next();
                if (leftedge.contains(nextedge)) {
                    leftedge.remove(nextedge);
                }
            }
            leftnodeset.add(leftnode.getName());


            boolean a = recursion_helper(k - 1, leftedge,leftnodeset);
            GraphNode rightnode = rightedge.get(0).secondEndpoint();
            Iterator<GraphEdge> iter2 = graph.incidentEdges(rightnode);
            while (iter2.hasNext()) {
                GraphEdge nextedge = iter2.next();
                if (rightedge.contains(nextedge)) {
                    rightedge.remove(nextedge);
                }
            }
            rightnodeset.add(rightnode.getName());
            boolean b = recursion_helper(k - 1, rightedge,rightnodeset);
            if(a ||b) return true;

        return false;





}


    public Set<GraphNode>  approxmethod() throws GraphException {
        Set<GraphNode> nodeSet = new HashSet<GraphNode>();
        List<GraphEdge> edges = new ArrayList(graph.returnedges());
        while(!edges.isEmpty()) {
            GraphEdge edge = edges.get(0);
            GraphNode leftnode = edge.firstEndpoint();
            GraphNode rightnode = edge.secondEndpoint();
            nodeSet.add(leftnode);
            nodeSet.add(rightnode);
            Iterator<GraphEdge> iter = graph.incidentEdges(leftnode);
            while (iter.hasNext()) {
                GraphEdge nextedge = iter.next();
                if (edges.contains(nextedge)) {
                    edges.remove(nextedge);
                }
            }

            Iterator<GraphEdge> iter2 = graph.incidentEdges(rightnode);
            while (iter2.hasNext()) {
                GraphEdge nextedge = iter2.next();
                if (edges.contains(nextedge)) {
                    edges.remove(nextedge);
                }
            }
        }

        return nodeSet;


    }
    public Set<Integer> recursionsolution(){
        return recursion_solution;
    }


}















