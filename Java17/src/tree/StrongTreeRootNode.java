package lb.sandbox.java11.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 *
 */
public class StrongTreeRootNode<C extends StrongTreeNode & TreeChildNode<?>> extends StrongTreeNode implements TreeParentNode<C> {

    @Override
    public @NotNull Iterable<C> getChildren() {
        return Collections.emptySet();
    }

}
