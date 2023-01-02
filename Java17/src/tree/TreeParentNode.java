package lb.sandbox.java11.tree;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public interface TreeParentNode<C extends TreeChildNode<?>> extends TreeNode {

    @NotNull Iterable<@NotNull C> getChildren();

}
