package cz.schutzpetr.dxl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petr Schutz on 28.11.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class DancingLinks {

    DancingLinksColumn root;
    private int solution = 0;
    private final List<DancingLinksNode> result;

    DancingLinks(int[][] matrix) {
        this.result = new ArrayList<>();
        setup(matrix);
    }

    /**
     * Run dancing links algorithm
     */
    void run() {
        search(0);
    }

    /**
     * Matrix conversion algorithm to structure dancing links
     *
     * @param matrix matrix
     */
    private void setup(int[][] matrix) {
        DancingLinksColumn header = new DancingLinksColumn(this, "root");

        List<DancingLinksColumn> columnNodes = new ArrayList<>();

        for (int i = 0; i < matrix[0].length; i++) {
            DancingLinksColumn n = new DancingLinksColumn(this, String.valueOf(i));
            columnNodes.add(n);
            header.addRight(n);
            header = n;
        }
        header = header.right.column;

        for (int[] row : matrix) {
            DancingLinksNode prev = null;
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[j] == 1) {
                    DancingLinksColumn column = columnNodes.get(j);
                    DancingLinksNode newNode = new DancingLinksNode(column);
                    if (prev == null) {
                        prev = newNode;
                    }
                    column.up.addDown(newNode);
                    prev.addRight(newNode);
                    prev = newNode;
                    column.size++;
                }
            }
        }

        header.size = matrix[0].length;

        this.root = header;
    }

    /**
     * DLX algorithm by Donald Ervin Knuth
     *
     * @param k recursion level
     */
    private void search(int k) {
        if (this.root.right == this.root) {
            for (DancingLinksNode node : this.result) {
                StringBuilder result = new StringBuilder();
                result.append(node.column.name).append(" ");
                DancingLinksNode tmp = node.right;
                while (tmp != node) {
                    result.append(tmp.column.name).append(" ");
                    tmp = tmp.right;
                }
                System.out.println(result);
            }

            this.solution++;
            System.out.println(this.solution);

            return;
        }

        DancingLinksColumn column = selectDXLColumnSHeuristic();
        column.cover();

        for (DancingLinksNode i = column.down; i != column; i = i.down) {
            this.result.add(i);

            for (DancingLinksNode j = i.right; j != i; j = j.right) {
                j.column.cover();
            }

            search(k + 1);
            this.result.remove(result.size() - 1);
            column = i.column;

            for (DancingLinksNode j = i.left; j != i; j = j.left) {
                j.column.uncover();
            }
        }
        column.uncover();
    }

    /**
     * S heuristic designed by Donald Ervin Knuth
     *
     * @return selected column using S heuristic
     */
    private DancingLinksColumn selectDXLColumnSHeuristic() {
        int min = Integer.MAX_VALUE;
        DancingLinksColumn result = null;
        DancingLinksColumn column = (DancingLinksColumn) root.right;
        while (column != root) {
            if (column.size < min) {
                min = column.size;
                result = column;
            }
            column = (DancingLinksColumn) column.right;
        }
        return result;
    }
}
