package lb.sandbox.java11.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
public interface TreeChildNode<P extends TreeParentNode<?>> extends TreeNode {

    P getParent();

}
