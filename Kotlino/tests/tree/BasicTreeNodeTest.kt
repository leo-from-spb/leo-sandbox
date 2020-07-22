package lb.sandbox.kotlino.tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


/**
 *
 */
class BasicTreeNodeTest {

    internal class TestNode : BasicTreeNode<TestNode> {
        constructor() : super()
        constructor(parent: TestNode?) : super(parent)
    }

    @Test
    fun test1() {
        val p = TestNode()
        val c = TestNode(p)
        assertEquals(1, p.childrenCount)
        assertSame(p, c.parent)
    }


}