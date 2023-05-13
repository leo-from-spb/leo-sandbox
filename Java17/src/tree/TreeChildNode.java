package lb.sandbox.java17.tree;

/**
 *
 */
public interface TreeChildNode<P extends TreeParentNode<?>> extends TreeNode {

    P getParent();

}
