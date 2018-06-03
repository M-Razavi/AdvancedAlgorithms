package net.mahdirazavi.test.stringmatching;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        String text = "In software engineering, a software design pattern is a general, reusable solution to a commonly occurring problem within a given context in software design. It is not a finished design that can be transformed directly into source or machine code. It is a description or template for how to solve a problem that can be used in many different situations. Design patterns are formalized best practices that the programmer can use to solve common problems when designing an application or system.\n" +
                "\nObject-oriented design patterns typically show relationships and interactions between classes or objects, without specifying the final application classes or objects that are involved. Patterns that imply mutable state may be unsuited for functional programming languages, some patterns can be rendered unnecessary in languages that have built-in support for solving the problem they are trying to solve, and object-oriented patterns are not necessarily suitable for non-object-oriented languages.\n" +
                "\nDesign patterns may be viewed as a structured approach to computer programming intermediate between the levels of a programming paradigm and a concrete algorithm.\n" +
                "\nContents\n" +
                "    1 History\n" +
                "    2 Practice\n" +
                "    3 Structure\n" +
                "        3.1 Domain-specific patterns\n" +
                "    4 Classification and list\n" +
                "        4.1 Creational patterns\n" +
                "        4.2 Structural patterns\n" +
                "        4.3 Behavioral patterns\n" +
                "        4.4 Concurrency patterns\n" +
                "    5 Documentation\n" +
                "    6 Criticism\n" +
                "    7 See also\n" +
                "    8 References\n" +
                "    9 Further reading\n" +
                "History\n" +
                "\nPatterns originated as an architectural concept by Christopher Alexander (1977/79). In 1987, Kent Beck and Ward Cunningham began experimenting with the idea of applying patterns to programming - specifically pattern languages - and presented their results at the OOPSLA conference that year.[1][2] In the following years, Beck, Cunningham and others followed up on this work.\n" +
                "\nDesign patterns gained popularity in computer science after the book Design Patterns: Elements of Reusable Object-Oriented Software was published in 1994 by the so-called \"Gang of Four\" (Gamma et al.), which is frequently abbreviated as \"GoF\". That same year, the first Pattern Languages of Programming Conference was held, and the following year the Portland Pattern Repository was set up for documentation of design patterns. The scope of the term remains a matter of dispute. Notable books in the design pattern genre include:\n" +
                "\n    Gamma, Erich; Helm, Richard; Johnson, Ralph; Vlissides, John (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.\n" +
                "    Brinch Hansen, Per (1995). Studies in Computational Science: Parallel Programming Paradigms. Prentice Hall. ISBN 0-13-439324-4.\n" +
                "    Buschmann, Frank; Meunier, Regine; Rohnert, Hans; Sommerlad, Peter (1996). Pattern-Oriented Software Architecture, Volume 1: A System of Patterns. John Wiley & Sons. ISBN 0-471-95869-7.\n" +
                "    Beck, Kent (1997). Smalltalk Best Practice Patterns. Prentice Hall. ISBN 978-0134769042.\n" +
                "    Schmidt, Douglas C.; Stal, Michael; Rohnert, Hans; Buschmann, Frank (2000). Pattern-Oriented Software Architecture, Volume 2: Patterns for Concurrent and Networked Objects. John Wiley & Sons. ISBN 0-471-60695-2.\n" +
                "    Fowler, Martin (2002). Patterns of Enterprise Application Architecture. Addison-Wesley. ISBN 978-0-321-12742-6.\n" +
                "    Hohpe, Gregor; Woolf, Bobby (2003). Enterprise Integration Patterns: Designing, Building, and Deploying Messaging Solutions. Addison-Wesley. ISBN 0-321-20068-3.\n" +
                "    Freeman, Eric T; Robson, Elisabeth; Bates, Bert; Sierra, Kathy (2004). Head First Design Patterns. O'Reilly Media. ISBN 0-596-00712-4.\n" +
                "\nAlthough design patterns have been applied practically for a long time, formalization of the concept of design patterns languished for several years.[3]\n" +
                "Practice\n" +
                "\nDesign patterns can speed up the development process by providing tested, proven development paradigms.[4] Effective software design requires considering issues that may not become visible until later in the implementation. Freshly written code can often have hidden subtle issues that take time to be detected, issues that sometimes can cause major problems down the road. Reusing design patterns helps to prevent such subtle issues[citation needed], and it also improves code readability for coders and architects who are familiar with the patterns.\n" +
                "\nIn order to achieve flexibility, design patterns usually introduce additional levels of indirection, which in some cases may complicate the resulting designs and hurt application performance.\n" +
                "\nBy definition, a pattern must be programmed anew into each application that uses it. Since some authors see this as a step backward from software reuse as provided by components, researchers have worked to turn patterns into components. Meyer and Arnout were able to provide full or partial componentization of two-thirds of the patterns they attempted.[5]\n" +
                "\nSoftware design techniques are difficult to apply to a broader range of problems.[citation needed] Design patterns provide general solutions, documented in a format that does not require specifics tied to a particular problem.";
        String pattern = "the ";


        Instant start = Instant.now();
        Naive.search(text, pattern);
        Instant end = Instant.now();
        System.out.println("Execution time(ns)= " + Duration.between(start, end).getNano());

        start = Instant.now();
        RabinKarp.search(text, pattern);
        end = Instant.now();
        System.out.println("Execution time(ns)= " + Duration.between(start, end).getNano());

        start = Instant.now();
        FiniteAutomata.search(text, pattern);
        end = Instant.now();
        System.out.println("Execution time(ns)= " + Duration.between(start, end).getNano());

        start = Instant.now();
        knuthMorrisPratt.search(text, pattern);
        end = Instant.now();
        System.out.println("Execution time(ns)= " + Duration.between(start, end).getNano());

    }
}
