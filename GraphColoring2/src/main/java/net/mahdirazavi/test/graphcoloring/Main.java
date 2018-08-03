package net.mahdirazavi.test.graphcoloring;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.DataFormatException;

public class Main {
    static int[][] g1_adj = {
            {0, 1, 1, 1, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0},
            {0, 1, 1, 0, 0}};

    int[][] g2_adj = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 0},
            {1, 0, 0, 0}};

    int[][] g3_adj = {{0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0}};

    int[][] g4_adj = {{0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}};

//                        int[][] adjacency_matrix=np.array(g1_adj);
//                        G=nx.from_numpy_array(adjacency_matrix) // Creating the graph given the adjacency matrix;

    //---------------- function generate_r&om_color ----------------;
// This function receives an integer, & generates that much r&om colors.;
    public static String[] generateColors(int number_of_colors) throws Exception {
        String[] colorNames = {"Black", "Blue", "Brown", "Orange", "Silver", "Purple", "Red", "Yellow", "AliceBlue", "AntiqueWhite", "Aqua", "Aquamarine", "Azure", "Beige", "Bisque", "BlanchedAlmond", "BlueViolet", "BurlyWood", "CadetBlue", "Chartreuse", "Chocolate", "Coral", "CornflowerBlue", "Cornsilk", "Crimson", "Cyan", "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray", "DarkGrey", "DarkGreen", "DarkKhaki", "DarkMagenta", "DarkOliveGreen", "Darkorange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray", "DarkSlateGrey", "DarkTurquoise", "DarkViolet", "DeepPink", "DeepSkyBlue", "DimGray", "DimGrey", "DodgerBlue", "FireBrick", "FloralWhite", "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold", "GoldenRod", "Gray", "Grey", "Green", "GreenYellow", "HoneyDew", "HotPink", "IndianRed", "Indigo", "Ivory", "Khaki", "Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue", "LightCoral", "LightCyan", "LightGoldenRodYellow", "LightGray", "LightGrey", "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen", "LightSkyBlue", "LightSlateGray", "LightSlateGrey", "LightSteelBlue", "LightYellow", "Lime", "LimeGreen", "Linen", "Magenta", "Maroon", "MediumAquaMarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen", "MediumSlateBlue", "MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream", "MistyRose", "Moccasin", "NavajoWhite", "Navy", "OldLace", "Olive", "OliveDrab", "OrangeRed", "Orchid", "PaleGoldenRod", "PaleGreen", "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru", "Pink", "Plum", "PowderBlue", "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon", "SandyBrown", "SeaGreen", "SeaShell", "Sienna", "SkyBlue", "SlateBlue", "SlateGray", "SlateGrey", "Snow", "SpringGreen", "SteelBlue", "Tan", "Teal", "Thistle", "Tomato", "Turquoise", "Violet", "Wheat", "White", "WhiteSmoke", "YellowGreen"};
        String[] list_of_colors = new String[number_of_colors];
        if (colorNames.length < number_of_colors) {
            throw new Exception("not enough defined color");
        }
        for (int i = 0; i < number_of_colors; i++) {
            list_of_colors[i] = colorNames[i];
        }

        return list_of_colors;
    }

    //---------------- function assign_color ----------------;
