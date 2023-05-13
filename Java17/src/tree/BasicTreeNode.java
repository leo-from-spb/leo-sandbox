package lb.sandbox.java17.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 */
public class BasicTreeNode<N extends BasicTreeNode<N>> implements TreeNode, TreeParentNode<N>, TreeChildNode<N> {

    /// STATE \\\

    private @Nullable N myParent;

    private @Nullable ArrayList<N> myChildren;


    /// CONSTRUCTORS \\\

    public BasicTreeNode() {
        this.myParent = null;
        this.myChildren = null;
    }

    public BasicTreeNode(@Nullable N parent) {
        this.myParent = parent;
        this.myChildren = null;
        if (parent != null) {
            //noinspection unchecked
            parent.registerChild((N)this);
        }
    }


    /// METHODS \\\

    @Override
    public @Nullable N getParent() {
        return myParent;
    }

    @Override
    public @NotNull Iterable<N> getChildren() {
        final Collection<N> children = myChildren;
        return children != null ? children : Collections.emptySet();
    }

    public int getChildrenCount() {
        final Collection<N> children = myChildren;
        return children != null ? children.size() : 0;
    }

    void registerChild(@NotNull N child) {
        ArrayList<N> children = myChildren;
        if (children == null) {
            children = new ArrayList<>();
            children.add(child);
            myChildren = children;
        }
        else {
            // TODO check for duplicates
            myChildren.add(child);
        }
    }

}
