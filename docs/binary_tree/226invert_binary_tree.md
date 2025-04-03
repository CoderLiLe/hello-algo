# [226.翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)

给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

示例 1：
![](asserts/226/01.png)
```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

示例 2：
![](asserts/226/02.png)
```
输入：root = [2,1,3]
输出：[2,3,1]
```

示例 3：
```
输入：root = []
输出：[]
```

提示：
- 树中节点数目范围在 [0, 100] 内
- -100 <= Node.val <= 100

# 思路
**二叉树镜像定义**： 对于二叉树中任意节点 root ，设其左 / 右子节点分别为 left,right ；则在二叉树的镜像中的对应 root 节点，其左 / 右子节点分别为 right, left 。

![](asserts/226/03.png)

可以发现想要翻转它，其实就把每一个节点的左右孩子交换一下就可以了。

遍历的过程中去翻转每一个节点的左右孩子就可以达到整体翻转的效果。

**层序遍历也是可以的！只要把每一个节点的左右孩子翻转一下的遍历方式都是可以的！**

## 递归法
### 前序遍历
```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root:
        return None
    root.left, root.right = root.right, root.left
    self.invertTree(root.left)
    self.invertTree(root.right)
    return root
```
### 中序遍历
```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root:
        return None
    self.invertTree(root.left)
    root.left, root.right = root.right, root.left
    self.invertTree(root.left)
    return root
```
### 后序遍历
```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root:
        return root
        
    left = self.invertTree(root.left)
    right = self.invertTree(root.right)
    root.left, root.right = right, left
    return root
```

## 迭代法
### 深度优先遍历
#### 前序遍历
```python
def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    if not root: return
        
    stack = [root]
    while stack:
        node = stack.pop()
        node.left, node.right = node.right, node.left
        if node.left: stack.append(node.left)
        if node.right: stack.append(node.right)
    return root
```

#### 伪中序遍历

伪中序遍历（结果是对的，看起来像是中序遍历，实际上它是前序遍历，只不过把中间节点处理逻辑放到了中间

```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root:
        return None      
    stack = [root]
    while stack:
        node = stack.pop()
        if node.right:
            stack.append(node.right)
        node.left, node.right = node.right, node.left
        if node.right:
            stack.append(node.right)
    return root
```

#### 伪后序遍历
伪后序遍历（结果是对的，看起来像是后序遍历，实际上它是前序遍历，只不过把中间节点处理逻辑放到了最后

```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root:
        return None
    stack = [root]    
    while stack:
        node = stack.pop()
        if node.right:
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
        node.left, node.right = node.right, node.left               
     
    return root
```

### 广度优先遍历（层序遍历）
```python
def invertTree(self, root: TreeNode) -> TreeNode:
    if not root: 
        return None

    queue = collections.deque([root])    
    while queue:
        node = queue.popleft()
        node.left, node.right = node.right, node.left
        if node.left: queue.append(node.left)
        if node.right: queue.append(node.right)
    return root   
```