// This function receives a graph & a list of colors as input, & assigns a valid color in the list to the;
// nodes of the graph & returns the result graph.;
    public static Graph assign_color(Graph graph, String[] color) throws Exception {

        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            graph.addVertexColor(node, "0");
        }

        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            ArrayList<String> adjacent_colors = new ArrayList<>();

            graph.getVertexNeighbour(node).stream().forEach(n -> adjacent_colors.add(graph.getVertexColor(n)));

            int cnt = 0;

            while ((graph.getVertexColor(node).equals("0")) || (adjacent_colors.contains(graph.getVertexColor(node)))) {
                graph.addVertexColor(node, color[new Random().nextInt(color.length - 1)]);
                cnt++;
                if (cnt > 1000) {
                    throw new Exception("Assignment is not possible");
                }
            }
        }
        return graph;
    }


    /**
     * This function calculates the loss-function for each state;
     * Loss value is public voidined to be the number of colors used for coloring a graph
     */
    public static int lossFunction(Graph graph) {
        Set<String> usedColors = new HashSet<>();
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            usedColors.add(graph.getVertexColor(node));
        }

        return usedColors.size();
    }


    public static double getProbability(int oldValue, int newValue, int t) {
        int changed_energy_level = oldValue - newValue;

        if (changed_energy_level >= 0) {
            return 1;
        } else ;
        {
            return Math.E * (changed_energy_level / t);
        }
    }

    /*


        //---------------- function choose_next_state ----------------;
    // This function receives a dictionary of nodes (zzz) & chooses one node r&omly among them all.;
        public void choose_next_state(graph_, nodes_dict) {
            (node, color)=r & om.choice(list(nodes_dict.keys()));
            if (nodes_dict[(node,color)]==1)
            {
                graph_.nodes[node]['color'] = color;
                return graph_;
                ////// change graph to new state;
            }
            else;
            {
                ////// generate r&om number an choose an interval, therefore next state;
                r & = r & om.r & om();
                if ((r & <= nodes_dict[(node,color)]))
                {
                    graph_.nodes[node]['color'] = color;
                    return graph_;
                }
            else;
                {
                    return graph_;
                }
            }
        }
*/
    //---------------- function simulated_annealing ----------------;


    /**
     * This function receives a graph, a node of that graph, a color & current temperature;
     * & returns the probability of accepting the next state.;
     * Next state is the input graph with colored input node with input color;
     */
    public double assign_probability(Graph graph_, int node_, String color_, int t) {
        int current_value = (-1) * lossFunction(graph_);
//        graph_.nodes[node_]['color'] = color_;
        int next_value = (-1) * lossFunction(graph_);
        int changed_energy_level = next_value - current_value;
        if ((changed_energy_level >= 0)) {
            return 1;
        } else ;
        {
            return Math.E * (changed_energy_level / t);
        }
    }


    public static Graph simulated_annealing(Graph graph, String[] colors, int t) {

        System.out.println("simulated_annealing-t=" + t);
        if (t == 0) {
            return graph;
        }
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            Graph initialColoredGraph = (Graph) graph.copy();
            int oldValue = lossFunction(graph);

            try {
                graph = assign_color(graph, colors);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                graph = initialColoredGraph;
            }
            int newValue = lossFunction(graph);
            System.out.println("loss=" + newValue);

            double probability = getProbability(oldValue, newValue, t);
            System.out.println("p=" + probability);
            if (probability <= 0) {
                graph = initialColoredGraph;
            }
        }

        t -= 1;
        graph = simulated_annealing(graph, colors, t);

        return graph;
    }

    public static Graph fillGraph(int[][] adjMatrix, Graph graph) {
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            nodes.add(i);
        }
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] == 1) {
                    graph.addEdge(nodes.get(i), nodes.get(j));
                }
            }
        }
        return graph;
    }

    public static Graph readGraphFromTxt(String filePath) throws IOException, DataFormatException {
        Graph<Integer> graph = new Graph();

        File readFile = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile.getCanonicalPath()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                String[] vertices = line.split("\\s");
                if (vertices.length != 2) {
                    throw new DataFormatException(line + "  should contain exactly 2 integers");
                }
                try {

                    int vertexV = Integer.parseInt(vertices[0]);
                    int vertexW = Integer.parseInt(vertices[1]);

                    graph.addEdge((vertexV), (vertexW));

                } catch (NumberFormatException exception) {
                    String msg = "One of the following is not intepretible as an integer: "
                            + vertices[0] + " " + vertices[1];
                    throw new NumberFormatException(msg);
                }
            }
        }
//        graph.fixObjectInMap();

        return graph;
    }

    public static void main(String[] args) throws Exception {
//        Path path =
//        String filePath = path.resolve("\\Developments\\AdvancedAlgorithms\\GraphColoring\\Test-Files\\myciel3.col.txt").toAbsolutePath().toString();
        String filePath = Paths.get("D:\\Developments\\AdvancedAlgorithms\\GraphColoring\\Test-Files\\myciel4.col.txt").toAbsolutePath().toString();

        Graph<Integer> graph = readGraphFromTxt(filePath);
//        fillGraph(g1_adj, graph);
        System.out.println(graph);

        int maxDegree = graph.getMaxDegree();
        String[] colorList = generateColors(maxDegree + 1);
        System.out.println("Generated Colors:" + Arrays.toString(colorList));

        while (true) {
            try {
                graph = assign_color(graph, colorList);
                break;
            } catch (Exception e) {
                //NOP
            }
        }
        System.out.println("First assigned Color:");
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            System.out.println(node + " : " + graph.getVertexColor(node));
        }

        int temperature = 5;
        graph = simulated_annealing(graph, colorList, temperature);

        System.out.println("loss=" + lossFunction(graph));
        System.out.println("Final assigned Color:");
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            System.out.println(node + " : " + graph.getVertexColor(node));
        }
        ShowGraph(graph);
    }

    private static void ShowGraph(Graph<Integer> graph) throws InterruptedException {
        Viewer viewer;
        SingleGraph visualGraph = new SingleGraph("Graph Coloring Problem");
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            visualGraph.addNode(node + "");
            visualGraph.getNode(node + "").addAttribute("ui.style", "fill-color: " + graph.getVertexColor(node) + ";");

        }
//        visualGraph.setStrict(false);
        int c = 0;
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();

            for (int v : graph.getVertexNeighbour(node)) {
                if (visualGraph.getNode(v + "").hasEdgeBetween(node + "")) {
                    continue;
                }
                visualGraph.addEdge((c++) + "", node+"", v+"");
                Thread.sleep(100);
            }
        }

        for (Node gNode : visualGraph) {
            gNode.addAttribute("ui.label", gNode.getId());
        }
        visualGraph.addAttribute("ui.stylesheet", "node {size: 25px, 25px; shape: box; text-size:17px;}");
        viewer = visualGraph.display(true);
    }


}
