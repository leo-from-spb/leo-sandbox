package lb.sandbox.java17.tree;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 *
 */
public class BasicTreeNodeTest {

    static class TestNode extends BasicTreeNode<TestNode> {
        public TestNode() { super(); }
        public TestNode(@Nullable TestNode parent) { super(parent); }
    }

    @Test
    void test1() {
        TestNode p = new TestNode();
        TestNode c = new TestNode(p);

        assertEquals(1, p.getChildrenCount());
        assertSame(p, c.getParent());
    }

}
