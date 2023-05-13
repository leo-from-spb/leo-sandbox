package lb.sandbox.java17.tree;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public interface TreeParentNode<C extends TreeChildNode<?>> extends TreeNode {

    @NotNull Iterable<@NotNull C> getChildren();

}
