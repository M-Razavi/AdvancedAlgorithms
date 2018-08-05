package net.mahdirazavi.test.graphcoloring;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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


    /**
     * This function receives an integer, & generates that much random colors.;
     *
     * @param number_of_colors
     * @return
     * @throws Exception
     */
    public static String[] generateColors(int number_of_colors) throws Exception {
        String[] colorNames = {"Linen", "Magenta", "Maroon", "YellowGreen", "Black", "Blue", "Brown", "Orange", "Green", "Purple", "Red", "Yellow", "Pink", "Tomato", "Turquoise", "Violet", "LimeGreen", "AliceBlue", "AntiqueWhite", "Aquamarine", "Azure", "Beige", "Bisque", "BlanchedAlmond", "BlueViolet", "BurlyWood", "CadetBlue", "Chartreuse", "Chocolate", "Coral", "CornflowerBlue", "Cornsilk", "Cyan", "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray", "DarkGrey", "DarkGreen", "DarkKhaki", "DarkMagenta", "DarkOliveGreen", "Darkorange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray", "DarkTurquoise", "DarkViolet", "DeepPink", "DeepSkyBlue", "DimGray", "DimGrey", "DodgerBlue", "FireBrick", "FloralWhite", "ForestGreen", "Gainsboro", "GhostWhite", "Gold", "GoldenRod", "Gray", "Grey", "GreenYellow", "HoneyDew", "HotPink", "IndianRed", "Ivory", "Khaki", "Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue", "LightCoral", "LightCyan", "LightGoldenRodYellow", "LightGray", "LightGrey", "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen", "LightSkyBlue", "LightSlateGray", "LightSlateGrey", "LightSteelBlue", "LightYellow", "MediumAquaMarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen", "MediumSlateBlue", "MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream", "MistyRose", "Moccasin", "NavajoWhite", "Navy", "OldLace", "OliveDrab", "OrangeRed", "Orchid", "PaleGoldenRod", "PaleGreen", "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru", "Plum", "PowderBlue", "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon", "SandyBrown", "SeaGreen", "SeaShell", "Sienna", "SkyBlue", "SlateBlue", "SlateGray", "SlateGrey", "Snow", "SpringGreen", "SteelBlue", "Tan", "Thistle", "Wheat", "White", "WhiteSmoke"};
        String[] list_of_colors = new String[number_of_colors];
        if (colorNames.length < number_of_colors) {
            throw new Exception("not enough defined color");
        }
        for (int i = 0; i < number_of_colors; i++) {
            list_of_colors[i] = colorNames[i];
        }

        return list_of_colors;
    }


    /**
     * This function receives a graph & a list of colors as input, & assigns a valid color in the list to the;
     * nodes of the graph & returns the result graph.;
     *
     * @param graph
     * @param color
     * @return
     * @throws Exception
     */
    public static Graph assignColor(Graph graph, String[] color) throws Exception {

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
     * Assign a random color for given node.
     * this assignment maybe not valid you should check with {@link #isColoringValid} method.
     *
     * @param graph
     * @param color
     * @param node
     * @return
     * @throws Exception
     */
    public static Graph assignColor(Graph graph, String[] color, int node) {
        graph.addVertexColor(node, color[new Random().nextInt(color.length)]);
        return graph;
    }

    /**
     * check validity of colors for graph nodes.
     *
     * @param graph
     * @return
     */
    public static boolean isColoringValid(Graph graph) {
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            ArrayList<String> adjacent_colors = new ArrayList<>();

            graph.getVertexNeighbour(node).stream().forEach(n -> adjacent_colors.add(graph.getVertexColor(n)));

            if ((graph.getVertexColor(node).equals("0")) || (adjacent_colors.contains(graph.getVertexColor(node)))) {
                return false;
            }
        }

        return true;
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
        double changed_energy_level = newValue - oldValue;

        if (changed_energy_level <= 0) {
            return changed_energy_level;
        } else {
            return Math.E * (changed_energy_level / t);
        }
    }

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


    public static Graph simulatedAnnealing(Graph inputGraph, String[] colors, int t) {

        Graph tmpGraph, graph;
        System.out.println("SA t=" + t + " ColorNumber="+ lossFunction(inputGraph));
        if (t == 0) {
            return inputGraph;
        }
        Graph initialColoredGraph = (Graph) inputGraph.copy();
        graph = (Graph) inputGraph.copy();

//        System.out.println("isvalid(copy)="+isColoringValid(initialColoredGraph));
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            int oldValue = lossFunction(graph);

            //new neighborhood
            for (int i = 0; i < 20; i++) {
                tmpGraph = assignColor(graph, colors, node);
                if (!isColoringValid(tmpGraph)) {
                    graph = (Graph) initialColoredGraph.copy();
                    continue;
                }
                graph = tmpGraph;
                int newValue = lossFunction(graph);

                double probability = getProbability(oldValue, newValue, t);

                if (probability <= 0) {
                    //Accept better result
//                    System.out.println("Accept better result.");
                    initialColoredGraph = (Graph) graph.copy();
                    oldValue = newValue;
                } else if (probability < 0.0500) {
                    // Accept with if Delta_E < 0.13820
                    initialColoredGraph = (Graph) graph.copy();
                    oldValue = newValue;
//                    System.out.println("accept result with probability=" + probability);
                }
                else
                {
                    graph = (Graph) initialColoredGraph.copy();
                }

            }
        }

        t -= 1;
        graph = simulatedAnnealing(initialColoredGraph, colors, t);

        return graph;
    }

    /**
     * Fill graph with adj matrix
     *
     * @param adjMatrix
     * @param graph
     * @return
     */
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
        Graph graph = new Graph();

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
        return graph;
    }

    private static void ShowGraph(Graph graph) throws InterruptedException {
        Viewer viewer;
        System.out.println("Visualize graph....");
        SingleGraph visualGraph = new SingleGraph("Graph Coloring Problem");
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            visualGraph.addNode(node + "");
            visualGraph.getNode(node + "").addAttribute("ui.style", "fill-color: " + graph.getVertexColor(node) + ";");

        }
        int c = 0;
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();

            for (int v : graph.getVertexNeighbour(node)) {
                if (visualGraph.getNode(v + "").hasEdgeBetween(node + "")) {
                    continue;
                }
                visualGraph.addEdge((c++) + "", node + "", v + "");
            }
        }

        for (Node gNode : visualGraph) {
            gNode.addAttribute("ui.label", gNode.getId());
        }
        visualGraph.addAttribute("ui.stylesheet", "node {size: 25px, 25px; shape: box; text-size:17px;}");
        viewer = visualGraph.display(true);
    }


    public static void testGraphColor(String[] colors) {
        Viewer viewer;
        System.out.println("Visualize graph....");
        SingleGraph visualGraph = new SingleGraph("Graph Coloring Problem");
        for (String name : colors) {
            visualGraph.addNode(name);
            visualGraph.getNode(name + "").addAttribute("ui.style", "fill-color: " + name + ";");

        }

        for (Node gNode : visualGraph) {
            gNode.addAttribute("ui.label", gNode.getId());
        }
        visualGraph.addAttribute("ui.stylesheet", "node {size: 25px, 25px; shape: box; text-size:17px;}");
        viewer = visualGraph.display(true);

    }

    public static void main(String[] args) throws Exception {

//        String[] myColors = generateColors(138);
//        testGraphColor(myColors);

        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = Paths.get("D:\\Developments\\AdvancedAlgorithms\\GraphColoring2\\Test-Files\\myciel4.col.txt").toAbsolutePath().toString();
        }
        Graph graph = readGraphFromTxt(filePath);
//        fillGraph(g1_adj, graph);
        System.out.println("Load graph from : " + filePath);
        System.out.println(graph);

        int maxDegree = graph.getMaxDegree();
        String[] colorList = generateColors(maxDegree + 1);
        System.out.println(colorList.length + " color used for graph at max.");

        while (true) {
            try {
                graph = assignColor(graph, colorList);
                break;
            } catch (Exception e) {
                //NOP
            }
        }
//        System.out.println("First assigned Color:");
//        for (Iterator it = graph.iterator(); it.hasNext(); ) {
//            int node = (int) it.next();
//            System.out.println(node + " : " + graph.getVertexColor(node));
//        }

        int temperature = 100;
        graph = simulatedAnnealing(graph, colorList, temperature);

        System.out.println(isColoringValid(graph));
        System.out.println("loss=" + lossFunction(graph));
        System.out.println("Final assigned Color:");
        for (Iterator it = graph.iterator(); it.hasNext(); ) {
            int node = (int) it.next();
            System.out.print(node + " : " + graph.getVertexColor(node) + " ");
        }
        ShowGraph(graph);
    }

}
