package lb.sandbox.java17.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 *
 */
public class StrongTreeMediumNode<P extends StrongTreeNode & TreeParentNode<?>, C extends StrongTreeNode & TreeChildNode<?>>
        extends StrongTreeNode
        implements TreeParentNode<C>, TreeChildNode<P>
{

    final P parent;

    public StrongTreeMediumNode(P parent) {
        this.parent = parent;
    }

    @Override
    public P getParent() {
        return parent;
    }

    @Override
    public @NotNull Iterable<C> getChildren() {
        return Collections.emptySet();
    }

}
