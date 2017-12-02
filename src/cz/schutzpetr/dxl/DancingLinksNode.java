package cz.schutzpetr.dxl;

/**
 * Created by Petr Schutz on 28.11.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
class DancingLinksNode {
    DancingLinksNode up;
    DancingLinksNode right;
    DancingLinksNode down;
    DancingLinksNode left;
    DancingLinksColumn column;
    int row;

    DancingLinksNode(int row) {
        this.right = this;
        this.up = this;
        this.down = this;
        this.left = this;
        this.row = row;
    }

    DancingLinksNode(DancingLinksColumn column, int row) {
        this(row);
        this.column = column;
    }

    void addRight(DancingLinksNode node) {
        node.right = this.right;
        node.right.left = node;
        node.left = this;
        this.right = node;
    }

    void addDown(DancingLinksNode node) {
        node.down = this.down;
        node.down.up = node;
        node.up = this;
        this.down = node;
    }

    void unlinkLeftRight() {
        this.left.right = this.right;
        this.right.left = this.left;
    }

    void relinkLeftRight() {
        this.left.right = this.right.left = this;
    }

    void unlinkUpDown() {
        this.up.down = this.down;
        this.down.up = this.up;
    }

    void relinkUpDown() {
        this.up.down = this.down.up = this;
    }

}
