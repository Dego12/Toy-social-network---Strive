package Console;

import domain.Friendship;
import service.FriendshipService;
import java.util.ArrayList;

public class Graph {
    private FriendshipService fs;
    ArrayList<ArrayList<String>> graph;
    int N;

    Graph(FriendshipService fs)
    {
        this.fs = fs;

        int n = 0;
        for (Friendship fr: this.fs.getAll())
        {
            n++;
        }
        this.N = n;
        graph = new ArrayList<ArrayList<String>>();
        for (int i = 0; i<N;i++)
        {
            graph.add(new ArrayList<String>());
        }

    }

    public void edgeMaker()
    {
        int j = 0;
        int h = 0;
        for (Friendship f: this.fs.getAll()) {
                graph.get(j).add(f.getUser1().getFirstName() + " " + f.getUser1().getLastName());
                graph.get(h).add(f.getUser2().getFirstName() + " " + f.getUser2().getLastName());
                j++;
                h++;
            }
    }

    public void printGraph()
    {
        for (int i = 0; i<N;i++) {
            System.out.print("Node " + i + ": ");
            for (String x : graph.get(i)) System.out.print(" -> " + x);
            System.out.println();
        }
    }

}
