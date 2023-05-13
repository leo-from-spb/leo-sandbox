package lb.sandbox.kotlino.tree

import lb.sandbox.java17.tree.TreeChildNode
import lb.sandbox.java17.tree.TreeNode
import lb.sandbox.java17.tree.TreeParentNode
import java.util.Collections.emptySet

open class BasicTreeNode<N : BasicTreeNode<N>> : TreeNode, TreeParentNode<N>, TreeChildNode<N> {

    /// STATE \\\

    private var myParent: N?
    private var myChildren: ArrayList<N>?

    /// CONSTRUCTORS \\\

    constructor() {
        myParent = null
        myChildren = null
    }

    constructor(parent: N?) {
        myParent = parent
        myChildren = null
        @Suppress("unchecked_cast")
        parent?.registerChild(this as N)
    }

    /// METHODS \\\

    override fun getParent(): N? {
        return myParent
    }

    override fun getChildren(): Iterable<N> {
        val children: Collection<N>? = myChildren
        return children ?: emptySet()
    }

    val childrenCount: Int
        get() {
            val children: Collection<N>? = myChildren
            return children?.size ?: 0
        }

    fun registerChild(child: N) {
        var children = myChildren
        if (children == null) {
            children = ArrayList()
            children.add(child)
            myChildren = children
        }
        else {
            // TODO check for duplicates
            myChildren!!.add(child)
        }
    }
}
