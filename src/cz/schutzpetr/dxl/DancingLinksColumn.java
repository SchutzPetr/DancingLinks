package cz.schutzpetr.dxl;

/**
 * Created by Petr Schutz on 28.11.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
class DancingLinksColumn extends DancingLinksNode {
    int size;
    String name;
    private DancingLinks dancingLinks;

    DancingLinksColumn(DancingLinks dancingLinks, String name) {
        this.size = 0;
        this.dancingLinks = dancingLinks;
        this.name = name;
        this.column = this;
    }

    void cover() {
        this.unlinkLeftRight();

        DancingLinksNode node = this.down;
        while (node != this) {
            DancingLinksNode node1 = node.right;

            while (node1 != node) {
                node1.column.size--;
                node1.unlinkUpDown();

                node1 = node1.right;
            }

            node = node.down;
        }

        this.dancingLinks.root.size--;
    }

    void uncover() {
        DancingLinksNode node = this.up;
        while (node != this) {
            DancingLinksNode node1 = node.left;

            while (node1 != node) {
                node1.column.size++;
                node1.relinkUpDown();

                node1 = node1.left;
            }
            node = node.up;
        }

        this.relinkLeftRight();
        this.dancingLinks.root.size++;
    }
}
