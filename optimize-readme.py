#!/usr/bin/env python3
"""
hello-algo 仓库 README.md 优化脚本
优化 CoderLiLe/hello-algo 仓库的 README.md 文件
"""

import os
import re
import json
from datetime import datetime
from pathlib import Path

def read_readme():
    """读取 README.md 文件"""
    with open('README.md', 'r', encoding='utf-8') as f:
        return f.read()

def write_readme(content):
    """写入 README.md 文件"""
    with open('README.md', 'w', encoding='utf-8') as f:
        f.write(content)

def analyze_repository_structure():
    """分析仓库结构，获取算法分类和文件统计"""
    structure = {
        'categories': {},
        'file_counts': {},
        'total_files': 0,
        'languages': set()
    }
    
    # 分析 docs 目录
    docs_path = Path('docs')
    if docs_path.exists():
        for item in docs_path.rglob('*.md'):
            rel_path = str(item.relative_to(docs_path))
            category = rel_path.split('/')[0] if '/' in rel_path else '其他'
            structure['categories'].setdefault(category, 0)
            structure['categories'][category] += 1
            structure['total_files'] += 1
    
    # 分析 codes 目录
    codes_path = Path('codes')
    if codes_path.exists():
        for item in codes_path.rglob('*'):
            if item.is_file():
                suffix = item.suffix.lower()
                if suffix in ['.java', '.py', '.js', '.cpp', '.c']:
                    structure['languages'].add(suffix[1:])
                    structure['file_counts'].setdefault(suffix[1:], 0)
                    structure['file_counts'][suffix[1:]] += 1
                    structure['total_files'] += 1
    
    return structure

def create_enhanced_readme(current_content, structure):
    """创建增强版的 README.md"""
    
    # 获取当前时间
    current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    # 创建新的 README 内容
    new_content = f"""# 🚀 Hello Algorithm - 算法学习与实践仓库

![GitHub stars](https://img.shields.io/github/stars/CoderLiLe/hello-algo?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/CoderLiLe/hello-algo?style=for-the-badge)
![GitHub issues](https://img.shields.io/github/issues/CoderLiLe/hello-algo?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/CoderLiLe/hello-algo?style=for-the-badge)
![GitHub repo size](https://img.shields.io/github/repo-size/CoderLiLe/hello-algo?style=for-the-badge)

## 📖 项目简介

这是一个专注于算法学习与实践的仓库，包含多种算法和数据结构的实现、LeetCode 题解、算法模板等。

## 📊 仓库统计

| 项目 | 数量 |
|------|------|
| 文档文件 | {structure['categories'].get('total', sum(structure['categories'].values()))} |
| 代码文件 | {sum(structure['file_counts'].values())} |
| 总文件数 | {structure['total_files']} |
| 支持语言 | {', '.join(sorted(structure['languages'])) if structure['languages'] else 'Java, Python'} |

## 🗂️ 目录结构

### 📚 文档分类
{create_category_table(structure['categories'])}

### 💻 代码实现
{create_language_table(structure['file_counts'])}

## 🎯 主要内容

### 1. 算法分类
- **基础算法**：排序、查找、递归等
- **数据结构**：数组、链表、栈、队列、树、图等
- **高级算法**：动态规划、回溯、贪心、分治等
- **LeetCode 题解**：热门题目解析与实现

### 2. 语言实现
- **Java**：面向对象实现，注重代码规范
- **Python**：简洁实现，注重算法思想
- **其他语言**：根据需求逐步添加

### 3. 学习资源
- 算法模板与套路总结
- 常见问题解决方案
- 性能分析与优化技巧

## 🚀 快速开始

### 环境要求
- Java 8+ 或 Python 3.6+
- Git

### 使用方式
```bash
# 克隆仓库
git clone https://github.com/CoderLiLe/hello-algo.git

# 查看特定算法
cd hello-algo/docs/动态规划
```

### 运行示例
```java
// Java 示例
javac Main.java
java Main
```

```python
# Python 示例
python3 sort/bubble_sort.py
```

## 📈 学习路线

1. **基础阶段**：掌握基本数据结构和算法
2. **进阶阶段**：学习高级算法和优化技巧
3. **实战阶段**：解决 LeetCode 等平台题目
4. **总结阶段**：整理模板，形成自己的知识体系

## 🤝 贡献指南

欢迎贡献代码和文档！请遵循以下步骤：

1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📝 更新日志

### 最新更新
- 添加 KMP 算法实现
- 更新字符串匹配题解
- 优化文档结构

### 计划功能
- [ ] 添加更多算法实现
- [ ] 增加算法可视化
- [ ] 添加测试用例
- [ ] 优化代码性能

## 🔗 相关资源

- [LeetCode 官网](https://leetcode.com/)
- [算法可视化网站](https://visualgo.net/)
- [算法导论](https://mitpress.mit.edu/books/introduction-algorithms)

## 📞 联系与支持

如有问题或建议，请：
1. 提交 [Issue](https://github.com/CoderLiLe/hello-algo/issues)
2. 通过邮件联系

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

---

**最后更新**: {current_time}

⭐ **如果这个项目对你有帮助，请给个 Star 支持一下！** ⭐

---

*持续更新中，欢迎关注和贡献！*
"""
    
    return new_content

