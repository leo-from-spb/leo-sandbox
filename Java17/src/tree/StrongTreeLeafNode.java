package lb.sandbox.java17.tree;

/**
 *
 */
public class StrongTreeLeafNode<P extends StrongTreeNode & TreeParentNode<?>>
        extends StrongTreeNode
        implements TreeChildNode<P>
{

    final P parent;

    public StrongTreeLeafNode(P parent) {
        this.parent = parent;
    }

    @Override
    public P getParent() {
        return parent;
    }
    
}
