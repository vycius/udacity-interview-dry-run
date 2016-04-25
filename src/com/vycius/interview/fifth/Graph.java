package com.vycius.interview.fifth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Graph {

    public static <T> boolean findPath(GraphNode<T> start, GraphNode<T> end) {
        Stack<GraphNode<T>> stack = new Stack<>();
        Set<GraphNode> visited = new HashSet<>();

        stack.add(start);

        while (!stack.empty()) {
            GraphNode<T> node = stack.pop();

            if (node.equals(end))
                return true;

            if (visited.contains(node))
                continue;

            visited.add(node);

            stack.addAll(node.getChildren().stream()
                    .filter(child -> !visited.contains(child))
                    .collect(Collectors.toList())
            );
        }

        return false;
    }

    public static class GraphNode<T> {

        private T value;
        private List<GraphNode<T>> children;

        public GraphNode(T value) {
            this.value = value;
            children = new ArrayList<>();
        }

        public T getValue() {
            return value;
        }

        public List<GraphNode<T>> getChildren() {
            return children;
        }

        public void addChild(GraphNode<T> child) {
            children.add(child);
        }
    }

    @RunWith(JUnit4.class)
    public static class GraphTest {

        GraphNode<Integer> firstNode;
        GraphNode<Integer> secondNode;
        GraphNode<Integer> thirdNode;
        GraphNode<Integer> forthNode;
        GraphNode<Integer> fifthNode;


        @Test
        public void positiveFindPathTestFirst() {
            firstNode.addChild(secondNode);
            secondNode.addChild(firstNode);

            secondNode.addChild(thirdNode);
            secondNode.addChild(forthNode);

            boolean result = findPath(firstNode, forthNode);

            assertEquals(true, result);
        }

        @Test
        public void positiveFindPathTestSecond() {
            fifthNode.addChild(forthNode);
            forthNode.addChild(thirdNode);
            thirdNode.addChild(secondNode);
            secondNode.addChild(firstNode);

            firstNode.addChild(secondNode);
            secondNode.addChild(thirdNode);
            thirdNode.addChild(forthNode);
            forthNode.addChild(fifthNode);

            boolean result = findPath(firstNode, fifthNode);

            assertEquals(true, result);
        }

        @Test
        public void negativeFindPathTest() {
            forthNode.addChild(thirdNode);
            thirdNode.addChild(secondNode);
            secondNode.addChild(firstNode);

            firstNode.addChild(secondNode);
            secondNode.addChild(thirdNode);
            thirdNode.addChild(forthNode);

            boolean result = findPath(secondNode, fifthNode);

            assertEquals(false, result);
        }

        @Before
        public void setUp() {
            firstNode = new GraphNode<>(1);
            secondNode = new GraphNode<>(2);
            thirdNode = new GraphNode<>(3);
            forthNode = new GraphNode<>(4);
            fifthNode = new GraphNode<>(5);
        }
    }
}