def create_category_table(categories):
    """创建分类表格"""
    if not categories:
        return "暂无分类数据"
    
    table = "| 分类 | 文档数量 |\n|------|----------|\n"
    for category, count in sorted(categories.items()):
        table += f"| {category} | {count} |\n"
    return table

def create_language_table(file_counts):
    """创建语言表格"""
    if not file_counts:
        return "暂无代码文件"
    
    table = "| 语言 | 文件数量 |\n|------|----------|\n"
    for lang, count in sorted(file_counts.items()):
        table += f"| {lang} | {count} |\n"
    return table

def add_badges_to_readme(content):
    """添加徽章到 README"""
    badges = """
<div align="center">

![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Markdown](https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white)
![LeetCode](https://img.shields.io/badge/LeetCode-000000?style=for-the-badge&logo=LeetCode&logoColor=#d16c06)

</div>
"""
    
    # 在标题后添加徽章
    lines = content.split('\n')
    for i, line in enumerate(lines):
        if line.startswith('# ') and i < len(lines) - 1:
            lines.insert(i + 1, badges)
            break
    
    return '\n'.join(lines)

def add_toc(content):
    """添加目录"""
    toc_lines = []
    headings = re.findall(r'^(#{2,3})\s+(.+)$', content, re.MULTILINE)
    
    if len(headings) > 3:
        toc = "## 📑 目录\n\n"
        for level, title in headings:
            if level == "##":
                anchor = re.sub(r'[^\w\s-]', '', title.lower())
                anchor = re.sub(r'[-\s]+', '-', anchor).strip('-')
                toc += f"- [{title}](#{anchor})\n"
        
        # 在简介后插入目录
        lines = content.split('\n')
        for i, line in enumerate(lines):
            if "## 📖 项目简介" in line:
                # 在简介部分结束后插入目录
                for j in range(i + 1, len(lines)):
                    if lines[j].startswith('## '):
                        lines.insert(j, '\n' + toc + '\n')
                        return '\n'.join(lines)
    
    return content

def main():
    print("🚀 开始优化 hello-algo 仓库的 README.md...")
    
    # 分析仓库结构
    print("📊 分析仓库结构...")
    structure = analyze_repository_structure()
    
    # 读取当前 README
    print("📖 读取当前 README...")
    current_content = read_readme()
    
    # 创建增强版 README
    print("🛠️ 创建增强版 README...")
    new_content = create_enhanced_readme(current_content, structure)
    
    # 添加徽章
    print("🎖️ 添加徽章...")
    new_content = add_badges_to_readme(new_content)
    
    # 添加目录
    print("📑 添加目录...")
    new_content = add_toc(new_content)
    
    # 写入新内容
    print("💾 写入优化后的 README...")
    write_readme(new_content)
    
    print(f"✅ README.md 优化完成！")
    print(f"📊 仓库统计：")
    print(f"  - 文档分类：{len(structure['categories'])} 个")
    print(f"  - 支持语言：{', '.join(sorted(structure['languages']))}")
    print(f"  - 总文件数：{structure['total_files']} 个")

if __name__ == "__main__":
    main()